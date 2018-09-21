package com.zoco.dao;

import com.zoco.exception.BaseException;
import com.zoco.vo.ValueObject;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * 默认服务接口，其内置了常见的业务方法。通常，其他业务接口可以考虑继承这一接口。
 * author zoco
 * creat time 2018/4/11
 */
public interface IBaseDao {

    /**
     * Spring受管Bean ID。
     */
    public static final String BEAN_ID = "base_baseDao";

    /**
     * 日志审计。
     *
     * @param bizType 操作的模块
     * @param opType 操作类型
     * @param opInfo 操作信息
     */
    public void auditLog(String bizType, String opType, String opInfo);

    /**
     * 返回JDBC SimpleJdbcTemplate模板。
     *
     * @return 返回JDBC SimpleJdbcTemplate模板
     *
     * @throws FbrpException FBRP异常
     */
//	public abstract SimpleJdbcTemplate getSimpleJdbcTemplate()
//			throws FbrpException;

    /**
     * 返回 sqlSessionTemplate。
     *
     * @return 返回 sqlSessionTemplate。
     *
     * @throws BaseException FBRP异常
     */
    public abstract SqlSessionTemplate getSqlSessionTemplate() throws BaseException;

    /**
     * 删除记录。
     *
     * @param key String
     * @param id String
     * @param <T> ValueObject
     *
     * @return ValueObject
     */
    public <T extends ValueObject> int delete(String key, String id);

    /**
     * 删除记录。
     *
     * @param key String
     * @param t T
     * @param <T> ValueObject
     */
    public <T extends ValueObject> void delete(String key, T t);

    /**
     * 更新记录。
     *
     * @param key String
     * @param t T
     * @param <T> ValueObject
     */
    public <T extends ValueObject> void update(String key, T t);

    /**
     * 创建记录。
     *
     * @param key String
     * @param t T
     * @param <T> ValueObject
     */
    public <T extends ValueObject> void create(String key, T t);
}
