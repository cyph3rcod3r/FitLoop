package in.cyberwalker.loop;

import java.util.Arrays;
import java.util.List;

public class CircleProgressDataSet {

    public float progress;
    // set secondary progress to 0 if not required
    public float secondaryProgress;
    public int progressColor;
    public int secondaryProgressColor;
    public int bgColor;

    public CircleProgressDataSet(int progress, int secondaryProgress, int progressColor, int secondaryProgressColor, int bgColor) {
        this.progress = progress;
        this.secondaryProgress = secondaryProgress;
        this.progressColor = progressColor;
        this.secondaryProgressColor = secondaryProgressColor;
        this.bgColor = bgColor;
    }

    /**
     * Right now only returning one Entry
     * @return
     */
    public List<DataEntry> getEntries() {
        return Arrays.asList(new DataEntry(progress, secondaryProgress, "", progressColor, bgColor, secondaryProgressColor));
    }
}