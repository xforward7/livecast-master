package com.aidingyun.ynlive.app.greendao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.aidingyun.ynlive.app.greendao.model.LiveRoomBean;
import com.aidingyun.ynlive.mvp.model.entity.HomeCourseModel;
import com.aidingyun.ynlive.mvp.model.entity.LiveWatchBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */
public class WatchRecordDao
{
    public static final String TABLE_NAME = "tab_watch_record";

    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_USER_ID = "user_id";
    public static final String COLUMN_NAME_PICTURE = "photo";
    public static final String COLUMN_NAME_TIME = "time";
    public static final String COLUMN_NAME_LIVE_TYPE = "live_type";
    public static final String COLUMN_NAME_USERNAME = "real_name";
    public static final String COLUMN_NAME_FANS_COUNT = "collection";
    public static final String COLUMN_NAME_TYPE = "type";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_ROOM_ID = "roomid";
    public static final String COLUMN_NAME_CONTENT = "content";
    public static final String COLUMN_NAME_ORGANIZATIONID = "organizationid";
    public static final String COLUMN_NAME_SCORE = "score";
    public static final String COLUMN_NAME_PAY_COIN = "price";
    public static final String COLUMN_NAME_SECOND_TYPEID = "second_typeid";
    public static final String COLUMN_NAME_TYPEID = "typeid";
    public static final String COLUMN_NAME_ATTENTION_COUNT = "peopel_count";
    public static final String COLUMN_NAME_COURSEID = "courseid";
    public static final String COLUMN_NAME_STATUS = "status";
    public static final String COLUMN_NAME_ATTENTION_ID = "collectioned";
    public static final String COLUMN_NAME_USER_STATUS = "lessoned";
    public static final String COLUMN_NAME_LIVE_STATUS = "live_status";
    public static final String COLUMN_NAME_START_DATA = "begin_date";
    public static final String COLUMN_NAME_END_DATA = "end_date";
    public static final String COLUMN_NAME_PAID = "paid";
    public static final String COLUMN_NAME_ALLOW = "allow";
    public static final String COLUMN_NAME_ALLOW_NAME = "allow_name";
    public static final String COLUMN_NAME_LIVE_TIME = "live_time";
    public static final String COLUMN_NAME_SECTION_COUNT = "section_count";
    public static final String COLUMN_NAME_TYPE_NAME = "type_name";
    public static final String COLUMN_NAME_SECOND_TYPE_NAME = "second_type_name";
    public static final String COLUMN_NAME_COURSE_TYPE = "course_type";
    public static final String COLUMN_NAME_SHARE_URL = "share_url";
//    public static final String COLUMN_NAME_LIVE_COUNT = "liveCount";

    private DbOpenHelper dbHelper;
    LiveWatchBean liveWatchBean;
    public WatchRecordDao(Context context)
    {
        dbHelper = DbOpenHelper.getInstance(context);
    }
    /**
     * 插入一条记录
     * @return 插入记录的id -1表示插入不成功
     */
    public long insertWatchRecord(LiveWatchBean liveRoomBean)
    {
        List<LiveWatchBean> watchList = getWatchRecordList();
        for (LiveWatchBean liveWatchBean: watchList)
        {
            if (liveRoomBean.getRoomid().equals(liveWatchBean.getRoomid()))
            {
                deleteWatchRecord(liveWatchBean.getRoomid());
            }
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        boolean isInsert = true;
        long id = -1;
        if (db != null && isInsert)
        {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_ID,Integer.parseInt(liveRoomBean.getId()));
            values.put(COLUMN_NAME_USER_ID, Integer.parseInt(liveRoomBean.getUserid()));
            values.put(COLUMN_NAME_USERNAME, liveRoomBean.getReal_name());
            values.put(COLUMN_NAME_PICTURE, liveRoomBean.getPhoto());
            values.put(COLUMN_NAME_ROOM_ID, liveRoomBean.getRoomid());
            values.put(COLUMN_NAME_TITLE, liveRoomBean.getTitle());
            values.put(COLUMN_NAME_LIVE_TYPE, liveRoomBean.getLive_type());
            values.put(COLUMN_NAME_FANS_COUNT, liveRoomBean.getCollection());
            values.put(COLUMN_NAME_TYPE, liveRoomBean.getType());
            values.put(COLUMN_NAME_CONTENT, liveRoomBean.getContent());
            values.put(COLUMN_NAME_ORGANIZATIONID, liveRoomBean.getOrganizationid());
            values.put(COLUMN_NAME_SCORE, liveRoomBean.getScore());
            values.put(COLUMN_NAME_PAY_COIN, liveRoomBean.getPrice());
            values.put(COLUMN_NAME_SECOND_TYPEID, liveRoomBean.getSecond_typeid());
            values.put(COLUMN_NAME_TYPEID, liveRoomBean.getTypeid());
            values.put(COLUMN_NAME_ATTENTION_COUNT, liveRoomBean.getPeopel_count());
            values.put(COLUMN_NAME_ATTENTION_ID, liveRoomBean.getCollectioned());
            values.put(COLUMN_NAME_COURSEID, liveRoomBean.getCourseid());
            values.put(COLUMN_NAME_STATUS, liveRoomBean.getStatus());
            values.put(COLUMN_NAME_USER_STATUS, liveRoomBean.getLessoned());
            values.put(COLUMN_NAME_LIVE_STATUS, liveRoomBean.getLive_status());
            values.put(COLUMN_NAME_START_DATA, liveRoomBean.getBegin_date());
            values.put(COLUMN_NAME_END_DATA, liveRoomBean.getEnd_date());
            values.put(COLUMN_NAME_PAID, liveRoomBean.getPaid());
            values.put(COLUMN_NAME_ALLOW, liveRoomBean.getAllow());
            values.put(COLUMN_NAME_ALLOW_NAME, liveRoomBean.getAllow_name());
            values.put(COLUMN_NAME_LIVE_TIME, liveRoomBean.getLive_time());
            values.put(COLUMN_NAME_SECTION_COUNT, liveRoomBean.getSection_count());
            values.put(COLUMN_NAME_TYPE_NAME, liveRoomBean.getType_name());
            values.put(COLUMN_NAME_SECOND_TYPE_NAME, liveRoomBean.getSecond_type_name());
            values.put(COLUMN_NAME_COURSE_TYPE, liveRoomBean.getCourse_type());
            values.put(COLUMN_NAME_SHARE_URL, liveRoomBean.getShare_url());
            values.put(COLUMN_NAME_TIME, liveRoomBean.getTime());
            id = db.insert(TABLE_NAME, null, values);
        }
        return id;
    }

