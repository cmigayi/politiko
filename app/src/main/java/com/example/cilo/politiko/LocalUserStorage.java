package com.example.cilo.politiko;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cilo on 4/28/17.
 */

public class LocalUserStorage {

    SharedPreferences userStorage,actorStorage;
    static String USER_DATA = "user";
    static String ACTOR_DATA = "actor";
    SharedPreferences.Editor editor;

    public LocalUserStorage(Context context){
        userStorage = context.getSharedPreferences(USER_DATA,0);
        actorStorage = context.getSharedPreferences(ACTOR_DATA,0);
    }

    public void StoreUserData(User user){
        editor = userStorage.edit();

        editor.putInt("user_id",user.user_id);
        editor.putString("email",user.email);

        editor.commit();
    }

    public void StoreActorData(Actor actor){
        editor = actorStorage.edit();

        editor.putInt("actor_id",actor.actor_id);
        editor.putString("gender",actor.gender);
        editor.putInt("ethnicity_id",actor.ethnicity_id);
        editor.putInt("age",actor.age);

        editor.commit();
    }

    public Actor getActor(){
        int actorID = actorStorage.getInt("actor_id",-1);
        String gender = actorStorage.getString("gender","");
        int age = actorStorage.getInt("age",-1);
        int ethnicityID = actorStorage.getInt("ethnicity_id",-1);

        Actor actor = new Actor();
        actor.setActor_id(actorID);
        actor.setAge(age);
        actor.setEthnicity_id(ethnicityID);
        actor.setGender(gender);

        return actor;
    }

    public User getSignedinUser(){
        int user_id = userStorage.getInt("user_id",-1);
        String email = userStorage.getString("email","");

        User user = new User();
        user.setUser_id(user_id);
        user.setEmail(email);

        return user;
    }

    public void setUserSignedinStatus(boolean status){
        editor = userStorage.edit();
        editor.putBoolean("signedIn",status);
        editor.commit();
    }

    public boolean getUserSignedinStatus(){
        boolean status = userStorage.getBoolean("signedIn",false);
        return status;
    }

    public void clearuserData(){
        editor = userStorage.edit();
        editor.clear();
        editor.commit();
    }

    public void clearActorData(){
        editor = actorStorage.edit();
        editor.clear();
        editor.commit();
    }
}

