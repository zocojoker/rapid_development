package com.foresee.dao.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.foresee.dao.IBaseDao;
import com.foresee.dao.IKeyGen;
import com.foresee.dao.IServerTime;
import com.foresee.persistence.Order;
import com.foresee.vo.PagedResult;
import com.foresee.vo.ValueObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.util.StringUtils;


/**
 * 基础Dao层实现类(服务实现类必须继承的基类。)
 *
 * @author foresee
 * @creat 2018-04-11-10:12
 */
public class BaseDaoImpl extends ApplicationObjectSupport implements IBaseDao {
    private static final Log log = LogFactory.getLog(BaseDaoImpl.class);

    private IServerTime serverTime;
    private IKeyGen keyGen;

    private DataSource dataSource;


    private SqlSessionTemplate sqlSessionTemplate;
    //private SimpleJdbcTemplate simpleJdbcTemplate;

    // 为集成测试工作准备
    private boolean productionFlag = true;


    /**
     * 借助BatchSqlUpdate批量执行JDBC操作。
     *
     * @param sql
     *            执行SQL，比如“update emp set sal=? where empno = ?”
     * @param parameterTypes
     *            参数类型，比如“new int[]{Types.FLOAT, Types.INTEGER}”
     * @param valueList
     *            参数值集合，每项值的数量要保持同参数类型的长度一致
     */
    /**
     * 升级到spring-4.1.9后依赖的方法不存在了
     */
//	protected void batchCommit_JDBC(String sql, int[] parameterTypes,
//			List<Object[]> valueList) {
//		BatchSqlUpdate batchSqlUpdate = new BatchSqlUpdate(this.dataSource, sql);
//		batchSqlUpdate.setTypes(parameterTypes);
//		for (Object[] valueArray : valueList) {
//			batchSqlUpdate.update(valueArray);
//		}
//		batchSqlUpdate.flush();
//	}


    /**
     * 设置 sqlSessionTemplate。
     *
     * @param sqlSessionTemplate 设置 sqlSessionTemplate。
     */
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * 返回 sqlSessionTemplate。
     *
     * @return 返回 sqlSessionTemplate。
     */
    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    /**
     * 返回 simpleJdbcTemplate。
     *
     * @return 返回 simpleJdbcTemplate。
     */
//	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
//		return simpleJdbcTemplate;
//	}

    /**
     * 设置 simpleJdbcTemplate。
     *
     * @param simpleJdbcTemplate
     *            设置 simpleJdbcTemplate。
     */
//	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
//		this.simpleJdbcTemplate = simpleJdbcTemplate;
//	}

    /**
     * 获取serverTime。
     *
     * @return IServerTime
     */
    public IServerTime getServerTime() {
        return serverTime;
    }

    /**
     * 设置serverTime。
     *
     * @param serverTime IServerTime
     */
    public void setServerTime(IServerTime serverTime) {
        this.serverTime = serverTime;
    }

    /**
     * 设置keyGen。
     *
     * @param keyGen IKeyGen
     */
    public void setKeyGen(IKeyGen keyGen) {
        this.keyGen = keyGen;
    }

    /**
     * 获取keyGen。
     *
     * @return IKeyGen
     */
    public IKeyGen getKeyGen() {
        return keyGen;
    }

    /**
     * 设置 productionFlag。
     *
     * @param productionFlag 设置 productionFlag。
     */
    public void setProductionFlag(boolean productionFlag) {
        this.productionFlag = productionFlag;
    }

    /**
     * 返回 productionFlag。
     *
     * @return 返回 productionFlag。
     */
    public boolean isProductionFlag() {
        return productionFlag;
    }

