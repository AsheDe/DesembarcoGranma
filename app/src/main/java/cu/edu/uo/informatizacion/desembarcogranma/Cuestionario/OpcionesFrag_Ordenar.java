package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;


import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

import cu.edu.uo.informatizacion.desembarcogranma.R;
import cu.edu.uo.informatizacion.desembarcogranma.Utilidades;

/**
 * Created by Gigabyte on 12/10/2018.
 */

public class OpcionesFrag_Ordenar extends FragmentPadre
        implements View.OnLongClickListener , View.OnDragListener

{
      ItemOrdenar draggin ;
      LinearLayout rootView ;
      Pregunta mPregunta ;
      ArrayList<ItemOrdenar> listitems = new ArrayList<>() ;

      @Nullable
      @Override
      public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

          Bundle argumentos = getArguments() ;
          mPregunta = argumentos.getParcelable("pregunta") ;
          Integer respuestadada = new Integer(-1);
          ScrollView scroll = new ScrollView(getContext()) ;
          rootView = new LinearLayout(getContext()) ;
          rootView.setVerticalGravity(Gravity.CENTER_VERTICAL);
          rootView.setOrientation(LinearLayout.VERTICAL);

          rootView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

          for(int i = 0 ; i < mPregunta.getmRespuestas().size() ; i++ )
          {
              ItemOrdenar item  = new ItemOrdenar(getContext()) ;
              String opcion = mPregunta.getmRespuestas().get(i).toString() ;
              item.setText(opcion ) ;
              item.setIndicador(i); ;

              item.setOnLongClickListener(this);
              item.setOnDragListener(this);
              listitems.add(item) ;
              rootView.addView(item);
          }

          scroll.addView(rootView);

          return scroll ;

      }


      @Override
      Object getRespuestDada() {
         StringBuilder respuesta = new StringBuilder() ;
          for (ItemOrdenar it : listitems)
          {
            respuesta.append(it.getText()) ;
            respuesta.append("*")  ;
          }

          return respuesta.toString();
      }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {

          int accion = dragEvent.getAction() ;
          switch (accion)
          {
              case DragEvent.ACTION_DRAG_STARTED:{
                 // draggin = (ItemOrdenar) view  ;
                  break;
              }

              case DragEvent.ACTION_DRAG_ENTERED:{
                Utilidades.CambiardeColor(R.color.colorPrimary,((ItemOrdenar)view).getContenedor());
                  break;
              }
              case DragEvent.ACTION_DRAG_EXITED:{
                  Utilidades.CambiardeColor(R.color.blanco,((ItemOrdenar)view).getContenedor());
                  break;
              }

              case DragEvent.ACTION_DROP:{

                  String almacentext = ((ItemOrdenar)view).getText() ;
                  int almacenpos = ((ItemOrdenar)view).getIndicadorPos() ;


                  ClipData.Item texto = dragEvent.getClipData().getItemAt(0);
                  ClipData.Item posicion = dragEvent.getClipData().getItemAt(1);
                  String dragData = texto.getText().toString();
                  int posic = Integer.parseInt(posicion.getText().toString()) ;
                  ((ItemOrdenar)view).setText(dragData);
                 ((ItemOrdenar)view).setIndicador(posic);
                 int where = Integer.parseInt( dragEvent.getClipData().getItemAt(2).getText().toString() ) ;
                 listitems.get(where).setText(almacentext);
                  listitems.get(where).setIndicador(almacenpos);
                  Utilidades.CambiardeColor(R.color.blanco ,((ItemOrdenar)view).getContenedor());
                  Utilidades.CambiardeColor(R.color.blanco ,listitems.get(where).getContenedor());
                      break;
              }
          }

        return true;
    }

    @Override
    public boolean onLongClick(View view) {
        ClipData.Item text = new ClipData.Item(((ItemOrdenar)view).getText());
        ClipData.Item position = new ClipData.Item(((ItemOrdenar)view).getIndicadorPos()+"");
        ClipData.Item indice = new ClipData.Item(listitems.indexOf(view)+"");
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(((ItemOrdenar)view).getText(), mimeTypes, text);
        data.addItem(position);
        data.addItem(indice);
       Utilidades.CambiardeColor(R.color.verde,((ItemOrdenar) view).getContenedor());
        // Instantiates the drag shadow builder.
        View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(view);

        // Starts the drag
        view.startDrag(data       // data to be dragged
                , dragshadow  // drag shadow
                , view            // local data about the drag and drop operation
                , 0          // flags set to 0 because not using currently
        );

        return true;
    }
}
