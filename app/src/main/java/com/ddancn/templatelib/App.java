package com.ddancn.templatelib;

import com.blankj.utilcode.util.Utils;
import com.ddancn.templatelib.base.BaseApp;

/**
 * @author ddan.zhuang
 * @date 2019/9/6
 */
public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
    }

}
