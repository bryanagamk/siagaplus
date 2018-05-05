package bro.id.siagaplus.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bro.id.siagaplus.Activity.IsiArtikelActivity;
import bro.id.siagaplus.R;

public class ArtikelTrimester2Adapter extends RecyclerView.Adapter<ArtikelTrimester2Adapter.ViewHolder> {

    Context c;
    private String [] menu={"Apakah Saya Perlu Menjalani Tes Amniocentesis Saat Hamil?"};
    private String [] nama={"amnio.html"};
    private String [] gambar={"amnio.png"};
    private String url = "file:///android_asset/";

    public ArtikelTrimester2Adapter(Context con) {
        this.c = con;
    }

    @Override
    public ArtikelTrimester2Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artikel, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtikelTrimester2Adapter.ViewHolder viewHolder, final int i) {
        viewHolder.txtJudul.setText(menu[i]);
        Glide.with(c)
                .load(url + gambar[i]).crossFade()
                .into(viewHolder.imageView);

        viewHolder.cvPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c.getApplicationContext(), IsiArtikelActivity.class);
                String namaArtikel = nama[i];
                String namaMenu = "Trimester 2";
                intent.putExtra("namaArtikel",namaArtikel);
                intent.putExtra("namaMenu",namaMenu);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menu.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtJudul;
        ImageView imageView;
        CardView cvPopular;

        public ViewHolder(View itemView) {
            super(itemView);
            txtJudul = (TextView) itemView.findViewById(R.id.txtJudul);
            imageView = (ImageView) itemView.findViewById(R.id.imghp);
            cvPopular = (CardView) itemView.findViewById(R.id.cvPopular);
        }
    }

}