    /**
     * 获取观看历史记录list
     * @return
     */
    public List<LiveWatchBean> getWatchRecordList()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<LiveWatchBean> watchList = new ArrayList<>();
        if(db.isOpen())
        {
            Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " order by " + COLUMN_NAME_TIME + " desc ", null);
            while(cursor.moveToNext())
            {
                LiveWatchBean typeCourseDataBean = new LiveWatchBean();
                String id = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ID));
                String userId = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USER_ID));
                String photo = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PICTURE));
                String title = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE));
                String status = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_STATUS));
                String live_status = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LIVE_STATUS));
                String time = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TIME));
                 String live_type = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LIVE_TYPE));
                 String real_name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_USERNAME));
                 String collection = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FANS_COUNT));
                String type = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TYPE));
                 String roomid = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ROOM_ID));
                 String content = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CONTENT));
                 String organizationid = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ORGANIZATIONID));
                 String score = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_SCORE));
                 String price = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_PAY_COIN));
                 String second_typeid = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_SECOND_TYPEID));
                 String typeid = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TYPEID));
                 String peopel_count = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ATTENTION_COUNT));
                 String courseid = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_COURSEID));
                 int collectioned = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ATTENTION_ID));
                 int lessoned = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_USER_STATUS));
                 String begin_date = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_START_DATA));
                 String end_date = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_END_DATA));
                 int paid = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PAID));
                 String allow = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ALLOW));
                 String allow_name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ALLOW_NAME));
                 String live_time = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_LIVE_TIME));
                 int section_count = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_SECTION_COUNT));
                 String type_name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TYPE_NAME));
                 String second_type_name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_SECOND_TYPE_NAME));
                 int course_type = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_COURSE_TYPE));
                 String share_url = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_SHARE_URL));

                typeCourseDataBean.setId(id);
                typeCourseDataBean.setUserid(userId);
                typeCourseDataBean.setReal_name(real_name);
                typeCourseDataBean.setPhoto(photo);
                typeCourseDataBean.setTime(time);
                typeCourseDataBean.setTitle(title);
                typeCourseDataBean.setCollection(collection);
                typeCourseDataBean.setType(status);
                typeCourseDataBean.setLive_status(live_status);
                typeCourseDataBean.setLive_type(live_type);
                typeCourseDataBean.setType(type);
                typeCourseDataBean.setRoomid(roomid);
                typeCourseDataBean.setContent(content);
                typeCourseDataBean.setOrganizationid(organizationid);
                typeCourseDataBean.setScore(score);
                typeCourseDataBean.setPrice(price);
                typeCourseDataBean.setSecond_typeid(second_typeid);
                typeCourseDataBean.setTypeid(typeid);
                typeCourseDataBean.setPeopel_count(peopel_count);
                typeCourseDataBean.setCourseid(courseid);
                typeCourseDataBean.setCollectioned(collectioned);
                typeCourseDataBean.setLessoned(lessoned);
                typeCourseDataBean.setBegin_date(begin_date);
                typeCourseDataBean.setEnd_date(end_date);
                typeCourseDataBean.setPaid(paid);
                typeCourseDataBean.setAllow(allow);
                typeCourseDataBean.setAllow_name(allow_name);
                typeCourseDataBean.setLive_time(live_time);
                typeCourseDataBean.setSection_count(section_count);
                typeCourseDataBean.setType_name(type_name);
                typeCourseDataBean.setSecond_type_name(second_type_name);
                typeCourseDataBean.setCourse_type(course_type);
                typeCourseDataBean.setShare_url(share_url);
                watchList.add(typeCourseDataBean);
            }
            cursor.close();
        }
        return watchList;
    }

    /**
     * 删除一个记录
     * @param roomId
     */
    public void deleteWatchRecord(String roomId)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db.isOpen())
        {
            String whereClauses = COLUMN_NAME_ROOM_ID + "=?";
            String[] whereArgs = { roomId };
            //调用delete方法，删除数据
            db.delete(TABLE_NAME, whereClauses, whereArgs);
            dbHelper.closeDb();
        }
    }

    /**
     * 清空数据
     */
    public void deleteAllRecord()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        dbHelper.closeDb();
    }

}
