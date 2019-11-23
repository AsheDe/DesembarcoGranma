package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cu.edu.uo.informatizacion.desembarcogranma.R;

/**
 * Created by Gigabyte on 25/10/2018.
 */

public class ItemSeleccion extends CoordinatorLayout

{
    TextView indicador ;
    TextView texto_opcion;
    CardView contenedor ;
    boolean seleccionado = false ;
    int indicadorposicion = 0 ;
    public ItemSeleccion(Context context) {
        super(context);
        ((LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_seleccionar, this, true);
        indicador = (TextView) findViewById(R.id.indicador_respuesta) ;
        texto_opcion = (TextView) findViewById(R.id.textrespuestaseleccion) ;
        contenedor = (CardView) findViewById(R.id.seleccionCard) ;

    }


    public void Seleccionar() {
            contenedor.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
            texto_opcion.setTextColor(getResources().getColor(R.color.blanco));
          seleccionado = true ;
        }
    public void DesSeleccionar()  {
           contenedor.setCardBackgroundColor(getResources().getColor(R.color.blanco));
            texto_opcion.setTextColor(getResources().getColor(R.color.negro));
            seleccionado = false ;
       }

}
