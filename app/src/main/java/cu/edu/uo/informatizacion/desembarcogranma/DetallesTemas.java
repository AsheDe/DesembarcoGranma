package cu.edu.uo.informatizacion.desembarcogranma;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetallesTemas extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String THEME_NUMBER = "tema";

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public ViewPager mViewPager;

    @Override
    public void onBackPressed() {
        if(mViewPager.getCurrentItem() == 1)
            mViewPager.setCurrentItem(0);
        else
            super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_detalles_temas);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        int tema = getIntent().getIntExtra("tema",0) ;
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),tema);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        if(getIntent().hasExtra(ARG_SECTION_NUMBER))
            mViewPager.setCurrentItem(1);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(this,"Deslice a la izquierda para ver las imágenes",Toast.LENGTH_LONG).show();
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        String [] Tmas ;
        int posiciongeneraltema ;

        public SectionsPagerAdapter(FragmentManager fm, int posgral)
        {
            super(fm);
            posiciongeneraltema = posgral ;

        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Tmas = getResources().getStringArray(R.array.temas) ;

            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, position);
            args.putInt(THEME_NUMBER, posiciongeneraltema);
            fragment.setArguments(args);


            return fragment;
        }



        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Tmas[posiciongeneraltema];
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        public PlaceholderFragment() {
        }



        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            int vista = getArguments().getInt(ARG_SECTION_NUMBER) ;
            TypedArray decripciones = getResources().obtainTypedArray(R.array.textos_detalles_temas) ;
            int numtema = getArguments().getInt(THEME_NUMBER) ;
            Integer [] arregloimagenes =   {
                    R.array.imagenesantecedentes ,
                    R.array.imagenespreparacion ,
                    R.array.imagenesexpedicion ,
                    R.array.imagenescubaespera ,
                    R.array.imagenestravesia ,
                    R.array.imagenesheroico ,
                    R.array.imagenessignificado
                    } ;
            View rootView ;
            String[] nombretemas = getResources().getStringArray(R.array.temas) ;
            TypedArray imagenescorresp = getResources().obtainTypedArray(arregloimagenes[numtema]) ;
            if (vista==0)
            {
                rootView = inflater.inflate(R.layout.detalletemavistamain, container, false);

                 Toolbar  mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
                ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(nombretemas[numtema]);

                TextView textView = (TextView) rootView.findViewById(R.id.textodetalletema);
                String textotema = decripciones.getString(numtema) ;
                textView.setText(textotema);

                CollapsingToolbarLayout collapse = (CollapsingToolbarLayout) rootView.findViewById(R.id.toolbar_layout);
                  //  ImageView flecha = rootView.findViewById(R.id.flechasgte);


                collapse.setBackgroundDrawable(imagenescorresp.getDrawable(0));
                collapse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(container instanceof ViewPager)
                             ((ViewPager) container).setCurrentItem(1);
                    }
                });

                rootView.findViewById(R.id.simboloatras).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getContext(),TEMAS.class));
                    }
                });


                }
                else {
                    rootView = inflater.inflate(R.layout.detallestemasimagenes, container, false);
                Toolbar mToolbar= (Toolbar) rootView.findViewById(R.id.toolbar);
                ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(nombretemas[numtema]);
                RecyclerView listaimagenes = (RecyclerView) rootView.findViewById(R.id.lista_imagenes_tema);
                int columnas = getResources().getInteger(R.integer.numcolumnastema) ;
                listaimagenes.setLayoutManager(new StaggeredGridLayoutManager(columnas,StaggeredGridLayoutManager.VERTICAL));
                listaimagenes.setAdapter(new Adaptador_Imagenes_Tema(imagenescorresp,numtema));


                }
            return rootView;
            }




    }



}
