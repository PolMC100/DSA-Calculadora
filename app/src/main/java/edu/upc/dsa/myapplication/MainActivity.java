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
    boolean empezarNuevoNumero = true;
    private double primerNumero;
    private String operacionPendiente = "";

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

        findViewById(R.id.buttonPlus)
                .setOnClickListener(v -> seleccionarOperacion("+"));

        findViewById(R.id.buttonMinus)
                .setOnClickListener(v -> seleccionarOperacion("-"));

        findViewById(R.id.buttonTimes)
                .setOnClickListener(v -> seleccionarOperacion("*"));

        findViewById(R.id.buttonDividedBy)
                .setOnClickListener(v -> seleccionarOperacion("/"));

        findViewById(R.id.buttonEquals)
                .setOnClickListener(v -> calcularResultado());

        findViewById(R.id.buttonClear)
                .setOnClickListener(v -> limpiarCalculadora());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    private void escribirNumero(String numero) {
        if (display.getText().toString().charAt(0) == '0' && numero.equals("0")){ // Si hay un 0 a la izquierda no mete otro mas aunque el usuario le de
            return;
        }
        else if (empezarNuevoNumero) {
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

    private void seleccionarOperacion(String operacion) {
        String textoActual = display.getText().toString();

        if (textoActual.isEmpty()) {
            return;
        }

        primerNumero = Double.parseDouble(textoActual);
        operacionPendiente = operacion;
        empezarNuevoNumero = true;
    }

    private void calcularResultado() {
        if (operacionPendiente.isEmpty()) {
            return;
        }

        double segundoNumero =
                Double.parseDouble(display.getText().toString());

        double resultado;

        switch (operacionPendiente) {
            case "+":
                resultado = primerNumero + segundoNumero;
                break;

            case "-":
                resultado = primerNumero - segundoNumero;
                break;

            case "*":
                resultado = primerNumero * segundoNumero;
                break;

            case "/":
                if (segundoNumero == 0) {
                    display.setText("Error al dividir por 0");
                    operacionPendiente = "";
                    empezarNuevoNumero = true;
                    return;
                }

                resultado = primerNumero / segundoNumero;
                break;

            default:
                return;
        }

        display.setText(String.valueOf(resultado));
        operacionPendiente = "";
        empezarNuevoNumero = true;
    }

    private void limpiarCalculadora() {
        display.setText("0");       // Restablece el texto
        primerNumero = 0;           // Elimina el numero guardado
        operacionPendiente = "";    // Cancela la operacion
        empezarNuevoNumero = true;  // La proxima cifra empieza un número nuevo
    }
}