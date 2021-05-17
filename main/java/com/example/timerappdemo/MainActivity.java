package com.example.timerappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Button button;
    SeekBar seekBar;
    Boolean counteractive=false;
    Button aftergo;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        seekBar=findViewById(R.id.seekBar);
        aftergo=findViewById(R.id.button);

        seekBar.setMax(600);
        seekBar.setProgress(300);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                UpdateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counteractive)
                {
                   resettimer();

                }else {

                    seekBar.setEnabled(false);
                    counteractive = true;
                    aftergo.setText("Stop!");
                    countDownTimer=new CountDownTimer(seekBar.getProgress() * 1000 + 100, 100) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            UpdateTimer((int) millisUntilFinished / 1000);

                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                            //MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.filename);
                            //mediaPlayer.start();// for start the song anything
                            resettimer();
                        }
                    }.start();
                }
            }
        });
    }
    public void UpdateTimer(int secondlist)
    {
        int minutes=secondlist/60;
        int seconds=secondlist-(minutes*60);

        String second= Integer.toString(seconds);
        if (seconds<=9)
        {
            second="0"+second;
        }
        textView.setText(Integer.toString(minutes)+":"+second);
    }
    public void resettimer()
    {
        seekBar.setEnabled(true);
        aftergo.setText("GO!");
        countDownTimer.cancel();
        counteractive=false;
        textView.setText("0:30");
        seekBar.setProgress(30);
    }
}