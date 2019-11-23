package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cu.edu.uo.informatizacion.desembarcogranma.R;

/**
 * Created by Gigabyte on 12/10/2018.
 */

public class OpcionesFrag_Selecciona extends FragmentPadre {

    ItemSeleccion item ;
    Integer respuestadada ;
   // int respuestaseleccionada= -1;
    LinearLayout rootView ;
    Pregunta mPregunta ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Bundle argumentos = getArguments() ;
        mPregunta = argumentos.getParcelable("pregunta") ;
        respuestadada = new Integer(-1);
        ScrollView scroll = new ScrollView(getContext()) ;
        rootView = new LinearLayout(getContext()) ;
        rootView.setVerticalGravity(Gravity.CENTER_VERTICAL);
        rootView.setOrientation(LinearLayout.VERTICAL);

        rootView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

        for(int i = 0 ; i < mPregunta.getmRespuestas().size() ; i++ )
        {
           // final int pos = i ;

            item  = new ItemSeleccion(getContext()) ;
           // item = inflater.inflate(R.layout.item_seleccionar , container, false) ;
            String opcion = mPregunta.getmRespuestas().get(i).toString() ;
            item.texto_opcion.setText(opcion ) ;
            item.indicador.setText((i+1) + "") ;
            item.indicadorposicion = i ;
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  if(!item.seleccionado) {
                    //    v.setBackgroundColor(v.getContext().getResources().getColor(R.color.colorPrimaryDark));
                    //  item.texto_opcion.setTextColor(v.getContext().getResources().getColor(R.color.blanco));
                    //item.seleccionado=true ;

                    for (int j = 0; j < rootView.getChildCount(); j++) {
                        if (rootView.getChildAt(j) instanceof ItemSeleccion) {
                            ItemSeleccion myitm = ((ItemSeleccion) rootView.getChildAt(j));
                            //  if (myitm.getId() == v.getId())
                            myitm.DesSeleccionar();
                        }
                        ((ItemSeleccion)v ).Seleccionar();
                        respuestadada = ((ItemSeleccion)v ).indicadorposicion;
                              //   respuestadada = j ;


                            // respuestaseleccionada = i ;


                      //

                        // else {
                        // myitm.DesSeleccionar();
                        //}


                        //if(i!=pos && myitm.seleccionado)
                        //   {
                        //     myitm.setBackgroundColor(v.getContext().getResources().getColor(R.color.blanco));
                        //   myitm.texto_opcion.setTextColor(v.getContext().getResources().getColor(R.color.negro));
                        // myitm.seleccionado=false ;
                        //}
                    }


                    //    }
                    // ((ItemSeleccion) rootView.getChildAt(respuestaseleccionada)).Seleccionar();
                    //  }
                    //  else{
                    //    v.setBackgroundColor(v.getContext().getResources().getColor(R.color.blanco));
                    //  item.texto_opcion.setTextColor(v.getContext().getResources().getColor(R.color.negro));
                    // item.seleccionado=false ;
                    //respuestaseleccionada = -1 ;

                }
            });
           // item.setTranslationY(i*40);
            rootView.addView(item);
        }
        scroll.addView(rootView);

       return scroll ;

    }


    @Override
    Object getRespuestDada() {

        return respuestadada;
    }
}
