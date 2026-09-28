package edu.upc.dsa.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    boolean empezarNuevoNumero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.textView);

        View.OnClickListener listenerNumeros = v -> {
            Button botonPulsado = (Button) v;
            escribirNumero(botonPulsado.getText().toString());
        };

        findViewById(R.id.button).setOnClickListener(listenerNumeros);
        findViewById(R.id.button2).setOnClickListener(listenerNumeros);
        findViewById(R.id.button3).setOnClickListener(listenerNumeros);
        findViewById(R.id.button4).setOnClickListener(listenerNumeros);
        findViewById(R.id.button5).setOnClickListener(listenerNumeros);
        findViewById(R.id.button6).setOnClickListener(listenerNumeros);
        findViewById(R.id.button7).setOnClickListener(listenerNumeros);
        findViewById(R.id.button8).setOnClickListener(listenerNumeros);
        findViewById(R.id.button9).setOnClickListener(listenerNumeros);
        findViewById(R.id.button0).setOnClickListener(listenerNumeros);

        findViewById(R.id.buttonPoint).setOnClickListener(v -> escribirPunto());





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    private void escribirNumero(String numero) {
        if (empezarNuevoNumero) {
            display.setText(numero);
            empezarNuevoNumero = false;
        } else {
            display.append(numero);
        }
    }
    private void escribirPunto() {
        String numeroActual = display.getText().toString();

        if (empezarNuevoNumero) {
            display.setText("0.");
            empezarNuevoNumero = false;
        } else if (!numeroActual.contains(".")) { // Solo se permite 1 punto por numero
            display.append(".");
        }
    }
}