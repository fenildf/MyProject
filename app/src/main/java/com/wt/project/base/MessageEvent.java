package com.wt.project.base;

/**
 * Created by RexXiang on 2017/8/2.
 */

public class MessageEvent<T> {

    public static final int NO_CODE = -1;
    public static final int USER_INFO_UPDATE = 36;  //用户信息更新


    /**
     * 更新Adapter item状态：高亮、半圈
     */

    private T mMessage;
    private int mCode;

    public MessageEvent(T message) {
        mMessage = message;
        mCode = NO_CODE;
    }

    public MessageEvent(T message, int code) {
        mMessage = message;
        mCode = code;
    }

    public MessageEvent(int code) {
        mMessage = null;
        mCode = code;
    }

    public T getMessageBody() {
        return mMessage;
    }

    public int getType() {
        return mCode;
    }
}
