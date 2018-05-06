package bro.id.siagaplus.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import bro.id.siagaplus.Model.Agenda;
import bro.id.siagaplus.R;

/**
 * Created by Rafif on 03/05/2018.
 */

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Agenda> mListdata;

    public AgendaAdapter(Context context, ArrayList<Agenda> mlistdata) {
        this.mContext = context;
        this.mListdata = mlistdata;
    }

    @Override
    public AgendaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda, null);
        return new AgendaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AgendaAdapter.ViewHolder holder, int position) {
        /*holder.tgl.setText(mListdata.get(position).getTgl_agenda());
        holder.waktu.setText(mListdata.get(position).getWaktu_agenda());
        holder.keterangan.setText(mListdata.get(position).getTitle_agenda());*/
    }

    @Override
    public int getItemCount() {
        return mListdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tgl, waktu,keterangan;
        public ViewHolder(View itemView) {
            super(itemView);
            tgl = itemView.findViewById(R.id.item_tanggal_agenda);
            waktu = itemView.findViewById(R.id.item_waktu_agenda);
            keterangan = itemView.findViewById(R.id.item_akitifitas_agenda);
        }

    }
}