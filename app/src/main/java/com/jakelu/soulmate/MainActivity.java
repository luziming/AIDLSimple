package com.jakelu.soulmate;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaGuaView mTaiji = (BaGuaView) findViewById(R.id.bg);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mTaiji, "scaleX", 1f,0.5f,0,0.5f,1f);
        scaleX.setRepeatCount(ValueAnimator.INFINITE);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTaiji, "scaleY", 1f,0.5f,0,0.5f,1f);
        scaleY.setRepeatCount(ValueAnimator.INFINITE);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mTaiji, "rotation", 0,360f);
        rotate.setRepeatCount(ValueAnimator.INFINITE);
        rotate.setRepeatMode(ValueAnimator.RESTART);
    }
}
