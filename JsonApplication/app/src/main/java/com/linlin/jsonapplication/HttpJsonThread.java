package com.linlin.jsonapplication;

import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import model.Person;
import model.Result;
import model.School;

/**
 * Created by linlin on 8/17/16.
 */
public class HttpJsonThread extends Thread{
    private Handler handler;
    private String url;
    private ListView listView;
    private LayoutInflater inflater;


    public HttpJsonThread(String url, Handler handler, LayoutInflater inflater, ListView listView) {
        this.handler = handler;
        this.url = url;
        this.inflater = inflater;
        this.listView = listView;
    }

    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            try {
                URLConnection connection = httpUrl.openConnection();
                connection.setConnectTimeout(5000);
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String str;
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }

                final Result result = parseJson(buffer.toString());

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!result.getResultCode().equals("1")) {
                            Toast.makeText(inflater.getContext(), "数据返回有误", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        ListViewAdapter adapter = new ListViewAdapter(result.getPersonsData(), inflater);
                        listView.setAdapter(adapter);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Result parseJson(String jsonString) {
        try {
            Result result = new Result();

            JSONObject jsonObject = new JSONObject(jsonString);
            String resultCode = jsonObject.getString("resultCode");
            result.setResultCode(resultCode);

            JSONArray personData = jsonObject.getJSONArray("personsData");
            ArrayList<Person> ps = new ArrayList<>();
            for (int i = 0; i < personData.length(); i++) {
                JSONObject obj = personData.getJSONObject(i);
                Person p = new Person();
                p.setAge(obj.getInt("age"));
                p.setAvatarUrl(obj.getString("avatarUrl"));
                p.setName(obj.getString("name"));

                JSONArray schoolsData = obj.getJSONArray("schools");
                ArrayList<School> ss = new ArrayList<>();
                for (int j = 0; j < schoolsData.length(); j++) {
                    JSONObject sObj = schoolsData.getJSONObject(j);
                    School s = new School();
                    s.setName(sObj.getString("name"));
                    s.setAddress(sObj.getString("address"));
                    ss.add(s);
                }
                p.setSchools(ss);
                ps.add(p);
            }
            result.setPersonsData(ps);

            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
