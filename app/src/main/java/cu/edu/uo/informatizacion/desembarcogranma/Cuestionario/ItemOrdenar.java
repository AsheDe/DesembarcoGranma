package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cu.edu.uo.informatizacion.desembarcogranma.R;

/**
 * Created by Gigabyte on 25/10/2018.
 */

public class ItemOrdenar extends CoordinatorLayout
{
    private TextView indicador ;
    private TextView texto_opcion;
    private CardView contenedor ;
   private int indicadorpos = 0;

    public ItemOrdenar(Context context) {
        super(context);
        ((LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_seleccionar, this, true);
        indicador = (TextView) findViewById(R.id.indicador_respuesta) ;
        texto_opcion = (TextView) findViewById(R.id.textrespuestaseleccion) ;
        contenedor = (CardView) findViewById(R.id.seleccionCard) ;

    }

    public String getIndicador() {
        return indicador.getText().toString();
    }
    public int getIndicadorPos() {
        return indicadorpos;
    }

    public void setIndicador(int indicador) {
        this.indicador.setText((indicador+1)+"");
        indicadorpos = indicador ;
    }
    public String getText(){
        return texto_opcion.getText().toString() ;
    }

    public void setText(String texto_opcion) {
        this.texto_opcion.setText(texto_opcion);
    }

    public void Seleccionar() {

            contenedor.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
            texto_opcion.setTextColor(getResources().getColor(R.color.blanco));

        }
    public void DesSeleccionar()  {
           contenedor.setCardBackgroundColor(getResources().getColor(R.color.blanco));
            texto_opcion.setTextColor(getResources().getColor(R.color.negro));

       }

    public CardView getContenedor() {
        return contenedor;
    }
    public void Reemplazar(ItemOrdenar it)
    {
        this.texto_opcion.setText(it.texto_opcion.getText());
        this.indicador.setText(it.getIndicador());
        this.contenedor = it.getContenedor() ;
    }

}
