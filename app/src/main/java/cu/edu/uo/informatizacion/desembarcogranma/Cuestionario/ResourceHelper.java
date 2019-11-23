package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;

import android.content.Context;
import android.content.res.TypedArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cu.edu.uo.informatizacion.desembarcogranma.R;

/**
 * Created by Gigabyte on 03/10/2018.
 */

public class ResourceHelper {

    public static List<TypedArray> getMultiTypedArrayAll(Context context, String key) {
        List<TypedArray> array = new ArrayList<>();

        try {
            Class<R.array> res = R.array.class;
            Field field;
            int counter = 1;

            do {
                field = res.getField(key + "_" + counter);
                array.add(context.getResources().obtainTypedArray(field.getInt(null)));
                counter++;
            } while (field != null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return array;
        }
    }

    public static List<TypedArray> getMultiTypedArrayPreguntas(Context context) {
        List<TypedArray> array = new ArrayList<>();

        try {
            Class<R.array> res = R.array.class;
            Field field;
            int counter = 1;

            do {
                field = res.getField("pregunta" + counter);
                array.add(context.getResources().obtainTypedArray(field.getInt(null)));
                counter++;
            } while (field != null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return array;
        }
    }
    public static List<TypedArray> getMultiTypedArrayRespuesta(Context context) {
        List<TypedArray> array = new ArrayList<>();

        try {
            Class<R.array> res = R.array.class;
            Field field;
            int counter = 1;

            do {
                field = res.getField("respuesta" + counter);
                array.add(context.getResources().obtainTypedArray(field.getInt(null)));
                counter++;
            } while (field != null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return array;
        }
    }
}