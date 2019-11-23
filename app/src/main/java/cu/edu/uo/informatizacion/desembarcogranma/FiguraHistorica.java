package cu.edu.uo.informatizacion.desembarcogranma;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gigabyte on 04/10/2018.
 */


public class FiguraHistorica
{

    Drawable imagen;
    String nombre;

    public FiguraHistorica(Drawable imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;

    }
    //--- get y set -/////////////////

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    ////-  Relativo al Parcel -----


}

/*public class FiguraHistorica implements Parcelable
{

    Integer imagen;
    String nombre;

    public FiguraHistorica(Integer imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;

    }
    //--- get y set -/////////////////

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    ////-  Relativo al Parcel -----

    public FiguraHistorica(Parcel figura) {
        this.setNombre(figura.readString());
        this.setImagen(figura.readInt() ) ;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(imagen);
    }

    static public final Parcelable.Creator<FiguraHistorica> CREATOR = new Parcelable.Creator<FiguraHistorica>()
    {
        public FiguraHistorica createFromParcel(Parcel in)
        {
            return new FiguraHistorica(in);
        }
        public FiguraHistorica[] newArray(int size)
        {
            return new FiguraHistorica[size];
        }
    };
}*/
