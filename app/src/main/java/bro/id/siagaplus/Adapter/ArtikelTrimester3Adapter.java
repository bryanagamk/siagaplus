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

public class ArtikelTrimester3Adapter extends RecyclerView.Adapter<ArtikelTrimester3Adapter.ViewHolder> {

    Context c;
    private String [] menu={"Tanda Bahaya Kehamilan dan Perilaku Perawatan Kehamilan pada Ibu Hamil Trimester III", "Perlengkapan Bayi yang Perlu Disiapkan Sebelum Lahiran", "Persiapan Melahirkan"};
    private String [] nama={"trimester3.html", "perlengkapan_bayi.html", "melahirkan.html"};
    private String [] gambar={"trimester3.jpg", "perlengkapan_bayi.jpg", "melahirkan.png"};
    private String url = "file:///android_asset/";

    public ArtikelTrimester3Adapter(Context con) {
        this.c = con;
    }

    @Override
    public ArtikelTrimester3Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artikel, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtikelTrimester3Adapter.ViewHolder viewHolder, final int i) {
        viewHolder.txtJudul.setText(menu[i]);
        Glide.with(c)
                .load(url + gambar[i]).crossFade()
                .into(viewHolder.imageView);

        viewHolder.cvPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c.getApplicationContext(), IsiArtikelActivity.class);
                String namaArtikel = nama[i];
                String namaMenu = "Trimester 3";
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
