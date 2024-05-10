package com.example.mobile_05_diceroll;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RadioButton rb01;
    private RadioButton rb02;
    private RadioButton rb03;
    private Spinner spDados;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rb01 = findViewById(R.id.RB01);
        rb01.setChecked(true);
        rb02 = findViewById(R.id.RB02);
        rb03 = findViewById(R.id.RB03);
        spDados = findViewById(R.id.spDados);
        Button btRolar = findViewById(R.id.brRolar);
        tvRes = findViewById(R.id.tvRes);

        preencheLista();
        btRolar.setOnClickListener(op -> rolar());

    }

    private void rolar() {

        String dado = (String) spDados.getSelectedItem();
        String res = "";
        int lados = rolarDados(dado)+1;


        Random aleatorio = new Random();
        if(rb01.isChecked()) {
            res = String.valueOf(aleatorio.nextInt(lados - 1)+ 1);
        } else if (rb02.isChecked()) {
            int cont  = 1;
            while (cont < 3){
                res = res + String.valueOf(aleatorio.nextInt(lados - 1)+ 1);
                if(cont < 2){
                    res = res + ", ";
                }
                cont++;
            }
        } else {
            int cont  = 1;
            while (cont < 4){
                res = res + String.valueOf(aleatorio.nextInt(lados - 1)+ 1);
                if(cont < 3){
                    res = res + ", ";
                }
                cont++;
            }
        }

        tvRes.setText(res);

    }

    private int rolarDados(String dado) {

        String[]split = dado.split("D");
        return Integer.parseInt(split[1]);

    }

    private void preencheLista() {
        List<String>lista = new ArrayList<>();
        lista.add("D4");
        lista.add("D6");
        lista.add("D8");
        lista.add("D10");
        lista.add("D12");
        lista.add("D20");
        lista.add("D100");

        ArrayAdapter adapter = new ArrayAdapter( this, android.R.layout.simple_spinner_item,lista);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDados.setAdapter(adapter);

    }
}