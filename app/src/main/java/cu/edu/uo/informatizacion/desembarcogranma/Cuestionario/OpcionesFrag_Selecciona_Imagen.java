package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;


import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import cu.edu.uo.informatizacion.desembarcogranma.R;

/**
 * Created by Gigabyte on 12/10/2018.
 */

public class OpcionesFrag_Selecciona_Imagen extends FragmentPadre implements
        View.OnClickListener , CompoundButton.OnCheckedChangeListener{

    ItemSeleccionImagen item ;
    int respuestaseleccionada= -1;
    View rootView ;
    Pregunta mPregunta ;
    Integer respuestadada ;
    ArrayList<ItemSeleccionImagen> listaimagenes;
    Integer [] arreglos = {
            R.array.selecciona_imagenes_1 ,
            R.array.selecciona_imagenes_2
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Bundle argumentos = getArguments() ;
        mPregunta = argumentos.getParcelable("pregunta") ;
        respuestadada = new Integer(-1);
        rootView = inflater.inflate(R.layout.vista_seleccion_imagenes,container,false);

        TypedArray imagenes = getResources().obtainTypedArray(arreglos[Integer.parseInt(mPregunta.getmRespuestas().get(0).toString())]) ;
        listaimagenes = new ArrayList<>() ;
        ItemSeleccionImagen imagen1 = (ItemSeleccionImagen) rootView.findViewById(R.id.seleccion_imagen_id1) ;
        imagen1.setOnClickListener(this);
        imagen1.setImageDrawable((Drawable)imagenes.getDrawable(0) );
        imagen1.setIndicadorposicion(0);
        listaimagenes.add(imagen1) ;
        ItemSeleccionImagen imagen2 = (ItemSeleccionImagen) rootView.findViewById(R.id.seleccion_imagen_id2) ;
        imagen2.setOnClickListener(this);
        imagen2.setImageDrawable((Drawable)imagenes.getDrawable(1) );
        imagen2.setIndicadorposicion(1);
        listaimagenes.add(imagen2) ;
        ItemSeleccionImagen imagen3 = (ItemSeleccionImagen) rootView.findViewById(R.id.seleccion_imagen_id3) ;
        imagen3.setOnClickListener(this);
        imagen3.setImageDrawable((Drawable)imagenes.getDrawable(2) );
        imagen3.setIndicadorposicion(2);
        listaimagenes.add(imagen3) ;
        ItemSeleccionImagen imagen4 = (ItemSeleccionImagen) rootView.findViewById(R.id.seleccion_imagen_id4) ;
        imagen4.setOnClickListener(this);
        imagen4.setImageDrawable((Drawable)imagenes.getDrawable(3) );
        imagen4.setIndicadorposicion(3);
        listaimagenes.add(imagen4) ;
       return rootView ;

    }


    @Override
    Object getRespuestDada() {
        return respuestaseleccionada;
    }

    @Override
    public void onClick(View view) {


        if(view instanceof ItemSeleccionImagen)
        {
            if(respuestaseleccionada!=-1)
                listaimagenes.get(respuestaseleccionada).Tocar(false);
            ((ItemSeleccionImagen)view).Tocar(true);
            respuestaseleccionada = ((ItemSeleccionImagen)view).getIndicadorposicion() ;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
      //  if(respuestaseleccionada!=-1)
        //    listaimagenes.get(respuestaseleccionada).Tocar(false);
       // ((ItemSeleccionImagen)compoundButton.getParent()).Tocar(b);
        respuestaseleccionada = ((ItemSeleccionImagen)compoundButton.getParent()).getIndicadorposicion() ;
    Log.d("g", respuestaseleccionada + "") ;
    }



}
