package com.ddancn.templatelib.base;

import java.lang.ref.WeakReference;

/**
 * @author ddan.zhuang
 * @date 2019/9/17
 */
public class BasePresenter<T> {

    private WeakReference<T> view;

    public void attachView(T view) {
        this.view = new WeakReference<>(view);
    }

    protected T getView() {
        if (view == null){
            return null;
        }
        return view.get();
    }

    public void detachView() {
        if (view != null) {
            view.clear();
            view = null;
        }
    }
}
