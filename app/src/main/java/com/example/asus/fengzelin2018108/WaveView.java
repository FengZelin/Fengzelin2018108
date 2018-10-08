package com.example.asus.fengzelin2018108;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WaveView extends View {
    private float fai=0;
    private Path path1;
    private Path path2;
    private PaintFlagsDrawFilter drawFilter;
    private Paint paint2;
    private Paint paint1;

    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private OnViewListener listener;

    public void setListener(OnViewListener listener) {
        this.listener = listener;
    }

    public interface OnViewListener{
        void onChanged(float y);
    }

    private void init() {
        paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        paint1.setAntiAlias(true);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(5);


        paint2 = new Paint();
        paint2.setColor(Color.BLUE);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeWidth(5);
        paint2.setAlpha(60);

        path1 = new Path();
        path2 =new Path();

        drawFilter = new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        double v = 2 * Math.PI / getMeasuredWidth();

        fai -=0.1f;

        int A=getMeasuredHeight() / 2;

        path1.reset();
        path2.reset();

        path1.moveTo(getLeft(),getBottom());
        path2.moveTo(getLeft(),getBottom());

        for(int x=0;x<=getMeasuredWidth();x+=20){
            float y1=A * (float) Math.sin(v * x +fai)+A;
            float y2=-A * (float) Math.sin(v * x +fai)+A;

            if(x>getMeasuredWidth() / 2 && x< getMeasuredWidth() / 2 + 10){
                listener.onChanged(y2);
            }
            path1.lineTo(x,y1);
            path2.lineTo(x,y2);
        }
        path1.lineTo(getWidth(),getBottom());
        path2.lineTo(getWidth(),getBottom());

        canvas.drawPath(path1,paint1);
        canvas.drawPath(path2,paint2);

        postInvalidateDelayed(50);

    }
}
