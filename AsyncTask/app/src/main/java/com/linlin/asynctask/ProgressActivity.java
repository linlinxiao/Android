package com.linlin.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        myTask = new MyTask();
        myTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myTask != null && myTask.getStatus() == AsyncTask.Status.RUNNING) {
            //调用cancel方法只是改变了当前异步任务的状态，而并没有终止当前线程，java不能强制停止线程
            myTask.cancel(true);
        }
    }

    private class MyTask extends AsyncTask<Void,Integer,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            if (isCancelled())return;
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < 100; i++) {

                if (isCancelled())break;
                try {
                    Thread.sleep(300);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
