package com.example.daniloikeda.zuptestemobile;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by DaniloIkeda on 06/01/2017.
 */
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);

        ImageView zupIcon = (ImageView)findViewById(R.id.zupImage);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_down);
        zupIcon.setAnimation(animation);

        ImageView background = (ImageView)findViewById(R.id.background);
        AlphaAnimation animation1 = new AlphaAnimation(1.0f, 0.3f);
        animation1.setStartOffset(1000);
        animation1.setDuration(1000);
        animation1.setFillAfter(true);
        background.startAnimation(animation1);

        Handler handler = new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}
