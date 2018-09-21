package com.zoco.constant;

/**
 * 响应编码
 *
 * @author zoco
 * @creat 2018-09-21-15:49
 */
public class Code {
    /**
     * 返回代码定义，操作成功
     */
    public static final int CODE_OK = 200;

    /**
     * 请求非法
     */
    public static final int CODE_BAD_REQUEST = 400;
    /**
     * 资源冲突
     */
    public static final int CODE_CONFLICT = 409;
    /**
     * 登录已过期
     */
    public static final int CODE_SESSION_OUT = 408;

    /**
     * 未认证
     */
    public static final int CODE_UNAUTHORIZED = 401;

    /**
     * 禁止访问
     */
    public static final int CODE_FORBIDDEN = 403;

    /**
     * 密码错误
     */
    public static final int CODE_PWD_NOT_CORRECT = 405;

    /**
     * 请求不被接受
     */
    public static final int CODE_NOT_ACCEPTABLE = 406;

    /**
     * 密码错误
     */
    public static final int CODE_NOT_CORRECT = 407;
    /**
     * 用户不存在
     */
    public static final int CODE_USER_NOTFOUND = 991;
    /**
     * 用户无效
     */
    public static final int CODE_USER_INVALID = 990;
    /**
     * ResultSet为空
     */
    public static final int RESULTSET_IS_NULL = 992;
    /**
     * 转json错误
     */
    public static final int TO_JSON_ERROR = 993;
    /**
     * 删除失败
     */
    public static final int CODE_DELETE_FAIL = 994;
    /**
     * 修改失败
     */
    public static final int CODE_UPDATE_FAIL = 995;
    /**
     * 添加失败
     */
    public static final int CODE_ADD_FAIL = 996;
    /**
     * 必填字段错误或为空
     */
    public static final int CODE_PARAM_ERROR = 997;
    /**
     * 查询语句为空
     */
    public static final int CODE_SQL_ERROR = 998;
    /**
     * 数据已存在
     */
    public static final int CODE_EXISTED = 999;
    /**
     * 数据为只读状态
     */
    public static final int CODE_ONLYREAD = 1000;
    /**
     * 查询语句为空
     */
    public static final int QUERY_IS_NULL = 899;
    /**
     * JdbcTemplate为空
     */
    public static final int JDBCTEMPLATE_IS_NULL = 898;
    /**
     * 查询语句和查询条件不匹配
     */
    public static final int QUERY_IS_ERROR = 897;
    /**
     * 无数据
     */
    public static final int NO_DATA = 895;
    /**
     * 数据不存在
     */
    public static final int CODE_NOT_EXISTED = 894;
    /**
     * 服务器内部错误
     */
    public static final int CODE_SERVER_ERROR = 500;

    public static enum Msg {
        SERVER_ERROR("系统错误", CODE_SERVER_ERROR), TEMPLATE_IS_NULL(
                "获取不到数据源", JDBCTEMPLATE_IS_NULL), QUERY_NULL("查询语句为空",
                QUERY_IS_NULL), PARAM_ERROR("查询条件值不正确或必填条件值为空", CODE_PARAM_ERROR), JSON_ERROR("json解析错误", TO_JSON_ERROR),
        RESULTSET_NULL("查询条件值不正确", RESULTSET_IS_NULL), QUERY_ERROR("查询语句和查询条件不匹配", QUERY_IS_ERROR), OK("操作成功", CODE_OK);

        private String message;
        private int index;

        private Msg(String message, int index) {
            this.message = message;
            this.index = index;
        }

        public static String getMessage(int index) {
            for (Msg c : Msg.values()) {
                if (c.getIndex() == index) {
                    return c.message;
                }
            }
            return null;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
