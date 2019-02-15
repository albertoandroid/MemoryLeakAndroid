package com.example.alten.memoryleakexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.leakcanary.RefWatcher;

public class MonitorearVariableActivity extends AppCompatActivity {

    /*
    Vamos a hacer la inspección sobre un objeto, para ver si este objeto produce Fugas de Memorias.
    Es decir vigilar algo concreto, en lugar de toda la aplicación.
     */
    private String monitorearMemoryLeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitorear_variable);


        monitorearMemoryLeak = "";

        /*
        Con esto nos permite monitorear esta variable especificamente y ver si produce memory leak al correr nuestra aplicación.
         */
        RefWatcher refWatcher = BaseApplication.getRefWatcher(this);
        refWatcher.watch(this.monitorearMemoryLeak);
    }
}
