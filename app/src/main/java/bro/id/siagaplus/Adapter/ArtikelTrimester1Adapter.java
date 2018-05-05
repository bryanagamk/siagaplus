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

public class ArtikelTrimester1Adapter extends RecyclerView.Adapter<ArtikelTrimester1Adapter.ViewHolder> {

    Context c;
    private String [] menu={"Awal Tanda Kehamilan", "Hubungan Antara Dukungan Keluarga dengan Penyesuaian Diri Perempuan pada Kehamilan Pertama", "Makanan yang Harus Dihindari oleh Ibu Hamil", "Morning Sickness Atau Mual", "Hal-Hal yang Perlu Diperhatikan dalam Kesehatan Gigi Selama Masa Kehamilan"};
    private String [] nama={"awal_kehamilan.html", "dukungan_keluarga.html", "makanan.html", "mual.html", "kesehatan_gigi.html"};
    private String [] gambar={"awal_kehamilan.jpg", "dukungan_keluarga.jpg", "makanan.jpg", "mual.png", "kesehatan_gigi.jpg"};
    private String url = "file:///android_asset/";

    public ArtikelTrimester1Adapter(Context con) {
        this.c = con;
    }

    @Override
    public ArtikelTrimester1Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artikel, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtikelTrimester1Adapter.ViewHolder viewHolder, final int i) {
        viewHolder.txtJudul.setText(menu[i]);
        Glide.with(c)
                .load(url + gambar[i]).crossFade()
                .into(viewHolder.imageView);

        viewHolder.cvPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c.getApplicationContext(), IsiArtikelActivity.class);
                String namaArtikel = nama[i];
                String namaMenu = "Trimester 1";
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
