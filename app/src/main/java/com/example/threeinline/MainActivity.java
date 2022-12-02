package com.example.threeinline;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnFicha;
    private TicTacToe ticTacToe;
    private TextView txtCasilla;
    private TextView textoVictoria;
    private Button btnReset;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFicha = findViewById(R.id.btnFicha);
        btnReset = findViewById(R.id.btnLimpiar);
        btnReset.setEnabled(false);
        //btnReset.setClickable(false);
        ticTacToe = findViewById(R.id.tablero);
        txtCasilla = findViewById(R.id.lblCasilla);
        textoVictoria = findViewById(R.id.lbltextoVictoria);
        textoVictoria.setVisibility(View.INVISIBLE);




        btnFicha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ticTacToe.alternarFichaActiva();
                if(ticTacToe.juegoGanado()==1){
                    textoVictoria.setVisibility(View.VISIBLE);
                    textoVictoria.setText("¡Has ganado!");
                    textoVictoria.setTextColor(Color.BLUE);
                    btnFicha.setEnabled(false);
                    btnReset.setEnabled(true);

                }else if(ticTacToe.juegoGanado()==2){
                    textoVictoria.setVisibility(View.VISIBLE);
                    textoVictoria.setText("¡Has ganado!");
                    textoVictoria.setTextColor(Color.RED);
                    btnFicha.setEnabled(false);
                    btnReset.setEnabled(true);

                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ticTacToe.limpiar();
                btnFicha.setEnabled(true);
                textoVictoria.setVisibility(View.INVISIBLE);
                ticTacToe.setClickable(true);

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