    /**
     * 设置 dataSource。
     *
     * @param dataSource 设置 dataSource。
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void auditLog(String bizType, String opType, String opInfo) {

    }

    /**
     * 分页查找。
     *
     * @param key       String
     * @param map       Map<String,Object>
     * @param pageIndex int
     * @param pageSize  int
     * @param <T>       ValueObject
     * @return PagedResult<T>
     */
    public <T> PagedResult<T> pagedQeury(String key, Map<String, Object> map,
                                         int pageIndex, int pageSize) {

        RowBounds rb = new RowBounds(pageIndex, pageSize);
        String statement = this.getStatement(key);
        List<T> list = (List<T>) this.getSqlSessionTemplate().selectList(
                statement, map, rb);
        String statement2 = statement + "_count";
        if (!this.getSqlSessionTemplate().getConfiguration()
                .hasStatement(statement2)) {
            throw new IllegalArgumentException("请提供 " + statement2
                    + " 用于分页查询时统计");
        }
        Long total = (Long) this.getSqlSessionTemplate().selectOne(statement2,
                map);
        return new PagedResult<T>(list, total);
    }

    /**
     * 根据id查询实体。
     *
     * @param key String
     * @param id  String
     * @param <T> ValueObject
     * @return T
     */
    public <T> T find(String key, String id) {
        String statement = this.getStatement(key);
        return (T) this.getSqlSessionTemplate().selectOne(statement, id);
    }

    /**
     * 根据条件查询实体的列表。
     *
     * @param key String
     * @param map Map<String,Object>
     * @param <T> ValueObject
     * @return List<T>
     */
    public <T> List<T> findBy(String key, Map<String, Object> map) {
        String statement = this.getStatement(key);
        return (List<T>) this.getSqlSessionTemplate()
                .selectList(statement, map);
    }

    /**
     * 列出所有的实体。
     *
     * @param key    String
     * @param orders Order
     * @param <T>    ValueObject
     * @return List<T>
     */
    public <T> List<T> list(String key, Order... orders) {
        return this.findBy(key, Collections.<String, Object>emptyMap());
    }

    /**
     * 查找符合条件的实体列表。
     *
     * @param key      String
     * @param property String
     * @param value    Object
     * @param <T>      ValueObject
     * @return List<T>
     */
    public <T> List<T> findBy(String key, String property, Object value) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(property, value);
        return this.findBy(key, map);
    }

    /**
     * 查找符合条件的一个实体。
     *
     * @param key      String
     * @param property String
     * @param value    Object
     * @param <T>      ValueObject
     * @return T
     */
    public <T> T findExactBy(String key, String property, Object value) {
        List<T> list = this.findBy(key, property, value);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 查找符合条件的一个实体。
     *
     * @param key String
     * @param map Map<String,Object>
     * @param <T> ValueObject
     * @return T
     */
    public <T> T findExactBy(String key, Map<String, Object> map) {
        List<T> list = this.findBy(key, map);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public <T extends ValueObject> void create(String key, T t) {
        if (!StringUtils.hasText(t.getId())) {
            t.setId(this.getKeyGen().getUUIDKey());
        }
        String statement = this.getStatement(key);
        this.getSqlSessionTemplate().insert(statement, t);
    }

    @Override
    public <T extends ValueObject> void update(String key, T t) {
        if (!StringUtils.hasText(t.getId())) {
            throw new IllegalArgumentException("更新操作必须提供实体类的ID值");
        }
        String statement = this.getStatement(key);
        this.getSqlSessionTemplate().update(statement, t);
    }

    @Override
    public <T extends ValueObject> void delete(String key, T t) {
        this.delete(key, t.getId());
    }

    @Override
    public int delete(String key, String id) {
        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException("删除操作必须提供实体类的ID值");
        }
        String statement = this.getStatement(key);
        return this.getSqlSessionTemplate().delete(statement, id);
    }

    /**
     * 根据条件删除实体。
     *
     * @param key String
     * @param map Map<String,Object>
     * @return int
     */
    public int deleteBy(String key, Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("条件删除操作必须提供至少一个条件");
        }
        String statement = this.getStatement(key);
        return this.getSqlSessionTemplate().delete(statement, map);
    }

    protected String getStatement(String key) {
        String statement = this.getClass().getName() + "." + key;
        return statement;
    }

    protected boolean hasText(String str) {
        return StringUtils.hasText(str);
    }

    protected static Order asc(String property) {
        return Order.ASC(property);
    }

    protected static Order desc(String property) {
        return Order.DESC(property);
    }

}
