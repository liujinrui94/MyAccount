package com.caipiao.framework.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

import com.caipiao.R;


public class BezierRoundView extends View implements ViewPager.OnPageChangeListener {
    public BezierRoundView(Context context) {
        this(context, null);
    }

    public BezierRoundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierRoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics());  //默认设置15dp

        //获得我们所定义的自定义样式属性
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BezierRoundView, defStyleAttr, 0);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            if (attr == R.styleable.BezierRoundView_color_bez) {
                color_bez = array.getColor(i, color_bez);
            } else if (attr == R.styleable.BezierRoundView_color_stroke) {
                color_stroke = array.getColor(i, color_stroke);
            } else if (attr == R.styleable.BezierRoundView_time_animator) {
                time_animator = array.getInteger(i, time_animator);
            } else if (attr == R.styleable.BezierRoundView_round_count) {
                default_round_count = array.getInteger(i, default_round_count);
            } else if (attr == R.styleable.BezierRoundView_radius) {
                mRadius = array.getDimensionPixelSize(attr, mRadius);
            }
        }
        array.recycle();
        init();
    }

    private int time_animator = 600;  //动画时间
    private Matrix matrix_bounceL;   //将向右弹的动画改为向左
    private int color_bez = 0xfffe626d;
    private int color_stroke = Color.GRAY;

    private void init() {

        DEFAULT_HEIGHT = mRadius * 3;
        mBezPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBezPaint.setColor(color_bez);//默认QQ糖的颜色为粉红色
        mBezPaint.setStyle(Paint.Style.FILL);

        mRoundStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRoundStrokePaint.setColor(color_stroke);
        mRoundStrokePaint.setStyle(Paint.Style.STROKE);
        mRoundStrokePaint.setStrokeWidth(2);

        mBezPath = new Path();

        //y轴一样
        float bezFactor = 0.551915024494f;
        p5 = new PointF(mRadius * bezFactor, mRadius);
        p6 = new PointF(0, mRadius);
        p7 = new PointF(-mRadius * bezFactor, mRadius);
        //y轴一样
        p0 = new PointF(0, -mRadius);
        p1 = new PointF(mRadius * bezFactor, -mRadius);
        p11 = new PointF(-mRadius * bezFactor, -mRadius);
        //x轴一样
        p2 = new PointF(mRadius, -mRadius * bezFactor);
        p3 = new PointF(mRadius, 0);
        p4 = new PointF(mRadius, mRadius * bezFactor);
        //x轴一样
        p8 = new PointF(-mRadius, mRadius * bezFactor);
        p9 = new PointF(-mRadius, 0);
        p10 = new PointF(-mRadius, -mRadius * bezFactor);

        matrix_bounceL = new Matrix();
        matrix_bounceL.preScale(-1, 1);
    }

    private int DEFAULT_WIDTH;
    private int DEFAULT_HEIGHT;
    private int default_round_count = 4;   //默认圆球的数量

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (DEFAULT_WIDTH == 0) {
            WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            DEFAULT_WIDTH = wm.getDefaultDisplay().getWidth();
        }

        int width = measureSize(1, DEFAULT_WIDTH, widthMeasureSpec);
        int height = measureSize(1, DEFAULT_HEIGHT, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mWidth = w;
        mHeight = h;
        initCountPos();
    }

    /**
     * 测绘measure
     *
     * @param specType    1为宽， 其他为高
     * @param contentSize 默认值
     */
    private int measureSize(int specType, int contentSize, int measureSpec) {
        int result;
        //获取测量的模式和Size
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = Math.min(contentSize, specSize);
        } else {
            result = contentSize;

            if (specType == 1) {
                // 根据传入方式计算宽
                result += (getPaddingLeft() + getPaddingRight());
            } else {
                // 根据传入方式计算高
                result += (getPaddingTop() + getPaddingBottom());
            }
        }

        return result;
    }

    private Paint mBezPaint;
    private Paint mRoundStrokePaint;
    private int mWidth;
    private int mHeight;
    private int mRadius;
    private Path mBezPath;

    private PointF p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;


    private float rRadio = 1;  //P2,3,4 x轴倍数
    private float lRadio = 1;  //P8,9,10倍数
    private float tbRadio = 1;  //y轴缩放倍数


    private float[] bezPos; //记录每一个圆心x轴的位置
    private int curPos = 0;  //当前圆的位置
    private int nextPos = 0; //圆要到达的下一个位置


    /**
     * 关联ViewPager，监听scroll进行改变bezRound
     */
    public void attachViewPage(ViewPager vPage) {
        if (vPage.getAdapter() != null) {
            this.default_round_count = vPage.getAdapter().getCount();
            initCountPos();
        }
        vPage.addOnPageChangeListener(this);
    }

    private void initCountPos() {
        bezPos = new float[default_round_count];
        for (int i = 0; i < default_round_count; i++) {
            bezPos[i] = mWidth / (default_round_count + 1) * (i + 1);
        }
    }


    private float animatedValue;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(0, mHeight / 2);


        mBezPath.reset();
        for (int i = 0; i < default_round_count; i++) {
            canvas.drawCircle(bezPos[i], 0, mRadius - 2, mRoundStrokePaint);   //绘制圆框
        }
        if (animatedValue == 1) {
            canvas.drawCircle(bezPos[nextPos], 0, mRadius, mBezPaint);
            return;
        }

        canvas.translate(bezPos[curPos], 0);

        float disL = 0.5f;//离开圆的阈值
        if (0 < animatedValue && animatedValue <= disL) {
            rRadio = 1f + animatedValue * 2;                         //  [1,2]
            lRadio = 1f;
            tbRadio = 1f;
        }

        float disM = 0.8f;//最大值的阈值
        if (disL < animatedValue && animatedValue <= disM) {
            rRadio = 2 - range0Until1(disL, disM) * 0.5f;          //  [2,1.5]
            lRadio = 1 + range0Until1(disL, disM) * 0.5f;          // [1,1.5]
            tbRadio = 1 - range0Until1(disL, disM) / 3;           // [1 , 2/3]
        }

        float boundRadio = 0.55f;//进入另一个圆的回弹效果
        float disA = 0.9f;//到达下个圆的阈值
        if (disM < animatedValue && animatedValue <= disA) {
            rRadio = 1.5f - range0Until1(disM, disA) * 0.5f;     //  [1.5,1]
            lRadio = 1.5f - range0Until1(disM, disA) * (1.5f - boundRadio);      //反弹效果，进场 内弹boundRadio
            tbRadio = (range0Until1(disM, disA) + 2) / 3;        // [ 2/3,1]
        }
        if (disA < animatedValue && animatedValue <= 1f) {
            rRadio = 1;
            tbRadio = 1;
            lRadio = boundRadio + range0Until1(disA, 1) * (1 - boundRadio);     //反弹效果，饱和
        }
        if (animatedValue == 1 || animatedValue == 0) {  //防止极其粗暴的滑动
            rRadio = 1f;
            lRadio = 1f;
            tbRadio = 1f;
        }


        boolean isTrans = false;
        float transX = (nextPos - curPos) * (mWidth / (default_round_count + 1));
        if (disL <= animatedValue && animatedValue <= disA) {
            isTrans = true;

            transX = transX * (animatedValue - disL) / (disA - disL);
        }
        if (disA < animatedValue && animatedValue <= 1) {
            isTrans = true;
        }
        if (isTrans) {
            canvas.translate(transX, 0);
        }

        bounce2RightRound();


        if (!direction) {
            mBezPath.transform(matrix_bounceL);
        }
        canvas.drawPath(mBezPath, mBezPaint);

        if (isTrans) {
            canvas.save();
        }

    }

    /**
     * 通过 path 将向右弹射的动画绘制出来
     * 如果要绘制向左的动画，只要设置path的transform(matrix)即可
     */
    private void bounce2RightRound() {
        mBezPath.moveTo(p0.x, p0.y * tbRadio);
        mBezPath.cubicTo(p1.x, p1.y * tbRadio, p2.x * rRadio, p2.y, p3.x * rRadio, p3.y);
        mBezPath.cubicTo(p4.x * rRadio, p4.y, p5.x, p5.y * tbRadio, p6.x, p6.y * tbRadio);
        mBezPath.cubicTo(p7.x, p7.y * tbRadio, p8.x * lRadio, p8.y, p9.x * lRadio, p9.y);
        mBezPath.cubicTo(p10.x * lRadio, p10.y, p11.x, p11.y * tbRadio, p0.x, p0.y * tbRadio);
        mBezPath.close();
    }


    /**
     * 将animatedValue值域转化为[0,1]
     *
     * @param minValue 大于等于
     * @param maxValue 小于等于
     * @return 根据当前 animatedValue,返回 [0,1] 对应的数值
     */
    private float range0Until1(float minValue, float maxValue) {
        return (animatedValue - minValue) / (maxValue - minValue);
    }


    private boolean direction; //方向 , true是位置向右(0->1)

    /**
     * @param position       当前cur位置，如果当前是1，手指右滑（vPage向左滑动）那就是0， 左滑至下一个位置才为2
     * @param positionOffset [0,1) ,到达下一个pos就置为0
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        String TAG = "BezierRoundView";
        Log.w(TAG, "onPageScrolled positionOffset==" + positionOffset);

        animatedValue = positionOffset;

        direction = ((position + positionOffset) - curPos > 0);  //运动方向。 true为右边(手往左滑动)
        nextPos = direction ? curPos + 1 : curPos - 1;  //右 +1   左 -1

        if (!direction)   //如果是向左
            animatedValue = 1 - animatedValue;  //让 animatedValue 不管是左滑还是右滑，都从[0,1)开始计算

        if (positionOffset == 0) {
            curPos = position;
            nextPos = position;
        }

        //快速滑动的时候，positionOffset有可能不会置于0
        if (direction && position + positionOffset > nextPos) {  //向右，而且
            curPos = position;
            nextPos = position + 1;
        } else if (!direction && position + positionOffset < nextPos) {
            curPos = position;
            nextPos = position - 1;
        }


        Log.w(TAG, "onPageScrolled animatedValue==" + animatedValue);
        Log.w(TAG, "onPageScrolled direction==" + direction);
        Log.w(TAG, "onPageScrolled curPos==" + curPos);
        Log.w(TAG, "onPageScrolled nextPos==" + nextPos);
        Log.w(TAG, "onPageScrolled position==" + position);

        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void setAnimDuration(int time) {
        time_animator = time;
    }

    /**
     * 设置圆的半径
     */
    public void setRadius(int radius) {
        this.mRadius = radius;
        init();
    }

    /**
     * 设置bez 圆的数量，默认4个
     */
    public void setRoundCount(int count) {
        this.default_round_count = count;
        initCountPos();
    }

    /**
     * 设置bez 圆的颜色，默认粉红色
     */
    public void setBezRoundColor(int roundColor) {
        color_bez = roundColor;
        mBezPaint.setColor(roundColor);
    }


    /**
     * 圆框的颜色
     */
    public void setStrokeColor(int strokeColor) {
        color_stroke = strokeColor;
        mRoundStrokePaint.setColor(strokeColor);
    }

}