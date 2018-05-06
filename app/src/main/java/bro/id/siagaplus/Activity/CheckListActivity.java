package bro.id.siagaplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import bro.id.siagaplus.Adapter.ChecklistAdapter;
import bro.id.siagaplus.Model.Checklist;
import bro.id.siagaplus.R;

public class CheckListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout lin1,lin2,lin3,lin4,lin5,lin6,lin7;
    ArrayList<Checklist> sblm_hamil_1,sblm_hamil_2,sblm_hamil_3,sblm_hamil_4;

    ChecklistAdapter mListAdapter1,mListAdapter2,mListAdapter3,mListAdapter4;

    ArrayList<Checklist> trimester1_1,trimester1_2,trimester1_3,trimester1_4;

    ArrayList<Checklist> trimester2_1,trimester2_2,trimester2_3;

    ArrayList<Checklist> trimester3_1,trimester3_2,trimester3_3,trimester3_4;

    ArrayList<Checklist> sdh_hamil_1,sdh_hamil_2,sdh_hamil_3,sdh_hamil_4;

    RecyclerView rv_checklist1,rv_checklist2,rv_checklist3,rv_checklist4,rv_checklist5,rv_checklist6,rv_checklist7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int code;


        sblm_hamil_1 = new ArrayList<>();
        sblm_hamil_2 = new ArrayList<>();
        sblm_hamil_3 = new ArrayList<>();
        sblm_hamil_4 = new ArrayList<>();

        trimester1_1 = new ArrayList<>();
        trimester1_2 = new ArrayList<>();
        trimester1_3 = new ArrayList<>();
        trimester1_4 = new ArrayList<>();

        trimester2_1 = new ArrayList<>();
        trimester2_2 = new ArrayList<>();
        trimester2_3 = new ArrayList<>();

        trimester3_1 = new ArrayList<>();
        trimester3_2 = new ArrayList<>();
        trimester3_3 = new ArrayList<>();
        trimester3_4 = new ArrayList<>();

        sdh_hamil_1 = new ArrayList<>();
        sdh_hamil_2 = new ArrayList<>();
        sdh_hamil_3 = new ArrayList<>();
        sdh_hamil_4 = new ArrayList<>();

        rv_checklist1 = findViewById(R.id.listview_pemeriksakesehatan);
        rv_checklist2 = findViewById(R.id.listview_menjagakesahatan);
        rv_checklist3 = findViewById(R.id.listview_kesehatangigidanrongamulut);
        rv_checklist4 = findViewById(R.id.listview_persiapanpernikahan);
        rv_checklist5 = findViewById(R.id.listview_kesehatankehamilan);
        rv_checklist6 = findViewById(R.id.listview_Persiapankelahiran);
        rv_checklist7 = findViewById(R.id.listview_Kesehatananak);

        LinearLayoutManager llmChecklist1 = new LinearLayoutManager(this);
        llmChecklist1.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager llmChecklist2 = new LinearLayoutManager(this);
        llmChecklist2.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager llmChecklist3 = new LinearLayoutManager(this);
        llmChecklist3.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager llmChecklist4 = new LinearLayoutManager(this);
        llmChecklist4.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager llmChecklist5 = new LinearLayoutManager(this);
        llmChecklist5.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager llmChecklist6 = new LinearLayoutManager(this);
        llmChecklist6.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager llmChecklist7 = new LinearLayoutManager(this);
        llmChecklist7.setOrientation(LinearLayoutManager.VERTICAL);

        rv_checklist1.setLayoutManager(llmChecklist1);
        rv_checklist2.setLayoutManager(llmChecklist2);
        rv_checklist3.setLayoutManager(llmChecklist3);
        rv_checklist4.setLayoutManager(llmChecklist4);
        rv_checklist5.setLayoutManager(llmChecklist5);
        rv_checklist6.setLayoutManager(llmChecklist6);
        rv_checklist7.setLayoutManager(llmChecklist7);

        rv_checklist1.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_checklist2.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_checklist3.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_checklist4.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_checklist5.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_checklist6.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv_checklist7.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        lin1 = findViewById(R.id.layout_checklist1);
        lin2 = findViewById(R.id.layout_checklist2);
        lin3 = findViewById(R.id.layout_checklist3);
        lin4 = findViewById(R.id.layout_checklist4);
        lin5 = findViewById(R.id.layout_checklist5);
        lin6 = findViewById(R.id.layout_checklist6);
        lin7 = findViewById(R.id.layout_checklist7);

        code = getIntent().getExtras().getInt("id");

        if (code == 0){
            lin1.setVisibility(View.VISIBLE);
            lin2.setVisibility(View.VISIBLE);
            lin3.setVisibility(View.VISIBLE);
            lin4.setVisibility(View.VISIBLE);

            mListAdapter1 = new ChecklistAdapter(this,sblm_hamil_1);
            mListAdapter2 = new ChecklistAdapter(this,sblm_hamil_2);
            mListAdapter3 = new ChecklistAdapter(this,sblm_hamil_3);
            mListAdapter4 = new ChecklistAdapter(this,sblm_hamil_4);



            rv_checklist1.setAdapter(mListAdapter1);
            rv_checklist2.setAdapter(mListAdapter2);
            rv_checklist3.setAdapter(mListAdapter3);
            rv_checklist4.setAdapter(mListAdapter4);

            initDatasblmhamil();

            mListAdapter1.notifyDataSetChanged();
            mListAdapter2.notifyDataSetChanged();
            mListAdapter3.notifyDataSetChanged();
            mListAdapter4.notifyDataSetChanged();


        } else if (code == 1){
            lin5.setVisibility(View.VISIBLE);
            lin1.setVisibility(View.VISIBLE);
            lin2.setVisibility(View.VISIBLE);
            lin3.setVisibility(View.VISIBLE);

            mListAdapter1 = new ChecklistAdapter(this,trimester1_1);
            mListAdapter2 = new ChecklistAdapter(this,trimester1_2);
            mListAdapter3 = new ChecklistAdapter(this,trimester1_3);
            mListAdapter4 = new ChecklistAdapter(this,trimester1_4);



            rv_checklist1.setAdapter(mListAdapter1);
            rv_checklist2.setAdapter(mListAdapter2);
            rv_checklist3.setAdapter(mListAdapter3);
            rv_checklist5.setAdapter(mListAdapter4);

            initrimesterchecklist();

            mListAdapter1.notifyDataSetChanged();
            mListAdapter2.notifyDataSetChanged();
            mListAdapter3.notifyDataSetChanged();
            mListAdapter4.notifyDataSetChanged();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.check_list, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.agenda) {
            Intent intent = new Intent(this, AgendaActivity.class);
            startActivity(intent);

        } else if (id == R.id.checklist) {

            Intent intent = new Intent(this, CheckListActivity.class);
            startActivity(intent);

        } else if (id == R.id.artikel) {
            Intent intent = new Intent(this, ArtikelActivity.class);
            startActivity(intent);

        } else if (id == R.id.aboutus) {
            Intent intent = new Intent(this, AboutUsActivity.class);
            startActivity(intent);

        } else if (id == R.id.setting) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initDatasblmhamil(){
        Checklist checklist1 = new Checklist("Tes Urin",false);
        sblm_hamil_1.add(checklist1);
        checklist1 = new Checklist("Tes Urin",false);
        sblm_hamil_1.add(checklist1);
        checklist1 = new Checklist("Pemeriksaan TT(Tetanus Toxoid)",false);
        sblm_hamil_1.add(checklist1);
        checklist1 = new Checklist("Imunisasi Tetanus Toxoid",false);
        sblm_hamil_1.add(checklist1);
        checklist1 = new Checklist("Pemeriksaan TORCH",false);
        sblm_hamil_1.add(checklist1);
        checklist1 = new Checklist("Tes Kesehatan umum",false);
        sblm_hamil_1.add(checklist1);
        checklist1 = new Checklist("Tes Glukosa darah",false);
        sblm_hamil_1.add(checklist1);
        checklist1 = new Checklist("Tes darah lenglap",false);
        sblm_hamil_1.add(checklist1);

        Checklist checklist2 = new Checklist("Hentikan mengkonsumsi alkohol",false);
        sblm_hamil_2.add(checklist2);
        checklist2 = new Checklist("kurangi konsumsi kafein",false);
        sblm_hamil_2.add(checklist2);
        checklist2 = new Checklist("Mulai rutin olahraga",false);
        sblm_hamil_2.add(checklist2);
        checklist2 = new Checklist("Konsumsi air putih minimal 2L perhari",false);
        sblm_hamil_2.add(checklist2);
        checklist2 = new Checklist("mengkonsumsi suplemen atau vitamin",false);
        sblm_hamil_2.add(checklist2);
        checklist2 = new Checklist("mengkonsumsi sayur dan buah secara rutin",false);
        sblm_hamil_2.add(checklist2);

        Checklist checklist3 = new Checklist("kontrol ke dokter gigi untuk cek kesahatan gigi dan mulut",false);
        sblm_hamil_3.add(checklist3);
        checklist3 = new Checklist("Sikat gigi rutin 2x sehari",false);
        sblm_hamil_3.add(checklist3);
        checklist3 = new Checklist("Sikat gigi setalah sarapan dan sebelum tidur",false);
        sblm_hamil_3.add(checklist3);
        checklist3 = new Checklist("Pembersihaan karang gigi",false);
        sblm_hamil_3.add(checklist3);
        checklist3 = new Checklist("menambal gigi berlubang(jika ada)",false);
        sblm_hamil_3.add(checklist3);
        checklist3 = new Checklist("menggunakan benang gigi(dental floss)",false);
        sblm_hamil_3.add(checklist3);
        checklist3 = new Checklist("mencabut gigi sisa akar (jika ada)",false);
        sblm_hamil_3.add(checklist3);

        Checklist checklist4 = new Checklist("Mempersiapkan budget",false);
        sblm_hamil_4.add(checklist4);
        checklist4 = new Checklist("Menentukan tanggan dan tempat",false);
        sblm_hamil_4.add(checklist4);
        checklist4 = new Checklist("Menyusun daftar tamu udangan",false);
        sblm_hamil_4.add(checklist4);
        checklist4 = new Checklist("Menghubungi vendor/EO Pernikahan",false);
        sblm_hamil_4.add(checklist4);
        checklist4 = new Checklist("Mempersiapkan baju",false);
        sblm_hamil_4.add(checklist4);
        checklist4 = new Checklist("Menghubungi catering",false);
        sblm_hamil_4.add(checklist4);


    }

    private void initrimesterchecklist() {

        Checklist checklist3 = new Checklist("kontrol ke dokter gigi untuk cek kesahatan gigi dan mulut",false);
        trimester1_3.add(checklist3);
        checklist3 = new Checklist("Sikat gigi rutin 2x sehari",false);
        trimester1_3.add(checklist3);
        checklist3 = new Checklist("Sikat gigi setalah sarapan dan sebelum tidur",false);
        trimester1_3.add(checklist3);
        checklist3 = new Checklist("Pembersihaan karang gigi",false);
        trimester1_3.add(checklist3);
        checklist3 = new Checklist("menambal gigi berlubang(jika ada)",false);
        trimester1_3.add(checklist3);
        checklist3 = new Checklist("menggunakan benang gigi(dental floss)",false);
        trimester1_3.add(checklist3);
        checklist3 = new Checklist("mencabut gigi sisa akar (jika ada)",false);
        trimester1_3.add(checklist3);

        Checklist checklist2 = new Checklist("Hentikan mengkonsumsi alkohol",false);
        trimester1_2.add(checklist2);
        checklist2 = new Checklist("kurangi konsumsi kafein",false);
        trimester1_2.add(checklist2);
        checklist2 = new Checklist("Mulai rutin olahraga",false);
        trimester1_2.add(checklist2);
        checklist2 = new Checklist("Konsumsi air putih minimal 2L perhari",false);
        trimester1_2.add(checklist2);
        checklist2 = new Checklist("mengkonsumsi suplemen atau vitamin",false);
        trimester1_2.add(checklist2);
        checklist2 = new Checklist("mengkonsumsi sayur dan buah secara rutin",false);
        trimester1_2.add(checklist2);

        Checklist checklist4 = new Checklist("istirahat yg cukup",false);
        trimester1_4.add(checklist4);
        checklist2 = new Checklist("kurangi aktifitas yg berat",false);
        trimester1_4.add(checklist4);
        checklist2 = new Checklist("Hentaikan kebiassaan merokok",false);
        trimester1_4.add(checklist4);
        checklist2 = new Checklist("rutin kontrol kehamilan di puskesmas",false);
        trimester1_4.add(checklist4);

        Checklist checklist1 = new Checklist("Pemeriksaan tekanan darah ketika awal memasuki masa kehamilan",false);
        trimester1_1.add(checklist1);

        checklist1 = new Checklist("Pemeriksaan darah lengkap yang bertujuan untuk mengetahui kemungkinan\n" +
                "adanya kelainan pada komponen darah secara keseluruhan",false);
        trimester1_1.add(checklist1);

        checklist1 = new Checklist("Periksa golongan darah dan rhesus",false);
        trimester1_1.add(checklist1);

        checklist1 = new Checklist("Pemeriksaan darah rutin (untuk mengetahui resiko anemia, gangguan\n" +
                "pembekuan darah, atau adanya infeksi)",false);
        trimester1_1.add(checklist1);
        checklist1 = new Checklist("Pemeriksaan glukosa darah",false);

        trimester1_1.add(checklist1);
        checklist1 = new Checklist("pemeriksaan  HbsAg (untuk mendeteksi virus hepatitis B), tes Anti HBs\n" +
                "(untuk mendeteksi antibodi pada hepatitis), dan Anti HCV (untuk mendeteksi\n" +
                "virus hepatitis C).",false);
        trimester1_1.add(checklist1);

        checklist1 = new Checklist("Pemeriksaan serologi untuk mengetahui ada tidaknya potensi penyakit sifilis",false);
        trimester1_1.add(checklist1);

        checklist1 = new Checklist("Pemeriksaan anti HIV untuk mendeteksi kemungkinan virus HIV yang bisa\n" +
                "menular kepada calon bayi",false);
        trimester1_1.add(checklist1);

        checklist1 = new Checklist("Pemeriksaan laboratorium Pemeriksaan hormon ini dilakukan pada hormon\n" +
                "bHCG-9 mendeteksi kehamilan di awal kehamilan), hormon progresteron\n" +
                "(untuk mendeteksi normal atau tidaknya kadar hormon progresteron), dan\n" +
                "juga hormon estradiol (untuk mendukung kehamilan itu sendiri). Jika hormon\n" +
                "ibu hamil tidak normal, maka dokter akan bisa memberikan rekomendasi\n" +
                "atau cara-cara untuk bisa menormalkan hormon tersebut",false);
        trimester1_1.add(checklist1);

        checklist1 = new Checklist("Pemeriksaan virus TORCH digunakan untuk mengetahui virus yang bisa\n" +
                "menyebabkan berbagai penyakit bawaan yang bisa diturunkan ke janin",false);
        trimester1_1.add(checklist1);
    }

}
