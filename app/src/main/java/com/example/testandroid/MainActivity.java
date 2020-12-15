package com.example.testandroid;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

   Handler handler;
    private final static long Interval=30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
    }

    public void xulyplay(View view) {

      /*  Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setContentView(R.layout.activity_playgame);
                    }
                });
            }
        }, 0, Interval);*/

        Intent intent = new Intent(MainActivity.this,Playgame.class);
        startActivity(intent);

    }

    public void xulythoat(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Xác Nhận Thoát");
        builder.setIcon(android.R.drawable.ic_dialog_dialer);
        builder.setMessage("Thoát?");
        builder.setPositiveButton("Đúng", new DialogInterface.OnClickListener() {
            @Override
            public void
            onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("NOOOOOOOOOOO", new DialogInterface.OnClickListener() {
            @Override
            public void
            onClick(DialogInterface dialogInterface, int i) { dialogInterface.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false)
        ;
        dialog.show();
    }
}