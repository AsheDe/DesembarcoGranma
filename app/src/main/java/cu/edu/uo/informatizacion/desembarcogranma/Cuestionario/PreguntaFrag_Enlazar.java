package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v4.app.Fragment;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.util.TypedValue;
import android.view.Display;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cu.edu.uo.informatizacion.desembarcogranma.R;

/**
 * Created by Gigabyte on 25/10/2018.
 */

public class PreguntaFrag_Enlazar extends Fragment
{

    public ArrayList<ItemAcomodatextoRespuestas> getListapreguntas() {
        return listapreguntas;
    }



    private ArrayList<ItemAcomodatextoRespuestas> listapreguntas = new ArrayList<>() ;
    private  LinearLayout vraiz ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        vraiz = new LinearLayout(getContext());
        vraiz.setOrientation(LinearLayout.VERTICAL);
        vraiz.setHorizontalGravity(Gravity.CENTER);
        Pregunta pregunta = getArguments().getParcelable("pregunta");
        String textpregunta = pregunta.getmPregunta().get(0).toString();
        TextView cont = new TextView(getContext());
        cont.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        cont.setText(textpregunta);
        cont.setTextSize(TypedValue.COMPLEX_UNIT_SP,getResources().getInteger(R.integer.letras_cuestionario_float));
        //TODO asignar tama;o de texto por SP cont.setTextSize();
        vraiz.addView(cont);

    /*    GridLayout grilla = new GridLayout(getContext()) ;

        grilla.setLayoutParams(new ViewGroup.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        grilla.setUseDefaultMargins(true);
        grilla.setColumnCount(3); */

        ChipGroup grilla = new ChipGroup(getContext()) ;
        grilla.setLayoutParams(new ChipGroup.LayoutParams(ChipGroup.LayoutParams.MATCH_PARENT,ChipGroup.LayoutParams.MATCH_PARENT));

        ArrayList preguntas = pregunta.getmPregunta() ;
        for (int i = 1; i < preguntas.size(); i++) {
            Object resp = preguntas.get(i) ;
            Chip posibresp = new Chip(getContext()) ;
            posibresp.setText(resp.toString() );
            posibresp.setChipBackgroundColorResource( R.color.colorPrimary);
            posibresp.setTextColor(getContext().getResources().getColor( R.color.blanco));
            posibresp.setOnDragListener(myDragger);
            posibresp.setOnLongClickListener(lonclick);
            grilla.addView(posibresp);

        }


      /*  for (int i = 1; i < pregunta.getmPregunta().size(); i++) {

                    ItemAcomodatextoRespuestas blankspace = new ItemAcomodatextoRespuestas(getContext(), true);
                    blankspace.setText(pregunta.getmPregunta().get(i).toString());
                    grilla.addView(blankspace);
                    listapreguntas.add(blankspace);

            }*/

        vraiz.addView(grilla);

        return vraiz;
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
