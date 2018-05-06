package bro.id.siagaplus.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import java.util.ArrayList;

import bro.id.siagaplus.Helper.DatabaseHelper;
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
        holder.setTittle(mListdata.get(position).getTitle());
        holder.setId(mListdata.get(position).getId());
        holder.setCek(mListdata.get(position).getCek());
        if (holder.getCek() == 1) {
            holder.checkBox.setChecked(true);
            holder.nama.setText("Sudah");
            holder.nama.setTextColor(Color.RED);
        } else {
            holder.checkBox.setChecked(false);
        }
//        holder.waktu.setText(mListdata.get(position).getWaktu_agenda());
//        holder.keterangan.setText(mListdata.get(position).getTitle_agenda());
    }

    @Override
    public int getItemCount() {
        return mListdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama;
        int id;
        CheckBox checkBox;
        DatabaseHelper db;
        int cek;
        String tittle;

        public String getTittle() {
            return tittle;
        }

        public void setTittle(String tittle) {
            this.tittle = tittle;
        }

        public int getCek() {
            return cek;
        }

        public void setCek(int cek) {
            this.cek = cek;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            db = new DatabaseHelper(mContext);
            nama = itemView.findViewById(R.id.item_nama_checklist);
//            keterangan = itemView.findViewById(R.id.item_akitifitas_agenda);
            checkBox = itemView.findViewById(R.id.item_checkbox_checklist);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        nama.setTextColor(Color.RED);
                        nama.setText("Sudah");
                        db.checkedChecklist(id);
                    } else {
                        db.uncheckedChecklist(id);
                        nama.setTextColor(Color.GRAY);
                        nama.setText(getTittle());
                    }
                }
            });
        }

    }
}
