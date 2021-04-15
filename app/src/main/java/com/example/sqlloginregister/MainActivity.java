package com.example.sqlloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etuser,etpass;
    Button login;
    Button register;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etuser = findViewById(R.id.username);
        etpass = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        DB = new DBHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user =  etuser.getText().toString();
                String pass= etpass.getText().toString();
                if(user.equals("") || pass.equals("") )
                {
                    Toast.makeText(MainActivity.this, "Please enter data in all fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                   Boolean checkuserpass  =  DB.checkusernamepassword(user,pass);
                   if(checkuserpass == true)
                   {
                       Toast.makeText(MainActivity.this, "user loggined in Successfully", Toast.LENGTH_LONG).show();
                       Intent i2 = new Intent(getApplicationContext(),RegisterActivity.class);
                       startActivity(i2);
                   }
                   else
                   {
                       Toast.makeText(MainActivity.this, "Invalid Credentials !", Toast.LENGTH_LONG).show();
                   }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 =  new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i3);
            }
        });


    }
}