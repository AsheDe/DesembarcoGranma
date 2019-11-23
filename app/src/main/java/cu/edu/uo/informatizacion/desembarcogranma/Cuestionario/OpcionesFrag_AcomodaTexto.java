package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;


import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cu.edu.uo.informatizacion.desembarcogranma.R;
import cu.edu.uo.informatizacion.desembarcogranma.Utilidades;

import java.util.ArrayList;

/**
 * Created by Gigabyte on 12/10/2018.
 */

public class OpcionesFrag_AcomodaTexto extends FragmentPadre
  {

    Pregunta mPregunta ;
      private ArrayList<ItemAcomodatextoRespuestas> itemsrespuestas = new ArrayList() ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mPregunta = getArguments().getParcelable("pregunta") ;
        container.setPadding(4,64,4,12);
        ChipGroup vRaiz = new ChipGroup(getContext()) ;
        vRaiz.setLayoutParams(new ChipGroup.LayoutParams(ChipGroup.LayoutParams.MATCH_PARENT,ChipGroup.LayoutParams.MATCH_PARENT));

        for(Object resp : mPregunta.getmRespuestas()){
            Chip posibresp = new Chip(getContext()) ;
            posibresp.setText(resp.toString() );
            posibresp.setChipBackgroundColorResource( R.color.colorPrimary);
            posibresp.setTextColor(getContext().getResources().getColor( R.color.blanco));
            posibresp.setOnDragListener(myDragger);
            posibresp.setOnLongClickListener(lonclick);
            vRaiz.addView(posibresp);

        }

       return vRaiz ;

    }


      public void setItemsrespuestas(ArrayList<ItemAcomodatextoRespuestas> resp) {
         itemsrespuestas = resp;
      }


      @Override
    Object getRespuestDada() {
      String  respuestadada="";
        for(int i = 0 ; i < itemsrespuestas.size();i++){
            ItemAcomodatextoRespuestas it = itemsrespuestas.get(i) ;
            respuestadada += it.getText().toString() ;
            respuestadada+="*" ;
        }
        return respuestadada;
    }

      public View.OnLongClickListener lonclick = new View.OnLongClickListener(){
          @Override
          public boolean onLongClick(View view) {

              ClipData.Item item = new ClipData.Item(((Chip)view).getText());
              String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
              ClipData data = new ClipData(((Chip)view).getText(), mimeTypes, item);

              ((Chip)view).setChipBackgroundColorResource( R.color.verde);
              //Utilidades.CambiardeColor(R.color.verde,contenedor);
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
      } ;

      public View.OnDragListener myDragger = new View.OnDragListener(){

          @Override
          public boolean onDrag(View view, DragEvent dragEvent) {
              int action = dragEvent.getAction();
              switch (action)
              {
                  case DragEvent.ACTION_DRAG_ENTERED :
                  {
                      ((Chip)view).setChipBackgroundColorResource(R.color.colorRojo);
                      break;
                  }
                  case DragEvent.ACTION_DRAG_EXITED :
                  {
                      ((Chip)view).setChipBackgroundColorResource(R.color.colorPrimary);
                      break;
                  }
                  case DragEvent.ACTION_DROP :
                  {

                          Toast.makeText(getContext(),"Debes arrastrar hasta las opciones" , Toast.LENGTH_LONG).show();
                      ((Chip)view).setChipBackgroundColorResource(R.color.colorPrimary);

                      break;
                  }

                  case DragEvent.ACTION_DRAG_ENDED :
                  {
                      //  view.setVisibility(INVISIBLE);
                      break;
                  }


              }

              return true;
          }
      };




  }
