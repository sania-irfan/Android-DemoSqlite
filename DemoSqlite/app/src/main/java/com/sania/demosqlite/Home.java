package com.sania.demosqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button add,update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//
//        add=(Button)findViewById(R.id.add);
//        update=(Button)findViewById(R.id.update);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addentry=new Intent(Home.this,AddEntry.class);
                startActivity(addentry);
            }
        });

//        if (!SQLHelper.checkStudent()){
//            SQLHelper.setStudent();
//            Log.e("Log","This is first launch");
//        }
//        else {
//            Log.e("Log","This is NOT first launch");
//        }
    }
}
