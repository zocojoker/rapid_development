package com.zoco.common;

/**
 * 
 * <pre>
 * 返回码定义类
 * </pre>
 *
 * @author weiyang weiyang@zoco.com.cn
 * @version 1.00.00
 *
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public interface Code {
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
	 * 用户无效
	 */
	public static final int CODE_USER_INVALID = 990;
	/**
	 * 用户不存在
	 */
	public static final int CODE_USER_NOTFOUND = 991;
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
	 * 查询服务不存在 或 服务获取失败
	 */
	public static final int SERVICE_NOT_EXISTED = 896;
	/**
	 * 无数据
	 */
	public static final int NO_DATA = 895;
	/**
	 * 接入厂商不存在
	 */
	public static final int JRCS_NOT_EXISTED = 894;
	/**
	 * 接入厂商无相应权限
	 */
	public static final int JRCS_NOT_AUTHORITY = 893;
	/**
	 * IP不在白名单
	 */
	public static final int IP_NOT_WHILELIST = 892;
	/**
	 * 纳税人识别号不在白名单
	 */
	public static final int NSR_NOT_WHILELIST = 891;
	/**
	 * 服务器内部错误
	 */
	public static final int CODE_SERVER_ERROR = 500;

	public static enum Msg {
		SERVER_ERROR("系统错误", CODE_SERVER_ERROR), TEMPLATE_IS_NULL("获取不到数据源", JDBCTEMPLATE_IS_NULL), QUERY_NULL("查询语句为空",
				QUERY_IS_NULL), PARAM_ERROR("查询条件值不正确或必填条件值为空", CODE_PARAM_ERROR), JSON_ERROR("json解析错误",
						TO_JSON_ERROR), RESULTSET_NULL("查询条件值不正确", RESULTSET_IS_NULL), QUERY_ERROR("查询语句和查询条件不匹配",
								QUERY_IS_ERROR), QUERY_SERVICE_ERROR("查询服务获取失败", SERVICE_NOT_EXISTED), OK("操作成功",
										CODE_OK), JRCS_NOTEXISTED("接入厂商不存在", JRCS_NOT_EXISTED);

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
