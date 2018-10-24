package com.example.gsb.rondademateapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText loNombrePersona;
    Button loBtnComenzar;
    CountDownTimer timer;
    Integer iCantidadPersonas;
    Integer iTiempoPorPersona;
    TextView loTiempoRestante;
    Bundle bundle;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        loNombrePersona = (EditText)findViewById(R.id.lo_nombrePersona);
        loBtnComenzar = (Button)findViewById(R.id.lo_botonComenzar);
        loBtnComenzar.setOnClickListener(new onClickComenzar());
        loTiempoRestante = (TextView)findViewById(R.id.lo_tiempo_restante);
        intent = getIntent();
        parsearValores();

        final ArrayList<Category> category = new ArrayList<Category>();
        for(int i=1;i<=iCantidadPersonas;i++)
            category.add(new Category("nombre"+i));


        ListView lv = (ListView) findViewById(R.id.lo_lv_personas);

        AdapterItem adapter = new AdapterItem(this, category);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                TextView title = (TextView) view.findViewById(R.id.nombre);
                title.setText(loNombrePersona.getText());

            }
        });


    }

    public class onClickComenzar implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try{
                setTimer(iTiempoPorPersona * 60 * 1000);
                timer.start();
            }
            catch (Exception e) {}
        }
    }



    private void setTimer(Integer milisegundos){
        timer = new CountDownTimer(milisegundos,1000) {
            @Override
            public void onTick(long lMillis) {
                //loTiempo.setText(Long.toString(l));
                String tiempoActual = String.format("%02d:%02d:%02d"
                        , TimeUnit.MILLISECONDS.toHours(lMillis)
                        , TimeUnit.MILLISECONDS.toMinutes(lMillis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(lMillis))
                        , TimeUnit.MILLISECONDS.toSeconds(lMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(lMillis)));
                loTiempoRestante.setText("Tiempo restante: "+tiempoActual);
            }

            @Override
            public void onFinish() {

                setTimer(iTiempoPorPersona * 60 * 1000);
                timer.start();
            }
        };
    }

    private void parsearValores(){
        bundle = intent.getExtras();
        int iAux = bundle.getInt("tiempoPorPersona");
        if(iAux != 0) {
            iTiempoPorPersona = iAux;
            setTimer(iTiempoPorPersona * 60 * 1000);
            timer.start();
        }
        iAux = bundle.getInt("cantPersonas");
        if(iAux != 0) {
            iCantidadPersonas = iAux;
        }

    }

    public void onBackPressed() {
        try {
            timer.cancel();
        }catch (Exception e){}
        finish();
    }

}
