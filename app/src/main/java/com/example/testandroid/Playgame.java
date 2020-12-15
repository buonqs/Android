package com.example.testandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.CompoundButtonCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.nio.file.Files;
import java.util.Timer;
import java.util.TimerTask;

public class Playgame extends AppCompatActivity {
    TextView txtscore,txtplay;
Button btn1,btn2,btn3;
FrameLayout frame;
LinearLayout lnlife;
ImageView imgplanelife1,imgplanelife2,imgplanelife3,imgface1,imgface2,imgdot1,imgdot2,imgdot3;
WindowManager wm;
/*int plane[]={
  R.drawable.airplanedied,
        R.drawable.airportlife
};*/
//private Paint scorePaint=new Paint();

//private Bitmap life[]=new Bitmap[2];

//private Bitmap plane[]=new Bitmap[2];
private float planeX,planeY,planeSpeed;
private float dot1X,dot1Y,dot2X,dot2Y,dot3X,dot3Y;
private Timer timer=new Timer();
private Handler handler=new Handler();
private boolean action=false;
private boolean start =false;
private int framheight,planesize;
private int screenheight,screenwidth;
private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);

        addControls();

        addEvents();

    }


    private void addControls() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        txtplay=findViewById(R.id.txtplay);
        txtscore=findViewById(R.id.txtscore);
        imgplanelife1 = findViewById(R.id.imgplaneife1);
        imgplanelife2 = findViewById(R.id.imgplanelife2);
        imgplanelife3 = findViewById(R.id.imgplanelife3);
        imgdot1=findViewById(R.id.dot1);
        imgdot2=findViewById(R.id.dot2);
        imgdot3=findViewById(R.id.dot3);
        lnlife=findViewById(R.id.lnlife);
        frame=findViewById(R.id.frame);
        imgface1 = findViewById(R.id.plane1);
        imgface2 = findViewById(R.id.plane2);

        wm=getWindowManager();
        Display dp=wm.getDefaultDisplay();
        Point size=new Point();
        dp.getSize(size);

        screenwidth=size.x;
        screenheight=size.y;

        imgdot1.setX(-80.0f);
        imgdot1.setY(-80.0f);
        imgdot2.setX(-80.0f);
        imgdot2.setY(-80.0f);
        imgdot3.setX(-80.0f);
        imgdot3.setY(-80.0f);

        txtscore.setText("Điểm của tui : "+score);
    //    txtplay.setVisibility(View.INVISIBLE);

       // planeY=500.0f;

/*scorePaint.setColor(Color.RED);
scorePaint.setTextSize(30);
scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
scorePaint.setAntiAlias(true);*/

/*life[0]= BitmapFactory.decodeResource(getResources(),R.drawable.airportlife);
        life[1]= BitmapFactory.decodeResource(getResources(),R.drawable.airplanedied);*/
//plane=BitmapFactory.decodeResource(getResources(),R.drawable.testplane1);
    }

    private void addEvents() {

    }
public void addChay(){

        tinhdiem();
        
        dot1X-=12;
        if(dot1X<0){
dot1X= screenwidth+20;
dot1Y=(float)Math.floor(Math.random()*(framheight-imgdot1.getHeight()));
        }
        imgdot1.setX(dot1X);
        imgdot1.setY(dot1Y);

    dot2X-=20;
    if(dot2X<0){
        dot2X= screenwidth+5000;
        dot2Y=(float)Math.floor(Math.random()*(framheight-imgdot1.getHeight()));
    }
    imgdot2.setX(dot2X);
    imgdot2.setY(dot2Y);

    dot3X-=20;
    if(dot3X<0){
        dot3X= screenwidth+10;
        dot3Y=(float)Math.floor(Math.random()*(framheight-imgdot1.getHeight()));
    }
    imgdot3.setX(dot3X);
    imgdot3.setY(dot3Y);

        if(action){
            planeY-=20;
        }else {
            planeY+=20;
        }

        if(planeY<0) {
            planeY= 0;
        }

        if(planeY>framheight-planesize) {
            planeY= framheight-planesize;
        }

    imgface1.setY(planeY);
        txtscore.setText("Điểm của tui : "+score);
}

    private void tinhdiem() {

        float dot1CenterX= dot1X+imgdot1.getWidth() / 2.0f;
        float dot1CenterY= dot1Y+imgdot1.getHeight() / 2.0f;
        if(0<=dot1CenterX && dot1CenterX<=planesize && planeY<=dot1CenterY && dot1CenterY<= planeY+planesize){
dot1X=-100.0f;
score+=10;
        }

        float dot2CenterX= dot2X+imgdot2.getWidth() / 2.0f;
        float dot2CenterY= dot2Y+imgdot2.getHeight() / 2.0f;
        if(0<=dot2CenterX && dot2CenterX<=planesize && planeY<=dot2CenterY && dot2CenterY<= planeY+planesize){
            dot2X=-100.0f;
            score+=40;
        }

        float dot3CenterX= dot3X+imgdot3.getWidth() / 2.0f;
        float dot3CenterY= dot3Y+imgdot3.getHeight() / 2.0f;
        if(0<=dot3CenterX && dot3CenterX<=planesize && planeY<=dot3CenterY && dot3CenterY<= planeY+planesize) {
        if(timer!=null)
        {
timer.cancel();
timer=null;
        }
            Intent intent = new Intent(getApplicationContext(),EndGame.class);
        intent.putExtra("ĐIỂM",score);
            startActivity(intent);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!start) {
    start=true;

    framheight=frame.getHeight();
    planeY=imgface1.getY();
    planesize=imgface1.getHeight();

    txtplay.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            addChay();
                        }

                    });
                }
            },0,20);
        }else {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {

                action=true;
            }else if(event.getAction()==MotionEvent.ACTION_UP)
            {
                action=false;
            }
        }


        return super.onTouchEvent(event);
    }

    public void xulydung(View view) {
if(action==false)
{
action=true;
timer.cancel();
timer=null;
btn1.setText("Resume");
}else {
action=false;
btn1.setText("Pause");
timer=new Timer();
timer.schedule(new TimerTask() {
    @Override
    public void run() {
        handler.post(new Runnable() {
            @Override
            public void run() {
           addChay();
            }
        });

    }
},0,20);
}
    }

    public void xulymogamemoi(View view) {
        startActivity(new Intent(getApplicationContext(),Playgame.class));
    }

    public void xulythoat(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Playgame.this);
        builder.setTitle("Xác Nhận Trở Về Màn Hình Chính");
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