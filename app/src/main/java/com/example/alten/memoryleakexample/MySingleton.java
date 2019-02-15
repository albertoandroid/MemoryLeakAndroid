package com.example.alten.memoryleakexample;

import android.content.Context;
import android.util.Log;

public class MySingleton {

    private static MySingleton instance;

    private Context context;
    private static int numero =0;

    private MySingleton(Context context){
        this.context = context;
    }

    public static MySingleton getInstance(Context context) {
        if(instance == null){
            instance = new MySingleton(context);
            Log.d("TAG1", "WWWXXXXX: " + numero);

            numero++;
        }
        Log.d("TAG1", "WWW: " + numero);
        return instance;
    }
}
