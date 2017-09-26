package com.example.cilo.politiko;

import org.json.JSONException;

/**
 * Created by cilo on 5/29/17.
 */

public interface UrlCallBack{
    public abstract void done(String response) throws JSONException;
}
