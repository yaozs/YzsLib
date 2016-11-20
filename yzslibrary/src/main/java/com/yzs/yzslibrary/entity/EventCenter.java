package com.yzs.yzslibrary.entity;

/**
 * Author: 姚智胜
 * Version: V1.0版本
 * Description: EventBus传递消息总体类
 * Date: 2016/11/17
 */

public class EventCenter<T> {

    private int eventCode = -1;

    private T data;

    public EventCenter(int eventCode) {
        this.eventCode = eventCode;
    }

    public EventCenter(int eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    public int getEventCode() {
        return eventCode;
    }

    public T getData() {
        return data;
    }
}
