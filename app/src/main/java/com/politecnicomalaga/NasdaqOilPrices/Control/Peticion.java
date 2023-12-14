package com.politecnicomalaga.NasdaqOilPrices.Control;

import android.os.Handler;
import android.os.Looper;

import com.politecnicomalaga.NasdaqOilPrices.Control.MainController;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Clase Peticion
 *
 * Es utilizado por el controlador. El controlador le proporciona
 * los datos necesarios
 *
 * Se apoyará en OkHttp (librería cliente http/http2)
 *
 */
public class Peticion {
    //ESTADO
    //Clase utilidad que no necesita nada más que poner a funcionar la peticion HTTPs
    private static final String API_KEY = "api_key=ogVQKAes14fk8kVj979s";
    //COMPORTAMIENTO
    public Peticion() {

    }

    public void requestData(String URL) {
        OkHttpClient cliente = new OkHttpClient();

        //construimos la peticion
        Request peticion = new Request.Builder()
                .url(URL+API_KEY)
                .get()
                .addHeader("cache-control", "no-cache")
                .build();


        //realizamos la llamada al server, pero en otro thread (con enqueue)
         Call llamada = cliente.newCall(peticion);
         llamada.enqueue(new Callback() {
             public void onResponse(Call call, Response respuestaServer)
                     throws IOException {
                 //Got answer!!!!!
                 String respuesta = respuestaServer.body().string();
                 // Create a handler that associated with Looper of the main thread
                 Handler manejador = new Handler(Looper.getMainLooper());
// Send a task to the MessageQueue of the main thread
                 manejador.post(new Runnable() {
                     @Override
                     public void run() {
                         // Code will be executed on the main thread
                         MainController.getSingleton().setDataFromNasdaq(respuesta);
                     }
                 });
             }

             public void onFailure(Call call, IOException e) {
                 String respuesta = e.getMessage();
                 Handler manejador = new Handler(Looper.getMainLooper());

// Send a task to the MessageQueue of the main thread
                 manejador.post(new Runnable() {
                     @Override
                     public void run() {
                         // Code will be executed on the main thread
                         MainController.getSingleton().setDataFromNasdaq("");
                         MainController.getSingleton().setErrorFromNasdaq(respuesta);
                     }
                 });
             }
         });





    }

}
