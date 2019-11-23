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
 * Created by Gigabyte on 25/10/2018.
 */

public class PreguntaFrag_Selecciona extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View vistapregunta = inflater.inflate(R.layout.vista_pregunta_1,container,false) ;
        String texto  = getArguments().getString("pregunta").toString() ;
        ((TextView)vistapregunta.findViewById(R.id.text1)).setText(texto);
        return vistapregunta;
    }


}
