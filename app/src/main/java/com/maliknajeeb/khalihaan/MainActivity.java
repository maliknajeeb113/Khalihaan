package com.maliknajeeb.khalihaan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{   DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private View header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            startActivity(new Intent(this, Login.class));
        }




        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationView);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(
                this, drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().add(R.id.container, RecFragment.class,null ).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();

                if (id==R.id.home){

                    getSupportFragmentManager().beginTransaction().replace(R.id.container, RecFragment.class,null ).commit();


                }else if (id==R.id.setting){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, SettingFragment.class,null ).commit();

                }else if (id==R.id.logout){
                    FirebaseAuth.getInstance().signOut();//logout
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();
                }
                drawerLayout.closeDrawer(GravityCompat.START);



                return true;
            }
        });

        header = navigationView.getHeaderView(0);
        if (user != null){
            TextView profileName=header.findViewById(R.id.profileName);
            TextView profileEmail=header.findViewById(R.id.profileEmail);
            profileName.setText(user.getDisplayName());
            profileEmail.setText(user.getEmail());

        }

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }


}