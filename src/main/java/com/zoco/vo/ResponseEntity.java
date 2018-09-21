package com.zoco.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 响应实体类
 *
 * @author zoco
 * @creat 2018-09-21-16:48
 */
public class ResponseEntity implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 3288974655972496353L;
    /**
     * 返回码
     */
    private Integer code;

    /**
     * 附加信息
     */
    private String message;

    /**
     * 时间戳
     */
    private Long timestamp;


    /**
     * 回执
     */
    private Map<String, Object> receipt;

    public List<Object> listJson;

    public List<Object> getListJson() {
        return listJson;
    }

    public void setListJson(List<Object> listJson) {
        this.listJson = listJson;
    }

    public ResponseEntity() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getReceipt() {
        return receipt;
    }

    public void setReceipt(Map<String, Object> receipt) {
        this.receipt = receipt;
    }
}
