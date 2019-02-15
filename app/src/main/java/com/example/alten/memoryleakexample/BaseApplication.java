package com.example.alten.memoryleakexample;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class BaseApplication extends Application {

    //Observador para vigilar los posibles leak.
    private RefWatcher refWatcher;

    //Método estatico para obtener el Watcher
    public static RefWatcher getRefWatcher(Context context){
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Instalamos el LeakCanary
        refWatcher = LeakCanary.install(this);
    }


    /*
    Other way
     
    @Override
    public void onCreate() {
        super.onCreate();
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
    }
     */

}
