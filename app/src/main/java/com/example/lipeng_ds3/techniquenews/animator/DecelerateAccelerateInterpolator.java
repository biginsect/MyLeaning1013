package com.example.lipeng_ds3.techniquenews.animator;

import android.animation.TimeInterpolator;

/**
 * Created by lipeng-ds3 on 2017/10/17.
 * 利用正弦和余弦在π/2前后的数值变化，实现先减速后加速
 */

public class DecelerateAccelerateInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float result;
        if (input <= 0.5){
            result = (float) Math.sin(Math.PI * input) / 2;
        }else {
            result = (float) (2 - Math.sin(Math.PI * input)) / 2;
        }
        return result;
    }
}
