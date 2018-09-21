package com.zoco.vo;

/**
 * 用户登录信息
 *
 * @author zoco
 * @creat 2018-04-13-9:39
 */
public class UserLogin {
    private String ZhId;
    private String Dlzh;
    private String Dlmm;

    public void setZhId(String zhId) {
        ZhId = zhId;
    }

    public void setDlzh(String dlzh) {
        Dlzh = dlzh;
    }

    public void setDlmm(String dlmm) {
        Dlmm = dlmm;
    }

    public String getZhId() {

        return ZhId;
    }

    public String getDlzh() {
        return Dlzh;
    }

    public String getDlmm() {
        return Dlmm;
    }


}
