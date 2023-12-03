package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.UserDbHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private RadioGroup type_RG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //初始化控件
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        type_RG = findViewById(R.id.lg_type_RG);
        //返回到登录,不是跳转，是销毁
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //注册事件
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //接受数据
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                int selectedId = 0;
                if (type_RG.getCheckedRadioButtonId() == R.id.rb_admin) {
                    selectedId = 1;
                }
                //如果为空，不能注册
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "用户名或密码不能为空",
                        Toast.LENGTH_SHORT).show();
                } else {
                    int row = UserDbHelper.getInstance(RegisterActivity.this)
                        .register(username, password, selectedId);//先默认是管理员
                    if (row > 0) {
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT)
                            .show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "用户名已存在，请登录",
                            Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}