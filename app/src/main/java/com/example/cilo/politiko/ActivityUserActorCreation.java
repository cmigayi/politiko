package com.example.cilo.politiko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ActivityUserActorCreation extends AppCompatActivity implements View.OnClickListener{
    Button proceedBtn;
    RadioGroup genderRG;
    RadioButton genderRB;
    String genderST;
    int ageINT;
    EditText ageET;

    HandleMethods handleMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_actor_creation);
        overridePendingTransition(0,0);

        handleMethods = new HandleMethods(this);

        genderRG = (RadioGroup) findViewById(R.id.gender);
        ageET = (EditText) findViewById(R.id.age);
        proceedBtn = (Button) findViewById(R.id.proceed_btn);

        proceedBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.proceed_btn:

                genderRB = (RadioButton) findViewById(genderRG.getCheckedRadioButtonId());
                genderST = genderRB.getText().toString();
                ageINT = Integer.parseInt(ageET.getText().toString());

                handleMethods.actorCreation(genderST, ageINT);
                break;
        }
    }
}
