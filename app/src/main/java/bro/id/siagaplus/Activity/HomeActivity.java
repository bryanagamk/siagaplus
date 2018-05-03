package bro.id.siagaplus.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import bro.id.siagaplus.Adapter.AgendaAdapter;
import bro.id.siagaplus.Adapter.BulanAdapter;
import bro.id.siagaplus.Adapter.ChecklistAdapter;
import bro.id.siagaplus.Model.Agenda;
import bro.id.siagaplus.Model.Bulan;
import bro.id.siagaplus.Model.Checklist;
import bro.id.siagaplus.R;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
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

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);*/

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawers_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_views);
        navigationView.setNavigationItemSelectedListener(this);*/

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawers_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.home:
                // Handle the camera action

                break;
            case R.id.agenda:

                break;
            case R.id.checklist:

                break;
            case R.id.artikel:

                break;
            case R.id.aboutus:

                break;
            case R.id.setting:

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
