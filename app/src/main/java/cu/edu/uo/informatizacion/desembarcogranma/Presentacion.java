package cu.edu.uo.informatizacion.desembarcogranma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Presentacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_presentacion);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startActivity(new Intent("cu.edu.uo.informatizacion.desembarcogranma.TEMAS"));
    }
}
