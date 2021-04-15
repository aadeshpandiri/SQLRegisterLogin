package com.example.sqlloginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etuser,etpass,etcpass;
    Button signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etuser = findViewById(R.id.username);
        etpass = findViewById(R.id.password);
        etcpass =  findViewById(R.id.cpassword);
        signup = findViewById(R.id.signup);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = etuser.getText().toString();
                String pass = etpass.getText().toString();
                String repass = etcpass.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please enter data in all fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser ==  false)
                        {
                            Boolean insert = DB.insertData(user,pass);
                            if(insert==true)
                            {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this, "Registered Failed ! Try Again !", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "User Already Exists ! Please Login",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Passwords doesnt match !", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}