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

public class ArtikelSetelahAdapter extends RecyclerView.Adapter<ArtikelSetelahAdapter.ViewHolder> {

    Context c;
    private String [] menu={"Baby Blues", "Lidah Bayi Berwarna Putih, Apakah Normal?", "Tumbuh Kembang Gigi", "Menjaga Kebersihan Mulut Bayi", "Waspada Karies pada Anak"};
    private String [] nama={"babyblues.html", "lidahputih.html", "gigi.html", "mulutbayi.html", "waspadakaries.html"};
    private String [] gambar={"babyblues.jpg", "lidahputih.jpg", "gigi.png","mulutbayi.jpg","waspadakaries.jpg"};
    private String url = "file:///android_asset/";

    public ArtikelSetelahAdapter(Context con) {
        this.c = con;
    }

    @Override
    public ArtikelSetelahAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artikel, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtikelSetelahAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.txtJudul.setText(menu[i]);
        Glide.with(c)
                .load(url + gambar[i]).crossFade()
                .into(viewHolder.imageView);

        viewHolder.cvPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c.getApplicationContext(), IsiArtikelActivity.class);
                String namaArtikel = nama[i];
                String namaMenu = "Setelah Lahir";
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
