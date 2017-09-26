package com.example.cilo.politiko;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 9/18/17.
 */

public class HandleMethods {
    Context context;
    ServerRequest serverRequest;
    LocalUserStorage localUserStorage;
    User user;
    Actor actor;
    HashMap<String,String> dataToDbHashmap,dataFromDbHashmap;
    ArrayList<HashMap<String,String>> dataFromServerArraylist;
    String url;
    HandleJsonDataFromServer handleJsonDataFromServer;
    boolean state;
    Intent intent;

    public HandleMethods(Context context){
        localUserStorage = new LocalUserStorage(context);
        this.user = localUserStorage.getSignedinUser();
        this.context = context;
        dataToDbHashmap = new HashMap<>();
    }

    public void signup(String email, final LinearLayout loadingLinear, final LinearLayout signupLinear,
                       final LinearLayout activationLinear){

        dataToDbHashmap.put("email",email);
        url = "/user_signup.php";

        loadingLinear.setVisibility(View.VISIBLE);
        signupLinear.setVisibility(View.GONE);

        new ServerRequest(dataToDbHashmap, url, new UrlCallBack() {
            @Override
            public void done(String response) {

                if(response == null){
                    Log.d("Signup","null");
                }else{
                    try {
                        handleJsonDataFromServer = new HandleJsonDataFromServer(response,"user");
                        dataFromDbHashmap = handleJsonDataFromServer.userData();

                        state = Boolean.parseBoolean(dataFromDbHashmap.get("status"));
                        String email = dataFromDbHashmap.get("email");
                        int userID = Integer.parseInt(dataFromDbHashmap.get("user_id"));

                        if(state == true){
                            Log.d("Signup","success");
                            user = new User(email, userID);
                            loadingLinear.setVisibility(View.GONE);
                            activationLinear.setVisibility(View.VISIBLE);
                        }else{
                            Log.d("Signup","failed");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).execute();
    }

    public void checkActivation(final LinearLayout loadingLinear, final LinearLayout signupLinear,
                                final LinearLayout activationLinear){

        dataToDbHashmap.put("user_id",""+user.user_id);
        url = "/user_checkActivation.php";

        loadingLinear.setVisibility(View.VISIBLE);

        new ServerRequest(dataToDbHashmap, url, new UrlCallBack() {
            @Override
            public void done(String response) {

                if(response == null){
                    Log.d("user activation","null");
                }else{
                    try {
                        handleJsonDataFromServer = new HandleJsonDataFromServer(response,"user");
                        state =  handleJsonDataFromServer.getState();

                        if(state == true){
                            localUserStorage.StoreUserData(user);
                            localUserStorage.setUserSignedinStatus(true);

                            if(localUserStorage.getUserSignedinStatus() == true){

                                intent = new Intent(context, CountryIntro.class);
                                context.startActivity(intent);
                                activationLinear.setVisibility(View.GONE);
                                loadingLinear.setVisibility(View.GONE);

                            }  else{
                                Log.d("signup","Not signed in!");
                            }

                        }else{
                            loadingLinear.setVisibility(View.GONE);
                            activationLinear.setVisibility(View.VISIBLE);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).execute();
    }

    public void actorCreation(String gender, int age){

        dataToDbHashmap.put("user_id",""+user.user_id);
        dataToDbHashmap.put("gender",gender);
        dataToDbHashmap.put("age",""+ age);

        url = "/actor_creation.php";

        new ServerRequest(dataToDbHashmap, url, new UrlCallBack() {
            @Override
            public void done(String response) {

                if(response == null){
                    Log.d("actor-creation","null");
                }else{
                    try {
                        handleJsonDataFromServer = new HandleJsonDataFromServer(response,"actor");
                        dataFromDbHashmap = handleJsonDataFromServer.actorData();

                        state = Boolean.parseBoolean(dataFromDbHashmap.get("status"));
                        String gender = dataFromDbHashmap.get("gender");
                        int age = Integer.parseInt(dataFromDbHashmap.get("age"));
                        int actorID = Integer.parseInt(dataFromDbHashmap.get("actor_id"));
                        int ethnicityID = Integer.parseInt(dataFromDbHashmap.get("ethnicity_id"));

                        if(state == true){
                            actor = new Actor(actorID, ethnicityID, age, gender);
                            localUserStorage.StoreActorData(actor);

                            getQuestion();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).execute();
    }

    public void determinePage(String event, HashMap<String,String> pageData){

           if(event.equals("news")){
               intent = new Intent(context, ActivityNews.class);
               intent.putExtra("pagedata",pageData);
               context.startActivity(intent);

           }else{

           }

    }

    public void getQuestion(){

        dataToDbHashmap.put("user_id",""+user.user_id);
        url = "/get_question.php";

        new ServerRequest(dataToDbHashmap, url, new UrlCallBack() {
            @Override
            public void done(String response){
                if(response == null){

                }else{
                    try {
                        handleJsonDataFromServer = new HandleJsonDataFromServer(response,"questions");
                        dataFromDbHashmap = handleJsonDataFromServer.getQuestionsData();

                        if(dataFromDbHashmap == null){
                            dataFromDbHashmap = null;
                        }else{
                            intent = new Intent(context, ActivityNews.class);
                            intent.putExtra("dataFromDB",dataFromDbHashmap);
                            context.startActivity(intent);
                        }
                        Log.d("question11",""+dataFromDbHashmap);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).execute();;
    }

    public void getAnswers(String answers){
        try {
            handleJsonDataFromServer = new HandleJsonDataFromServer(answers,"answers");
            dataFromServerArraylist = handleJsonDataFromServer.getAnswersData();

            if(dataFromServerArraylist == null){
                dataFromServerArraylist = null;
            }else{
                intent = new Intent(context, ActivityNews.class);
                intent.putExtra("dataFromDB",dataFromServerArraylist);
                context.startActivity(intent);
            }
            Log.d("question11",""+dataFromServerArraylist);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
