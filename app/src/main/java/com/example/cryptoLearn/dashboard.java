package com.example.cryptoLearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;

    private BottomNavigationView botNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        botNav = findViewById(R.id.bottom_navigation);

        botNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item1) {
                    clearSideNav();
                    return true;
                } else if (item.getItemId() == R.id.item2) {
                    Toast.makeText(dashboard.this, "Community", Toast.LENGTH_SHORT).show();
                    clearSideNav();
                    return true;
                } else if (item.getItemId() == R.id.item3) {
                    Intent intentScan = new Intent(dashboard.this, VPRS_Scanner.class);
                    startActivity(intentScan);
                    finish();
                    clearSideNav();
                    return true;
                } else if (item.getItemId() == R.id.item4) {
                    Intent intentWallet = new Intent(dashboard.this, Wallet.class);
                    startActivity(intentWallet);
                    finish();
                    clearSideNav();
                    return true;
                } else if (item.getItemId() == R.id.item5) {
                    Intent intentVprs = new Intent(dashboard.this, Vprs.class);
                    startActivity(intentVprs);
                    finish();
                    clearSideNav();
                    return true;
                }
                return false;
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
            clearSideNav();
        } else if (id == R.id.nav_setup) {
            Intent intentSetup = new Intent(dashboard.this, Setup.class);
            startActivity(intentSetup);
            clearSideNav();
        } else if (id == R.id.nav_about) {
            Intent intentAbout = new Intent(dashboard.this, About.class);
            startActivity(intentAbout);
            clearSideNav();
        } else if (id == R.id.nav_logout) {
            Intent intentLogout = new Intent(dashboard.this, login.class);
            startActivity(intentLogout);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void clearSideNav(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
    }
    protected void onResume() {
        super.onResume();
        clearSideNav();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            clearSideNav();
        }
    }

}