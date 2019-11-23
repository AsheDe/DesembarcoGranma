package cu.edu.uo.informatizacion.desembarcogranma;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Personajes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PersonajeDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PersonajeListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    //private boolean mTwoPane;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itempersonaje_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ////////-------------------NAV DRAWER
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        /////////////



        recyclerView  = (RecyclerView) findViewById(R.id.itempersonaje_list);
        assert recyclerView != null;



        /////////// TODO CREAR BUSCADOR PARA LOS HEROES
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        setupRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()== android.R.id.home)
                NavUtils.navigateUpTo( this , new Intent( this , TEMAS.class) );

        return false;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new PersonajesRecyclerViewAdapter(this, this));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(getResources().getInteger(R.integer.numcolumnas), StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Utilidades.DrawerLayout(item,this,drawer);
        return true;
    }


    public static class PersonajesRecyclerViewAdapter
            extends RecyclerView.Adapter<PersonajesRecyclerViewAdapter.ViewHolder>
    implements Runnable
    {

        private final PersonajeListActivity mParentActivity;
        ArrayList<FiguraHistorica> figurahist;
        Context mContext;
        PersonajesRecyclerViewAdapter(PersonajeListActivity parent,
                                      Context c) {

            mParentActivity = parent;

            mContext = c ;
            run();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.itempersonaje_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            synchronized (figurahist) {
                FiguraHistorica mFigura = figurahist.get(position);

                holder.mImagenView.setImageDrawable(mFigura.getImagen());
                holder.mNombreView.setText(mFigura.getNombre());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), FullscreenFigura.class);
                        // ------ TODO Usar si las biografias no vienen en paginas webs/////

                        intent.putExtra("figura", position);
                        //intent.putExtras(b);
                        v.getContext().startActivity(intent);
                    }
                });
            }

        }

        @Override
        public int getItemCount() {

                return figurahist.size();

        }

        @Override
        public void run() {
          figurahist =   Utilidades.ListadoFiguras(mContext) ;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView mImagenView;
            final TextView mNombreView;

            ViewHolder(View view) {
                super(view);
                mImagenView = (ImageView) view.findViewById(R.id.imagen_heroe);
                mNombreView = (TextView) view.findViewById(R.id.nombre_heroe);
            }
        }
    }
}
