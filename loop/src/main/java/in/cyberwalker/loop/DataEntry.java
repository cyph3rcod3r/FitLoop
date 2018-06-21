package in.cyberwalker.loop;

import android.graphics.drawable.Drawable;

public class DataEntry {

    private float mProgress, mProgress2;
    private Drawable mDrawable;
    private String mText;
    private int mFillColor, mFillColorSecondary;
    private int mEmptyColor;

    /**
     * @param progress   : progress value in percentage (%)
     * @param drawable   : drawable to draw over the progress bar
     * @param fillColor  : progression color
     * @param emptyColor : empty progression color
     */
    public DataEntry(float progress, Drawable drawable, int fillColor, int emptyColor) {
        mProgress = progress;
        mDrawable = drawable;
        mFillColor = fillColor;
        mEmptyColor = emptyColor;
        mText = null;
    }

    /**
     * @param progress   : progress value in percentage (%)
     * @param text       : text to draw over the progress bar
     * @param fillColor  : progression color
     * @param emptyColor : empty progression color
     */
    public DataEntry(float progress, float progress2, String text, int fillColor, int emptyColor, int fillColorSecondary) {
        mProgress = progress;
        mProgress2 = progress2;
        mText = text;
        mFillColor = fillColor;
        mFillColorSecondary = fillColorSecondary;
        mEmptyColor = emptyColor;
        mDrawable = null;
    }

    public int getFillColorSecondary() {
        return mFillColorSecondary;
    }

    public void setFillColorSecondary(int mFillColorSecondary) {
        this.mFillColorSecondary = mFillColorSecondary;
    }

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float progress) {
        mProgress = progress;
    }

    public float getProgress2() {
        return mProgress2;
    }

    public void setProgress2(float mProgress2) {
        this.mProgress2 = mProgress2;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
        mText = null;
    }

    public int getFillColor() {
        return mFillColor;
    }

    public void setFillColor(int fillColor) {
        mFillColor = fillColor;
    }

    public int getEmptyColor() {
        return mEmptyColor;
    }

    public void setEmptyColor(int emptyColor) {
        mEmptyColor = emptyColor;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
        mDrawable = null;
    }
}