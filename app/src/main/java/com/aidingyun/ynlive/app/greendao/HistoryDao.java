package com.aidingyun.ynlive.app.greendao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2017/5/9.
 */
public class HistoryDao
{
    public static final String TABLE_NAME = "tab_history";

    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_NAME = "name";

    private DbOpenHelper dbHelper;

    public HistoryDao(Context context)
    {
        dbHelper = DbOpenHelper.getInstance(context);
    }

    /**
     * 插入一条记录
     * @return 插入记录的id -1表示插入不成功
     */
    public long insertRecord(String searchStr)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        boolean isInsert = true;
        String[] searchRecord = queryAllSearchRecord();
        for (int i = 0; i < searchRecord.length; i++) {
            if (searchStr.equals(searchRecord[i])) {
                isInsert = false;
                break;
            }
        }
        long id = -1;
        if (db != null && isInsert)
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_NAME, searchStr);
            id = db.insert(TABLE_NAME, null, values);
        }
        return id;
    }

    /**
     * 查询所有搜索字段
     * @return 所有记录的list集合
     */
    public String[] queryAllSearchRecord()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if (db != null)
        {
            Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            int count = cursor.getCount();
            String[] searchRecord = new String[count];
            if (count > 0)
            {
                cursor.moveToFirst();
                for (int i = 0; i < count; i++)
                {
                    searchRecord[i] = cursor.getString(cursor
                            .getColumnIndex(COLUMN_NAME_NAME));
                    cursor.moveToNext();
                }
            }
            return searchRecord;
        }
        else
        {
            return new String[0];
        }
    }

    /**
     *  删除一条记录
     * @param roomId
     */
    public void deleteRecord(String roomId)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String whereClauses = COLUMN_NAME_NAME + "=?";
        String[] whereArgs = { roomId };
        //调用delete方法，删除数据
        db.delete(TABLE_NAME, whereClauses, whereArgs);
        dbHelper.closeDb();
    }

    /**
     * 清空数据
     */
    public void deleteData()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        dbHelper.closeDb();
    }

}
