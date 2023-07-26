package com.example.quizeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText userlogin,passlogin;
    Button btnlogin;

    DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        userlogin=findViewById(R.id.usernamelogin);
        passlogin=findViewById(R.id.passwordlogin);
        btnlogin=findViewById(R.id.btnlogin);

        myDB=new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user=userlogin.getText().toString().trim();
                String pass=passlogin.getText().toString().trim();

                if(user.equals("") || pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Please enter credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean result= myDB.checkusernamepassword(user,pass);

                    if(result==true)
                    {
                        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else
                    {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}