package bro.id.siagaplus.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bro.id.siagaplus.Fragment.FiveFragment;
import bro.id.siagaplus.Fragment.FourFragment;
import bro.id.siagaplus.Fragment.OneFragment;
import bro.id.siagaplus.Fragment.ThreeFragment;
import bro.id.siagaplus.Fragment.TwoFragment;
import bro.id.siagaplus.R;
import bro.id.siagaplus.Utils.SharedPref;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String username, name, tgl;
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        sharedPref = new SharedPref(getApplicationContext());

        username = getIntent().getStringExtra("username");
//        HashMap<String, String> user = sharedPref.getUserDetails();
//        name = user.get(SharedPref.KEY_NAME);
//        tgl = user.get(SharedPref.KEY_TGL);
//        Toast.makeText(this, name + " & " + tgl, Toast.LENGTH_SHORT).show();
//        Log.d(name,"crot");


//        TextView mTextView = findViewById(R.id.navHeaderName);
//        mTextView.setText(name);

//        Calendar today = Calendar.getInstance();
//        Log.d(String.valueOf(today.getTime()), "onCreate: today");

/*        today.add(Calendar.MONTH, 1);
        Log.d(String.valueOf(today.getTime()), "onCreate: 1 month");*/

/*        today.add(Calendar.DATE, 14);
        Log.d(String.valueOf(today.getTime()), "onCreate: today 2 minggu sekali");*/


        /*
        * Trimester 1
        * Tiap Hari
        * Gosok Gigi
        * Senin - Seminggu Sekali - Kontrol Dokter Gigi
        * 8, 15, 22, 29, 5
        * Kamis - Sebulan Sekali - Kontrol Bidan
        * 11
        * */

        Calendar mulaiHamil = Calendar.getInstance();
        Calendar melahirkan = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        mulaiHamil.set(Calendar.MONTH, 3);
        mulaiHamil.set(Calendar.DAY_OF_YEAR, 4);
        Log.d(String.valueOf(mulaiHamil.getTime()), "onCreate: mulaiHamil");
        melahirkan = mulaiHamil;
        melahirkan.add(Calendar.MONTH, 9);
        Log.d(String.valueOf(melahirkan.getTime()), "onCreate: Melahirkan");

        long deff = melahirkan.getTimeInMillis() - today.getTimeInMillis();
        long aDay = deff / (24 * 60* 60 * 1000);
        Log.d(String.valueOf(aDay), "onCreate: aDay");

        long def = melahirkan.getTimeInMillis() - mulaiHamil.getTimeInMillis();
        long hari = def / (24 * 60* 60 * 1000);
        Log.d(String.valueOf(hari), "onCreate: jumlah melahirkan");

        Calendar no1 = Calendar.getInstance();
        no1.get(Calendar.DAY_OF_WEEK);

        int a = 0;
        /*Setiap Hari*/
        Log.d(String.valueOf(no1.getTime()), "onCreate: Setiap hari");
        while(a <= 20){
            Calendar startTime1 = Calendar.getInstance();
            Calendar endTime1 = Calendar.getInstance();
            startTime1.add(Calendar.DATE, a);
            endTime1 = startTime1;
            /*Log.d(String.valueOf(startTime1.getTime()), "onCreate: startTime1");
            Log.d(String.valueOf(endTime1.getTime()), "onCreate: endtime1");*/
            a++;
        }
        int b = 0;
        /*Seminggu Sekali*/
        Log.d(String.valueOf(no1.getTime()), "onCreate: Seminggu Sekali");
        while(b <= 31){
            Calendar startTime1 = Calendar.getInstance();
            startTime1.add(Calendar.DATE, b);
            Calendar endTime1 = Calendar.getInstance();

            if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){

                startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
                startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
                endTime1 = startTime1;
//                Log.d(String.valueOf(startTime1.getTime()), "onCreate: startTime1");
//                Log.d(String.valueOf(endTime1.getTime()), "onCreate: endtime1");
            }
            b++;
        }
        int c = 0;
        /*sebulan Sekali*/
        Log.d(String.valueOf(no1.getTime()), "onCreate: sebulan Sekali");
        while(c <= 31){
            Calendar startTime1 = Calendar.getInstance();
            startTime1.add(Calendar.MONTH, 1);
            startTime1.add(Calendar.DATE, c);
            Calendar endTime1 = Calendar.getInstance();

            if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){

                startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
                startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
                endTime1 = startTime1;
                Log.d(String.valueOf(startTime1.getTime()), "onCreate: startTime1");
                Log.d(String.valueOf(endTime1.getTime()), "onCreate: endtime1");
                break;
            }
            c++;
        }
        int d = 0;
        /*Dua Minggu Sekali*/
        Log.d(String.valueOf(no1.getTime()), "onCreate: Dua Minggu Sekali");
        while(d <= 31){
            Calendar startTime1 = Calendar.getInstance();
            startTime1.add(Calendar.DAY_OF_YEAR, d*2);
            Calendar endTime1 = Calendar.getInstance();

            if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){

                startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
                startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
                endTime1 = startTime1;
                Log.d(String.valueOf(startTime1.getTime()), "onCreate: startTime1");
            }
            d++;
        }
        int e = 0;
        /*Seminggu Sekali*/
        Log.d(String.valueOf(no1.getTime()), "onCreate: Seminggu Sekali");
        while(e <= 31){
            Calendar startTime1 = Calendar.getInstance();
            startTime1.add(Calendar.DATE, e);
            Calendar endTime1 = Calendar.getInstance();

            if (startTime1.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){

                startTime1.set(Calendar.MONTH,startTime1.get(Calendar.MONTH));
                startTime1.set(Calendar.DATE,startTime1.get(Calendar.DATE));
                endTime1 = startTime1;
//                Log.d(String.valueOf(startTime1.getTime()), "onCreate: startTime1");
//                Log.d(String.valueOf(endTime1.getTime()), "onCreate: endtime1");
            }
            e++;
        }




        /**/

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager_home);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "Belum Hamil");
        adapter.addFragment(new TwoFragment(), "Trimester 1");
        adapter.addFragment(new ThreeFragment(), "Trimester 2");
        adapter.addFragment(new FourFragment(), "Trimester 3");
        adapter.addFragment(new FiveFragment(), "Sudah Melahirkan");
        viewPager.setAdapter(adapter);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        } else if (id == R.id.notes) {
            Intent intent = new Intent(this, NoteActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);

        } else if (id == R.id.agenda) {
            Intent intent = new Intent(this, AgendaActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);

        } else if (id == R.id.checklist) {

            Intent intent = new Intent(this, CheckListActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);

        } else if (id == R.id.artikel) {
            Intent intent = new Intent(this, ArtikelActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);

        } else if (id == R.id.aboutus) {
            Intent intent = new Intent(this, AboutUsActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);

        } else if (id == R.id.setting) {
            Intent intent = new Intent(this, SettingActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
