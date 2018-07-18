package com.iigo.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import static android.view.View.MeasureSpec.AT_MOST;

/**
 * @author SamLeung
 * @e-mail 729717222@qq.com
 * @github https://github.com/samlss
 * @csdn https://blog.csdn.net/Samlss
 * @description The pin ball loading view.
 */
public class PinBallLoadingView  extends View{

    //速度比例，值越大移动越快,不超过内圆半径的一半，默认为5
    // (speed ratio, the bigger the value, the faster the speed, not exceeding half of the inner circle radius, the default is 5)
    private float movingSpeedRatio = 5f;

    //外圈的绘边大小，默认为5，不超过外圆半径的十分之一，默认为5
    // (Outer circle width, not more than one tenth of the radius of the outer circle, the default is 5)
    private float outerCircleStrokeWidth = 5f;

    //外圆画笔
    // (the paint of the outer circle)
    private Paint outerCirclePaint;

    //外圆半径
    // (the radius of outer circle)
    private float outerCircleRadius;

    //内圆轨迹半径
    // (the inner circle track radius)
    private float innerCircleTrackRadius;

    //内圈圆轨迹路径
    // (the inner circle track path)
    private Path innerCircleTrackPath;

    //内圆路径测量
    // (the inner circle PathMeasure)
    private PathMeasure innerCirclePathMeasure;

    //可移动小球的画笔
    private Paint movingCirclePaint;

    //可移动小球的半径，默认为10，不超过内圆轨迹半径的五分之一
    // (the moving circle radius, the default is 10, not more than one-fifth of the radius of the inner circle)
    private float movingCircleRadius;

    //可移动轨迹路径
    //The path of moving track
    private Path movingTrackPath;

    //可移动轨迹路径测量
    //the PathMeasure of moving track
    private PathMeasure movingTrackPathMeasure;

    //内圆的innerCirclePathMeasure的距离
    // (The distance of #innerCirclePathMeasure.getLength())
    private float innerCircleDistance;

    //可移动轨迹movingTrackPathMeasure的距离
    // (The distance of #movingTrackPathMeasure.getLength())
    private float movingTrackDistance;

    //外圆描边颜色，默认为白色
    // (The color of the outer circle stroke, the default is white)
    private int outerCircleStrokeColor = Color.WHITE;

    //可移动圆的颜色，默认为红色
    // (The color of the moving circle, the default is red)
    private int movingCircleColor = Color.RED; //


    //是否启动动画，默认为true
    // (Whether to start the animation, the default is true)
    private boolean startAnimation = true;

    public PinBallLoadingView(Context context) {
        super(context);

        init();
    }

    public PinBallLoadingView(Context context,AttributeSet attrs) {
        super(context, attrs);
        parseAttrs(attrs);

        init();
    }

    public PinBallLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttrs(attrs);

