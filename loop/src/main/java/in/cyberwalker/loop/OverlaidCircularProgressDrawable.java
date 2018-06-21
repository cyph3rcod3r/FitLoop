package in.cyberwalker.loop;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.SparseArray;
import android.view.animation.AnticipateOvershootInterpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * So this does the magic, We are making a drawable
 * instead of view to achieve this circular drawing
 * supply data set to drawable with respective values
 * and let this draw the progress
 */
public class OverlaidCircularProgressDrawable extends Drawable {

    private final static int PI = 180;
    private final static int START_ANGLE = -90;
    private static final int POINT_INCREASED = 0;
    private static final long INITIAL_DELAY = 500;

    private static int ANIMATION_DURATION = 1000;

    /**
     *  it will help in determining radius, Currently fixed to 0.9F
     */
    private float mCircleScale;
    /**
     * Outsourced DataSet for setting values. Currently support two progress on same path with same radius
     */
    private CircleProgressDataSet mCircleProgressDataSet;

    private SparseArray<Float> mProgressValues;
    private final RectF mArcElements;
    private final Paint mPaint;
    private float mRadius;
    private float mRingWidth;
    private Animator mAnimator;
    // for future support of multiple circles on different paths

    private boolean animate = true;
    private final List<DataEntry> entries;

    public OverlaidCircularProgressDrawable(CircleProgressDataSet circleProgressDataSet, float innerCircleScale) {
        mCircleProgressDataSet = circleProgressDataSet;
        mCircleScale = innerCircleScale;
        this.entries = circleProgressDataSet.getEntries();
        mArcElements = new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        initValues();
    }

    /**
     * get the angle from the current progress
     * @param progress
     * @return
     */
    private static float getAngleFromProgress(float progress) {
        return 2f * PI / 100f * progress;
    }

    @Override
    public boolean setVisible(boolean visible, boolean restart) {
        final boolean changed = super.setVisible(visible, restart);
        if (animate) {
            if (mAnimator == null) {
                mAnimator = prepareShowAnimation();
            }
            mAnimator.cancel();
            new Handler().postDelayed(() -> mAnimator.start(), INITIAL_DELAY);
        }else{
            mProgressValues.put(0, mCircleProgressDataSet.progress);
            mProgressValues.put(1, mCircleProgressDataSet.secondaryProgress);
        }
        return changed;
    }

    /**
     * Allow me to animate the circle drawing, otherwise will draw straight
     * @param animate
     */
    public void setAnimate(boolean animate) {
        this.animate = animate;
    }

    /**
     * Default is 1000ms
     * @param duration
     */
    public void setAnimateDuration(int duration) {
        ANIMATION_DURATION = duration;
    }

    @Override
    public void draw(Canvas canvas) {
        final Rect bounds = getBounds();

        // Get the min size
        final int size = Math.min(bounds.height(), bounds.width());
        mRadius = size * mCircleScale / 2;

        if (mProgressValues != null) {
            mRingWidth = ((size - 2 * mRadius)) / 1.2f;
            mRadius = (size / 2) - (mRingWidth / 2);
            for (DataEntry entry : entries) {
                // right now we are drawing one
                drawRingForDataEntry(canvas, entry);
            }
        }

    }

    /**
     * Init values for progress map
     */
    private void initValues() {
        if (mCircleProgressDataSet != null && entries != null) {
            mProgressValues = new SparseArray<>();
            mProgressValues.put(0, 0.0f);
            mProgressValues.put(1, 0.0f);
        }
    }

    /**
     * This is responsible for actual Drawing from the data entry
     * @param canvas
     * @param entry
     */
    private void drawRingForDataEntry(Canvas canvas, DataEntry entry) {

        final Rect bounds = getBounds();

        final float arcX0 = bounds.centerX() - mRadius / 1.35f - mRingWidth;
        final float arcY0 = bounds.centerY() - mRadius / 1.35f - mRingWidth;
        final float arcX = bounds.centerX() + mRadius / 1.35f + mRingWidth;
        final float arcY = bounds.centerY() + mRadius / 1.35f + mRingWidth;

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mRingWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mArcElements.set(arcX0 - POINT_INCREASED, arcY0 - POINT_INCREASED, arcX + POINT_INCREASED, arcY + POINT_INCREASED);

        // full ring arc will be drawn with the empty color
        mPaint.setColor(entry.getEmptyColor());
        canvas.drawArc(mArcElements, 0, 2 * PI, false, mPaint);

        mPaint.setColor(entry.getFillColorSecondary());
        canvas.drawArc(mArcElements, START_ANGLE, getAngleFromProgress(mProgressValues.get(1)), false, mPaint);

        mPaint.setColor(entry.getFillColor());
        canvas.drawArc(mArcElements, START_ANGLE, getAngleFromProgress(mProgressValues.get(0)), false, mPaint);

    }

    /**
     * if instructed prepare animation
     * @return
     */
    private Animator prepareShowAnimation() {
        final AnimatorSet animatorSet = new AnimatorSet();
        final List<Animator> animators = new ArrayList<>();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, mCircleProgressDataSet.progress);
        ValueAnimator valueAnimatorSecondary = ValueAnimator.ofFloat(0.0f, mCircleProgressDataSet.secondaryProgress);
        valueAnimator.addUpdateListener(animation -> {
            mProgressValues.put(0, (Float) animation.getAnimatedValue());
            invalidateSelf();
        });
        valueAnimator.setInterpolator(new AnticipateOvershootInterpolator());
        valueAnimator.setDuration(ANIMATION_DURATION);
        animators.add(valueAnimator);

        valueAnimatorSecondary.addUpdateListener(animation -> {
            mProgressValues.put(1, (Float) animation.getAnimatedValue());
            invalidateSelf();
        });
        valueAnimatorSecondary.setInterpolator(new AnticipateOvershootInterpolator());
        valueAnimatorSecondary.setDuration(ANIMATION_DURATION);
        animators.add(valueAnimatorSecondary);

        animatorSet.playTogether(animators);

        return animatorSet;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return 1 - mPaint.getAlpha();
    }

    public CircleProgressDataSet getDataSet() {
        return mCircleProgressDataSet;
    }

}