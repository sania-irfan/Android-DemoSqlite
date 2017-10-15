package com.sania.demosqlite;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    SQLiteDatabase db=LocalDatabase.db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText name=(EditText)findViewById(R.id.loginname);
        final EditText pass=(EditText)findViewById(R.id.loginpass);

        Button loginbtn=(Button)findViewById(R.id.loginbtn);
        Button regbtn=(Button)findViewById(R.id.registerbtn);
        TextView forgot=(TextView)findViewById(R.id.forgot);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user=name.getText().toString();
                String password=pass.getText().toString();

                Cursor cursor=SQLHelper.getAllData();

                if(cursor.getCount()>0)
                {
                    while(cursor.moveToNext()) {
                        String dbuser = cursor.getString(cursor.getColumnIndex(SQLHelper.COL_1));
                        String dbpass = cursor.getString(cursor.getColumnIndex(SQLHelper.COL_3));

                        if(dbuser.equals(user)&&dbpass.equals(password)){

                            //Snackbar.make(LoginActivity.this,"Login Successful",Snackbar.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),dbuser+" "+dbpass,Toast.LENGTH_SHORT).show();
                            name.getText().clear();
                            pass.getText().clear();

                        }
                        else{
                            Toast.makeText(getApplication(),"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                            pass.getText().clear();
                        }
                    }
                }
                cursor.close();

            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoreg=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(gotoreg);
                finish();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
    }
    public void update(){

        Log.d("FORGOT","In update method");
        Dialog forgot_dialog=new Dialog(getApplication());

        forgot_dialog.setContentView(R.layout.forgot_dialog);
        forgot_dialog.show();

        final EditText forgotname=(EditText)forgot_dialog.findViewById(R.id.forgotname);
        final EditText forgotPass=(EditText)forgot_dialog.findViewById(R.id.forgotpass);
        Button editbtn=(Button)forgot_dialog.findViewById(R.id.edit);
        Button delete=(Button)forgot_dialog.findViewById(R.id.delete);

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=forgotname.getText().toString();
                String pass=forgotPass.getText().toString();

                ContentValues newValues=new ContentValues();
                newValues.put(SQLHelper.COL_1,name);
                newValues.put(SQLHelper.COL_3,pass);
                db.update("Contact",newValues,"NAME=?",new String[]{name});
                forgotname.getText().clear();
                forgotPass.getText().clear();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name=forgotname.getText().toString();

                final AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("Are You Sure You Want To Delete The data?");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.delete("Contact","NAME=?",new String[]{name});
                        forgotname.getText().clear();
                        forgotPass.getText().clear();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
}
