package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import cu.edu.uo.informatizacion.desembarcogranma.Ayuda;
import cu.edu.uo.informatizacion.desembarcogranma.R;
import cu.edu.uo.informatizacion.desembarcogranma.TEMAS;
import cu.edu.uo.informatizacion.desembarcogranma.Utilidades;

/**
 * Created by Gigabyte on 03/10/2018.
 */

public class Cuestionario extends AppCompatActivity implements View.OnClickListener
       {

    ArrayList<Pregunta> listapreguntas;
    ArrayList<Integer> ordendesalida;
    int tiempo = 5 ;
    TextView reloj ;
    int puntuacion = 0 ;
    TextView puntaje ;
  //  ArrayList respuestasdadas;
    Fragment instancipregunta ;
    FragmentPadre instanciopciones ;
    Pregunta preguntaamostrar ;
   // Timer temporizador;
    Handler manejadortiempo;
   // TimerTask corrertiempo;
    Runnable runtiempo ;
   AppCompatActivity actividad ;
   Context contexto ;

           @Override
           public void onBackPressed() {
               Intent padre = new Intent(this,TEMAS.class);
               padre.putExtra("cuestionario",1) ;
               NavUtils.navigateUpTo(this,padre);
           }



           @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_cuestionario);

        Utilidades.FullScreen(this);


        actividad = this;
        contexto = this ;
////////  establecer tipo fuente del cronometro
        Typeface fuentepropia = Typeface.createFromAsset(getAssets(), "LIQUIDCRYSTAL-EXBOLD.OTF");
        reloj =  (TextView) findViewById(R.id.cronometro) ;
        puntaje  =  (TextView) findViewById(R.id.puntuacion) ;
        reloj.setTypeface(fuentepropia);
        //reloj.setText("30");


        /////////// Crear lista de preguntas
        FabricaPreguntas fact   = new FabricaPreguntas(this);
        listapreguntas = fact.CreaPreguntas(); ;

