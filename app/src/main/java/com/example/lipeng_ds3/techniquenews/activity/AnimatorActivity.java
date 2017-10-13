package com.example.lipeng_ds3.techniquenews.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lipeng_ds3.techniquenews.R;

/**
 * Created by lipeng-ds3 on 2017/10/13.
 */

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener{

    private Button startAnimatorBtn;
    private TextView animatorTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animator_activity);
        startAnimatorBtn = (Button)findViewById(R.id.animator_Btn);
        startAnimatorBtn.setOnClickListener(this);
        animatorTV = (TextView)findViewById(R.id.ani_tv);

        startAnimatorFromXML();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.animator_Btn:
                startAnimator();
                break;
        }
    }

    //代码设置启动属性动画
    private void startAnimator(){
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(animatorTV, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(animatorTV, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(animatorTV, "alpha", 1f, 0f ,1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(fadeInOut).with(rotate).after(moveIn);
        animSet.setDuration(3000)
                .start();
    }

    //启动XML设置好的属性动画
    private void startAnimatorFromXML(){
        Animator animator = AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.animator_activity);
        animator.setTarget(animatorTV);
        animator.start();
    }
}
