package com.example.gmore.lapag;


import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


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
    private TableLayout tableLayout;
    private ConstraintLayout constrain;
    private LoginActivity loginActivity = new LoginActivity();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Definir fontes
        Typeface dosis_bold = Typeface.createFromAsset(getAssets(), "Dosis-Bold.ttf");
        Typeface dosis_extra_bold = Typeface.createFromAsset(getAssets(), "Dosis-ExtraBold.ttf");
        Typeface dosis_extra_light = Typeface.createFromAsset(getAssets(), "Dosis-ExtraLight.ttf");
        Typeface dosis_light = Typeface.createFromAsset(getAssets(), "Dosis-Light.ttf");
        Typeface dosis_medium = Typeface.createFromAsset(getAssets(), "Dosis-Medium.ttf");
        Typeface dosis_regular = Typeface.createFromAsset(getAssets(), "Dosis-Regular.ttf");
        Typeface dosis_semi_bold= Typeface.createFromAsset(getAssets(), "Dosis-SemiBold.ttf");

        score_view = (TextView) findViewById(R.id.score_view);
        score_value = (TextView) findViewById(R.id.score);
        areceber_text = (TextView) findViewById(R.id.areceber_texto);
        proximo_recebimento_valor = (TextView) findViewById(R.id.proximo_recebimento_valor);
        proximo_recebimento_data = (TextView) findViewById(R.id.proximo_recebimento_data);
        tableLayout = (TableLayout) findViewById(R.id.tablelayout);
        constrain = (ConstraintLayout) findViewById(R.id.constrain);

        score_value.setTypeface(dosis_bold);
        areceber_text.setTypeface(dosis_medium);
        proximo_recebimento_valor.setTypeface(dosis_semi_bold);
        proximo_recebimento_data.setTypeface(dosis_medium);
        // mais fontes aqui



        button_areceber = (Button) findViewById(R.id.areceber_button);
        button_areceber.setTypeface(dosis_bold);
        button_areceber.setOnClickListener(this);
        button_entradas = (Button) findViewById(R.id.button_entradas);
        button_entradas.setTypeface(dosis_bold);
        button_entradas.setOnClickListener(this);
        button_recebidas = (Button) findViewById(R.id.button_recebidas);
        button_recebidas.setTypeface(dosis_bold);
        button_recebidas.setOnClickListener(this);


        button_areceber.setBackgroundResource(R.drawable.button_rounded_corner_areceber);
        button_areceber.setTextColor(getResources().getColor(R.color.white));
        button_entradas.setBackgroundResource(R.drawable.not_clicked_button);
        button_recebidas.setBackgroundResource(R.drawable.not_clicked_button);
        button_entradas.setTextColor(getResources().getColor(R.color.faturado));
        button_recebidas.setTextColor(getResources().getColor(R.color.tranferido));





        try {
            List<Transactions> transactionsList = loginActivity.getTransactions();
            List<Transactions> ordered_transactions = orderedTransactions(transactionsList);
            List<Transactions> future_transactions = getFutureTransactions(ordered_transactions, getTodayIterator());
            createTransactions(ordered_transactions);
            proximo_recebimento_data.setText(getNextTransactionDate(future_transactions));
            proximo_recebimento_valor.setText(getNextTransactionAmount(future_transactions));
            score_value.setText(calculateTotal(future_transactions));
        }
        catch (NullPointerException e){
            //Intent intent = new Intent(this, LoginActivity.class);
            //this.startActivity(intent);
            //Toast.makeText(getApplicationContext(), "Erro ao fazer download de dados", Toast.LENGTH_LONG).show();
        }






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
            tableLayout.removeAllViews();
            List<Transactions> transactionsList = loginActivity.getTransactions();
            List<Transactions> ordered_transactions = orderedTransactions(transactionsList);
            Log.d("HELLO",getDaysList(ordered_transactions).toString());
            createTransactions(ordered_transactions);
            List<Transactions> future_transactions = getFutureTransactions(ordered_transactions,getTodayIterator());
            if (animation_chooser == true){

                score_view.startAnimation(fadeInAnimation);
                score_value.startAnimation(fadeInAnimation);
                areceber_text.startAnimation(fadeInAnimation);
                proximo_recebimento_data.startAnimation(fadeInAnimation);
                proximo_recebimento_valor.startAnimation(fadeInAnimation);
            }

            Drawable areceber_drawable = getResources().getDrawable(R.drawable.areceber);
            proximo_recebimento_data.setText(getNextTransactionDate(future_transactions));
            proximo_recebimento_valor.setText(getNextTransactionAmount(future_transactions));
            score_value.setText(calculateTotal(future_transactions));
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
            tableLayout.removeAllViews();
            LoginActivity loginActivity = new LoginActivity();
            List<Transactions> transactionsList = loginActivity.getTransactions();
            List<Transactions> ordered_transactions = orderedTransactions(transactionsList);
            List<Transactions> futures_transactions = new ArrayList<>(getFutureTransactions(ordered_transactions, getTodayIterator()));

            createTransactionsEntradas(futures_transactions);
            if (animation_chooser == true){
                score_view.startAnimation(fadeInAnimation);
                score_value.startAnimation(fadeInAnimation);
                areceber_text.startAnimation(fadeInAnimation);
                proximo_recebimento_data.startAnimation(fadeInAnimation);
                proximo_recebimento_valor.startAnimation(fadeInAnimation);
            }
            List<Transactions> future_transactions = getFutureTransactions(ordered_transactions,getTodayIterator());
            proximo_recebimento_data.setText(getNextTransactionDate(future_transactions));
            proximo_recebimento_valor.setText(getNextTransactionAmount(future_transactions));
            score_value.setText(calculateTotal(future_transactions));

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
            tableLayout.removeAllViews();
            LoginActivity loginActivity =  new LoginActivity();
            List<Transactions> transactionsList = loginActivity.getTransactions();
            List<Transactions> ordered_transactions = orderedTransactions(transactionsList);
            List<Transactions> past_transactions = getPastTransactions(ordered_transactions,getTodayIterator());
            createTransactionsEntradas(past_transactions);

            Drawable transferido_drawable = getResources().getDrawable(R.drawable.transferido);
            score_value.setText(calculateTotal(past_transactions));

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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_pag) {
            // Handle the camera action
        } else if (id == R.id.nav_cale) {
            Intent intent = new Intent(this, calendarActivity.class);
            this.startActivity (intent);
        } else if (id == R.id.nav_perfil) {
            Intent intent = new Intent(this, profileActivity.class);
            this.startActivity (intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void createTransactions(List<Transactions> transactionsList){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Typeface dosis_bold = Typeface.createFromAsset(getAssets(), "Dosis-Bold.ttf");
        Typeface dosis_extra_bold = Typeface.createFromAsset(getAssets(), "Dosis-ExtraBold.ttf");
        Typeface dosis_extra_light = Typeface.createFromAsset(getAssets(), "Dosis-ExtraLight.ttf");
        Typeface dosis_light = Typeface.createFromAsset(getAssets(), "Dosis-Light.ttf");
        Typeface dosis_medium = Typeface.createFromAsset(getAssets(), "Dosis-Medium.ttf");
        Typeface dosis_regular = Typeface.createFromAsset(getAssets(), "Dosis-Regular.ttf");
        Typeface dosis_semi_bold= Typeface.createFromAsset(getAssets(), "Dosis-SemiBold.ttf");

        TableRow.LayoutParams llp = new TableRow.LayoutParams (TableRow.LayoutParams.WRAP_CONTENT ,TableRow.LayoutParams.WRAP_CONTENT);

        llp.setMargins(width/30, 10,5 , 10); // llp.setMargins(left, top, right, bottom);

        for (int i = 0; i<transactionsList.size(); ++i){

            Transactions transaction = transactionsList.get(i);
            TableRow row = new TableRow(this);
            tableLayout.addView(row);
            row.setLayoutParams(llp);
            TextView textView1 = new TextView(this);
            textView1.setText(transaction.getTransfer_day());
            textView1.setPadding(7,7,7,7);
            textView1.setTextSize(18);
            textView1.setTypeface(dosis_medium);
            textView1.setTextAlignment(textView1.TEXT_ALIGNMENT_VIEW_END);


            TextView textView2 = new TextView(this);
            textView2.setText(transactionsList.get(i).getName());
            textView2.setPadding(7,7,7,7);
            textView2.setTextSize(18);
            textView2.setTypeface(dosis_medium);
            textView2.setTextAlignment(textView1.TEXT_ALIGNMENT_TEXT_END);



            ImageView imageView = new ImageView(this);

            if (!transaction.getStatus()){
                // colocar bola vermelha
                imageView.setImageResource(R.drawable.b_vermelha);
            }
            else{
                // colocar bola verde
                imageView.setImageResource(R.drawable.b_verde);
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_END);
            imageView.setPadding(7,20,10,7);


            TextView textView3 = new TextView(this);
            textView3.setText(transactionsList.get(i).getAmount());
            textView3.setPadding(7,7,7,7);
            textView3.setTextSize(18);
            textView3.setTypeface(dosis_medium);
            textView3.setTextAlignment(textView1.TEXT_ALIGNMENT_TEXT_END);

            row.addView(textView1);
            row.addView(textView2);
            row.addView(imageView);
            row.addView(textView3);

        }
    }

    public void createTransactionsEntradas(List<Transactions> transactionsList){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Typeface dosis_bold = Typeface.createFromAsset(getAssets(), "Dosis-Bold.ttf");
        Typeface dosis_extra_bold = Typeface.createFromAsset(getAssets(), "Dosis-ExtraBold.ttf");
        Typeface dosis_extra_light = Typeface.createFromAsset(getAssets(), "Dosis-ExtraLight.ttf");
        Typeface dosis_light = Typeface.createFromAsset(getAssets(), "Dosis-Light.ttf");
        Typeface dosis_medium = Typeface.createFromAsset(getAssets(), "Dosis-Medium.ttf");
        Typeface dosis_regular = Typeface.createFromAsset(getAssets(), "Dosis-Regular.ttf");
        Typeface dosis_semi_bold= Typeface.createFromAsset(getAssets(), "Dosis-SemiBold.ttf");

        TableRow.LayoutParams llp = new TableRow.LayoutParams (TableRow.LayoutParams.WRAP_CONTENT ,TableRow.LayoutParams.WRAP_CONTENT);

        llp.setMargins(width/7, 5,5 , 5); // llp.setMargins(left, top, right, bottom);

        List<Integer> days_list_iterator = getDaysList(transactionsList);
        for(int i = 0; i < days_list_iterator.size(); ++i) {
            TableRow row = new TableRow(this);
            tableLayout.addView(row);
            List<Transactions> day_list = getTodayTrasactions(transactionsList, days_list_iterator.get(i));
            try {
                ImageView imageView = new ImageView(this);

                imageView.setImageResource(R.drawable.ic_menu_gallery);
                imageView.setPadding(7,47,7,7);

                imageView.setScaleType(ImageView.ScaleType.FIT_END);
                TextView textView1 = new TextView(this);
                textView1.setText(day_list.get(0).getTransfer_day());
                textView1.setPadding(33, 33, 43, 7);
                textView1.setTextSize(25);
                textView1.setTypeface(dosis_medium);
                textView1.setTextAlignment(textView1.TEXT_ALIGNMENT_TEXT_START);


                TextView textView2 = new TextView(this);
                textView2.setText(calculateTotal(day_list));
                textView2.setPadding(90, 7, 23, 33);
                textView2.setTextSize(25);
                textView2.setTypeface(dosis_medium);
                textView2.setTextAlignment(textView1.TEXT_ALIGNMENT_TEXT_END);


                row.setLayoutParams(llp);
                //row.addView(imageView);
                row.addView(textView1);
                row.addView(textView2);
            } catch (NullPointerException e) {
                Toast.makeText(getApplicationContext(), "Erro ao fazer download de dados", Toast.LENGTH_LONG).show();
            }

        }
    }


    public List<Transactions> orderedTransactions(List<Transactions> original_list){
        try{
        List<Transactions> ordered_transactions = new ArrayList<>(original_list);
        // Sorting
        Collections.sort(ordered_transactions, new Comparator<Transactions>() {
            @Override
            public int compare(Transactions tr1, Transactions tr2)
            {

                return tr1.getDate_iterator() < tr2.getDate_iterator() ? -1 : (tr1.getDate_iterator() > tr2.getDate_iterator() ) ? 1 : 0;

            }
        });

        return  ordered_transactions;}
        catch (NullPointerException e){
            Toast.makeText(getApplicationContext(), "Erro ao fazer download de dados", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    public int getTodayIterator(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date now = new Date();
        String transfer_day = sdf.format(now);
        int date_iterator = 0;
        date_iterator += Character.getNumericValue(transfer_day.charAt(5))*1000 + Character.getNumericValue(transfer_day.charAt(6))*100 + Character.getNumericValue(transfer_day.charAt(8))*10 + Character.getNumericValue(transfer_day.charAt(9));
       return date_iterator;
    }


    public List<Transactions> getPastTransactions(List<Transactions> list, int today_iterator){
        List<Transactions> new_list = new ArrayList<>(list);
        List<Transactions> remove_list = new ArrayList<Transactions>();
        for (int i = 0; i<new_list.size(); ++i){
            if (new_list.get(i).getDate_iterator() >= today_iterator){
                remove_list.add(new_list.get(i));

            }
        }
        for (int j = 0; j<remove_list.size(); ++j){
            //Log.d("LIST", String.valueOf(remove_list.get(j)));
            new_list.remove(remove_list.get(j));
        }

        return new_list;

    }

    public List<Transactions> getFutureTransactions(List<Transactions> list, int today_iterator){
        List<Transactions> new_list = new ArrayList<>(list);
        List<Transactions> remove_list = new ArrayList<Transactions>();
        for (int i = 0; i<new_list.size(); ++i){
            if (new_list.get(i).getDate_iterator() <= today_iterator){
                remove_list.add(new_list.get(i));

            }
        }
        for (int j = 0; j<remove_list.size(); ++j){
            //Log.d("LIST", String.valueOf(remove_list.get(j)));
            new_list.remove(remove_list.get(j));
        }

        return new_list;

    }

    public String getNextTransactionDate(List<Transactions> future_transactions){
        return "Próximo recebimento "+ " " + future_transactions.get(0).getTransfer_day();

    }

    public String getNextTransactionAmount(List<Transactions> future_transactions){
        List<Transactions> daily =  getTodayTrasactions(future_transactions,future_transactions.get(0).getDate_iterator());
        return calculateTotal(daily);

    }

    public static String calculateTotal(List<Transactions> list){
        int total = 0;
        for (int i = 0; i<list.size(); ++i){
            total += list.get(i).getReal_value();
        }
        String amount = Integer.toString(total);
        if (amount.length() == 4){
            String amount_real = "R$" + amount.charAt(0) + amount.charAt(1)  + "," + amount.charAt(2) + amount.charAt(3);
            return amount_real;
        }
        else if (amount.length() == 5){
            String amount_real = "R$" + amount.charAt(0) + amount.charAt(1) + amount.charAt(2) + "," + amount.charAt(3) + amount.charAt(4);
            return amount_real;
        }
        else if (amount.length() == 6){
            String amount_real = "R$" + amount.charAt(0) + amount.charAt(1) + amount.charAt(2) + amount.charAt(3) + "," + amount.charAt(4) + amount.charAt(5);
            return amount_real;
        }

        return null;
    }

    public List<Transactions> getTodayTrasactions (List<Transactions> list, int day_iterator){
        List<Transactions> new_list = new ArrayList<>();
        for (int i = 0; i<list.size(); ++i){
            if (list.get(i).getDate_iterator() == day_iterator){
                new_list.add(list.get(i));

            }
        }
        return new_list;

    }

    public List<Integer> getDaysList(List<Transactions> list){
        List<Integer> list_days = new ArrayList<>();
        for (int i = 0; i<list.size(); ++i){
            if (!list_days.contains(list.get(i).getDate_iterator())){
                list_days.add(list.get(i).getDate_iterator());
            }
        }
        return list_days;
    }
}