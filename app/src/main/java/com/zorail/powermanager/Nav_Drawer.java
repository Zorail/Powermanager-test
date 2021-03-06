package com.zorail.powermanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zorail.powermanager.AboutUs.AboutUsFragment;
import com.zorail.powermanager.ContactUs.ContactUsFragment;
import com.zorail.powermanager.Details.DetailsFragment;
import com.zorail.powermanager.Home.HomeFragment;
import com.zorail.powermanager.PayBIll.PayBillFragment;
import com.zorail.powermanager.Settings.SettingsFragment;
import com.zorail.powermanager.Stats.StatsFragment;
import com.zorail.powermanager.Util.SessionManager;

public class Nav_Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__drawer);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav__drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        displaySelectedScreen(item.getItemId());

        return true;
    }

    private void displaySelectedScreen(int itemId) {
        Fragment fragment = null;

        switch (itemId) {
            case R.id.stats:
                fragment = new StatsFragment();
                break;

            case R.id.details:
                fragment = new DetailsFragment();
                break;

            case R.id.home:
                fragment = new HomeFragment();
                break;

            case R.id.pay:
                fragment = new PayBillFragment();
                break;

            case R.id.settings:
                fragment = new SettingsFragment();
                break;

            case R.id.contact:
                fragment = new ContactUsFragment();
                break;

            case R.id.about:
                fragment = new AboutUsFragment();
                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                SessionManager sessionManager = new SessionManager(this);
                sessionManager.setLogin(false);
                Intent i = new Intent(Nav_Drawer.this, StarterActivity.class);
                startActivity(i);
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        displaySelectedScreen(item.getItemId());

        return true;
    }

}