//// generar numero random, nuevo, para no repetir preguntas
         ordendesalida = new ArrayList<>();

        /////  prueba hacer correr el cronometro
        findViewById(R.id.responder).setOnClickListener(this);


        PonerRelojACorrer() ;
        manejadortiempo = new Handler() ;


        ColocarPreguntas();

    }

           @Override
           protected void onPostResume() {
               super.onPostResume();

               new AsyncTask() {
                   @Override
                   protected Object doInBackground(Object[] params) {
                       return PreferenceManager.getDefaultSharedPreferences ((Context)params[0]).getBoolean("vioayuda",false);
                   }

                   @Override
                   protected void onPostExecute(Object o) {
                       super.onPostExecute(o);
                      // if(!(Boolean) o)
                       Snackbar.make(reloj,"Si necesitas ayuda sobre el cuestionario, aqui puedes encontrarla",Snackbar.LENGTH_INDEFINITE).setAction(
                               "AYUDA", new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       startActivity(new Intent(getBaseContext(), Ayuda.class));
                                   }
                               }
                       ).setActionTextColor(getResources().getColor(R.color.blanco))
                               .show();
                   }
               }.execute(this) ;

           }

           @Override
    protected void onStop() {
        super.onStop();
        manejadortiempo.removeCallbacks(runtiempo);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manejadortiempo.removeCallbacks(runtiempo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manejadortiempo.removeCallbacks(runtiempo);
    }



    private void ColocarPreguntas() {

        RandNumber();
        preguntaamostrar = listapreguntas.get(ordendesalida.get(ordendesalida.size() - 1));
        tiempo = preguntaamostrar.getmTiempo();
        reloj.setText("" + tiempo);

        manejadortiempo.postDelayed(runtiempo, 1000);

        if (preguntaamostrar.EsValida() == false) {
            AlertDialog.Builder dial = new AlertDialog.Builder(this);
            dial.setMessage("Hay un error en los datos de entrada ... "
                    + " Preguntas -- " + preguntaamostrar.getmPregunta().size()
                    + " Respuestas -- " + preguntaamostrar.getmRespuestas().size()
            );
            dial.create().show();
        }

        ////////////////////////
        switch (preguntaamostrar.getmTipoPregunta()) {
            case SELECCIONA: {

                instanciopciones = new OpcionesFrag_Selecciona();
                Bundle options = new Bundle();
                options.putParcelable("pregunta", preguntaamostrar);
                instanciopciones.setArguments(options);
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_opciones, instanciopciones
                ).commit();

                    instancipregunta = new PreguntaFrag_Selecciona();
                    Bundle quest = new Bundle();
                    quest.putString("pregunta", preguntaamostrar.getmPregunta().get(0).toString());
                    instancipregunta.setArguments(quest);
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.frame_pregunta, instancipregunta
                    ).commit();
                    break;

            }
        case ACOMODA_TEXTO: {
            instancipregunta = new PreguntaFrag_AcomodaTexto();
            Bundle quest = new Bundle();
            quest.putParcelable("pregunta", preguntaamostrar);
            instancipregunta.setArguments(quest);
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.frame_pregunta, instancipregunta
            ).commit();

            instanciopciones = new OpcionesFrag_AcomodaTexto();
            Bundle options = new Bundle();
            options.putParcelable("pregunta", preguntaamostrar);
            instanciopciones.setArguments(options);
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.frame_opciones, instanciopciones
            ).commit();

            ArrayList art = ((PreguntaFrag_AcomodaTexto)instancipregunta).getListapreguntas();
            ((OpcionesFrag_AcomodaTexto)instanciopciones).setItemsrespuestas(art) ;
            break;
        }

            case SELECCIONA_IMAGEN: {
                instancipregunta = new PreguntaFrag_Selecciona();
                Bundle quest = new Bundle();
                quest.putString("pregunta", preguntaamostrar.getmPregunta().toString());
                instancipregunta.setArguments(quest);
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_pregunta, instancipregunta).commit();

                instanciopciones = new OpcionesFrag_Selecciona_Imagen();
                Bundle options = new Bundle();
                options.putParcelable("pregunta", preguntaamostrar);
                instanciopciones.setArguments(options);
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_opciones, instanciopciones).commit();
                break;
            }

            case SELECCION_MULTIPLE: {
                instancipregunta = new PreguntaFrag_Selecciona();
                Bundle quest = new Bundle();
                quest.putString("pregunta", preguntaamostrar.getmPregunta().toString());
                instancipregunta.setArguments(quest);
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_pregunta, instancipregunta).commit();

                instanciopciones = new OpcionesFrag_Seleccion_Multiple();
                Bundle options = new Bundle();
                options.putParcelable("pregunta", preguntaamostrar);
                instanciopciones.setArguments(options);
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_opciones, instanciopciones).commit();
                break;
            }
            case ORDENAR: {
                instancipregunta = new PreguntaFrag_Selecciona();
                Bundle quest = new Bundle();
                quest.putString("pregunta", preguntaamostrar.getmPregunta().toString());
                instancipregunta.setArguments(quest);
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_pregunta, instancipregunta).commit();

                instanciopciones = new OpcionesFrag_Ordenar();
                Bundle options = new Bundle();
                options.putParcelable("pregunta", preguntaamostrar);
                instanciopciones.setArguments(options);
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_opciones, instanciopciones).commit();
                break;
            }

            case ENLAZA: {
                instancipregunta = new PreguntaFrag_Enlazar();
                Bundle quest = new Bundle();
                quest.putParcelable("pregunta", preguntaamostrar);
                instancipregunta.setArguments(quest);
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_pregunta, instancipregunta).commit();

                instanciopciones = new OpcionesFrag_Enlazar();
                Bundle options = new Bundle();
                options.putParcelable("pregunta", preguntaamostrar);
                instanciopciones.setArguments(options);
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.frame_opciones, instanciopciones).commit();
                break;
            }

            }



    }





    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.responder)
        {
            manejadortiempo.removeCallbacks(runtiempo);
            boolean resp_correcta = preguntaamostrar.Evaluar( instanciopciones.getRespuestDada()) ;
            String mensaje =  "pregunta" ;
            String titulo = "respuesta"  ;
           if(resp_correcta)
           {
               int asumar = tiempo+10 ;
               puntuacion += asumar ;

               mensaje = "Obtienes "+ asumar +" puntos y ahora tienes en tu poder **" + puntuacion+ " puntos**. Sigue el buen trabajo" ;
                titulo = "Has acertado"  ;
               puntaje.setText("Ptos: " + puntuacion);
           }
           else {
            titulo =  "Respuesta incorrecta";
            mensaje =  "Sigue intentándolo" ;
           }
            AlertDialog.Builder dialogorespuesta = new AlertDialog.Builder(this) ;
            dialogorespuesta.setPositiveButton("CONTINUAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();

                }
            }).setMessage(mensaje)
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if(ordendesalida.size() < listapreguntas.size()  )
                            {
                                ColocarPreguntas();
                            }
                            else
                            {
                                int oldpunt = PreferenceManager.getDefaultSharedPreferences(contexto).getInt("puntuacion",-1) ;
                                String mesage = "Has terminado el cuestionario con ***" + puntuacion+ " puntos*** acumulados. Ahora comparte la aplicación con tus amigas y amigos. ¿Veamos cuantos puntos pueden obtener ellos?" ;
                                String titulo = "Bien hecho" ;
                                if(oldpunt < puntuacion )
                                {
                                    PreferenceManager.getDefaultSharedPreferences(actividad)
                                            .edit().putInt("puntuacion",puntuacion).apply();
                                    if( oldpunt!=-1)
                                    {
                                        titulo = "FELICIDADES" ;
                                        mesage = "Has superado tu puntuación anterior, ahora tienes ***" + puntuacion+ " puntos*** acumulados. Comparte la app con tus amigas y amigos. ¿Veamos si ellos pueden superarlo?" ;
                                    }

                                }
                                AlertDialog.Builder dialogogfinal = new AlertDialog.Builder(contexto) ;
                                dialogogfinal.setTitle(titulo);
                                dialogogfinal.setMessage(mesage);
                                dialogogfinal.setNegativeButton("IR A TEMAS", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        Intent temas = new Intent(getBaseContext() , TEMAS.class);
                                        temas.putExtra("cuestionario",1) ;
                                        actividad.startActivity(temas);
                                    }
                                });
                                dialogogfinal.setPositiveButton("REINICIAR CUESTINARIO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        Intent cuest = new Intent(getBaseContext() , Cuestionario.class);
                                        actividad.startActivity(cuest);
                                    }
                                });
                                dialogogfinal.create().show();

                            }
                        }
                    })
                    .setTitle(titulo);
            dialogorespuesta.create().show();



          //  corrertiempo.cancel() ;
          //  temporizador.cancel();
            //// sumar los puntos y pasar a la proxima pregunta si es correcta
           // if( ((OpcionesFrag_Selecciona)instanciopciones).seleccionado == Integer.parseInt( preguntaamostrar.getmCorrecta().get(0).toString()))



        }
    }

    private void RandNumber()
    {
        Random r = new Random() ;
        int n = r.nextInt( listapreguntas.size())  ;
        if(ordendesalida.contains(n))
        {
            RandNumber();
        }
        else
        {
            ordendesalida.add(n);
        }

    }



    private void PonerRelojACorrer (){

          //  temporizador = new Timer();
            //manejador = new Handler();
        runtiempo = new Runnable() {
            @Override
            public void run() {
                if (tiempo > 0) {
                    tiempo--;
                    reloj.setText("" + tiempo);
                    manejadortiempo.postDelayed(this,1000) ;
                } else if (tiempo == 0){

                    // temporizador.cancel();
                    Snackbar.make(reloj, "Tiempo agotado...", Snackbar.LENGTH_LONG).show();
                    tiempo = -1 ;
                    if (ordendesalida.size() < listapreguntas.size()) {
                        //     manejador.postDelayed(this, 1000);
                        findViewById(R.id.responder).performClick();

                    }
            }
        }
            };




    }



        }







