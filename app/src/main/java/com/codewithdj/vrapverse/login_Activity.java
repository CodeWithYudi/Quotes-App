package com.codewithdj.vrapverse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        final EditText editText;
        final EditText editText1;

        final Button reg;

        editText = findViewById(R.id.editTextTextEmailAddress);
        editText1 = findViewById(R.id.editTextTextPassword);
        reg = findViewById(R.id.btnreg);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String mail = editText.getText().toString().trim();
                String pass = editText1.getText().toString().trim();

                reg.setEnabled(!mail.isEmpty() && !pass.isEmpty());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        editText.addTextChangedListener(textWatcher);
        editText1.addTextChangedListener(textWatcher);




        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(login_Activity.this, "Sign in has been clicked", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(login_Activity.this , versePage_Activity.class);
                startActivity(intent1);
            }
        });


    }
}