package com.example.cilo.politiko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityActorResponse extends AppCompatActivity {
    Intent intent;
    HandleMethods handleMethods;
    HandleJsonDataFromServer handleJsonDataFromServer;
    ArrayList<HashMap<String, String>> answersArraylist;
    String answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_response);

        handleMethods = new HandleMethods(this);
        intent = getIntent();

        answersArraylist = new ArrayList<HashMap<String, String>>();
        answers = intent.getStringExtra("answers");

        Log.d("answers",""+answers);

        try {
            handleJsonDataFromServer = new HandleJsonDataFromServer(answers,"answers");
            answersArraylist = handleJsonDataFromServer.getAnswersData();

            Log.d("answers",""+answersArraylist);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
