package com.aidingyun.ynlive.component.log.trace;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ianmao   2014-6-12
 */
public abstract class Tracer implements Handler.Callback {
    private static final int MSG_FLUSH_ALL = 1;
    private static final int MSG_FLUSH_CONTENT = 2;

    private List<String> mBuffer = Collections.synchronizedList(new ArrayList<String>()); // Log������
    private long mBufferSize = 0; // ��ǰBuffer��С(�ַ���Size)

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    public Tracer() {
        ensureLogThread();
        sendFlushDelayMsg();
    }

    private void ensureLogThread() {
        if ((mHandlerThread != null) && (mHandlerThread.isAlive()))
            return;

        try {
            if (mHandlerThread == null)
                mHandlerThread = new HandlerThread("LoggerThread", Thread.MIN_PRIORITY);

            mHandlerThread.start();

            if (mHandlerThread.isAlive())
                mHandler = new Handler(mHandlerThread.getLooper(), this);
        } catch (Throwable e) {
            mHandler = new Handler(Looper.getMainLooper(), this); // �������߳�ʧ�ܣ�ֻ�������̶߳�����
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg == null)
            return false;
        switch (msg.what) {
            case MSG_FLUSH_ALL:
                flush();
                sendFlushDelayMsg();
                break;
            case MSG_FLUSH_CONTENT:
                try {
                    String content = (String) msg.obj;
                    flush(content);
                } catch (Throwable e) {
                }
                break;
            default:
                break;
        }
        return false;
    }

    private void sendFlushDelayMsg() {
        Message msg = Message.obtain();
        msg.what = MSG_FLUSH_ALL;
        mHandler.sendMessageDelayed(msg, TracerConfig.LOG_FLUSH_DURATION);
    }

    protected void flush(List<String> logs) {
        if (logs == null || logs.size() <= 0)
            return;

        String content = "";
        try {
            StringBuilder builder = new StringBuilder();
            for (String str : logs) {
                builder.append(str).append("\n");
            }
            content = builder.toString();
        } catch (OutOfMemoryError e) {
        }

        if (!TextUtils.isEmpty(content)) {
            Message msg = Message.obtain();
            msg.what = MSG_FLUSH_CONTENT;
            msg.obj = content;
            mHandler.sendMessage(msg);
        }
    }

    protected abstract void flush(String content);

    public void appendRecord(String level, String tag, String msg, Throwable t) {
        Record r = new Record(level, tag, msg, t);
        String log = r.toString();

        synchronized (this) {
            if (mBufferSize >= TracerConfig.MEMORY_SIZE || mBufferSize + log.length() > TracerConfig.MEMORY_SIZE) { // ����������
                flush();
            }

            mBuffer.add(log);
            mBufferSize += log.length();
        }
    }

    public void flush() {
        if (mBufferSize <= 0)
            return;

        synchronized (this) {
            List<String> tmp = new ArrayList<String>(mBuffer);
            flush(tmp);
            mBuffer.clear();
            mBufferSize = 0;
        }
    }
}
