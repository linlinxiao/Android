package http;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import model.Category;

/**
 * Created by linlin on 8/18/16.
 */
public class CategoryJsonParser implements HttpJsonThread.JsonParser {

    private static final String TAG = "CategoryJsonParser";

    @Override
    public Object parseJson(String jsonString) {
        ArrayList<Category> cs = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray personData = jsonObject.getJSONArray("result");
            for (int i = 0; i < personData.length(); i++) {
                JSONObject obj = personData.getJSONObject(i);
                Category c = new Category();
                c.setCid(obj.getInt("cid"));
                c.setDescription(obj.getString("description"));
                c.setName(obj.getString("name"));
                c.setHasChildren(obj.getString("hasChildren"));
                c.setIcon(obj.getString("icon"));

                JSONArray subsData = obj.getJSONArray("subs");
                ArrayList<Category> ss = new ArrayList<>();
                for (int j = 0; j < subsData.length(); j++) {
                    JSONObject sObj = subsData.getJSONObject(j);
                    Category s = new Category();
                    s.setCid(sObj.getInt("cid"));
                    s.setName(sObj.getString("name"));
                    s.setHasChildren(sObj.getString("hasChildren"));
                    s.setIcon(sObj.getString("icon"));

                    if (s.getHasChildren().equals("true")) {
                    JSONArray sData = sObj.getJSONArray("subs");
                    ArrayList<Category> subs = new ArrayList<>();
                    for (int k = 0; k < sData.length(); k++) {
                        JSONObject o = sData.getJSONObject(k);
                        Category category = new Category();
                        category.setCid(o.getInt("cid"));
                        category.setName(o.getString("name"));
                        category.setIcon(o.getString("icon"));
                        subs.add(category);
                    }
                    s.setSubs(subs);
                }
                    ss.add(s);
                }

                c.setSubs(ss);
                cs.add(c);
            }

            Log.i(TAG, "parseJson:\n "+ cs.toString());
            return cs;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cs;
    }
}
