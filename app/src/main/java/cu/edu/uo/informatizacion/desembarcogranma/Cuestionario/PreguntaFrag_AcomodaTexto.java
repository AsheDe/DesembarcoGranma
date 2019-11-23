package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cu.edu.uo.informatizacion.desembarcogranma.R;

/**
 * Created by Gigabyte on 25/10/2018.
 */

public class PreguntaFrag_AcomodaTexto extends Fragment
{

    public ArrayList<ItemAcomodatextoRespuestas> getListapreguntas() {
        return listapreguntas;
    }



    private ArrayList<ItemAcomodatextoRespuestas> listapreguntas = new ArrayList<>() ;
    private  LinearLayout vraiz ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // View vistapregunta = inflater.inflate(R.layout.vista_pregunta_2,container,false) ;

        vraiz = new LinearLayout(getContext());
        vraiz.setOrientation(LinearLayout.VERTICAL);
        Pregunta pregunta = getArguments().getParcelable("pregunta");
        String textpregunta = pregunta.getmPregunta().get(0).toString();


        String buffer = "";
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int widthscreen = display.getWidth();
        LinearLayout linea = new LinearLayout(getContext());
        linea.setOrientation(LinearLayout.HORIZONTAL);
        linea.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        for (int i = 0; i < textpregunta.length(); i++) {
            if (textpregunta.charAt(i) == '_' || i == textpregunta.length()-1) {
                int lineaancho = linea.getMeasuredWidth();
                int anchotext = 0;
                if (buffer.length() > 0) {

                    TextView cont = new TextView(getContext());
                    cont.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    cont.setText(buffer);
                    cont.setTextSize(TypedValue.COMPLEX_UNIT_SP,getResources().getInteger(R.integer.letras_cuestionario_float));
                    cont.setLines(1);
                    cont.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    linea.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    anchotext = cont.getMeasuredWidth();
                    lineaancho = linea.getMeasuredWidth() ;
                    if (lineaancho + anchotext < widthscreen)
                    {
                        linea.addView(cont);
                    }
                    else {
                        vraiz.addView(linea);
                        linea = new LinearLayout(getContext());
                        linea.setOrientation(LinearLayout.HORIZONTAL);
                        linea.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linea.addView(cont);
                    }
                }

                if(i != textpregunta.length()-1)
                {
                    ItemAcomodatextoRespuestas blankspace = new ItemAcomodatextoRespuestas(getContext(), true);
                   // blankspace.setOnDragListener(blankspace.myDragger);
                    linea.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lineaancho = linea.getMeasuredWidth();
                    blankspace.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    anchotext = blankspace.getMeasuredWidth();
                    if (lineaancho + anchotext < widthscreen) {
                        blankspace.setPregunta(true);
                        linea.addView(blankspace);
                        listapreguntas.add(blankspace);
                    } else {
                        vraiz.addView(linea);
                        linea = new LinearLayout(getContext());
                        linea.setOrientation(LinearLayout.HORIZONTAL);
                        linea.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        blankspace = new ItemAcomodatextoRespuestas(getContext(), true);
                        //blankspace.setOnDragListener(blankspace.myDragger);
                        linea.addView(blankspace);
                        listapreguntas.add(blankspace);
                    }

                }
                buffer = "";

            }

            else
            {
                buffer+= textpregunta.charAt(i) ;
            }
        }
        vraiz.addView(linea);

        return vraiz;
    }




}
