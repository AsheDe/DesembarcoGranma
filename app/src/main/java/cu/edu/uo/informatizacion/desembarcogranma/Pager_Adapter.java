package cu.edu.uo.informatizacion.desembarcogranma;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Pager_Adapter extends FragmentPagerAdapter {

    //Context contexto;
    //String[] temas;
    public Pager_Adapter(FragmentManager fm) {
        super(fm);
       // contexto = c ;
       //temas = contexto.getResources().getStringArray(R.array.temas) ;
    }

    @Override
    public int getCount() {
        return 7;
    }



    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new TemaAdapter();
        Bundle b = new Bundle() ;
        b.putInt("tema",position) ;
        fragment.setArguments(b);
        return fragment ;
    }

    public static class TemaAdapter extends Fragment{

        int [] imagenes = {
            R.mipmap.cuartel_moncada ,
                    R.mipmap.practicas_de_tiro ,
                    R.mipmap.yate_granma ,
                    R.mipmap.frank_isaac_pais_garcia ,
                    R.mipmap.travesia_del_yate_granma ,
                    R.mipmap.cinco_palmas ,
                    R.mipmap.legado
        };
        String [] temas ;

        int posicion;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View vista = inflater.inflate(R.layout.fragment_pager,container,false) ;
            temas =  getContext().getResources().getStringArray(R.array.temas) ;
            posicion = getArguments().getInt("tema") ;

            ImageView imagen = (ImageView)  vista.findViewById(R.id.imagen_tema);
            imagen.setImageDrawable( getResources().getDrawable(imagenes[posicion]));

            TextView texto = (TextView) vista.findViewById(R.id.texto_tema);
            texto.setText(temas[posicion]);

            vista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detalles = new Intent(getContext() , DetallesTemas.class) ;
                    detalles.putExtra("tema" , posicion  ) ;
                    startActivity(detalles);
                }
            });

            return vista;

        }


    }


}
