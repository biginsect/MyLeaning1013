package com.example.lipeng_ds3.techniquenews.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.lipeng_ds3.techniquenews.animator.Pointer;
import com.example.lipeng_ds3.techniquenews.animator.PointerEvaluator;

/**
 * Created by lipeng-ds3 on 2017/10/13.
 */

public class MyAnimView extends View {
    public static final float RADIUS = 30f;
    private Pointer currentPointer;
    private Paint mPaint;

    public MyAnimView(Context context, AttributeSet att){
        super(context, att);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPointer == null){
            currentPointer = new Pointer(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        }else {
            drawCircle(canvas);
        }
    }

    //在currentPointer(随动的)的位置上画一个半径为RADIUS的圆
    //由于属性动画一直在动，因此需要检测当前位置是否构图，没有则创建
    private void drawCircle(Canvas canvas){
        float x = currentPointer.getX();
        float y = currentPointer.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation(){
        //view的左上角
        Pointer startPointer = new Pointer(RADIUS, RADIUS);
        //view的右下角
        Pointer endPointer = new Pointer(getWidth() - RADIUS, getHeight() - RADIUS);
        ValueAnimator animator = ValueAnimator.ofObject(new PointerEvaluator(), startPointer, endPointer);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPointer = (Pointer)animation.getAnimatedValue();
                invalidate();
            }
        });
        //动画时间3s
        animator.setDuration(3000);
        animator.start();
    }
}
