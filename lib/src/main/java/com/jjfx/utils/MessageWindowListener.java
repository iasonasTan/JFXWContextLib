package com.jjfx.utils;

public interface MessageWindowListener<T extends Disposable> {
    void onOk(T window);
}
