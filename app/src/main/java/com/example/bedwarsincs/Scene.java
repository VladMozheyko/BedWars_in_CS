package com.example.bedwarsincs;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class Scene extends SurfaceView implements SurfaceHolder.Callback {
    private Thread gameThread;
    private SurfaceHolder holder;
    private boolean isRunning = false;
    private Paint paint;
    private Bitmap bitmap;
    private float imgX, imgY;
    private float imgXFinger, imgYFinger;




    public Scene(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bed_red);
        imgX = getWidth()/4;
        imgY = getHeight()/4;




    }







    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        drawImage();
    }

    private void drawImage() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            canvas.drawBitmap(bitmap, getWidth()/2- bitmap.getWidth()/2, getHeight()/2-bitmap.getHeight()/2, null);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.solder1);
            canvas.drawBitmap(bitmap, imgX, imgY, null);
        }
        catch (Exception ex){

        }
        finally {
            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawCharacter() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.solder1);
            canvas.drawBitmap(bitmap, imgX, imgY, null);
        }
        catch (Exception ex){

        }
        finally {
            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                imgXFinger = event.getX();
                imgYFinger = event.getY();
                updatePosition();
        }
        return true;
    }

    private void updatePosition() {
        imgX = imgX + bitmap.getWidth()/3 + (imgX - imgXFinger)/5;
        imgY = imgY + bitmap.getHeight()/3- (imgY + imgYFinger)/5;
        drawCharacter();
    }
}
