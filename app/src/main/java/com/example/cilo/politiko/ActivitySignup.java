package com.example.cilo.politiko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ActivitySignup extends AppCompatActivity implements View.OnClickListener{
    Button proceedBtn,signupBtn;
    EditText emailET;
    LinearLayout loadingLinear,signupLinear,activationLinear;

    String email_st;
    HandleMethods handleMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        handleMethods = new HandleMethods(this);

        proceedBtn = (Button) findViewById(R.id.proceed_btn);
        signupBtn = (Button) findViewById(R.id.sign_btn);
        emailET = (EditText) findViewById(R.id.email);
        loadingLinear = (LinearLayout) findViewById(R.id.loading_linear);
        signupLinear = (LinearLayout) findViewById(R.id.signup_linear);
        activationLinear = (LinearLayout) findViewById(R.id.activation_linear);

        proceedBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.sign_btn:
                email_st = emailET.getText().toString();
                handleMethods.signup(email_st, loadingLinear, signupLinear, activationLinear);
                break;

            case R.id.proceed_btn:
                handleMethods.checkActivation(loadingLinear, signupLinear, activationLinear);
                break;
        }
    }
}
