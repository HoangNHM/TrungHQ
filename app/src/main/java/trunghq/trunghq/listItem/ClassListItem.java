package trunghq.trunghq.listItem;

import android.graphics.Bitmap;

/**
 * Created by vantuegia on 10/1/2015.
 */
public class ClassListItem {
    private Bitmap mBitmap; // chart
    private String mClassNumber;

    public ClassListItem(Bitmap bitmap, String classNumber) {
        this.mBitmap = bitmap;
        this.mClassNumber = classNumber;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public String getClassNumber() {
        return mClassNumber;
    }
}
