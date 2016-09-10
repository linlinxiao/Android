package com.linlin.file;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private TextView textView;
    private Button btn;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView2);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
        Log.i(TAG, "onClick: hhhh");
    }

    public void writeToFile() {
        try {
            FileOutputStream fileOutputStream = openFileOutput("text.txt"/*file.getAbsolutePath()*/, MODE_PRIVATE);
            try {
                fileOutputStream.write(editText.getText().toString().getBytes());
                fileOutputStream.close();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        try {
            FileInputStream fileInputStream = openFileInput("text.txt");
            byte[] b = new byte[1024];
            try {
                while (fileInputStream.read(b) != -1) {

                }

                String text = new String(b);
                textView.setText(text);
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (editText.getText().toString().length() == 0) {
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.i(TAG, "onClick: hhhh");
        writeToFile();
        readFromFile();
    }
}
