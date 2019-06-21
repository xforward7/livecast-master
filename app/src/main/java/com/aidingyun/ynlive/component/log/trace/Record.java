package com.aidingyun.ynlive.component.log.trace;

import android.text.format.Time;
import android.util.Log;

/**
 *
 * @author ianmao   2014-6-12
 */
public class Record {
    public static final String TRACE_TIME_FORMAT = "%Y-%m-%d %H:%M:%S";

    public String tag;
    public String msg;
    public Throwable throwable;

    public String level;
    public long timestamp;
    public long threadId;
    public String threadName;

    public Record(String level, String tag, String msg, Throwable t) {
        this.level = level;
        this.tag = tag;
        this.msg = msg;
        this.throwable = t;

        this.timestamp = System.currentTimeMillis();
        this.threadId = Thread.currentThread().getId();
        this.threadName = Thread.currentThread().getName();
    }

    public String toString() {
        try {
            StringBuilder builder = new StringBuilder();

            Time timeObj = new Time();
            timeObj.set(timestamp);
            builder.append(level).append('/').append(timeObj.format(TRACE_TIME_FORMAT)).append('.');
            long ms = timestamp % 1000;
            if (ms < 10)
                builder.append("00");
            else if (ms < 100)
                builder.append('0');

            builder.append(ms).append(' ').append('[').append(threadName + " " + threadId);

            builder.append(']').append('[').append(tag).append(']').append(' ').append(msg).append('\n');

            if (throwable != null) {
                builder.append("* Exception : \n").append(Log.getStackTraceString(throwable)).append('\n');
            }

            return builder.toString();
        } catch (OutOfMemoryError e) {
            return "";
        }
    }
}
