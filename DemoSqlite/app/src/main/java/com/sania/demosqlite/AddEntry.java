package com.sania.demosqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEntry extends AppCompatActivity {

    EditText name,Class,rollno;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_entry);
//
//        name=(EditText)findViewById(R.id.name);
//        Class=(EditText)findViewById(R.id.Class);
//        rollno=(EditText)findViewById(R.id.rollno);
//        save=(Button) findViewById(R.id.save);
        addData();
    }

    public void addData(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInserted= SQLHelper.insertData(name.getText().toString(),Class.getText().toString(),rollno.getText().toString());
            if(isInserted==true)
                Toast.makeText(AddEntry.this,"Data inserted",Toast.LENGTH_SHORT).show();
                else
                Toast.makeText(AddEntry.this,"Data not inserted",Toast.LENGTH_SHORT).show();

            }
        });
    }

}
