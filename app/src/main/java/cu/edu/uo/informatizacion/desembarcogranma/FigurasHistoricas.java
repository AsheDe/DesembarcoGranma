package cu.edu.uo.informatizacion.desembarcogranma;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gigabyte on 04/10/2018.
 */

public class FigurasHistoricas extends AsyncTask {


    private ArrayList<FiguraHistorica> todasFiguras;
    private Context mContexto ;

    public FigurasHistoricas(Context contexto) {
          mContexto = contexto ;
          todasFiguras = new ArrayList() ;
    }

    public  List<FiguraHistorica> getTodasFiguras() {
        return todasFiguras;
    }


    @Override
    protected Object doInBackground(Object[] params) {

        TypedArray  imagenesFiguras = mContexto.getResources().obtainTypedArray(R.array.imagenesfiguras);
        for(int i = 0 ; i < imagenesFiguras.length() ; i++ )
        {
            Drawable imagen = imagenesFiguras.getDrawable(i) ;
            String nombre = mContexto.getResources().getResourceEntryName( imagenesFiguras.getResourceId(i,R.mipmap.fidel) );
            String [] partes = nombre.split("_");
            StringBuilder nombrecompleto = new StringBuilder() ;
            for(String parte : partes)
            {
                parte = parte.replaceFirst(parte.substring(0,1),parte.substring(0,1).toUpperCase()) ;
                nombrecompleto.append(parte + " ");

            }
            FiguraHistorica f = new FiguraHistorica(imagen,nombrecompleto.toString()) ;
            todasFiguras.add(f);
        }

        return todasFiguras;
    }

    @Override
    protected void onPostExecute(Object o) {

    }
}
