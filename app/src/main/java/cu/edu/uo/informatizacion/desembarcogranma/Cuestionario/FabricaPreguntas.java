package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.util.Pair;
import android.support.v7.util.AsyncListUtil;
import android.widget.Gallery;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import cu.edu.uo.informatizacion.desembarcogranma.R;

/**
 * Created by Gigabyte on 05/10/2018.
 */

public class FabricaPreguntas {
    private Context contexto;
    private Pregunta mPregunta;
    private ArrayList<Pregunta> todaspreguntas;
    private AssetManager assetManager ;
    public FabricaPreguntas(Context c) {
        contexto = c;
        assetManager = contexto.getAssets() ;
        todaspreguntas = new ArrayList<>() ;

    }

    public ArrayList<Pregunta> CreaPreguntas() {
       CreaPreguntasTipo_Seleccion();
       CreaPreguntasTipo_Seleccion_imagenes();
       CreaPreguntasTipoSeleccion_multiple();
       CreaPreguntasTip_Acomoda_texto();
       CreaPreguntasTipoOrdenar();
       CreaPreguntasTipo_Enlazar();
        return todaspreguntas;
    }

    private Element getRootElement(String filexml){
        Element root = null ;
        try {
            InputStream is = assetManager.open(filexml);
            DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(is);
            root = dom.getDocumentElement();

        }
        catch (Exception e)
        {
        }
        finally {
            return root ;
        }
    }

    public void CreaPreguntasTipo_Seleccion() {
        Element raiz = getRootElement("seleccion.xml") ;
        NodeList nodos  = raiz.getElementsByTagName("preguntaseleccion") ;
        for(int  i = 0 ; i < nodos.getLength() ; i++)
        {
            NodeList preguntafull = nodos.item(i).getChildNodes() ;
            Pregunta actual = new Pregunta() ;
            actual.setmTipoPregunta(TipoPregunta.SELECCIONA);
            int indice = 0 ;

            while(indice < preguntafull.getLength()) {
                String newline = System.getProperty("line.separator") ;
                if (preguntafull.item(indice).getTextContent().startsWith(newline))
                {
                    indice++;
                }
                else {
                  String tag =  preguntafull.item(indice).getNodeName();
                    switch (tag) {
                        case "tiempo": {
                            // crear tiempo
                            // Log.d( "TAG : tiempo - " , "t="+  preguntafull.item(indice).getTextContent() + " seg " ) ;
                            actual.setmTiempo(Integer.parseInt(preguntafull.item(indice).getTextContent()));
                            break;
                        }
                        case "pregunta": {
                            /// crear preguntas
                                actual.getmPregunta().add(preguntafull.item(indice).getTextContent());
                            break;
                        }
                        case "respuesta": {
                            /// crear respuesta
                            actual.getmRespuestas().add(preguntafull.item(indice).getTextContent());
                            break;
                        }
                        case "correcta": {
                            ///  respuesta correcta
                            actual.setmCorrecta(preguntafull.item(indice).getTextContent());
                            break;
                        }
                    }
                    indice++;
                }
            }
        todaspreguntas.add(actual);
        }
    }


    private void CreaPreguntasTipo_Seleccion_imagenes()
    {
        Element raiz = getRootElement("seleccion_imagenes.xml") ;
        NodeList nodos  = raiz.getElementsByTagName("preguntaseleccionimagenes") ;
        for(int  i = 0 ; i < nodos.getLength() ; i++)
        {
            NodeList preguntafull = nodos.item(i).getChildNodes() ;
            Pregunta actual = new Pregunta() ;
            actual.setmTipoPregunta(TipoPregunta.SELECCIONA_IMAGEN);
            int indice = 0 ;

            while(indice < preguntafull.getLength()) {
                String newline = System.getProperty("line.separator") ;
                if (preguntafull.item(indice).getTextContent().startsWith(newline))
                {
                    indice++;
                }
                else {
                    String tag =  preguntafull.item(indice).getNodeName();
                    switch (tag) {
                        case "tiempo": {
                            // crear tiempo
                             actual.setmTiempo(Integer.parseInt(preguntafull.item(indice).getTextContent()));
                            break;
                        }
                        case "pregunta": {
                            /// crear preguntas
                            actual.getmPregunta().add(preguntafull.item(indice).getTextContent());
                            break;
                        }
                        case "respuesta": {
                            /// crear respuesta

                            int posicion = Integer.parseInt(preguntafull.item(indice).getTextContent()) ;
                            actual.getmRespuestas().add(posicion) ;

                            break;
                        }
                        case "correcta": {
                            ///  respuesta correcta
                            actual.setmCorrecta(preguntafull.item(indice).getTextContent());
                            break;
                        }
                    }
                    indice++;
                }
            }
            todaspreguntas.add(actual);
        }
    }

    private void CreaPreguntasTip_Acomoda_texto()
    {
        Element raiz = getRootElement("acomoda_texto.xml") ;
        NodeList nodos  = raiz.getElementsByTagName("preguntaacomodartexto") ;
        for(int  i = 0 ; i < nodos.getLength() ; i++)
        {
            NodeList preguntafull = nodos.item(i).getChildNodes() ;
            Pregunta actual = new Pregunta() ;
            actual.setmTipoPregunta(TipoPregunta.ACOMODA_TEXTO);
            int indice = 0 ;

            while(indice < preguntafull.getLength()) {
                String newline = System.getProperty("line.separator") ;
                if (preguntafull.item(indice).getTextContent().startsWith(newline))
                {
                    indice++;
                }
                else {
                    String tag =  preguntafull.item(indice).getNodeName();
                    switch (tag) {
                        case "tiempo": {
                            // crear tiempo
                            // Log.d( "TAG : tiempo - " , "t="+  preguntafull.item(indice).getTextContent() + " seg " ) ;
                            actual.setmTiempo(Integer.parseInt(preguntafull.item(indice).getTextContent()));
                            break;
                        }
                        case "pregunta": {
                            /// crear preguntas
                            actual.getmPregunta().add(preguntafull.item(indice).getTextContent());
                            break;
                        }
                        case "respuesta": {
                            /// crear respuesta
                            actual.getmRespuestas().add(preguntafull.item(indice).getTextContent());
                            break;
                        }
                        case "correcta": {
                            /// crear respuesta
                            actual.setmCorrecta(preguntafull.item(indice).getTextContent());
                            break;
                        }
                    }
                    indice++;
                }
            }
            todaspreguntas.add(actual);
        }
    }

