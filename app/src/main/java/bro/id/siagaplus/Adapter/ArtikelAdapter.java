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

import java.util.ArrayList;

import bro.id.siagaplus.Activity.IsiArtikelActivity;
import bro.id.siagaplus.Model.Artikel;
import bro.id.siagaplus.R;

/**
 * Created by Rafif on 03/05/2018.
 */

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Artikel> mListdata;
    private String url = "file:///android_asset/";

    public ArtikelAdapter(Context context, ArrayList<Artikel> mlistdata) {
        this.mContext = context;
        this.mListdata = mlistdata;
    }

    @Override
    public ArtikelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artikel_vertical, null);
        return new ArtikelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtikelAdapter.ViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(url + mListdata.get(position).getGambar_artikel()).crossFade()
                .into(holder.imghape);
        holder.txtTitle.setText(mListdata.get(position).getTitle_artikel());
        holder.cvArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, IsiArtikelActivity.class);
                intent.putExtra("namaArtikel",mListdata.get(position).getFile_artikel());
                intent.putExtra("namaMenu",mListdata.get(position).getMenu_artikel());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView imghape;
        CardView cvArtikel;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            imghape = (ImageView) itemView.findViewById(R.id.imgHp);
            cvArtikel = (CardView) itemView.findViewById(R.id.cvArtikel);
        }
    }
}
