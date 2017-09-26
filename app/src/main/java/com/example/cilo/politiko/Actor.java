package com.example.cilo.politiko;

/**
 * Created by cilo on 9/19/17.
 */

public class Actor {
    int actor_id, ethnicity_id, age;
    String gender;

    public Actor(){}

    public Actor(int actor_id, int ethnicity_id, int age, String gender) {
        this.ethnicity_id = ethnicity_id;
        this.actor_id = actor_id;
        this.age = age;
        this.gender = gender;
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public int getEthnicity_id() {
        return ethnicity_id;
    }

    public void setEthnicity_id(int ethnicity_id) {
        this.ethnicity_id = ethnicity_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
