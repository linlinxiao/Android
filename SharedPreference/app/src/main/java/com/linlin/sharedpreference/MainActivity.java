package com.linlin.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText pwET;
    private CheckBox checkBox;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        nameET = (EditText) findViewById(R.id.nameET);
        pwET = (EditText) findViewById(R.id.pwET);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //属性列表
        sharedPreferences = getSharedPreferences("myPrefereces", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String name = sharedPreferences.getString("name", null);
        checkBox.setChecked(false);
        if (name != null) {
            nameET.setText(name);
            pwET.setText(sharedPreferences.getString("password", null));
            checkBox.setChecked(true);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBtn:
                if (nameET.getText().toString().equals("admin") && pwET.getText().toString().equals("123456")) {
                    if (checkBox.isChecked()) {
                        editor.putString("name", nameET.getText().toString());
                        editor.putString("password", pwET.getText().toString());
                    } else {
                        editor.remove("name");
                        editor.remove("password");
                    }

                    editor.commit();
                    Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.cancelBtn:
                break;
        }
    }
}
