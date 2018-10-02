package com.zorail.powermanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.zorail.powermanager.CreateAccount.CreateAccountFragment;
import com.zorail.powermanager.Login.LoginFragment;
import com.zorail.powermanager.Util.SessionManager;

public class StarterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starter_activity);

//        if(getSupportActionBar() != null){
//            getSupportActionBar().hide();
//        }
        SessionManager sessionManager = new SessionManager(this);

        if(sessionManager.isLoggedIn()) {
            startNavActivity();
        } else {
            displayFragment(0);
        }

    }

    private void startNavActivity() {
        Intent i = new Intent(this, Nav_Drawer.class);
        startActivity(i);
    }

    private void displayFragment(int choose) {
        Fragment fragment = null;

        switch (choose) {
            case 0:
                fragment = new CreateAccountFragment();
                break;
            case 1:
                fragment = new LoginFragment();
                break;
        }

        if(fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.starter_fragment, fragment);
            ft.commit();
        }


    }
}
