package com.example.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

public class TicTacToe extends View {
    private static int vacia = 0;
    private static int FICHA_0 = 1;
    private static int FICHA_X = 2;

    private int fichaActiva;
    private final int[][] tablero = new int[3][3];
    private int xColor = Color.RED;
    private int oColor = Color.BLUE;

    private final Paint pBorde;
    private final Paint pMarca0;
    private final Paint pMarcaX;

    private onCasillaSeleccionadaListener listener;


    public TicTacToe(Context ctx, AttributeSet attrs){
        super(ctx, attrs);
        this.pBorde = new Paint();
        pBorde.setStyle(Paint.Style.STROKE);
        pBorde.setColor(Color.BLACK);
        pBorde.setStrokeWidth(4f);

        this.pMarca0 = new Paint();
        pMarca0.setStyle(Paint.Style.STROKE);
        pMarca0.setStrokeWidth(10f);

        this.pMarcaX = new Paint();
        pMarcaX.setStyle(Paint.Style.STROKE);
        pMarcaX.setStrokeWidth(10f);

        this.fichaActiva=2;


    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int ancho = this.calcularAncho(widthMeasureSpec);
        int alto = this.calcularAlto(heightMeasureSpec);
        if (ancho < alto) {
            alto = ancho;
        } else {
            ancho = alto;
        }

        this.setMeasuredDimension(ancho, alto);
    }

    private final int calcularAlto(int limitesSpec) {
        int res = 100;
        int modo = MeasureSpec.getMode(limitesSpec);
        int limite = MeasureSpec.getSize(limitesSpec);
        if (modo == MeasureSpec.AT_MOST) {
            res = limite;
        } else if (modo == MeasureSpec.EXACTLY) {
            res = limite;
        }

        return res;
    }

    private final int calcularAncho(int limitesSpec) {
        int res = 100;
        int modo = MeasureSpec.getMode(limitesSpec);
        int limite = MeasureSpec.getSize(limitesSpec);
        if (modo == MeasureSpec.AT_MOST) {
            res = limite;
        } else if (modo == MeasureSpec.EXACTLY) {
            res = limite;
        }

        return res;
    }

    public final void limpiar() {
        int i = 0;

        for(byte var2 = 2; i <= var2; ++i) {
            int j = 0;

            for(byte var4 = 2; j <= var4; ++j) {
                this.tablero[i][j] = 0;
            }
        }

    }

    protected void onDraw(@Nullable Canvas canvas) {
        int alto = this.getMeasuredHeight();
        int ancho = this.getMeasuredWidth();
        Intrinsics.checkNotNull(canvas);
        canvas.drawLine((float)(ancho / 3), 0.0F, (float)(ancho / 3), (float)alto, this.pBorde);
        canvas.drawLine((float)(2 * ancho / 3), 0.0F, (float)(2 * ancho / 3), (float)alto, this.pBorde);
        canvas.drawLine(0.0F, (float)(alto / 3), (float)ancho, (float)(alto / 3), this.pBorde);
        canvas.drawLine(0.0F, (float)(2 * alto / 3), (float)ancho, (float)(2 * alto / 3), this.pBorde);
        canvas.drawRect(0.0F, 0.0F, (float)ancho, (float)alto, this.pBorde);
        this.pMarca0.setColor(this.oColor);
        this.pMarcaX.setColor(this.xColor);
        int fil = 0;

        for(byte var5 = 2; fil <= var5; ++fil) {
            int col = 0;

            for(byte var7 = 2; col <= var7; ++col) {
                if (this.tablero[fil][col] == 2) {
                    canvas.drawLine((float)(col * (ancho / 3)) + (float)(ancho / 3) * 0.1F, (float)(fil * (alto / 3)) + (float)(alto / 3) * 0.1F, (float)(col * (ancho / 3)) + (float)(ancho / 3) * 0.9F, (float)(fil * (alto / 3)) + (float)(alto / 3) * 0.9F, this.pMarcaX);
                    canvas.drawLine((float)(col * (ancho / 3)) + (float)(ancho / 3) * 0.1F, (float)(fil * (alto / 3)) + (float)(alto / 3) * 0.9F, (float)(col * (ancho / 3)) + (float)(ancho / 3) * 0.9F, (float)(fil * (alto / 3)) + (float)(alto / 3) * 0.1F, this.pMarcaX);
                } else if (this.tablero[fil][col] == 1) {
                    canvas.drawCircle((float)(col * (ancho / 3) + ancho / 6), (float)(fil * (alto / 3) + alto / 6), (float)(ancho / 6) * 0.8F, this.pMarca0);
                }
            }
        }

    }

    public boolean onTouchEvent(@Nullable MotionEvent event) {
        Intrinsics.checkNotNull(event);
        int fil = (int)(event.getY() / (float)(this.getMeasuredHeight() / 3));
        int col = (int)(event.getX() / (float)(this.getMeasuredWidth() / 3));
        this.tablero[fil][col] = this.fichaActiva;
        onCasillaSeleccionadaListener listener = this.listener;
        if (listener != null) {
            listener.onCasillaSeleccionada(fil, col);
        }

        this.invalidate();
        return super.onTouchEvent(event);
    }

    public final void setOnCasillaSeleccionadaListener(@NotNull onCasillaSeleccionadaListener l) {
        Intrinsics.checkNotNullParameter(l, "l");
        this.listener = l;
    }

    public final void setOnCasillaSeleccionadaListener(@NotNull final Function2 seleccion) {
        Intrinsics.checkNotNullParameter(seleccion, "seleccion");
        this.listener = (onCasillaSeleccionadaListener)(new onCasillaSeleccionadaListener() {
            public void onCasillaSeleccionada(int fila, int columna) {
                seleccion.invoke(fila, columna);
            }
        });
    }

    public final void setCasilla(int fil, int col, int valor) {
        this.tablero[fil][col] = valor;
    }

    public final int getCasilla(int fil, int col) {
        return this.tablero[fil][col];
    }

    public final void alternarFichaActiva() {
        this.fichaActiva = this.fichaActiva == 1 ? 2 : 1;
    }

    public final onCasillaSeleccionadaListener getListener() {
        return this.listener;
    }

    public void setListener(onCasillaSeleccionadaListener listener) {
        this.listener = listener;
    }


}
