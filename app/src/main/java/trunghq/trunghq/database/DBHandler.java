package trunghq.trunghq.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vantuegia on 9/26/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    public static final String TAG = DBHandler.class.getSimpleName();

    public static final String DATABASE_NAME = "myDB.db";
    public static final String CLASS_TABLE_NAME = "Class";
    public static final String CLASS_COLUMN_ID = "id";
    public static final String CLASS_COLUMN_PERCENT = "percent";

//    SQLiteDatabase mWriteDB;
//    SQLiteDatabase mReadDB;

    public DBHandler(Context context) {
        // Create a Database
        super(context, DATABASE_NAME, null, 1);
        Log.i(TAG, "DBHandler()");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate");

        db.execSQL("create table if not exists " + CLASS_TABLE_NAME + " ( " +
                CLASS_COLUMN_ID + " integer primary key autoincrement, " +
                CLASS_COLUMN_PERCENT + " integer not null" +
                " )");

//        mWriteDB = this.getWritableDatabase();
//        mReadDB = this.getReadableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onCreate");
    }

    public boolean insertClassPercent(int iPercent) {
        ContentValues contentValues = new ContentValues();
        // id: autoincrement
        contentValues.put(CLASS_COLUMN_PERCENT, iPercent);
        long ret = this.getWritableDatabase().insert(CLASS_TABLE_NAME, null, contentValues);
        return -1 != ret; // nice
    }

    /**
     * get row from table
     * @param strTableName
     * @param id
     * @return Cursor
     */
    public Cursor getData(String strTableName, int id) {
        if (0 == id) {
            return this.getReadableDatabase().rawQuery("select * from " + strTableName, null);
        } else {
            return this.getReadableDatabase().rawQuery("select * from " + strTableName + " where " + CLASS_COLUMN_ID + " = " + id, null);
        }
    }
}
