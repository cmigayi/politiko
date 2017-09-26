package com.example.cilo.politiko;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by cilo on 4/26/17.
 */

public class HandleJsonDataFromServer {
    JSONObject jsonObject;
    JSONArray dataFromServerJsonArray;
    HashMap<String,String> dataFromServerHashMap;
    ArrayList<HashMap<String,String>> dataFromServerArraylist;
    String response,jsonStringName,jsonStringData = null;
    boolean state;

    public HandleJsonDataFromServer(String response, String jsonStringName) throws JSONException {
        this.response = response;
        this.jsonStringName = jsonStringName;
        dataFromServerArraylist = new ArrayList<HashMap<String, String>>();
        jsonStringData = null;
        jsonObject = new JSONObject(response);
    }

    public boolean postData(){
        try{
            dataFromServerJsonArray = getJsonArray();
            JSONObject dataItem = dataFromServerJsonArray.getJSONObject(0);
            state = dataItem.getBoolean("status");

        }catch(Exception e){
            e.printStackTrace();
        }
        return state;
    }

    public HashMap<String,String> userData(){
        try{
            dataFromServerJsonArray = getJsonArray();
            JSONObject dataItem = dataFromServerJsonArray.getJSONObject(0);
            state = dataItem.getBoolean("status");

            dataFromServerHashMap = new HashMap<>();

            if(state == true){

                int userID = Integer.parseInt(dataItem.getString("user_id"));
                String email = dataItem.getString("email");

                dataFromServerHashMap.put("user_id",""+userID);
                dataFromServerHashMap.put("email",email);
            }

            dataFromServerHashMap.put("status",Boolean.toString(state));

        }catch(Exception e){
            e.printStackTrace();
        }
        return dataFromServerHashMap;
    }

    public HashMap<String,String> actorData(){
        try{
            dataFromServerJsonArray = getJsonArray();
            JSONObject dataItem = dataFromServerJsonArray.getJSONObject(0);
            state = dataItem.getBoolean("status");

            dataFromServerHashMap = new HashMap<>();

            if(state == true){

                int actorID = Integer.parseInt(dataItem.getString("actor_id"));
                String gender = dataItem.getString("gender");
                int age = Integer.parseInt(dataItem.getString("age"));
                int ethnicityID = Integer.parseInt(dataItem.getString("ethnicity_id"));

                dataFromServerHashMap.put("actor_id",""+actorID);
                dataFromServerHashMap.put("gender",gender);
                dataFromServerHashMap.put("age",""+age);
                dataFromServerHashMap.put("ethnicity_id",""+ethnicityID);
            }
            dataFromServerHashMap.put("status",Boolean.toString(state));

        }catch(Exception e){
            e.printStackTrace();
        }
        return dataFromServerHashMap;
    }

    public HashMap<String, String> getQuestionsData(){
        try {
            dataFromServerJsonArray = getJsonArray();
            JSONObject dataItem = dataFromServerJsonArray.getJSONObject(0);
            state = dataItem.getBoolean("status");

            dataFromServerHashMap = new HashMap<>();

            if(state == true){
                JSONObject dataItem2 = dataFromServerJsonArray.getJSONObject(1);

                int queID = Integer.parseInt(dataItem2.getString("question_id"));
                String question = dataItem2.getString("question");
                String respondent = dataItem2.getString("respondent");
                String answers = dataItem2.getString("answers");

                dataFromServerHashMap.put("question_id",""+queID);
                dataFromServerHashMap.put("question",question);
                dataFromServerHashMap.put("respondent",""+respondent);
                dataFromServerHashMap.put("answers",""+answers);
            }
            dataFromServerHashMap.put("status",Boolean.toString(state));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataFromServerHashMap;
    }

    public ArrayList<HashMap<String,String>> getAnswersData(){
        try {
            dataFromServerJsonArray = getJsonArray();
            JSONObject dataItem = dataFromServerJsonArray.getJSONObject(0);
            state = dataItem.getBoolean("status");

            if(state == true){
                dataItem = dataFromServerJsonArray.getJSONObject(1);
                String answer = dataItem.getString("answer");
                dataFromServerJsonArray = jsonObject.getJSONArray(answer);

                for(int i=0; i< dataFromServerJsonArray.length(); i++){
                    JSONObject dataItem3 = dataFromServerJsonArray.getJSONObject(i);
                    int answerID = dataItem3.getInt("answer_id");
                    String ans = dataItem3.getString("answer");

                    dataFromServerHashMap = new HashMap<String, String>();
                    dataFromServerHashMap.put("answer_id", ""+answerID);
                    dataFromServerHashMap.put("answer", ans);

                    dataFromServerArraylist.add(dataFromServerHashMap);
                }
            }else{

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataFromServerArraylist;
    }

    public boolean getState(){
        try {
            dataFromServerJsonArray = getJsonArray();
            JSONObject dataItem = dataFromServerJsonArray.getJSONObject(0);
            state = dataItem.getBoolean("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return state;
    }

    public JSONArray getJsonArray() throws JSONException {

        if(jsonObject.length() == 0){
            dataFromServerArraylist = null;
        }
        dataFromServerJsonArray = jsonObject.getJSONArray(jsonStringName);

        return dataFromServerJsonArray;
    }
}
