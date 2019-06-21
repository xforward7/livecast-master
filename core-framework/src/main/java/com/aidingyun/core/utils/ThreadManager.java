package com.aidingyun.core.utils;

import com.lzh.easythread.EasyThread;

public final class ThreadManager {

    private final static EasyThread io;
    private final static EasyThread cache;
    private final static EasyThread calculator;
    private final static EasyThread file;

    public static EasyThread getIO() {
        return io;
    }

    public static EasyThread getCache() {
        return cache;
    }

    public static EasyThread getCalculator() {
        return calculator;
    }

    public static EasyThread getFile() {
        return file;
    }

    static {
        io = EasyThread.Builder.createFixed(6).setName("IO").setPriority(7).build();
        cache = EasyThread.Builder.createCacheable().setName("cache").build();
        calculator = EasyThread.Builder.createFixed(4).setName("calculator").setPriority(Thread.MAX_PRIORITY).build();
        file = EasyThread.Builder.createFixed(4).setName("file").setPriority(3).build();
    }
}