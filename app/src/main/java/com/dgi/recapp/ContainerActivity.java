package com.dgi.recapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dgi.recapp.Api.Interface.ApiService;
import com.dgi.recapp.Api.TokenManager;
import com.dgi.recapp.CurrentAccount.view.ChartCurrentAccountFragment;
import com.dgi.recapp.Home.view.HomeFragment;

public class ContainerActivity extends AppCompatActivity {


    private ApiService service;
    private TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tokenManager = TokenManager.getInstance(this.getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }


        String valor= getIntent().getStringExtra("idmenu");
        String IdMenu = (valor!=null)?valor:"0";
        int IDMENU = IdMenu.length()>0 ? Integer.parseInt(IdMenu):0;

        SwitchOption(IDMENU);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);

        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.CerraarSesion:
                tokenManager.deleteToken();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.AccercaDe:
                Toast.makeText(this,"Dirección General de Ingresos",Toast.LENGTH_SHORT).show();
                break;


        }

        return super.onOptionsItemSelected(menuItem);

    }

    private void SwitchOption(int IDMENU)
    {

        switch (IDMENU)
        {
            case 1:
                SwitchFragment(new ChartCurrentAccountFragment(), true);
                break;
            case 2:
                SwitchFragment(new HomeFragment(), true);
                break;
            case 3:
                SwitchFragment(new HomeFragment(), true);
                break;
            default:
                SwitchFragment(new HomeFragment(), true);
                break;

        }

    }

    private void SwitchFragment(Fragment fragment, boolean isHome)
    {

        FragmentManager fragmentManager = getSupportFragmentManager();


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if(isHome)
        {

            fragmentTransaction
                    .replace(R.id.container,fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
        else
        {
            fragmentTransaction
                        .replace(R.id.container,fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(null)
                        .commit();
        }

    }
}
