package bro.id.siagaplus.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import bro.id.siagaplus.Adapter.AgendaAdapter;
import bro.id.siagaplus.Adapter.BulanAdapter;
import bro.id.siagaplus.Adapter.ChecklistAdapter;
import bro.id.siagaplus.Model.Agenda;
import bro.id.siagaplus.Model.Bulan;
import bro.id.siagaplus.Model.Checklist;
import bro.id.siagaplus.R;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rvAgenda,rvchecklist,rvbulan;
    ArrayList<Agenda> listData;
    ArrayList<Checklist> arrayList;
    ArrayList<Bulan> listbulan;

    AgendaAdapter adapterAgenda;
    BulanAdapter adapterBulan;
    ChecklistAdapter adapterChecklist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);

        listData = new ArrayList<>();
//        arrayList = new ArrayList<>();
        listbulan = new ArrayList<>();

//        adapterAgenda = new AgendaAdapter(getApplicationContext(),listData);
//        adapterChecklist = new ChecklistAdapter(getApplicationContext(),arrayList);
        adapterBulan = new BulanAdapter(getApplicationContext(),listbulan);

//        rvAgenda = (RecyclerView) findViewById(R.id.rv_agenda);
//        rvchecklist = (RecyclerView) findViewById(R.id.rv_checklist);
        rvbulan = (RecyclerView) findViewById(R.id.rv_bulan);
//        LinearLayoutManager llmAgenda = new LinearLayoutManager(this);
//        llmAgenda.setOrientation(LinearLayoutManager.VERTICAL);
//        LinearLayoutManager llmChecklist = new LinearLayoutManager(this);
//        llmChecklist.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager llmBulan = new LinearLayoutManager(getApplicationContext());
        llmBulan.setOrientation(LinearLayoutManager.HORIZONTAL);
        llmBulan.setReverseLayout(false);


//        rvAgenda.setLayoutManager(llmAgenda);
//        rvchecklist.setLayoutManager(llmChecklist);
        rvbulan.setLayoutManager(llmBulan);
//
//        rvAgenda.setAdapter(adapterAgenda);
//        rvchecklist.setAdapter(adapterChecklist);
        rvbulan.setAdapter(adapterBulan);
        bulanInitator();
//        getDataAgenda();
//        getDataChecklist();
        rvbulan.setHasFixedSize(true);

    }

    private void bulanInitator(){
        Bulan bulan = new Bulan("belum hamil",0);
        listbulan.add(bulan);
        bulan = new Bulan("1 BLN",1);
        listbulan.add(bulan);
        bulan = new Bulan("2 BLN",2);
        listbulan.add(bulan);
        bulan = new Bulan("3 BLN",3);
        listbulan.add(bulan);
        bulan = new Bulan("4 BLN",4);
        listbulan.add(bulan);
        bulan = new Bulan("5 BLN",5);
        listbulan.add(bulan);
        bulan = new Bulan("6 BLN",6);
        listbulan.add(bulan);
        bulan = new Bulan("7 BLN",7);
        listbulan.add(bulan);
        bulan = new Bulan("8 BLN",8);
        listbulan.add(bulan);
        bulan = new Bulan("9 BLN",9);
        listbulan.add(bulan);
        bulan = new Bulan("Sudah lahir",10);
        listbulan.add(bulan);
        adapterBulan.notifyDataSetChanged();

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
