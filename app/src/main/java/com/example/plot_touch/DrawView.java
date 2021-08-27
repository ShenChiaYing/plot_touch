package com.example.plot_touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class DrawView extends View {
    private double speed_x;
    private double speed_y;
    private int x = 200;               //initial position of circle
    private int y = 200;
    private int x1 = 300;
    private int y1 = 300;
    Context context;

    public DrawView(Context context, AttributeSet attrs, int defstyleAttr){
        super(context, attrs, defstyleAttr);
        this.context = context;
    }

    public DrawView(Context context, AttributeSet attrs){
        super (context, attrs);
        this.context = context;
    }

    public DrawView(Context context){
        super(context);
        this.context = context;
    }
    Paint paint = new Paint();
    Paint paint1 = new Paint();
    Point point  = new Point();
    int width;
    int height;
    int currentX;
    int currentY;

    //初始化数据
    private  void init() {
        paint.setDither(true);
        paint.setAlpha(128);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        width = getResources().getDisplayMetrics().widthPixels;
        height = getResources().getDisplayMetrics().heightPixels;
        currentX = width / 2;
        currentY = height / 2;
        point.set(width / 2, height / 2);


    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(x, y, 40, paint);
        paint.setColor(Color.RED);
        canvas.drawCircle(currentX, currentY, 30, paint);

        paint.setColor(Color.BLACK);
        canvas.drawLines(new float[]{0, height / 2, width, height / 2, width / 2, 0, width / 2, height,}, paint);

        paint1.setStyle(Paint.Style.STROKE);
        paint1.setColor(Color.BLACK);
        canvas.drawCircle(width / 2, height / 2, width / 2, paint1);

    }
    public void initdata(int x1, int y1) {
        currentX = currentX - x1;
        currentY = currentY - y1;
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();
        if (currentX>=40 && currentY>=150 && currentX<=width-40 && currentY<=height-150){
            invalidate();
        }
        invalidate();
    }


    public boolean onTouchEvent(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                x = (int) event.getX();
                y = (int) event.getY();
                postInvalidate();
                break;
        }
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();
        if (x>=30 && y>=30 && x<=width-30 && y<=height-90){
            invalidate();
        }
        return true;
    }

}

