package com.ddancn.templatelib.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.jaeger.library.StatusBarUtil;
import com.ddancn.templatelib.lib.R;
import com.ddancn.templatelib.view.HeaderView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ddan.zhuang
 * @date 2019/9/5
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements View.OnClickListener {
    private static final long FAST_CLICK_DURATION = 500L;
    private long lastClickTime;

    private HeaderView headerView;
    private Unbinder unbinder;
    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initParam();

        generateContentView();
        unbinder = ButterKnife.bind(this);

        if (isImmersion()) {
            StatusBarUtil.setLightMode(this);
            StatusBarUtil.setColor(this, setStatusBarColor(), 0);
        }
        if (getPresenter() != null) {
            presenter = getPresenter();
            // noinspection unchecked
            presenter.attachView(this);
        }

        initView();
        bindListener();
        applyData();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    private void generateContentView() {
        ViewGroup rootLayout = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_base, null);

        headerView = rootLayout.findViewById(R.id.view_header);
        headerView.setVisibility(hasHeader() ? View.VISIBLE : View.GONE);
        headerView.setTitle(setHeaderTitle());

        View contentView = LayoutInflater.from(this).inflate(bindLayout(), rootLayout, true);

        setContentView(contentView);
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

    /**
     * 沉浸式状态栏
     *
     * @return 是否沉浸
     */
    protected boolean isImmersion() {
        return true;
    }

    /**
     * 状态栏颜色
     *
     * @return 颜色resId
     */
    protected int setStatusBarColor() {
        return Color.WHITE;
    }

    protected void toast(String msg) {
        ToastUtils.showShort(msg);
    }

    protected void toast(int resId) {
        ToastUtils.showShort(resId);
    }

    protected void start(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }

    protected void startForResult(Class<?> clazz, int requestCode) {
        startActivityForResult(new Intent(this, clazz), requestCode);
    }

    /**
     * 绑定资源文件
     *
     * @return layoutResId
     */
    protected abstract @LayoutRes
    int bindLayout();

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

    protected void enableLeftBack() {
        setLeftImage(R.drawable.ic_left_arrow);
        headerView.setLeftClickListener(v -> finish());
    }

    protected void setTitleText(String title) {
        headerView.setTitle(title);
    }
}