        init();
    }

    public PinBallLoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        parseAttrs(attrs);
        init();
    }

    private void init(){
        outerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerCirclePaint.setStyle(Paint.Style.STROKE);
        outerCirclePaint.setColor(outerCircleStrokeColor);

        innerCircleTrackPath = new Path();
        innerCirclePathMeasure = new PathMeasure();

        movingCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        movingCirclePaint.setColor(movingCircleColor);
        movingCirclePaint.setStyle(Paint.Style.FILL);

        movingTrackPath = new Path();
        movingTrackPathMeasure = new PathMeasure();
    }


    private void parseAttrs(AttributeSet attrs){
        TypedArray typedArray   = getContext().obtainStyledAttributes(attrs, R.styleable.PinBallLoadingView);
        movingCircleRadius      = typedArray.getFloat(R.styleable.PinBallLoadingView_moving_circle_radius, 15f);
        outerCircleStrokeColor  = typedArray.getColor(R.styleable.PinBallLoadingView_outer_circle_stoke_color, Color.WHITE);
        movingCircleColor       = typedArray.getColor(R.styleable.PinBallLoadingView_moving_circle_color, Color.RED);
        movingSpeedRatio        = typedArray.getFloat(R.styleable.PinBallLoadingView_moving_speed_ratio, 5f);
        outerCircleStrokeWidth  = typedArray.getFloat(R.styleable.PinBallLoadingView_outer_circle_stoke_width, 5f);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int modeWidth  = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //没有指定宽高,使用了wrap_content,则手动指定宽高为MATCH_PARENT
        // (No width or height is specified, wrap_content is used, and the width and height are manually specified as MATCH_PARENT)
        if (modeWidth == AT_MOST && modeHeight == AT_MOST){
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            setLayoutParams(layoutParams);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float referRadius = w > h ? h : w;
        if (outerCircleStrokeWidth > (referRadius / 10f)){
            outerCircleStrokeWidth = referRadius / 10f;
        }

        outerCirclePaint.setStrokeWidth(outerCircleStrokeWidth);

        //根据宽高获取外圆圈的半径大小
        // (Get the radius of the outer circle according to the width and height)
        outerCircleRadius = (referRadius - 2 * outerCircleStrokeWidth) / 2f;

        if (movingCircleRadius > (outerCircleRadius / 5f)){
            movingCircleRadius = outerCircleRadius / 5f;
        }

        innerCircleTrackRadius = outerCircleRadius - movingCircleRadius - outerCircleStrokeWidth / 2;

        innerCircleTrackPath.reset();
        innerCircleTrackPath.addCircle(w / 2, h / 2, innerCircleTrackRadius, Path.Direction.CW);

        if (movingSpeedRatio > (innerCircleTrackRadius / 2)){
            movingSpeedRatio = innerCircleTrackRadius / 2;
        }

        innerCirclePathMeasure.setPath(innerCircleTrackPath, false);


        innerCircleDistance = getInitedRandomDistance();

        //宽高产生变化的时候，重新计算第一个点的开始位置
        // (Recalculate the start position of the first point when this view size changed.)
        float[] startPos = getInnerCirclePos(innerCircleDistance);
        calculateNextTrack(startPos);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(canvas.getWidth() / 2, canvas.getHeight() / 2, outerCircleRadius, outerCirclePaint);

        float[] pos = getPos(movingTrackPathMeasure, movingTrackDistance);

        canvas.drawCircle(pos[0],
                pos[1],
                movingCircleRadius,
                movingCirclePaint);


        movingTrackDistance += movingSpeedRatio;

        if (movingTrackDistance >= movingTrackPathMeasure.getLength()) {
            movingTrackDistance = 0;
            calculateNextTrack(pos);
        }

        //如果该标志为false，则不做动画
        // (If the flag is false, no animation)
        if (startAnimation){
            invalidate();
        }
    }

    private void calculateNextTrack(float[] startPos){
        innerCircleDistance = (innerCircleDistance + getRandom((int)innerCircleTrackRadius, (int)innerCircleTrackRadius * 2)) % innerCirclePathMeasure.getLength();

        float[] endPos = getInnerCirclePos(innerCircleDistance);

        movingTrackPath.reset();
        movingTrackPath.moveTo(startPos[0], startPos[1]);
        movingTrackPath.lineTo(endPos[0], endPos[1]);

        movingTrackPathMeasure.setPath(movingTrackPath, false);
    }

    private float getInitedRandomDistance(){
        return new Random().nextInt((int) innerCirclePathMeasure.getLength());
    }

    private float[] getInnerCirclePos(float distance){
        return getPos(innerCirclePathMeasure, distance);
    }

    private float[] getPos(PathMeasure pathMeasure, float distance){
        float[] pos = new float[2];
        float[] tan = new float[2];

        if (pathMeasure != null){
            pathMeasure.getPosTan(distance, pos, tan);
        }

        return pos;
    }

    public static int getRandom(int start,int end){
        if(end > start){
            Random random = new Random();
            return random.nextInt(end - start) + start;
        }
        return 0;
    }

    /**
     * 停止动画
     *
     * stop animation
     * */
    public void stop(){
        startAnimation = false;
    }

    /**
     * 开始动画
     *
     * start animation
     * */
    public void start(){
        startAnimation = true;
        invalidate();
    }

    /**
     * 代码设置可移动圆的颜色
     *
     * set the color of the moving circle.
     * */
    public void setMovingCircleColor(int movingCircleColor) {
        this.movingCircleColor = movingCircleColor;
        movingCirclePaint.setColor(movingCircleColor);
        invalidate();
    }

    /**
     * 代码设置外圆描边颜色
     *
     * set the stroke color of the outer circle
     * */
    public void setOuterCircleStrokeColor(int outerCircleStrokeColor) {
        this.outerCircleStrokeColor = outerCircleStrokeColor;
        outerCirclePaint.setColor(outerCircleStrokeColor);
        invalidate();
    }
}
