package com.example.manoe.controle;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private final int PORT = 5005;
    private Socket socket;
    private PrintWriter escritor;
    private Thread thread;
    private boolean isPressedUp = false;
    private boolean isPressedDown = false;
    private boolean isPressedLeft = false;
    private boolean isPressedRight = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edtIP = findViewById(R.id.edt_ip);

        final Button btnCima = findViewById(R.id.btn_cima);
        final Button btnBaixo = findViewById(R.id.btn_baixo);
        final Button btnEsquerda = findViewById(R.id.btn_esquerda);
        final Button btnDireita = findViewById(R.id.btn_direita);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    socket = new Socket(edtIP.getText().toString(), PORT);
                    escritor = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

                    while(true){

                        if (isPressedUp){
                            escritor.println("0");
                        }else{
                            if (isPressedDown){
                                escritor.println("1");
                            }else{
                                if (isPressedLeft){
                                    escritor.println("2");
                                }else{
                                    if (isPressedRight){
                                        escritor.println("3");
                                    }else{
                                        escritor.println("-");
                                    }
                                }
                            }
                        }
                    }

                }catch (Exception e){
                    Log.e("erro", e.toString());
                }
            }
        });

        Button btnConectar = findViewById(R.id.btn_conectar);
        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                thread.start();
            }
        });

        btnCima.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    isPressedUp = true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    isPressedUp = false;
                }

                return false;
            }
        });

        btnBaixo.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    isPressedDown = true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    isPressedDown = false;
                }

                return false;
            }
        });

        btnEsquerda.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    isPressedLeft = true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    isPressedLeft = false;
                }

                return false;
            }
        });

        btnDireita.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    isPressedRight = true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    isPressedRight = false;
                }

                return false;
            }
        });
    }
}
