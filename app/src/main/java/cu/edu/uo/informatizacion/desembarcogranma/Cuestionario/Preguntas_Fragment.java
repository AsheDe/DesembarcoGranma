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

public class Preguntas_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.vista_pregunta_1, container, false) ;
/// configurar segun pregunta
        // Show the dummy content as text in a TextView.

       // String [] opciones = getResources().getStringArray(getArguments().getInt("opciones")) ;

        /*rootView.findViewById<TextView>(R.id.textrespuestaB).text = opciones[0]
        rootView.findViewById<TextView>(R.id.textrespuestaB).text = opciones[1]
        rootView.findViewById<TextView>(R.id.textrespuestaB).text = opciones[2]*/
        return rootView ;
    }
}
