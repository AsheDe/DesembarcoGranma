package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cu.edu.uo.informatizacion.desembarcogranma.R;
import cu.edu.uo.informatizacion.desembarcogranma.Utilidades;

/**
 * Created by Gigabyte on 25/10/2018.
 */

public class ItemAcomodatextoRespuestas extends CoordinatorLayout {
    TextView textopropuesta ;
    CardView contenedor ;

    public boolean isPregunta() {
        return pregunta;
    }

    public void setPregunta(boolean pregunta) {
        this.pregunta = pregunta;
    }

    boolean pregunta = false ;
    public ItemAcomodatextoRespuestas(Context context, boolean ppregunta) {
        super(context);
        Integer xml = (ppregunta) ? R.layout.item_acomodatexto : R.layout.item_acomodatexto_respuesta ;
        ((LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE)).inflate(xml, this, true);
        textopropuesta = (TextView) findViewById(R.id.acomodatextoText) ;
        contenedor = (CardView) findViewById(R.id.acomodatextoCard) ;
        pregunta = ppregunta ;
        setOnDragListener(myDragger);
        setOnLongClickListener(lonclick);
    }
    public void setText(String text)
    {
        textopropuesta.setText(text);
    }
    public CharSequence getText()
    {
        return textopropuesta.getText() ;
    }

    public void RestaurarColor()
    {
        contenedor.setBackgroundResource(R.color.colorPrimary);
    }
    public void RecibirDrop()
    {
        contenedor.setBackgroundResource(R.color.colorAccent);
    }

    public View.OnDragListener myDragger = new View.OnDragListener(){

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int action = dragEvent.getAction();
            switch (action)
            {
                case DragEvent.ACTION_DRAG_ENTERED :
                {
                    if(pregunta)
                        Utilidades.CambiardeColor(R.color.verde,contenedor);
                    else
                        Utilidades.CambiardeColor(R.color.colorRojo,contenedor);
                    break;
                }
                case DragEvent.ACTION_DRAG_EXITED :
                {
                    RestaurarColor();
                    break;
                }
                case DragEvent.ACTION_DROP :
                {
                    if(pregunta)
                    {
                        ClipData.Item item = dragEvent.getClipData().getItemAt(0);
                        String dragData = item.getText().toString();
                        setText(dragData);
                    }
                    else {
                        Toast.makeText(getContext(),"Debes arrastrar hasta las opciones" , Toast.LENGTH_LONG).show();
                        RestaurarColor();
                    }
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


    public View.OnLongClickListener lonclick = new View.OnLongClickListener(){
        @Override
        public boolean onLongClick(View view) {

            ClipData.Item item = new ClipData.Item(getText());
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData data = new ClipData(getText(), mimeTypes, item);

            Utilidades.CambiardeColor(R.color.verde,contenedor);
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
}
