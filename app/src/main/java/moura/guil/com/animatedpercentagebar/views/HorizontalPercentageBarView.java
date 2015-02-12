package moura.guil.com.animatedpercentagebar.views;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

public class HorizontalPercentageBarView extends BaseInterpolatedAnimationView {

    private static final Interpolator COLOR_INTERPOLATOR
            = new AccelerateInterpolator(10f);

    private Paint paint;

    private int progressColor;

    private int backgroundColor;

    private int excessUsageColor;

    private boolean showAsExcess;

    private ArgbEvaluator argbEvaluator;

    public HorizontalPercentageBarView(Context context) {
        super(context);
        init(context);
    }

    public HorizontalPercentageBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HorizontalPercentageBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        progressColor = context.getResources().getColor(android.R.color.holo_blue_dark);
        excessUsageColor = context.getResources().getColor(android.R.color.holo_red_dark);
        backgroundColor = context.getResources().getColor(android.R.color.black);
        argbEvaluator = new ArgbEvaluator();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int w = canvas.getWidth();
        final int h = canvas.getHeight();
        final float interpolatedValue = getInterpolatedValue();

        final float progressXEnd = w * interpolatedValue;

        // Draw progress
        if (showAsExcess) {
            paint.setColor((Integer) argbEvaluator.evaluate(
                            COLOR_INTERPOLATOR.getInterpolation(interpolatedValue),
                            progressColor,
                            excessUsageColor)
            );
        } else {
            paint.setColor(progressColor);
        }

        canvas.drawRect(0, 0, progressXEnd, h, paint);

        // Draw background color
        paint.setColor(backgroundColor);
        canvas.drawRect(progressXEnd, 0, w, h, paint);
    }

    public void setPercentage(float percentage) {
        //setPercentage(percentage, Float.compare(percentage, 1f) >= 0);
        setPercentage(percentage, true);
    }

    public void setPercentage(float percentage, boolean showAsExcess) {
        this.showAsExcess = showAsExcess;
        animateTo(clamp(percentage));
    }

    private static float clamp(float percentage) {
        return Math.min(1f, Math.max(0f, percentage));
    }
}
