package com.example.cilo.politiko;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class ActivityNews extends AppCompatActivity implements View.OnClickListener{
    Button proceedBtn;
    TextView questionTV;
    Intent intent;
    HandleMethods handleMethods;
    HashMap<String, String> dataFromDBHashmap;
    String question = null, answers = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        overridePendingTransition(0,0);

        questionTV = (TextView) findViewById(R.id.question_tv);
        proceedBtn = (Button) findViewById(R.id.proceed_btn);

        handleMethods = new HandleMethods(this);
        intent = getIntent();

        dataFromDBHashmap = new HashMap<>();
        dataFromDBHashmap = (HashMap<String, String>) intent.getSerializableExtra("dataFromDB");

        Log.d("question3",""+dataFromDBHashmap);

        if(dataFromDBHashmap != null){
            question = dataFromDBHashmap.get("question");
            answers = dataFromDBHashmap.get("answers");
        }

        questionTV.setText(question);

        proceedBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.proceed_btn:
                intent = new Intent(this, ActivityActorResponse.class);
                intent.putExtra("answers",answers);
                startActivity(intent);
                break;
        }

    }
}
