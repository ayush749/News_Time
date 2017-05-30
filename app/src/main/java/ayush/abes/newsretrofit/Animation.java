package ayush.abes.newsretrofit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;


import com.github.florent37.viewanimator.AnimationBuilder;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import java.util.Locale;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.LV;

public class Animation extends AppCompatActivity {
    ImageView animated_image;
    TextView textView;
    ViewAnimator viewAnimator=new ViewAnimator();
    AnimationBuilder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        getSupportActionBar().hide();
        animated_image = (ImageView) findViewById(R.id.mountain);
        textView = (TextView) findViewById(R.id.text_animated);
        viewAnimator
                .animate(animated_image)
                .translationY(-1000, 0)
                .alpha(0, 1)
                .andAnimate(textView)
                .dp().translationX(-20, 0)
                .decelerate()
                .duration(2000)
                .thenAnimate(animated_image)
                .scale(1f, 0.5f, 1f)
                .accelerate()
                .duration(1000)
                .start();
simpleAnimation();
//        viewAnimator.onStop(new AnimationListener.Stop() {
//            @Override
//            public void onStop() {
//                Intent intent=new Intent(Animation.this,MainActivity.class);
//                startActivity(intent);
//            }
//        }).start();
    }
    protected void simpleAnimation() {
        ViewAnimator.animate(animated_image)
                .translationY(-1000, 0)
                .alpha(0, 1)
                .andAnimate(textView)
                .translationX(-200, 0)
                .interpolator(new DecelerateInterpolator())
                .duration(2000)

                .thenAnimate(animated_image)
                .scale(1f, 0.5f, 1f)
                .interpolator(new AccelerateInterpolator())
                .duration(1000)

                .start();


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                viewAnimator.cancel();
                Intent intent=new Intent(Animation.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        }, 3000);
    }
}