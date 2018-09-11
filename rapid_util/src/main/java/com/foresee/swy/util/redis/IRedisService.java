package com.foresee.swy.util.redis;

import com.foresee.swy.util.redis.entity.RedisObject;

/**
 * <pre>
 * redis接口类
 * </pre>
 * 
 * @author wuchunlin wuchunlin@foresee.com.cn
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public interface IRedisService {

	/**
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param expiredSeconds
	 *            失效时间
	 */
	public void set(String key, String value, int expiredSeconds);

	/**
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void set(String key, String value);
	
	/**
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public long setnx(String key, String value);

	/**
	 * 
	 * @param key
	 *            键
	 * @param expiredSeconds
	 *            失效时间
	 */
	public void expire(String key, int expiredSeconds);

	/**
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public String get(String key);

	/**
	 * 
	 * @param key
	 *            键
	 */
	public void del(String key);

	/**
	 * 
	 * @param key
	 *            键
	 * @param object
	 *            值对象
	 */
	public void setObject(String key, RedisObject object);

	/**
	 * 
	 * @param key
	 *            键
	 * @param object
	 *            值对象
	 * @param seconds
	 *            失效时间
	 */
	public void setObject(String key, RedisObject object, int expiredSeconds);

	/**
	 * 
	 * @param key
	 *            键
	 * @return 值对象
	 */
	public RedisObject getObject(String key);
}