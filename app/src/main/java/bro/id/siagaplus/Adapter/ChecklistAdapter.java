package bro.id.siagaplus.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import bro.id.siagaplus.Model.Checklist;
import bro.id.siagaplus.R;

/**
 * Created by Rafif on 03/05/2018.
 */

public class ChecklistAdapter  extends RecyclerView.Adapter<ChecklistAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Checklist> mListdata;

    public ChecklistAdapter(Context context, ArrayList<Checklist> mlistdata) {
        this.mContext = context;
        this.mListdata = mlistdata;
    }

    @Override
    public ChecklistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checklist, null);
        return new ChecklistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChecklistAdapter.ViewHolder holder, int position) {
        holder.nama.setText(mListdata.get(position).getTitle());
//        holder.waktu.setText(mListdata.get(position).getWaktu_agenda());
//        holder.keterangan.setText(mListdata.get(position).getTitle_agenda());
    }

    @Override
    public int getItemCount() {
        return mListdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama;
        public ViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.item_nama_checklist);
//            keterangan = itemView.findViewById(R.id.item_akitifitas_agenda);
        }

    }
}
