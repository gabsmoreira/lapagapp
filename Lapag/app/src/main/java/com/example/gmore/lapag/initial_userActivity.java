package com.example.gmore.lapag;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;





public class initial_userActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Button button_areceber;
    private Button button_entradas;
    private Button button_recebidas;
    private TextView score_view;
    private TextView score_value;
    private TextView areceber_text;
    private TextView proximo_recebimento_valor;
    private TextView proximo_recebimento_data;
    private boolean animation_chooser;
    private MenuItem initial;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        score_view = (TextView) findViewById(R.id.score_view);
        score_value = (TextView) findViewById(R.id.score);
        areceber_text = (TextView) findViewById(R.id.areceber_texto);
        proximo_recebimento_valor = (TextView) findViewById(R.id.proximo_recebimento_valor);
        proximo_recebimento_data = (TextView) findViewById(R.id.proximo_recebimento_data);




        button_areceber = (Button) findViewById(R.id.areceber_button);
        button_areceber.setOnClickListener(this);
        button_entradas = (Button) findViewById(R.id.button_entradas);
        button_entradas.setOnClickListener(this);
        button_recebidas = (Button) findViewById(R.id.button_recebidas);
        button_recebidas.setOnClickListener(this);


        button_areceber.setBackgroundResource(R.drawable.button_rounded_corner_areceber);
        button_areceber.setTextColor(getResources().getColor(R.color.white));
        button_entradas.setBackgroundResource(R.drawable.not_clicked_button);
        button_recebidas.setBackgroundResource(R.drawable.not_clicked_button);
        button_entradas.setTextColor(getResources().getColor(R.color.faturado));
        button_recebidas.setTextColor(getResources().getColor(R.color.tranferido));




// Now Set your animation




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void onClick(View v) {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        if (v.getId() == R.id.areceber_button) {
            if (animation_chooser == true){
                score_view.startAnimation(fadeInAnimation);
                score_value.startAnimation(fadeInAnimation);
                areceber_text.startAnimation(fadeInAnimation);
                proximo_recebimento_data.startAnimation(fadeInAnimation);
                proximo_recebimento_valor.startAnimation(fadeInAnimation);
            }

            Drawable areceber_drawable = getResources().getDrawable(R.drawable.areceber);

            button_areceber.setBackgroundResource(R.drawable.button_rounded_corner_areceber);
            button_areceber.setTextColor(getResources().getColor(R.color.white));
            button_entradas.setBackgroundResource(R.drawable.not_clicked_button);
            button_entradas.setTextColor(getResources().getColor(R.color.faturado));
            button_recebidas.setBackgroundResource(R.drawable.not_clicked_button);
            button_recebidas.setTextColor(getResources().getColor(R.color.tranferido));
            score_view.setCompoundDrawablesWithIntrinsicBounds(areceber_drawable, null, null, null);
            areceber_text.setText("À receber");
            score_view.setBackgroundColor(getResources().getColor(R.color.areceber));

            proximo_recebimento_data.setVisibility(View.VISIBLE);
            proximo_recebimento_valor.setVisibility(View.VISIBLE);
            animation_chooser = false;



        }
        if (v.getId() == R.id.button_entradas) {
            if (animation_chooser == true){
                score_view.startAnimation(fadeInAnimation);
                score_value.startAnimation(fadeInAnimation);
                areceber_text.startAnimation(fadeInAnimation);
                proximo_recebimento_data.startAnimation(fadeInAnimation);
                proximo_recebimento_valor.startAnimation(fadeInAnimation);
            }

            Drawable areceber_drawable = getResources().getDrawable(R.drawable.areceber);

            button_recebidas.setBackgroundResource(R.drawable.not_clicked_button);
            button_entradas.setTextColor(getResources().getColor(R.color.faturado));
            button_recebidas.setTextColor(getResources().getColor(R.color.tranferido));
            button_entradas.setBackgroundResource(R.drawable.button_rounded_corner);
            button_entradas.setTextColor(getResources().getColor(R.color.white));
            button_areceber.setBackgroundResource(R.drawable.not_clicked_button);
            button_recebidas.setBackgroundResource(R.drawable.not_clicked_button);
            button_areceber.setTextColor(getResources().getColor(R.color.areceber));
            button_recebidas.setTextColor(getResources().getColor(R.color.tranferido));
            // quando clicar nas entradas rodar função para atualizar valores
            score_view.setCompoundDrawablesWithIntrinsicBounds(areceber_drawable, null, null, null);
            score_view.setBackgroundColor(getResources().getColor(R.color.areceber));
            areceber_text.setText("À receber");
            proximo_recebimento_data.setVisibility(View.VISIBLE);
            proximo_recebimento_valor.setVisibility(View.VISIBLE);

            animation_chooser = false;

        }
        if (v.getId() == R.id.button_recebidas) {

            Drawable transferido_drawable = getResources().getDrawable(R.drawable.transferido);

            button_recebidas.setBackgroundResource(R.drawable.button_rounded_corner_transferido);
            button_recebidas.setTextColor(getResources().getColor(R.color.white));
            button_entradas.setBackgroundResource(R.drawable.not_clicked_button);
            button_areceber.setBackgroundResource(R.drawable.not_clicked_button);
            button_entradas.setTextColor(getResources().getColor(R.color.faturado));
            button_areceber.setTextColor(getResources().getColor(R.color.areceber));
            // quando clicar no recebidas rodar função para atualizar valores
            //animação
            score_view.startAnimation(fadeInAnimation);
            score_value.startAnimation(fadeInAnimation);
            areceber_text.startAnimation(fadeInAnimation);

            proximo_recebimento_data.setVisibility(View.GONE);

            proximo_recebimento_valor.setVisibility(View.GONE);
            score_view.setCompoundDrawablesWithIntrinsicBounds(transferido_drawable, null, null, null);
            score_view.setBackgroundColor(getResources().getColor(R.color.tranferido));
            areceber_text.setText("Recebido");



            animation_chooser = true;




        }
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

        //initial.setEnabled(true);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.initial_user, menu);
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

        if (id == R.id.nav_pag) {
            // Handle the camera action
        } else if (id == R.id.nav_cale) {

        } else if (id == R.id.nave_fale) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
