package com.ddancn.templatelib.api;

/**
 * @author ddan.zhuang
 * @date 2019/10/21
 */
public enum BaseResultCode {
    /** 成功 */
    SUCCESS(0),

    /** 失败 */
    FAIL(1);

    private int value;

    BaseResultCode(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
