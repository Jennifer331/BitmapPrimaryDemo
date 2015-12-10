package com.example.administrator.overlaybitmaps;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by Lei Xiaoyue on 2015-11-13.
 */
public class ImageDrawable extends Drawable {
    private final static String TAG = "ImageDrawable";
    private final static int DEFAULT_LIMIT_WIDTH = 0;
    private final static int DEFAULT_LIMIT_HEIGHT = 0;

    private float mDisplayPartX = 1;
    private float mDisplayPartY = 1;

    private Bitmap mDisplayBitmap;

    private Paint mPaint;

    private int mLimitWidth;
    private int mLimitHeight;
    private int mBitmapWidth;
    private int mBitmapHeight;

    public ImageDrawable() {
        this(null, DEFAULT_LIMIT_WIDTH, DEFAULT_LIMIT_HEIGHT);
    }

    public ImageDrawable(Bitmap bitmap, int width, int height) {
        super();
        mDisplayBitmap = bitmap;
        mLimitWidth = width;
        mLimitHeight = height;
        if (null != mDisplayBitmap) {
            mBitmapWidth = mDisplayBitmap.getWidth();
            mBitmapHeight = mDisplayBitmap.getHeight();
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        Log.v(TAG, "limit width :" + mLimitWidth + "limit height :" + mLimitHeight);
        Log.v(TAG,"bitmap width :" + mBitmapWidth + "bitmap height :" + mBitmapHeight);
    }

    public Bitmap getBitmap() {
        return mDisplayBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mDisplayBitmap = bitmap;
    }

    public void setLimitWidth(int mLimitWidth) {
        this.mLimitWidth = mLimitWidth;
    }

    public int getLimitHeight() {
        return mLimitHeight;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(prepareBitmap(), 0, 0, mPaint);
    }

    private Bitmap prepareBitmap() {
        int scaleX = mLimitWidth;
        int scaleY = mLimitHeight;

        if (0 != mBitmapHeight && 0 != mLimitHeight
                && mBitmapWidth / mBitmapHeight > mLimitWidth / mLimitHeight) {
            scaleX = Math.round(mBitmapWidth * ((float)mLimitHeight / (float)mBitmapHeight));
            scaleY = mLimitHeight;
            mDisplayPartX = mLimitWidth/(float)scaleX;
            mDisplayPartY = 1;
        } else {
            scaleX = mLimitWidth;
            scaleY = Math.round(mBitmapHeight * ((float)mLimitWidth / (float)mBitmapWidth));
            mDisplayPartX = 1;
            mDisplayPartY = mLimitHeight/(float)scaleY;
        }
        Bitmap result = Bitmap.createScaledBitmap(mDisplayBitmap,scaleX,scaleY,false);

        int x = 0;
        int y = 0;
        if (1 != mDisplayPartX) {
            x += (scaleX - mLimitWidth) / 2;
        }
        if (1 != mDisplayPartY) {
            y += (scaleY - mLimitHeight) / 2;
        }
        return Bitmap.createBitmap(result, x, y, mLimitWidth, mLimitHeight);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }
}
