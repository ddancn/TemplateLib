package com.ddancn.templatelib.api;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ddancn.templatelib.lib.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author ddan.zhuang
 * @date 2019/9/16
 */
public abstract class BaseCallback<T> implements Callback<BaseResponse<T>> {

    @Override
    public void onResponse(@NonNull Call<BaseResponse<T>> call, @NonNull Response<BaseResponse<T>> response) {
        BaseResponse baseResponse = response.body();
        if (baseResponse != null) {
            // noinspection unchecked
            T re = (T) baseResponse.getData();
            if (baseResponse.getCode() == BaseResultCode.SUCCESS.value()) {
                onSuccess(re);
            } else if (baseResponse.getCode() == BaseResultCode.FAIL.value() && onFail(baseResponse)) {
                ToastUtils.showShort(baseResponse.getMsg());
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<BaseResponse<T>> call, @NonNull Throwable t) {
        ToastUtils.showShort(R.string.request_fail);
        LogUtils.eTag("网络请求接口错误", t);
    }

    /**
     * 请求成功
     *
     * @param data data
     */
    protected abstract void onSuccess(T data);

    /**
     * 接口返回错误信息
     *
     * @param response response
     * @return 是否需要吐司
     */
    protected boolean onFail(BaseResponse response) {
        return true;
    }
}
