package com.sania.demosqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText username=(EditText)findViewById(R.id.regname);
        final EditText address=(EditText)findViewById(R.id.regaddress);
        final EditText password=(EditText)findViewById(R.id.regpass);
        Button register=(Button)findViewById(R.id.regbtn);
        Button login=(Button)findViewById(R.id.logbtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                String addr=address.getText().toString();
                String pass=password.getText().toString();

                boolean isInserted=SQLHelper.insertData(name,addr,pass);
                if(isInserted){
                    Toast.makeText(RegisterActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                    username.getText().clear();
                    address.getText().clear();
                    password.getText().clear();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLogin=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(gotoLogin);
                finish();
            }
        });

    }
}
