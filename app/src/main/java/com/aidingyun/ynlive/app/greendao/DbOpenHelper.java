package com.aidingyun.ynlive.app.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/5/8.
 */
public class DbOpenHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "ynhistorybase.db";
    private static final int DATABASE_VERSION = 1;
    private static DbOpenHelper instance;

    public static final String CREATE_HISTORY_TABLE = "CREATE TABLE "
            + HistoryDao.TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HistoryDao.COLUMN_NAME_ID + " INTEGER, "
            + HistoryDao.COLUMN_NAME_NAME + " VARCHAR)";


    private static final String CREATE_WATCH_RECORD_TABLE = "CREATE TABLE "
            + WatchRecordDao.TABLE_NAME + "("
            + WatchRecordDao.COLUMN_NAME_ID + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_USER_ID + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_USERNAME + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_PICTURE + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_ROOM_ID + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_TITLE + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_LIVE_TYPE + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_FANS_COUNT + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_TYPE + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_CONTENT + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_ORGANIZATIONID + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_SCORE + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_PAY_COIN + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_SECOND_TYPEID + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_TYPEID + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_ATTENTION_COUNT + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_ATTENTION_ID + " INTEGER, "
            + WatchRecordDao.COLUMN_NAME_COURSEID + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_STATUS + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_USER_STATUS + " INTEGER, "
            + WatchRecordDao.COLUMN_NAME_LIVE_STATUS + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_START_DATA + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_END_DATA + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_PAID + " INTEGER, "
            + WatchRecordDao.COLUMN_NAME_ALLOW + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_ALLOW_NAME + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_LIVE_TIME + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_SECTION_COUNT + " INTEGER, "
            + WatchRecordDao.COLUMN_NAME_TYPE_NAME + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_SECOND_TYPE_NAME + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_COURSE_TYPE + " INTEGER, "
            + WatchRecordDao.COLUMN_NAME_SHARE_URL + " VARCHAR, "
            + WatchRecordDao.COLUMN_NAME_TIME + " VARCHAR) ";


    private DbOpenHelper(Context context)
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DbOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_HISTORY_TABLE);
        db.execSQL(CREATE_WATCH_RECORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // 更改数据库版本
        onCreate(db);
    }

    public void closeDb()
    {
        if (instance != null) {
            try {
                SQLiteDatabase db = instance.getWritableDatabase();
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }
}
