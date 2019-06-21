package com.aidingyun.ynlive.component.log;

import android.content.Context;
import android.util.Log;

import com.aidingyun.ynlive.component.log.trace.FileTracer;
import com.aidingyun.ynlive.component.log.trace.Tracer;

public class LoggerImpl implements LogProxy {
    private Tracer mTracer;

    public LoggerImpl(Context context) {
        mTracer = new FileTracer(context);
    }

    @Override
    public boolean isVerboseEnabled() {
        return true;
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void v(String tag, String message, Throwable t) {
        if (isVerboseEnabled())
            mTracer.appendRecord("V", tag, message, t);

        Log.v(tag, message, t);
    }

    @Override
    public void d(String tag, String message, Throwable t) {
        mTracer.appendRecord("D", tag, message, t);

        Log.d(tag, message, t);
    }

    @Override
    public void i(String tag, String message, Throwable t) {
        Log.i(tag, message, t);

        if (isInfoEnabled())
            mTracer.appendRecord("I", tag, message, t);
    }

    @Override
    public void w(String tag, String message, Throwable t) {
        Log.w(tag, message, t);

        if (isWarnEnabled())
            mTracer.appendRecord("W", tag, message, t);
    }

    @Override
    public void e(String tag, String message, Throwable t) {
        Log.e(tag, message, t);

        if (isErrorEnabled())
            mTracer.appendRecord("E", tag, message, t);
    }

    @Override
    public void flush() {
        mTracer.flush();
    }
}
