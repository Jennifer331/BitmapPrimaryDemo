package com.example.administrator.overlaybitmaps;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * Created by Lei Xiaoyue on 2015-11-03.
 */
public class MyActivity extends Activity {
    public static final String TAG = "overlayBitmaps";
    private Bitmap resBitmaps[];
    private Bitmap destBitmap;
    private BitmapWorker mBitmapWorker;

    private ImageView imageView;
    private Button mLayoutBtn,mRoundBtn,mRound2Btn,mPortableBtn,mDrawableBtn;
    private PortableBitmap mPortableBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);

        initWidget();

        initListener();
    }

    private void initWidget(){
        imageView = (ImageView) findViewById(R.id.imageview);
        mLayoutBtn = (Button)findViewById(R.id.overlay);
        mRoundBtn = (Button)findViewById(R.id.round1);
        mRound2Btn = (Button)findViewById(R.id.round2);
        mPortableBtn = (Button)findViewById(R.id.portable);
        mDrawableBtn = (Button)findViewById(R.id.mydrawable);
        mPortableBitmap = (PortableBitmap)findViewById(R.id.portablebitmap);
        mBitmapWorker = new BitmapWorker();
    }

    private void initListener(){
        mLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                mPortableBitmap.setVisibility(View.INVISIBLE);
                if (setResBitmaps() && mBitmapWorker != null) {
                    destBitmap = mBitmapWorker.pileUpBitmaps(MyActivity.this,resBitmaps);
                    if (destBitmap != null) {
                        imageView.setImageBitmap(destBitmap);
                    }
                }
            }
        });
        mRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                mPortableBitmap.setVisibility(View.INVISIBLE);
                if (setResBitmaps() && mBitmapWorker != null) {
                    imageView.setImageBitmap(mBitmapWorker.getRoundedCornerBitmap1(resBitmaps[0], 10.0f));
                }
            }
        });
        mRound2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                mPortableBitmap.setVisibility(View.INVISIBLE);
                if (setResBitmaps() && mBitmapWorker != null) {
                    imageView.setImageBitmap(mBitmapWorker.getRoundedCornerBitmap2(resBitmaps[0]));
                }
            }
        });
        mPortableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
                mPortableBitmap.setVisibility(View.VISIBLE);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                mPortableBitmap.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.maple_leaf, options));
            }
        });
        mDrawableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.VISIBLE);
                mPortableBitmap.setVisibility(View.INVISIBLE);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.maple_leaf);
                ImageDrawable myDrawable = new ImageDrawable(bitmap,700,900);
                imageView.setImageDrawable(myDrawable);
            }
        });
    }

    private boolean setResBitmaps() {
      // get drawbles
        Bitmap bitmaps[] = new Bitmap[4];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 5;

        bitmaps[1] = BitmapFactory.decodeResource(getResources(),R.drawable.angle,options);
        bitmaps[2] = BitmapFactory.decodeResource(getResources(),R.drawable.maple_leaf,options);
        bitmaps[3] = BitmapFactory.decodeResource(getResources(),R.drawable.seal,options);
        bitmaps[0] = BitmapFactory.decodeResource(getResources(),R.drawable.vegetables,options);
        resBitmaps = bitmaps;
        return true;
    }
}
