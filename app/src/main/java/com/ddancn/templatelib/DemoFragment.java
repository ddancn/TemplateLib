package com.ddancn.templatelib;

import android.os.Bundle;

import com.ddancn.templatelib.base.BaseFragment;

/**
 * @author ddan.zhuang
 * @date 2019/10/21
 */
public class DemoFragment extends BaseFragment {

    private static final String ARG_TITLE = "title";
    private String title;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_demo;
    }

    @Override
    protected String setHeaderTitle() {
        return title;
    }

    public static DemoFragment newInstance(String title){
        DemoFragment fragment = new DemoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initParam() {
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    protected void initView() {

    }

}
