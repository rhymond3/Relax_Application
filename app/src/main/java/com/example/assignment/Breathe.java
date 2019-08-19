package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment.R;
import com.github.florent37.viewanimator.AnimationBuilder;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

public class Breathe extends AppCompatActivity {

    private Chronometer chronometer;
    private long stopOffset;
    private boolean running;
    private ImageView imageView;
    private TextView guidetext;
    private ImageButton back;

    ViewAnimator animator = new ViewAnimator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathe);

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Time %s");
        imageView = findViewById(R.id.imageCircle);
        guidetext = findViewById(R.id.guideTxt);
        back = findViewById(R.id.backButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        starting();

        animator.animate(imageView)
                .translationY(-1000, 0)
                .alpha(0,1)
                .dp().translationX(-20, 0)
                .decelerate()
                .duration(2000)
                .thenAnimate(imageView)
                .scale(1f, 0.5f, 1f)
                .rotation(360)
                .accelerate()
                .duration(1000)
                .start();

        

        /*ViewAnimator
                .animate(imageView)
                .translationY(-1000, 0)
                .alpha(0,1)
                .dp().translationX(-20, 0)
                .decelerate()
                .duration(2000)
                .thenAnimate(imageView)
                .scale(1f, 0.5f, 1f)
                .rotation(360)
                .accelerate()
                .duration(1000)
                .start();*/
    }

    private void starting(){
        ViewAnimator
                .animate(guidetext)
                .scale(0,1)
                .duration(2000)
                .onStart(new AnimationListener.Start() {
                    @Override
                    public void onStart() {
                        guidetext.setText("Breathe");
                    }
                })
                .start();

    }

    //the method for when user press the "start" button
    public void startChronometer(View view) {
        if (!running){
            chronometer.setBase(SystemClock.elapsedRealtime()- stopOffset);
            chronometer.start();
            running = true;
        }

        animator.animate(imageView)
                .alpha(0,1)
                .scale(0.05f,1.0f,0.05f)
                .onStart(new AnimationListener.Start() {
                    @Override
                    public void onStart() {
                        guidetext.setText("Inhale...Exhale");
                    }
                })
                .decelerate()
                .duration(10000)
                .thenAnimate(imageView)
                .scale(0.05f,1.0f,0.05f)
                .rotation(190)
                .repeatCount(Animation.INFINITE)
                .accelerate()
                .duration(6000)
                .start();

    }

    //the method for when user press the "stop" button
    public void stopChronometer(View view) {

        if (running){
            chronometer.stop();
            stopOffset = SystemClock.elapsedRealtime() - chronometer.getBase();



            ViewAnimator animation = ViewAnimator
                    .animate(imageView)
                    .onStop(new AnimationListener.Stop() {
                        @Override
                        public void onStop() {
                            if (running){
                                chronometer.stop();
                                animator.cancel();

                            guidetext.setText("Good Job");
                        }}
                    })
                    .start();
            animation.cancel();
        }
            running = false;
    }

}