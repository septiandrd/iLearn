package com.example.septiandrd.ilearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txEmail, txPassword;
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txEmail = (EditText)findViewById(R.id.edt_login_email);
        txPassword = (EditText)findViewById(R.id.edt_login_password);
        btnLogin = (Button)findViewById(R.id.btn_login);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = db.searchPassword(txEmail.getText().toString());
                if (password.equals(txPassword.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("userEmail",txEmail.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Username/password incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    
    public void ForgotClicked(View v) {
        Toast.makeText(getApplicationContext(), "Sorry, this feature is not available yet.", Toast.LENGTH_SHORT).show();
    }
    
    public void SocialClicked(View v) {
        Toast.makeText(getApplicationContext(), "Sorry, this feature is not available yet.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
