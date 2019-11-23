package cu.edu.uo.informatizacion.desembarcogranma;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ayuda extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_ayuda);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(cambiardepagina);

        TabLayout tabdots = (TabLayout) findViewById(R.id.tabDots) ;
        tabdots.setupWithViewPager(mViewPager,true);
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
         new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {
             PreferenceManager.getDefaultSharedPreferences ((Context)params[0]).edit().putBoolean("vioayuda",true).apply();
            return true;
            }
        }.execute(this) ;

    }

    ViewPager.OnPageChangeListener cambiardepagina = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            toolbar.setTitle(mViewPager.getAdapter().getPageTitle(position));
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    } ;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_TEXT = "section_text";
        private static final String ARG_SECTION_IMAGE = "section_image";
        private static final String ARG_SECTION_TEXT_SIZE = "section_text_size";
        private static final String ARG_SECTION_SEE_IMAGE = "section_image_see";
        private static final String ARG_SECTION_IMAGE_SIZE = "section_image_size";
        public PlaceholderFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(String texto, Integer imagen, boolean textogrande, boolean imagenpeque ) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(ARG_SECTION_TEXT, texto);
            args.putInt(ARG_SECTION_IMAGE, imagen);
            args.putBoolean(ARG_SECTION_TEXT_SIZE, textogrande);
            args.putBoolean(ARG_SECTION_IMAGE_SIZE, imagenpeque);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_ayuda, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText( getArguments().getString(ARG_SECTION_TEXT));
            if(getArguments().getBoolean(ARG_SECTION_TEXT_SIZE))
            {
                textView.setTextSize(44);
                textView.setGravity(Gravity.CENTER);
                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }


           Integer imag = getArguments().getInt(ARG_SECTION_IMAGE) ;
            if(imag!=0)
            {
                ImageView imagen = (ImageView) rootView.findViewById(R.id.section_image);
                imagen.setImageResource(imag);
                if(getArguments().getBoolean(ARG_SECTION_IMAGE_SIZE) ){
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imagen.getLayoutParams() ;
                   params.width = 160 ;
                   params.height = 160 ;
                   params.gravity = Gravity.CENTER_HORIZONTAL ;
                   params.topMargin = 44 ;
                    imagen.setLayoutParams(params);
                    imagen.setScaleType(ImageView.ScaleType.CENTER_CROP);

                }
            }


            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private String [] titulos;
        private String[] textos;
        private Integer[] imagenes;
        private Boolean[] imagenes_peque;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            titulos = getResources().getStringArray(R.array.titulosayuda) ;
            textos = getResources().getStringArray(R.array.textosayuda) ;
            imagenes = new Integer[] {
                    R.mipmap.logodesembarcoredondo,
                    R.mipmap.btnterminar,
                    0,
                    R.mipmap.ordena,
                    R.mipmap.enlaza,
                    R.mipmap.acomoda,
                    R.mipmap.selecciona,
                    R.mipmap.selecmultiple,
                    R.mipmap.selecimagen,
                    0};
        }




        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(textos[position],imagenes[position],position==0, position==0||position==1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return titulos.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return titulos[position];
        }
    }
}
