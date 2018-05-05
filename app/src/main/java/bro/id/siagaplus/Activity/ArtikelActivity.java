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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bro.id.siagaplus.Fragment.ArtikelSebelumFragment;
import bro.id.siagaplus.Fragment.ArtikelSetelahFragment;
import bro.id.siagaplus.Fragment.ArtikelTrimester1Fragment;
import bro.id.siagaplus.Fragment.ArtikelTrimester2Fragment;
import bro.id.siagaplus.Fragment.ArtikelTrimester3Fragment;
import bro.id.siagaplus.R;

public class ArtikelActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        username = getIntent().getStringExtra("username");

        TextView navHeaderName = findViewById(R.id.navHeaderName);
        navHeaderName.setText(username);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_artikel);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_menu);
        tabLayout.setupWithViewPager(viewPager);
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
        getMenuInflater().inflate(R.menu.artikel, menu);
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

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ArtikelSebelumFragment(), "Sebelum Hamil");
        adapter.addFragment(new ArtikelTrimester1Fragment(), "Trimester 1");
        adapter.addFragment(new ArtikelTrimester2Fragment(), "Trimester 2");
        adapter.addFragment(new ArtikelTrimester3Fragment(), "Trimester 3");
        adapter.addFragment(new ArtikelSetelahFragment(), "Setelah Lahir");
        viewPager.setAdapter(adapter);
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
