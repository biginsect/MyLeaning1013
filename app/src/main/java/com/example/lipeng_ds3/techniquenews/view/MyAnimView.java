package com.example.lipeng_ds3.techniquenews.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.lipeng_ds3.techniquenews.animator.ColorEvaluator;
import com.example.lipeng_ds3.techniquenews.animator.DecelerateAccelerateInterpolator;
import com.example.lipeng_ds3.techniquenews.animator.Pointer;
import com.example.lipeng_ds3.techniquenews.animator.PointerEvaluator;

/**
 * Created by lipeng-ds3 on 2017/10/13.
 */

public class MyAnimView extends View {
    public static final float RADIUS = 50f;
    private Pointer currentPointer;
    private Paint mPaint;

    //颜色设置 #RRGGBB格式
    private String color;
    /****************如果写了属性的get，set方法，ObjectAnimator就能识别这个属性**********************/
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        //将字符串color转换成Color类型，并赋值给画笔mPaint
        mPaint.setColor(Color.parseColor(color));
        //重新调用onDraw，刷新试图
        invalidate();
    }

    public MyAnimView(Context context, AttributeSet att){
        super(context, att);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
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
        Pointer startPointer = new Pointer(getWidth() / 2, RADIUS);
        //view的右下角
        Pointer endPointer = new Pointer(getWidth() / 2, getHeight() - RADIUS);
        ValueAnimator animator = ValueAnimator.ofObject(new PointerEvaluator(), startPointer, endPointer);
        //监听动画的过程，当Pointer发生变化的时候回调onAnimationUpdate，对currentPointer重新赋值
        //调用invalidate()---->onDraw()，currentPointer的坐标发生改变，因此绘制的位置也改变--->因此实现动画的平移效果
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPointer = (Pointer)animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.setInterpolator(new DecelerateAccelerateInterpolator());
        ObjectAnimator transferAnim = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), "#0000FF", "#FF0000");
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(animator).with(transferAnim);
        //组合动画时间
        animSet.setDuration(3000);
        animSet.start();
    }


}
