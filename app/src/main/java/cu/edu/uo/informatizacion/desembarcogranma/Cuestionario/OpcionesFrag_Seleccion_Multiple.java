package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Gigabyte on 12/10/2018.
 */

public class OpcionesFrag_Seleccion_Multiple extends FragmentPadre {

    ItemSeleccion item ;
   // int respuestaseleccionada= -1;
    LinearLayout rootView ;
    Pregunta mPregunta ;
    ArrayList selecciones ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Bundle argumentos = getArguments() ;
        mPregunta = argumentos.getParcelable("pregunta") ;
        selecciones = new ArrayList<>();
        ScrollView scroll = new ScrollView(getContext()) ;
        rootView = new LinearLayout(getContext()) ;
        rootView.setVerticalGravity(Gravity.CENTER_VERTICAL);
        rootView.setOrientation(LinearLayout.VERTICAL);

        rootView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

        for(int i = 0 ; i < mPregunta.getmRespuestas().size() ; i++ )
        {
            item  = new ItemSeleccion(getContext()) ;
            String opcion = mPregunta.getmRespuestas().get(i).toString() ;
            item.texto_opcion.setText(opcion ) ;
            item.indicador.setText((i+1) + "") ;
            item.indicadorposicion = i ;
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   if(!((ItemSeleccion)v ).seleccionado) {
                       ((ItemSeleccion) v).Seleccionar();
                       selecciones.add(((ItemSeleccion) v).indicadorposicion);
                   }else {
                       ((ItemSeleccion) v).DesSeleccionar();
                       selecciones.remove((Object) ((ItemSeleccion) v).indicadorposicion);
                    }

                }
            });
            rootView.addView(item);
        }
        scroll.addView(rootView);

       return scroll ;

    }


    @Override
    Object getRespuestDada() {
        StringBuilder builder = new StringBuilder() ;
        for(Object x:selecciones)
        {
            builder.append(x.toString())  ;
            builder.append("*") ;
        }
        return builder.toString();
    }
}
