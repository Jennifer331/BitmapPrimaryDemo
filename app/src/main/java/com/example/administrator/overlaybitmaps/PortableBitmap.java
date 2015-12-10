package com.example.administrator.overlaybitmaps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Lei Xiaoyue on 2015-11-04.
 */
public class PortableBitmap extends View{
    private Bitmap mBitmap;
    private float mRegion[];//{left,top,right,bottom}

    private float mLastX = 0;
    private float mLastY = 0;

    private final static int LEFT = 0;
    private final static int TOP = 1;
    private final static int RIGHT = 2;
    private final static int BOTTOM = 3;

    public PortableBitmap(Context context) {
        this(context, null);
    }

    public PortableBitmap(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRegion = new float[4];
    }

//    public PortableBitmap(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }

//    public PortableBitmap(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;

        mRegion[LEFT] = getWidth()/2 - mBitmap.getWidth()/2;
        mRegion[RIGHT] = getWidth()/2 + mBitmap.getWidth()/2;
        mRegion[TOP] = getHeight()/2 - mBitmap.getHeight()/2;
        mRegion[BOTTOM] = getWidth()/2 + mBitmap.getHeight()/2;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(mBitmap, mRegion[LEFT], mRegion[TOP], paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();

        if (currentX < mRegion[LEFT] || currentX > mRegion[RIGHT]
                || currentY < mRegion[TOP] || currentY > mRegion[BOTTOM]) {
            return super.onTouchEvent(event);
        }

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                mLastX = currentX;
                mLastY = currentY;
                break;
            }
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                mRegion[LEFT] += (currentX - mLastX);
                mRegion[TOP] += (currentY - mLastY);

                if (mRegion[LEFT] < 0) {
                    mRegion[LEFT] = 0;
                }
                if (mRegion[TOP] < 0) {
                    mRegion[TOP] = 0;
                }
                mRegion[RIGHT] = mRegion[LEFT] + mBitmap.getWidth();
                mRegion[BOTTOM] = mRegion[TOP] + mBitmap.getHeight();
                mLastX = currentX;
                mLastY = currentY;
                invalidate();
                break;
            }
        }
        return true;
    }
}
