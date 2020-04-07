package com.anubys.example.dynamicallygridlayoutdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/** @Autor Created by Anubys on the 17.02.2020 */


public class MyView extends View {
    private static final String TAG = MyView.class.getSimpleName();

    private int idX = 0;
    private int idY = 0;
    private boolean mDownTouch = false;
    private boolean touchOn;

    private OnToggledListener toggledListener;


    //* *********************************************** *
    //*             K O N S T R U K T O R               *
    //* *********************************************** *
    public MyView(Context context) {
        super(context);
        Log.d(TAG, "TAG - MyView - MyView(context)");
        init();
    }

    public MyView(Context context, int x, int y) {
        super(context);
        Log.d(TAG, "TAG - MyView - MyView(context, x, y)");
        idX = x;
        idY = y;
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "TAG - MyView - MyView(context, attributeSet)");
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "TAG - MyView - MyView(context, attributeSet, defStyleAttr)");
        init();
    }


    //* ************************************************ *
    //*           I N I T I A L I Z A T I O N            *
    //* ************************************************ *
    private void init() {
        Log.d(TAG, "TAG - MyView - init()");
        touchOn = false;
    }


    //* ************************************************ *
    //*                 O V E R R I D E                  *
    //* ************************************************ *
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "TAG - MyView - onMeasure()");
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "TAG - MyView - onDraw()");

        if (touchOn) {
            canvas.drawColor(Color.RED);
        } else {
            canvas.drawColor(Color.GRAY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "TAG - MyView - onTouchEvent()");
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchOn = !touchOn;
                invalidate();

                if (toggledListener != null) {
                    toggledListener.OnToggled(this, touchOn);
                }

                mDownTouch = true;

                return (true);
            case MotionEvent.ACTION_UP:
                if (mDownTouch) {
                    mDownTouch = false;
                    performClick();

                    return (true);
                }
        }

        return (false);
    }

    @Override
    public boolean performClick() {
        Log.d(TAG, "TAG - MyView - performClick()");
        super.performClick();
        return (true);
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    public void setOnToggledListener(OnToggledListener toggledListener){
        Log.d(TAG, "TAG - MyView - setOnToggledListener()");
        this.toggledListener = toggledListener;
    }

    public int getIdX(){
        Log.d(TAG, "TAG - MyView - getIdX()");
        return (idX);
    }

    public int getIdY(){
        Log.d(TAG, "TAG - MyView - getIdY()");
        return (idY);
    }
}
