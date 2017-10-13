package com.example.lipeng_ds3.techniquenews.animator;

import android.animation.TypeEvaluator;

/**
 * Created by lipeng-ds3 on 2017/10/13.
 */

public class PointerEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Pointer startPointer = (Pointer)startValue;
        Pointer endPointer = (Pointer)endValue;
        //根据fraction计算当前动画的x 和y 的值，
        float x = startPointer.getX() + fraction * (endPointer.getX() - startPointer.getX());
        float y = startPointer.getY() + fraction * (endPointer.getY() - startPointer.getY());
        return new Pointer(x, y);
    }
}
