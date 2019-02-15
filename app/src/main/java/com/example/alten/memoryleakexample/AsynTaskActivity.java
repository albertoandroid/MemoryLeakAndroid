package com.example.alten.memoryleakexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class AsynTaskActivity extends AppCompatActivity {

    private MyAsyncTask myAsyncTask;

    private Button lanzarPararAsynTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn_task);

        lanzarPararAsynTask = findViewById(R.id.lanzarPararAsynTask);
        lanzarPararAsynTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si ya ha sido creado la AsynsTask, lo que hacemos es finalizar la Activity para probar que se genera ese Memory Leak
                if(myAsyncTask != null){
                    finish();
                }
                myAsyncTask = new MyAsyncTask(AsynTaskActivity.this);
                myAsyncTask.execute();
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*
        Para evitar el Memory Leak de una Asynt Task en onDestroy borramos su referencia.
         */
        myAsyncTask.cancel(true);
        Log.d("TAG1", "onDestroy: activity destruida");
    }

    /*
    Memory Leak
    Tener una referencia a View, Activity, Context en algo que ocurre en background -> suele generar Memory Leak sino se hace con cuidado.
    En este ejemplo el AsynTask necestia el Context..
     */

    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        private Context context;

        public MyAsyncTask(Context context){
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_background);
            //Vamos a lanzar la tarea MyAsyntTask y antes de que acabe que como poco tardara el tiempo que tengamos dormido el hilo
            //vamos a destruir la activity, con lo que se pierde referencia a la Asyns Task y el Garbage Collecto no lo puede detectar.
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
