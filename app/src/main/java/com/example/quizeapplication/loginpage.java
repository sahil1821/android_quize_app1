package com.example.quizeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginpage extends AppCompatActivity {

    EditText etusername,etpassword,etpassrenter;
    Button btnresister,btnsignin;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        etusername=findViewById(R.id.username);
        etpassword=findViewById(R.id.password);
        etpassrenter=findViewById(R.id.repassword);
        btnresister=findViewById(R.id.buttonresister);
        btnsignin=findViewById(R.id.buttonlogin);

        myDB =new DBHelper(this);

        btnresister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = etusername.getText().toString().trim();
                String pass = etpassword.getText().toString().trim();
                String repass = etpassrenter.getText().toString().trim();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                {
                    Toast.makeText(loginpage.this, "Fill All The Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean usercheckResult = myDB.checkusername(user);

                        if(usercheckResult == false)
                        {
                            Boolean regResult = myDB.insertData(user,pass);

                            if(regResult == true)
                            {
                                Toast.makeText(loginpage.this, "Resistration Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(loginpage.this, "Resistration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(loginpage.this, "User Already Exists \n Please Sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(loginpage.this, "Password Not Matching.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}