package com.jakelu.soulmate;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Jake Lu
 */
public class BaGuaView extends View {

    /**
     * 黑色画笔
     */
    private Paint mBlackPaint;
    /**
     * 黑色虚线画笔
     */
    private Paint mDashPaint;
    /**
     * 白色画笔
     */
    private Paint mWhitePaint;
    private Paint mWhitePaintBig;

    /**
     * 背景颜色
     */
    private int mBgColor;

    /**
     * 八卦半径
     */
    private int mRadius;

    private String[] mGua = {"乾", "兌", "離", "震", "坤", "艮", "坎", "巽"};

    public BaGuaView(Context context) {
        this(context, null);
    }

    public BaGuaView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaGuaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BaGuaView, defStyleAttr, 0);

        try {
            mBgColor = array.getColor(R.styleable.BaGuaView_bgColor, Color.GRAY);//默认为棕色
            mRadius = array.getInteger(R.styleable.BaGuaView_radius, 100);//默认半径为100
        } finally {
            array.recycle();
        }

        mWhitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBlackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDashPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWhitePaintBig = new Paint(Paint.ANTI_ALIAS_FLAG);

        mWhitePaint.setColor(Color.WHITE);
        mBlackPaint.setColor(Color.BLACK);
        mDashPaint.setColor(Color.BLACK);
        mWhitePaintBig.setColor(Color.WHITE);
        mDashPaint.setStrokeWidth(8);
        mWhitePaintBig.setStrokeWidth(8);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        //设定宽度
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = getPaddingLeft() + getPaddingRight() + widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = getPaddingLeft() + getPaddingRight() + mRadius * 2;
        }

        //设定高度
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = getPaddingBottom() + getPaddingTop() + heightSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            height = getPaddingBottom() + getPaddingTop() + mRadius * 2;
        }

        setMeasuredDimension(width, height);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    //设置旋转角度
    private float degrees = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //将Canvas坐标移动到画布中心
        canvas.translate(getWidth() / 2, getHeight() / 2);
        //设置背景色
        canvas.drawColor(mBgColor);

        //设置完背景色之后让Canvas旋转
        degrees = degrees + 3;
//        canvas.rotate(degrees);
        canvas.drawCircle(0, 0, mRadius + 3, mBlackPaint);
        //设置绘制区域，这里是以画布中心为坐标原点
        RectF rectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);
        //画左边黑色半圆
        canvas.drawArc(rectF, 90, 180, true, mBlackPaint);
        //画右边白色半圆
        canvas.drawArc(rectF, -90, 180, true, mWhitePaint);

        //绘制两个小圆
        canvas.drawCircle(0, -mRadius / 2, mRadius / 2, mBlackPaint);
        canvas.drawCircle(0, mRadius / 2, mRadius / 2, mWhitePaint);
//
        //绘制两个鱼眼，画两个更小的圆，还以刚鱼头的圆心为圆心，半径为原半径的1/6,这个值可以自己设定
        canvas.drawCircle(0, -mRadius / 2, mRadius / 6, mWhitePaint);
        canvas.drawCircle(0, mRadius / 2, mRadius / 6, mBlackPaint);
        float h = mRadius + 80;
        float v = (float) (h * Math.tan(Math.PI / 8));
        mBlackPaint.setTextSize(50f);
        float h1 = mRadius + 60;
        float h2 = mRadius + 40;
        float h3 = mRadius + 20;
        float v1 = (float) (h1 * Math.tan(Math.PI / 18));
        float v2 = v1 / 3;
        for (int i = 0; i < 8; i++) {
            canvas.drawLine(-v, -h, v, -h, mBlackPaint);
            canvas.drawText(mGua[i], -25, -h - 20, mBlackPaint);
            canvas.drawLine(-v1, -h1, v1, -h1, mDashPaint);
            canvas.drawLine(-v1, -h2, v1, -h2, mDashPaint);
            canvas.drawLine(-v1, -h3, v1, -h3, mDashPaint);
            switch (i) {
                case 1:
                    canvas.drawLine(-v2, -h3, v2, -h3, mWhitePaintBig);
                    break;
                case 2:
                    canvas.drawLine(-v2, -h2, v2, -h2, mWhitePaintBig);
                    break;
                case 3:
                    canvas.drawLine(-v2, -h2, v2, -h2, mWhitePaintBig);
                    canvas.drawLine(-v2, -h3, v2, -h3, mWhitePaintBig);
                    break;
                case 4:
                    canvas.drawLine(-v2, -h1, v2, -h1, mWhitePaintBig);
                    canvas.drawLine(-v2, -h2, v2, -h2, mWhitePaintBig);
                    canvas.drawLine(-v2, -h3, v2, -h3, mWhitePaintBig);
                    break;
                case 5:
                    canvas.drawLine(-v2, -h1, v2, -h1, mWhitePaintBig);
                    canvas.drawLine(-v2, -h2, v2, -h2, mWhitePaintBig);
                    break;
                case 6:
                    canvas.drawLine(-v2, -h1, v2, -h1, mWhitePaintBig);
                    canvas.drawLine(-v2, -h3, v2, -h3, mWhitePaintBig);
                    break;
                case 7:
                    canvas.drawLine(-v2, -h1, v2, -h1, mWhitePaintBig);
                    break;
            }
            canvas.rotate(-45);
        }
//        postInvalidateDelayed(80);
    }

}
