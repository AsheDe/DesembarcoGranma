package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.Pair;
import android.util.Log;


import java.util.ArrayList;

/**
 * Created by Gigabyte on 03/10/2018.
 */

public class Pregunta implements Parcelable {
    private TipoPregunta mTipoPregunta ;
    private ArrayList mPregunta  ;
    private ArrayList mRespuestas  ;
    private Integer mTiempo= 1 ;
    private String mCorrecta;

    public Pregunta(TipoPregunta mTipoPregunta,ArrayList mPregunta, ArrayList mRespuestas, Integer mTiempo, String mCorrecta) {
        this.mTipoPregunta = mTipoPregunta;
        this.mPregunta = mPregunta;
        this.mRespuestas = mRespuestas;
        this.mTiempo = mTiempo;
        this.mCorrecta = mCorrecta ;
    }

    public Pregunta() {
        this.mTipoPregunta = TipoPregunta.SELECCIONA;
        this.mPregunta = new ArrayList();
        this.mRespuestas = new ArrayList();
        this.mTiempo = 30;
        mCorrecta = "" ;
    }

    public String getmCorrecta() {
        return mCorrecta;
    }

    public void setmCorrecta(String mCorrecta) {
        this.mCorrecta = mCorrecta;
    }

    public TipoPregunta getmTipoPregunta() {
        return mTipoPregunta;
    }

    public void setmTipoPregunta(TipoPregunta mTipoPregunta) {
        this.mTipoPregunta = mTipoPregunta;
    }

    public ArrayList getmPregunta() {
        return mPregunta;
    }

    public void setmPregunta(ArrayList mPregunta) {
        this.mPregunta = mPregunta;
    }

    public ArrayList getmRespuestas() {
        return mRespuestas;
    }

    public void setmRespuestas(ArrayList mRespuestas) {
        this.mRespuestas = mRespuestas;
    }

    public Integer getmTiempo() {
        return mTiempo;
    }

    public void setmTiempo(Integer mTiempo) {
        this.mTiempo = mTiempo;
    }

    public boolean Evaluar(Object respuestafinal)
    {
        if(mCorrecta != null)
        switch (mTipoPregunta)
        {
            case SELECCION_MULTIPLE:{
                String correct = mCorrecta.toString() ;
                String answer = respuestafinal.toString() ;
               return correct.contentEquals(answer) ;
               }
            default:{
                return   mCorrecta.toString().equals(respuestafinal.toString()) ;
            }
        }
      return false;
    }

    public boolean EsValida()
    {
         if (mPregunta.isEmpty() || mRespuestas.isEmpty()  )
             return false ;
         return true ;
    }

    ///// relacionado con el parcelable



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mTipoPregunta.ordinal());
        dest.writeInt(mTiempo);
        dest.writeValue(mPregunta);
        dest.writeValue(mRespuestas);
        dest.writeValue(mCorrecta);
    }


    public Pregunta(Parcel parcel) {
        this.mTipoPregunta = TipoPregunta.values()[parcel.readInt()];
        this.mTiempo = parcel.readInt();
        this.mPregunta = (ArrayList) parcel.readValue(ClassLoader.getSystemClassLoader());
        this.mRespuestas = (ArrayList) parcel.readValue(ClassLoader.getSystemClassLoader());
        this.mCorrecta = parcel.readString();

    }

    public static final Creator<Pregunta> CREATOR
            = new Creator<Pregunta>(){

        @Override
        public Pregunta createFromParcel(Parcel source) {
            return new Pregunta(source);
        }

        @Override
        public Pregunta[] newArray(int size) {
            return new Pregunta[0];
        }
    };




}
