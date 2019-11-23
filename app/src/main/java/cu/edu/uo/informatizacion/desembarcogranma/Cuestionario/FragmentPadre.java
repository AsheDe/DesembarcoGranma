package cu.edu.uo.informatizacion.desembarcogranma.Cuestionario;

import android.content.ClipData;
import android.content.ClipDescription;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.DragEvent;
import android.view.View;

import java.util.ArrayList;

abstract class FragmentPadre extends Fragment {


    abstract Object getRespuestDada();

}
