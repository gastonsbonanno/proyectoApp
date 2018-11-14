package com.example.gsb.rondademateapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    EditText loConfCantPersonas;
    EditText loConfTiempoPorPersona;
    TextView loTextConfTiempoPorPersona;
    Switch loSwitchManual;
    Button loBtnAceptar;
    Intent intent;
    Integer iCantPersonas;
    Integer iTiempoPorPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        getSupportActionBar().hide();

        loConfCantPersonas = (EditText)findViewById(R.id.lo_cantidad_personas);
        loConfTiempoPorPersona = (EditText)findViewById(R.id.lo_tiempo_por_persona);
        loTextConfTiempoPorPersona = (TextView) findViewById(R.id.lo_text_tiempo_por_persona);
        loBtnAceptar = (Button)findViewById(R.id.lo_botonAceptar);
        loSwitchManual = (Switch)findViewById(R.id.lo_switch_manual);
        loBtnAceptar.setOnClickListener(new onClickAceptar());


        loSwitchManual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //loTextConfTiempoPorPersona.setFocusable(false);
                    loTextConfTiempoPorPersona.setEnabled(false);
                    //loTextConfTiempoPorPersona.setCursorVisible(false);

                    //loConfTiempoPorPersona.setFocusable(false);
                    loConfTiempoPorPersona.setEnabled(false);
                    //loConfTiempoPorPersona.setCursorVisible(false);
                    loConfTiempoPorPersona.setVisibility(View.INVISIBLE);
                }else{
                    //loTextConfTiempoPorPersona.setFocusable(true);
                    loTextConfTiempoPorPersona.setEnabled(true);
                    //loTextConfTiempoPorPersona.setCursorVisible(true);

                    //loConfTiempoPorPersona.setFocusable(true);
                    loConfTiempoPorPersona.setEnabled(true);
                    //loConfTiempoPorPersona.setCursorVisible(true);
                    loConfTiempoPorPersona.setVisibility(View.VISIBLE);
                }

            }
        });

    }


    public class onClickAceptar implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try{
                parsearValores();
                if(validarPermiteModificar()) {
                    intent = new Intent(ConfigActivity.this, MainActivity.class);
                    agregarExtras(intent);
                    startActivity(intent);
                }
            }
            catch (Exception e) {
                Toast.makeText(ConfigActivity.this, "Error al iniciar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validarPermiteModificar(){
        parsearValores();

        if(iCantPersonas == null || iCantPersonas < 2){
            Toast.makeText(this, "Deben ser 2 o mas personas", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(loSwitchManual.isEnabled()) {
            if (iTiempoPorPersona == null || iTiempoPorPersona < 1) {
                Toast.makeText(this, "El tiempo por persona debe ser mayor a cero", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if(iCantPersonas > 30){
            Toast.makeText(this, "Deben ser como máximo 30 personas", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void agregarExtras(Intent intent) {
        intent.putExtra("cantPersonas",iCantPersonas);
        intent.putExtra("tiempoPorPersona",(loSwitchManual.isChecked()) ? null : iTiempoPorPersona);
        intent.putExtra("esManual",(loSwitchManual.isChecked()) ? true : false);
    }

    private void parsearValores(){
        try {
            iCantPersonas = Integer.parseInt(loConfCantPersonas.getText().toString());
            iTiempoPorPersona = Integer.parseInt(loConfTiempoPorPersona.getText().toString());
        }catch (Exception e){}
    }

}

