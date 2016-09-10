package http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by linlin on 8/17/16.
 */
public class HttpJsonThread extends Thread {
    private Handler handler;
    private String url;
    private LayoutInflater inflater;
    private JsonParser parser;//json数据解析
    private RefreshUIListener refreshUIListener;//刷新UI监听
    private ProgressDialog dialog;

//    ProgressDialog dialog;

    public JsonParser getParser() {
        return parser;
    }

    public void setParser(JsonParser parser) {
        this.parser = parser;
    }

    public RefreshUIListener getRefreshUIListener() {
        return refreshUIListener;
    }

    public void setRefreshUIListener(RefreshUIListener refreshUIListener) {
        this.refreshUIListener = refreshUIListener;
    }

    public interface JsonParser {
        public Object parseJson(String jsonString);

    }

    public interface RefreshUIListener {
        public void refreshUI(Object data);
    }

    public HttpJsonThread(String url, Handler handler, LayoutInflater inflater) {
        this.handler = handler;
        this.url = url;
        this.inflater = inflater;
    }

    @Override
    public synchronized void start() {
        dialog = new ProgressDialog(inflater.getContext(), ProgressDialog.STYLE_SPINNER);
        dialog.show();
        super.start();
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

                ((Activity)inflater.getContext()).setProgressBarIndeterminateVisibility(false);
                if (parser == null) return;

                final Object result = parser.parseJson(buffer.toString());

                    handler.post(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();

                        if (result == null) Toast.makeText(inflater.getContext(), "数据返回有误", Toast.LENGTH_SHORT).show();

                        if (refreshUIListener != null) refreshUIListener.refreshUI(result);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
