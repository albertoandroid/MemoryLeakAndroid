package com.example.alten.memoryleakexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner);



        /*
        Genera Memory Leak
        Al girar el móvil se llama a esta función y por tanto se reserva memoria para ese hilo anonimo.
        Como es un hilo anonimo el GC no va a encontrar referencia al hilo y por lo tanto no lo va a poder borrar.
        Cada vez que giremos el móvil se crea un nuevo hilo, etc. Y son acumulativos.
         */
        generaMemoryLeak();


        /*
        No genera Memory Leak
        */
        noGeneraMemoryLeak();


    }

    /*
        Genera Memory Leak con Clase Anonima
     */
    private void generaMemoryLeak(){
        new Thread(){
            @Override
            public void run() {
                try{
                    sleep(1000);
                }catch (Exception e){

                }
            }
        }.start();
    }

    /*
    No genera Memory Leak
    Es un hilo referenciado, por lo tanto el Garbage Collector lo puede referenciar y localizar.
     */
    private static class Hilo extends Thread{
        @Override
        public void run() {
            try{
                sleep(1000);
            }catch (Exception e){

            }
        }
    }

    private void noGeneraMemoryLeak(){
        new Hilo().start();
    }
}
