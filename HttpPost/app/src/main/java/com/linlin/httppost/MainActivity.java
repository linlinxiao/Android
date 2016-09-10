package com.linlin.httppost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        EditText nameEditText = (EditText) findViewById(R.id.nameEditView);
        new HttpPostThread(nameEditText.getText().toString(), "http://192.168.1.111:8080/Struts2_SympleDataValidation/addUser", "post");
    }
}
