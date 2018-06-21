package in.cyberwalker.loop;

import android.content.Context;
import android.util.AttributeSet;

public class CircularProgressImageView extends android.support.v7.widget.AppCompatImageView {

    private OverlaidCircularProgressDrawable mDrawable;

    public CircularProgressImageView(Context context) {
        super(context);
    }

    public CircularProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleProgressDataSet getDataSet() {
        return mDrawable == null ? null : mDrawable.getDataSet();
    }

    public CircularProgressImageView setDataSet(CircleProgressDataSet circleProgressDataSet, boolean animate) {
        mDrawable = new OverlaidCircularProgressDrawable(circleProgressDataSet, 0.9f);
        mDrawable.setAnimate(animate);
        return this;
    }

    public CircularProgressImageView build() {
        setImageDrawable(mDrawable);
        return this;
    }
}