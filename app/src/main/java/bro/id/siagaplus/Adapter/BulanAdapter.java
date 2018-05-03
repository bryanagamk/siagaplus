package bro.id.siagaplus.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import bro.id.siagaplus.Model.Agenda;
import bro.id.siagaplus.Model.Bulan;
import bro.id.siagaplus.Model.Checklist;
import bro.id.siagaplus.R;

/**
 * Created by Rafif on 03/05/2018.
 */

public class BulanAdapter extends RecyclerView.Adapter<BulanAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Bulan> mListdata;

    public BulanAdapter(Context context, ArrayList<Bulan> mlistdata) {
        this.mContext = context;
        this.mListdata = mlistdata;
    }

    @Override
    public BulanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bulan_test, null);
        return new BulanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BulanAdapter.ViewHolder holder, int position) {
        holder.bulan.setText(mListdata.get(position).getBulan());
        holder.setId(mListdata.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return mListdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bulan;
        RecyclerView rvAgenda,rvchecklist;
        ArrayList<Agenda> listData;
        ArrayList<Checklist> arrayList;
        AgendaAdapter adapterAgenda;
        ChecklistAdapter adapterChecklist;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            bulan = itemView.findViewById(R.id.header_rv);

            listData = new ArrayList<>();
            arrayList = new ArrayList<>();

            adapterAgenda = new AgendaAdapter(mContext.getApplicationContext(),listData);
            adapterChecklist = new ChecklistAdapter(mContext.getApplicationContext(),arrayList);

            rvAgenda = (RecyclerView) itemView.findViewById(R.id.rv_agenda);
            rvchecklist = (RecyclerView) itemView.findViewById(R.id.rv_checklist);

            LinearLayoutManager llmAgenda = new LinearLayoutManager(mContext.getApplicationContext());
            llmAgenda.setOrientation(LinearLayoutManager.VERTICAL);
            LinearLayoutManager llmChecklist = new LinearLayoutManager(mContext.getApplicationContext());
            llmChecklist.setOrientation(LinearLayoutManager.VERTICAL);


            rvAgenda.setLayoutManager(llmAgenda);
            rvchecklist.setLayoutManager(llmChecklist);

            rvAgenda.setAdapter(adapterAgenda);
            rvchecklist.setAdapter(adapterChecklist);

            getDataAgenda();
            getDataChecklist();
            rvAgenda.setHasFixedSize(true);
            rvchecklist.setHasFixedSize(true);

        }

        private void getDataAgenda(){
            Agenda agenda = new Agenda("28/10/2018","10.00am","kontrol gigi");
            listData.add(agenda);

            agenda = new Agenda("30/10/2018","11.00am","ngesing");
            listData.add(agenda);

            adapterAgenda.notifyDataSetChanged();
        }

        private void getDataChecklist(){
            Checklist checklist = new Checklist("sikat gigi",false);
            arrayList.add(checklist);

            checklist = new Checklist("ngising",false);
            arrayList.add(checklist);

            checklist = new Checklist("cak nan",false);
            arrayList.add(checklist);

            adapterChecklist.notifyDataSetChanged();
        }

    }
}
