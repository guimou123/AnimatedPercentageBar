package moura.guil.com.animatedpercentagebar.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class BaseInterpolatedAnimationView extends View {

    private static final long DEFAULT_ANIMATION_DURATION = 1000;

    private static final long DEFAULT_START_DELAY = 100;

    private static final Interpolator DEFAULT_INTERPOLATOR = new DecelerateInterpolator(2.0f);

    private float mInterpolatedValue;

    public BaseInterpolatedAnimationView(Context context) {
        super(context);
    }

    public BaseInterpolatedAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseInterpolatedAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressWarnings("unused")
    public float getInterpolatedValue() {
        return mInterpolatedValue;
    }

    @SuppressWarnings("unused")
    public void setInterpolatedValue(float interpolatedValue) {
        mInterpolatedValue = interpolatedValue;
        invalidate();
    }

    public long getAnimationDuration() {
        return DEFAULT_ANIMATION_DURATION;
    }

    public long getStartDelay() {
        return DEFAULT_START_DELAY;
    }

    protected Interpolator getInterpolator() {
        return DEFAULT_INTERPOLATOR;
    }

    protected void onAnimationEnd() {
        // Override is functionality is needed
    }

    protected void animateTo(float target) {

        final ObjectAnimator anim = ObjectAnimator.ofFloat(this, "interpolatedValue", target);
        anim.setInterpolator(getInterpolator());
        anim.setDuration(getAnimationDuration());
        anim.setStartDelay(getStartDelay());
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                BaseInterpolatedAnimationView.this.onAnimationEnd();
            }
        });
        anim.start();
    }
}
