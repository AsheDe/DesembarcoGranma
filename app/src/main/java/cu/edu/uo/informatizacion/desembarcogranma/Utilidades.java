package cu.edu.uo.informatizacion.desembarcogranma;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;

import cu.edu.uo.informatizacion.desembarcogranma.Cuestionario.Cuestionario;

/**
 * Created by Gigabyte on 03/10/2018.
 */

public class Utilidades {

    static public void FullScreen(AppCompatActivity actividad)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                actividad.getWindow().setStatusBarColor(Color.TRANSPARENT);
                actividad.getWindow()
                        .getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                actividad.getWindow()
                        .setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    static public void DrawerLayout(MenuItem item, Context contexto, DrawerLayout drawer){

        int id = item.getItemId();

       switch (id) {
           case R.id.temas: {
               contexto.startActivity(new Intent(contexto, TEMAS.class));
               break;
           }
           case R.id.cuestionario: {
               contexto.startActivity(new Intent(contexto, Cuestionario.class));
               break;
           }
           case R.id.figuras: {
               contexto.startActivity(new Intent(contexto, PersonajeListActivity.class));
               break;
           }
           case R.id.creditos: {
               AlertDialog.Builder credits = new AlertDialog.Builder(contexto);
                        ImageView img = new ImageView(contexto) ;
               credits.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                   }
               })
                       .setTitle("CRÉDITOS")
                       .setMessage(contexto.getResources().getString(R.string.textocreditos));
               img.setImageDrawable(contexto.getResources().getDrawable(R.mipmap.logo_uo_original));
                        credits.setView(img);
               credits.create().show();
               break;
           }
           case R.id.ayuda: {
               contexto.startActivity(new Intent(contexto, Ayuda.class));
               break;
           }
       }

        drawer.closeDrawer(GravityCompat.START);
    }

    static public void CambiardeColor(Integer color, View vista)
    {
        vista.setBackgroundResource(color);
    }

    static public ArrayList<FiguraHistorica> ListadoFiguras(Context mContext)
    {
        ArrayList<FiguraHistorica> figurahist = new ArrayList();
        TypedArray imagenesFiguras = mContext.getResources().obtainTypedArray(R.array.imagenesfiguras);
        for(int i = 0 ; i < imagenesFiguras.length() ; i++ )
        {
            Drawable imagen = imagenesFiguras.getDrawable(i) ;
            String nombre = mContext.getResources().getResourceEntryName( imagenesFiguras.getResourceId(i,R.mipmap.fidel_castro_ruz) );
            String [] partes = nombre.split("_");
            StringBuilder nombrecompleto = new StringBuilder() ;
            for(String parte : partes)
            {
                parte = parte.replaceFirst(parte.substring(0,1),parte.substring(0,1).toUpperCase()) ;
                nombrecompleto.append(parte + " ");

            }
            FiguraHistorica f = new FiguraHistorica(imagen,nombrecompleto.toString()) ;
            figurahist.add(f);
        }
        return figurahist ;
    }


}
