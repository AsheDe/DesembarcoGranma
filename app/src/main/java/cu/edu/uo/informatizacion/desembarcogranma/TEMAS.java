package cu.edu.uo.informatizacion.desembarcogranma;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import cu.edu.uo.informatizacion.desembarcogranma.Cuestionario.Cuestionario;

public class TEMAS extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{
        Integer layout;
        boolean istablet ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_temas);
        //Utilidades.FullScreen(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


   ////////-------------------NAV DRAWER
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
///////// SABER SI ES TABLET Y SI TENGO ENTONCES QUE MOSTRAR PAGER O GRID
        istablet = getResources().getBoolean(R.bool.tablet) ;
        ///----------- Configuracion View Pager
       if(!istablet)
       {
           ViewPager mpager = (ViewPager) findViewById(R.id.paginador) ;
           mpager.setAdapter(new Pager_Adapter(getSupportFragmentManager()));
           mpager.setPageTransformer(false,new PageTransformer(this));
       }
       else
       {
           findViewById(R.id.cardantecedentes).setOnClickListener(this);
           findViewById(R.id.cardprepexp).setOnClickListener(this);
          findViewById(R.id.cardexpedicion).setOnClickListener(this);
           findViewById(R.id.cardcubaespera).setOnClickListener(this);
           findViewById(R.id.cardtravesia).setOnClickListener(this);
           findViewById(R.id.carddiasheroicos).setOnClickListener(this);
           findViewById(R.id.cardsignificacion).setOnClickListener(this);

       }

        ///////////////////////////
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        int puntuacion =  PreferenceManager.getDefaultSharedPreferences(this).getInt("puntuacion",-1) ;
        if( getIntent().hasExtra("cuestionario") || puntuacion == -1 ) {
            String title = "Realiza el cuestionario";
            String mensaje = "Veamos cuantos puntos obtienes";
            if (puntuacion != -1) {
                title = "¡¡ Inténtalo!!";
                mensaje = "Supera los " + puntuacion + " puntos en el cuestionario";
            }
            NotificationCompat.Builder notoficacion =
                    new NotificationCompat.Builder(this)
                            .setColor(getResources().getColor(R.color.verde))
                            .setContentText(mensaje)
                            .setSmallIcon(R.mipmap.logodesembarcoredondo)
                            .setContentTitle(title)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.logodesembarcoredondo))
                    ;
            Intent resultIntent = new Intent(this, Cuestionario.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(Cuestionario.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent =
                    stackBuilder.getPendingIntent(
                            0,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            notoficacion.setContentIntent(resultPendingIntent);

            if (Build.VERSION.SDK_INT <= 25) {
                notoficacion.setDefaults(Notification.DEFAULT_ALL);
                notoficacion.setPriority(NotificationCompat.PRIORITY_HIGH);
            } else {
                notoficacion.setPriority(NotificationManager.IMPORTANCE_HIGH);
            }

            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, notoficacion.build());
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       Utilidades.DrawerLayout(item,this,drawer);

        return true;
    }

    @Override
    public void onClick(View view) {
     if(istablet)
     {
         switch (view.getId())
         {
             case R.id.cardantecedentes:{
                 Intent inet = new Intent(this,DetallesTemas.class) ;
                 inet.putExtra("tema",0) ;
                    startActivity(inet);
                    break;
                }
             case R.id.cardprepexp:{
                 Intent inet = new Intent(this,DetallesTemas.class) ;
                 inet.putExtra("tema",1) ;
                 startActivity(inet);
                 break;
             }
             case R.id.cardexpedicion:{
                 Intent inet = new Intent(this,DetallesTemas.class) ;
                 inet.putExtra("tema",2) ;
                 startActivity(inet);
                 break;
             }
             case R.id.cardcubaespera:{
                 Intent inet = new Intent(this,DetallesTemas.class) ;
                 inet.putExtra("tema",3) ;
                 startActivity(inet);
                 break;
             }
             case R.id.cardtravesia:{
                 Intent inet = new Intent(this,DetallesTemas.class) ;
                 inet.putExtra("tema",4) ;
                 startActivity(inet);
                 break;
             }
             case R.id.carddiasheroicos:{
                 Intent inet = new Intent(this,DetallesTemas.class) ;
                 inet.putExtra("tema",5) ;
                 startActivity(inet);
                 break;
             }
             case R.id.cardsignificacion:{
                 Intent inet = new Intent(this,DetallesTemas.class) ;
                 inet.putExtra("tema",6) ;
                 startActivity(inet);
                 break;
             }
         }
     }
    }
}
