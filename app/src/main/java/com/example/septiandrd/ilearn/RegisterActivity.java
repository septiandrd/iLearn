package com.example.septiandrd.ilearn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText txNama1,txNama2,txPhone,txEmail,txPass1,txPass2;
    CheckBox cbAgree;
    String name;
    DatabaseHelper database = new DatabaseHelper(this);


    public static boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        btnRegister = (Button)findViewById(R.id.btn_register);
        txNama1 = (EditText)findViewById(R.id.edt_reg_firstname);
        txNama2 = (EditText)findViewById(R.id.edt_reg_lastname);
        txPhone = (EditText)findViewById(R.id.edt_reg_phone);
        txEmail = (EditText)findViewById(R.id.edt_reg_email);
        txPass1 = (EditText)findViewById(R.id.edt_reg_password);
        txPass2 = (EditText)findViewById(R.id.edt_reg_confpassword);
        cbAgree = (CheckBox)findViewById(R.id.cb_agree);
        
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txNama1.getText()) || TextUtils.isEmpty(txNama2.getText()) || TextUtils.isEmpty(txPhone.getText()) 
                        || TextUtils.isEmpty(txEmail.getText()) || TextUtils.isEmpty(txPass1.getText()) || TextUtils.isEmpty(txPass2.getText())) {
                    if (TextUtils.isEmpty(txNama1.getText())) {
                        txNama1.setError("This field is required!");
                    }
                    if (TextUtils.isEmpty(txNama2.getText())) {
                        txNama2.setError("This field is required!");
                    }
                    if (TextUtils.isEmpty(txPhone.getText())) {
                        txPhone.setError("This field is required!");
                    }
                    if (TextUtils.isEmpty(txEmail.getText())) {
                        txEmail.setError("This field is required!");
                    }
                    if (TextUtils.isEmpty(txPass1.getText())) {
                        txPass1.setError("This field is required!");
                    }
                    if (TextUtils.isEmpty(txPass2.getText())) {
                        txPass2.setError("This field is required!");
                    }
                } else if ( !isValidEmail(txEmail.getText()) ) {
                    txEmail.setError("Please enter a valid email address!");
                } else if ( !isValidMobile(txPhone.getText().toString())) {
                    txPhone.setError("Please enter a valid phone number!");
                } else if (!txPass1.getText().toString().equals(txPass2.getText().toString())) {
                    txPass2.setError("Password doesn't match!");
                } else {
                    if (!cbAgree.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Please check the Registration Agreement!", Toast.LENGTH_SHORT).show();
                    } else {
                        name = txNama1.getText().toString() + " " + txNama2.getText().toString();
                        User user = new User();
                        user.setName(name);
                        user.setPhone("0"+txPhone.getText().toString());
                        user.setEmail(txEmail.getText().toString());
                        user.setPassword(txPass1.getText().toString());
                        database.insertUser(user);
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        intent.putExtra("userEmail",user.getEmail());
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
        
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
