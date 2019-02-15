package com.example.alten.memoryleakexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/*
¿Qué es un Memory Leak?
Memory Leak en español Fuga de Memoria, no es más que un error de nuestra aplicación que se da cuando un bloque
de memoria reservado no es liberado por nuestra app en ejecución.
Las fugas de memoria se van acumulando en nuestra app con el tiempo y por tanto si no son solucionadas nuestro móvil
se quedara sin memoria.

En Java esta el Garbage Collector que se encarga de borrar la memoria que no es utilizada.
1.-  Garbace Collector analiza todas las referencias de objetos de nuestra aplicación que estan en memoria y se marcan
como objetos activos.
2.-  Todo objeto detectado por el Garbace collector que no esta referenciado es marcado como Basura y son borrados de la memoria.

Ventajas GC:
- Quita la responsabilidad a los desarrolladores de liberar y destruir objetios.
- Facilita el uso de grandes cantidades de memorias

Desventaja
- Consumo adicional de recursos. Es decir el Garbage Collector no funciona solo, sino que necesita recursos y eso impacta en el rendimiento de la aplicación.

Visto todo esto como detectamos un Memory Leak
Si el código de nuestra aplicación no lo hemos realizado correctamente se nos van a generar memory leak
¿Pero como detectamos un MemoryLeak?
Imagina que todas las referencias a un objeto se pierden antes de que esa memoria haya sido liberada. Ya tienes un memory leak. Luego en los ejemplos se vera claro.

Que pasa con los memory leak si mi telefono tiene memoria infinita
Al ir incrementando los memory leak con el paso del tiempo de nuestra aplicación, llegara un momento en el que la app
pida más memoria al dispositivo pero el sistema se lo negara y por lo tanto nuestra aplicación crahseara.
 */


/*
La definición de una fuga de memoria es un escenario que ocurre cuando nuestra aplicación android ya no utiliza los objetos
pero el recolector de basarua no puede eliminarlos de la memoria debido a que todavía se hacen referencia a ellos.
Como resultado, nuestra app consuma más y más recursos, lo que la hace insostenible en el tiempo.

Mostrar dibujo

  -----------------------------------------------------------------------
  -      Objetos            - Objetos           -    Objetos            -
  -      No                 - Referenciados     -    Referenciados      -
  -      Referenciados      - No Usados         -                       -
  -----------------------------------------------------------------------

 Tipos de Objetos:
Objetos Referenciados -> Los Objetos Referenciados no pueden eliminarse y por lo tanto generan memory leak
Objetos No Referenciados -> El Garbace Collector puede eliminar los objetos no referenciados.

 */

/*
Leak Canary -> Nos permite detectar leaks de memoria que nos encontramos cuando perdemos las referencias de los objetos y el Garbace Collector
No los puede localizar y no los puede borrar.
 */

public class MainActivity extends AppCompatActivity {

    Button btSingleton;
    Button btInner;
    Button btMonitorear;
    Button btAsynsTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSingleton = findViewById(R.id.btSinglenton);
        btSingleton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SinglentonActivity.class));
            }
        });

        btInner = findViewById(R.id.btInner);
        btInner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InnerActivity.class));
            }
        });

        btMonitorear = findViewById(R.id.btMonitorear);
        btMonitorear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MonitorearVariableActivity.class));
            }
        });

        btAsynsTask = findViewById(R.id.btAsynsTask);
        btAsynsTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AsynTaskActivity.class));
            }
        });
    }
}
