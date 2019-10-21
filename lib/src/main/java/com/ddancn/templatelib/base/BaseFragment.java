package com.ddancn.templatelib.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.ddancn.templatelib.lib.R;
import com.ddancn.templatelib.view.HeaderView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ddan.zhuang
 * @date 2019/9/5
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements View.OnClickListener {

    private static final long FAST_CLICK_DURATION = 500L;
    private long lastClickTime;

    private HeaderView headerView;
    private Unbinder unbinder;
    protected T presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootLayout = (ViewGroup) inflater.inflate(R.layout.activity_base, null);

        initParam();

        headerView = rootLayout.findViewById(R.id.view_header);
        headerView.setVisibility(hasHeader() ? View.VISIBLE : View.GONE);
        headerView.setTitle(setHeaderTitle());

        inflater.inflate(bindLayout(), rootLayout, true);

        unbinder = ButterKnife.bind(this, rootLayout);
        if (getPresenter() != null) {
            presenter = getPresenter();
            // noinspection unchecked
            presenter.attachView(this);
        }

        initView();
        bindListener();
        applyData();

        return rootLayout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        long timeDelta = now - lastClickTime;
        lastClickTime = now;
        return 0L < timeDelta && timeDelta < FAST_CLICK_DURATION;
    }

    /**
     * 控件点击事件
     *
     * @param v 控件
     */
    protected void widgetClick(View v) {
    }

    @Override
    public void onClick(View v) {
        if (!isFastClick()) {
            this.widgetClick(v);
        }
    }

    /**
     * 获取presenter实例
     *
     * @return 具体的presenter
     */
    protected T getPresenter() {
        return null;
    }

    protected boolean hasHeader() {
        return true;
    }

    protected void toast(String msg) {
        ToastUtils.showShort(msg);
    }

    protected void toast(int resId) {
        ToastUtils.showShort(resId);
    }

    protected void start(Class<?> clazz) {
        startActivity(new Intent(getContext(), clazz));
    }

    protected void startForResult(Class<?> clazz, int requestCode) {
        startActivityForResult(new Intent(getContext(), clazz), requestCode);
    }

    /**
     * 绑定资源文件
     *
     * @return layoutResId
     */
    protected abstract @LayoutRes int bindLayout();

    /**
     * 初始化参数
     */
    protected void initParam() {
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 绑定监听事件
     */
    protected void bindListener() {
    }

    /**
     * 展示数据
     */
    protected void applyData() {
    }

    /**
     * 设置标题
     *
     * @return 标题
     */
    protected String setHeaderTitle() {
        return "";
    }

    protected void setLeftText(String text) {
        headerView.setLeftText(text);
    }

    protected void setLeftText(@StringRes int resId) {
        headerView.setLeftText(getString(resId));
    }

    protected void setRightText(String text) {
        headerView.setRightText(text);
    }

    protected void setRightText(@StringRes int resId) {
        headerView.setRightText(getString(resId));
    }

    protected void setLeftImage(@DrawableRes int resId) {
        headerView.setLeftImage(resId);
    }

    protected void setRightImage(@DrawableRes int resId) {
        headerView.setRightImage(resId);
    }

    protected void setLeftClickListener(View.OnClickListener listener) {
        headerView.setLeftClickListener(listener);
    }

    protected void setRightClickListener(View.OnClickListener listener) {
        headerView.setRightClickListener(listener);
    }
}
