package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cu.edu.uo.informatizacion.desembarcogranma.R;

/**
 * Created by Gigabyte on 03/10/2018.
 */

public class Opciones_Fragment extends FragmentPadre {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

     /*   int numpregunta = getArguments().getInt("pregunta") ;
        ArrayList<Pregunta> listapreguntas = new FabricaPreguntas(container.getContext()).CreaPreguntas() ;
        Pregunta pregunta = listapreguntas.get(numpregunta) ;
        int tipopregunta  = pregunta.getmTipoPregunta().ordinal() ;
        View rootView = new View(container.getContext()) ;

        switch (tipopregunta)
        {
            case 1:{
                Integer layout = R.layout.vista_cuestionario_2 ;
                rootView = inflater.inflate(layout, container, false) ;
                break;
            }
            case 2:{
                Integer layout = R.layout.vista_cuestionario_3 ;
                rootView = inflater.inflate(layout, container, false) ;
                break;
            }
            case 3:{
                Integer layout = R.layout.vista_cuestionario_4 ;
                rootView = inflater.inflate(layout, container, false) ;
                break;
            }
            default: {

                String [] opciones = pregunta.getmRespuestas() ;
                for(int i = 0 ; i < opciones.length ; i++ )
                {


                }

              //  ((TextView)rootView.findViewById(R.id.textrespuestaB)).setText( pregunta.getmRespuestas()[1]) ;
              //  ((TextView)rootView.findViewById(R.id.textrespuestaA)).setText( pregunta.getmRespuestas()[2]) ;
                break;
            }

        }*/

        return new View(container.getContext()) ;


    }



    @Override
    ArrayList getRespuestDada() {
        return null;
    }
}
