package cu.edu.uo.informatizacion.desembarcogranma;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Adaptador_Imagenes_Tema extends RecyclerView.Adapter {

    private TypedArray mImagenes;
    private int mTema ;

    public Adaptador_Imagenes_Tema(TypedArray imagenes, int tema) {
        mTema = tema ;
        mImagenes = imagenes ;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vistaitem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_imagenes_tema,
                parent,false) ;
        HolderImagenesTema hit = new HolderImagenesTema(vistaitem);
        return hit ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
            HolderImagenesTema rholder = (HolderImagenesTema) holder ;
            rholder.myImage.setImageDrawable(mImagenes.getDrawable(position));
            rholder.myImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent imgfullscreen = new Intent(view.getContext(),ImagenFullScreen.class) ;
                    imgfullscreen.putExtra("numerotema",mTema) ;
                    imgfullscreen.putExtra("numeroimagen",position) ;
                      view.getContext().startActivity(imgfullscreen );
                }
            });
    }

    @Override
    public int getItemCount() {
        return mImagenes.length();
    }

    class HolderImagenesTema extends RecyclerView.ViewHolder{

        final ImageView myImage;
        public HolderImagenesTema(View itemView) {
            super(itemView);
            myImage = (ImageView) itemView.findViewById(R.id.imagen_tema_item);
        }
    }

}
