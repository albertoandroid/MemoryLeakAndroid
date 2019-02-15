package com.example.alten.memoryleakexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class SinglentonActivity extends AppCompatActivity {

    MySingleton mySingleton;

    //Este activity causa Memory Leak al ser Static.
    private static SinglentonActivity activity;
    //Solución Weak Reference. Si marcamos un objeto como WeakReference quiere decir que no estará protegido contra el Garbage Collector.
    //Da igual que tenga una referencia a otro objeto. Cuando pase el Garbage Collector lo recogera.
    private static WeakReference<SinglentonActivity> wrActivity;
    //
    private static TextView tvSin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlenton);

        //this -> ActivityContext causa memory leak al rotar pantalla.
        //Cada vez que se crea y destrulle una activity al rotar la pantalla, se crea un nuevo ActivityContent y por
        //tanto al crear una instancia con ActivityContext se queda en MemoryLeak.
        mySingleton = MySingleton.getInstance(this);

        //getApplicationContext -> No causa memory leak
        //Porque por mucho que se destruya la activity siempre se usa el mimo context.
        //Que es el contex de la aplicacion.
        //mySingleton = MySingleton.getInstance(getApplicationContext());


        // new MyAsyncTask((TextView) findViewById(R.id.textview)).execute();


        //Genera Memory LEAK
        activity = this;
        //No genera MemoryLead
        wrActivity = new WeakReference<SinglentonActivity>(this);


        //Genera memory leak
        tvSin = findViewById(R.id.tvSin);


        /*
        Inner Class
         */
    }


    /*
    Para eleminar memory leak de textview
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        tvSin = null;
    }


    private class TareaAsyncTask extends AsyncTask<Void, Integer, String> {

        @Override
        protected void onPreExecute(){
            tvSin.setText("0");
        }

        @Override
        protected String doInBackground(Void... voids) {
            for(int i=1; i<=5; i++){
                esperarUnSegundo();
                publishProgress(i);
            }
            return "Finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            tvSin.setText(values[0].toString());
        }

        @Override
        protected void onPostExecute(String resultado){
            tvSin.setText(resultado);
        }
    }

    private void esperarUnSegundo(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }

    }
}
