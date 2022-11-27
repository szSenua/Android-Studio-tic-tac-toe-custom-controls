package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnFicha;
    private TicTacToe ticTacToe;
    private TextView txtCasilla;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFicha = findViewById(R.id.btnFicha);
        ticTacToe = findViewById(R.id.tablero);
        txtCasilla = findViewById(R.id.lblCasilla);

        btnFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticTacToe.alternarFichaActiva();
            }
        });

        ticTacToe.setOnCasillaSeleccionadaListener(new onCasillaSeleccionadaListener() {
            @Override
            public void onCasillaSeleccionada(int fila, int columna) {
                txtCasilla.setText("Ultima casilla seleccionada " + fila + " " + columna );
            }
        });
    }
}