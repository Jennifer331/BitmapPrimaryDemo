package com.example.administrator.overlaybitmaps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

/**
 * Created by Lei Xiaoyue on 2015-11-03.
 */
public class BitmapWorker {
    public BitmapWorker() {

    }

    public Bitmap pileUpBitmaps(Context context, Bitmap bitmaps[]) {
        if (bitmaps.length == 0) {
            return null;
        }

        // Bitmap newBitmap =
        // Bitmap.createBitmap(bitmaps[0]).copy(Bitmap.Config.ARGB_8888, true);
        Bitmap newBitmap = Bitmap.createBitmap(600, 1000, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        Paint paint = new Paint();
        canvas.translate(100, 0);
        for (int i = 0; i < bitmaps.length; i++) {
            int width = bitmaps[i].getWidth();
            int height = bitmaps[i].getHeight();
            canvas.drawBitmap(bitmaps[i], 0, 0, paint);
            canvas.translate(-30, 30);
        }
        canvas.restore();

        // for test
        // newBitmap =
        // BitmapFactory.decodeResource(context.getResources(),R.drawable.angle);
        return newBitmap;
    }

    public Bitmap getRoundedCornerBitmap1(Bitmap bitmap, float cornerRadius) {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth() + 6, bitmap.getHeight() + 6,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);

        final int color = 0xff424242;
        int shadowRadius = 3;
        final Rect imageRect = new Rect(shadowRadius, shadowRadius, bitmap.getWidth(),
                bitmap.getHeight());
        final RectF rectF = new RectF(imageRect);

        // canvas.drawARGB(0,0,0,0);
        // final Paint paint = new Paint();
        // paint.setAntiAlias(true);
        // paint.setColor(color);
        // canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);

        Paint shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setColor(Color.BLACK);
        shadowPaint.setShadowLayer((float) shadowRadius, 2.0f, 2.0f, Color.BLACK);
        canvas.drawOval(rectF, shadowPaint);

        shadowPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, imageRect, shadowPaint);

        return newBitmap;

    }

    public Bitmap getRoundedCornerBitmap2(Bitmap bitmap) {

        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth() + 6, bitmap.getHeight() + 6,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);

        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        paint.setShadowLayer(6.0f, 2.0f, 2.0f, Color.RED);

        canvas.drawOval(0.0f, 0.0f, newBitmap.getWidth(), newBitmap.getHeight(), paint);

        return newBitmap;

    }
}
