package trunghq.trunghq.item;

/**
 * Created by vantuegia on 10/1/2015.
 */
public class ClassItem {
    private int mPercent; // chart
    private int[] mYVals;
    private String mClassName;

    public ClassItem(String className, int percent, int[] yVals) {
        this.mPercent = percent;
        this.mClassName = className;
        this.mYVals = yVals;
    }

    public int getPercent() {
        return mPercent;
    }

    public String getClassName() {
        return mClassName;
    }

    public int[] getYVals() {
        return mYVals;
    }
}