    private void CreaPreguntasTipoSeleccion_multiple()
    {
        Element raiz = getRootElement("seleccion_multiple.xml") ;
        NodeList nodos  = raiz.getElementsByTagName("preguntaseleccionmultiple") ;
        for(int  i = 0 ; i < nodos.getLength() ; i++) {
            NodeList preguntafull = nodos.item(i).getChildNodes();
            Pregunta actual = new Pregunta();
            actual.setmTipoPregunta(TipoPregunta.SELECCION_MULTIPLE);
            int indice = 0;

            while (indice < preguntafull.getLength()) {
                String newline = System.getProperty("line.separator");
                if (preguntafull.item(indice).getTextContent().startsWith(newline)) {
                    indice++;
                } else {
                    String tag = preguntafull.item(indice).getNodeName();
                    switch (tag) {
                        case "tiempo": {
                            // crear tiempo
                            // Log.d( "TAG : tiempo - " , "t="+  preguntafull.item(indice).getTextContent() + " seg " ) ;
                            actual.setmTiempo(Integer.parseInt(preguntafull.item(indice).getTextContent()));
                            break;
                        }
                        case "pregunta": {
                            /// crear preguntas
                            actual.getmPregunta().add(preguntafull.item(indice).getTextContent());
                            break;
                        }
                        case "respuesta": {
                            /// crear respuesta
                            actual.getmRespuestas().add(preguntafull.item(indice).getTextContent());
                            break;
                        }
                        case "correcta": {
                            ///  respuesta correcta
                            actual.setmCorrecta(preguntafull.item(indice).getTextContent());
                            break;
                        }
                    }
                    indice++;
                }
            }
            todaspreguntas.add(actual);
        }
    }

    private void CreaPreguntasTipoOrdenar() {

        Element raiz = getRootElement("ordenar.xml");
        NodeList nodos = raiz.getElementsByTagName("preguntaordenar");
        for (int i = 0; i < nodos.getLength(); i++) {
            NodeList preguntafull = nodos.item(i).getChildNodes();
            Pregunta actual = new Pregunta();
            actual.setmTipoPregunta(TipoPregunta.ORDENAR);
            int indice = 0;

            while (indice < preguntafull.getLength()) {
                String newline = System.getProperty("line.separator");
                if (preguntafull.item(indice).getTextContent().startsWith(newline)) {
                    indice++;
                } else {
                    String tag = preguntafull.item(indice).getNodeName();
                    switch (tag) {
                        case "tiempo": {
                            // crear tiempo
                            // Log.d( "TAG : tiempo - " , "t="+  preguntafull.item(indice).getTextContent() + " seg " ) ;
                            actual.setmTiempo(Integer.parseInt(preguntafull.item(indice).getTextContent()));
                            break;
                        }
                        case "pregunta": {
                            /// crear preguntas
                            actual.getmPregunta().add(preguntafull.item(indice).getTextContent());
                            break;
                        }
                        case "respuesta": {
                            /// crear respuesta
                            actual.getmRespuestas().add(preguntafull.item(indice).getTextContent());
                            break;
                        }
                        case "correcta": {
                            /// crear respuesta
                            actual.setmCorrecta(preguntafull.item(indice).getTextContent());
                            break;
                        }
                    }
                    indice++;
                }
            }
            todaspreguntas.add(actual);
        }

    }



    private void CreaPreguntasTipo_Enlazar()
    {

        Element raiz = getRootElement("enlazar.xml");
        NodeList nodos = raiz.getElementsByTagName("preguntaenlazar");
        for (int i = 0; i < nodos.getLength(); i++) {
            NodeList preguntafull = nodos.item(i).getChildNodes();
            Pregunta actual = new Pregunta();
            actual.setmTipoPregunta(TipoPregunta.ENLAZA);
            int indice = 0;

            while (indice < preguntafull.getLength()) {
                String newline = System.getProperty("line.separator");
                if (preguntafull.item(indice).getTextContent().startsWith(newline)) {
                    indice++;
                } else {
                    String tag = preguntafull.item(indice).getNodeName();
                    switch (tag) {
                        case "tiempo": {
                            // crear tiempo
                            // Log.d( "TAG : tiempo - " , "t="+  preguntafull.item(indice).getTextContent() + " seg " ) ;
                            actual.setmTiempo(Integer.parseInt(preguntafull.item(indice).getTextContent()));
                            break;
                        }
                        case "pregunta": {
                            /// crear preguntas
                            actual.getmPregunta().add(preguntafull.item(indice).getTextContent());
                            break;
                        }
                        case "respuesta": {
                            /// crear respuesta
                            actual.getmRespuestas().add(preguntafull.item(indice).getTextContent());
                            break;
                        }
                        case "correcta": {
                            /// crear respuesta
                            String correct = preguntafull.item(indice).getTextContent() ;
                            actual.setmCorrecta(correct);
                            break;
                        }
                    }
                    indice++;
                }
            }
            todaspreguntas.add(actual);
        }
    }
}
