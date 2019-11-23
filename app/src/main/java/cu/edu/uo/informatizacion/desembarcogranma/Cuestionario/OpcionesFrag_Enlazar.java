package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;


import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Gigabyte on 12/10/2018.
 */

public class OpcionesFrag_Enlazar extends FragmentPadre
    implements View.OnDragListener
  {

    Pregunta mPregunta ;
      private ArrayList<ItemSeleccion> itemsrespuestas = new ArrayList() ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mPregunta = getArguments().getParcelable("pregunta") ;
        container.setPadding(4,20,4,12);

        LinearLayout vRaiz = new LinearLayout(getContext()) ;
        vRaiz.setOrientation(LinearLayout.VERTICAL);
        vRaiz.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));

        for(Object resp : mPregunta.getmRespuestas()){
            ItemSeleccion posibresp = new ItemSeleccion(getContext()) ;
            posibresp.texto_opcion.setText(resp.toString() );
           posibresp.indicador.setText("?");
           posibresp.setOnDragListener(this);
                vRaiz.addView(posibresp);
            itemsrespuestas.add(posibresp) ;
            }
        ScrollView deslizar = new ScrollView(getContext()) ;
        deslizar.addView(vRaiz);
       return deslizar ;

    }



      public void setItemsrespuestas(ArrayList<ItemSeleccion> resp) {
         itemsrespuestas = resp;
      }


      @Override
    Object getRespuestDada() {
         StringBuilder respuestadada=new StringBuilder();
        for(int i = 0 ; i < itemsrespuestas.size();i++){
            ItemSeleccion it = itemsrespuestas.get(i) ;
            respuestadada.append(it.indicador.getText().toString()) ;
            respuestadada.append(",") ;
            respuestadada.append(it.texto_opcion.getText().toString()) ;
            respuestadada.append("*") ;
        }
        return respuestadada.toString();
    }


      @Override
      public boolean onDrag(View view, DragEvent dragEvent) {
        int accionrealizada = dragEvent.getAction() ;
        switch (accionrealizada)
        {
            case DragEvent.ACTION_DRAG_ENTERED:
            {
                ((ItemSeleccion)view).Seleccionar();
                break;
            }
            case DragEvent.ACTION_DRAG_EXITED:
            {
                ((ItemSeleccion)view).DesSeleccionar();
                break;
            }
            case DragEvent.ACTION_DROP:{
                ClipData.Item item = dragEvent.getClipData().getItemAt(0);
                String dragData = item.getText().toString();
                ((ItemSeleccion)view).indicador.setText(dragData);
                ((ItemSeleccion)view).DesSeleccionar();
                break;
            }
        }
          return true;
      }
  }
