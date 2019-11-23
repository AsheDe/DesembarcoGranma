package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import cu.edu.uo.informatizacion.desembarcogranma.R;

/**
 * Created by Gigabyte on 25/10/2018.
 */

public class ItemSeleccionImagen extends CoordinatorLayout
{
    private  CheckBox indicador ;
    private ImageView imagen_opcion;
    private CardView contenedor ;
    //int width = 0;
    //int height = 0 ;
    //FrameLayout.LayoutParams parametrosoriginales ;
    private int indicadorposicion = 0 ;
    public ItemSeleccionImagen(Context context) {
        super(context);
        ((LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_seleccionar_imagen, this, true);
        indicador = (CheckBox) findViewById(R.id.indicador_respuesta) ;
        imagen_opcion = (ImageView) findViewById(R.id.imagenrespuestaseleccion) ;
        contenedor = (CardView) findViewById(R.id.seleccionCard) ;
        indicador.setChecked(false);
        //parametrosoriginales = (FrameLayout.LayoutParams) imagen_opcion.getLayoutParams() ;
    }

    public ItemSeleccionImagen(Context context, AttributeSet attrs) {
        super(context, attrs);
        ((LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_seleccionar_imagen, this, true);
        indicador = (CheckBox) findViewById(R.id.indicador_respuesta) ;
        imagen_opcion = (ImageView) findViewById(R.id.imagenrespuestaseleccion) ;
        contenedor = (CardView) findViewById(R.id.seleccionCard) ;
        indicador.setChecked(false);
        //parametrosoriginales = (FrameLayout.LayoutParams) imagen_opcion.getLayoutParams() ;
    }


    public void Tocar(boolean metoco) {

            indicador.setChecked(metoco);

        }

       public void setImageDrawable(Drawable imagen){
        imagen_opcion.setImageDrawable(imagen);
       }


    public int getIndicadorposicion() {
        return indicadorposicion;
    }

    public void setIndicadorposicion(int indicadorposicion) {
        this.indicadorposicion = indicadorposicion;
    }
}
