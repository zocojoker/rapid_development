package com.zoco.swy.util.redis.impl;

import java.util.HashSet;
import java.util.Set;

import com.zoco.swy.util.SerializeUtil;
import com.zoco.swy.util.redis.IRedisService;
import com.zoco.swy.util.redis.entity.RedisObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * <pre>
 * redis接口实现类
 * </pre>
 * 
 * @author wuchunlin wuchunlin@zoco.com.cn
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
//@Slf4j
public class RedisServiceImpl implements IRedisService {

	public final static String REDIS_SERVICE_ID = "top_serviceproxy_redis_service";

	private final static int MAXIDLE = 500; // 最大空闲连接数, 应用自己评估，不要超过AliCloudDB

	private final static int MAXTOTAL = 30000; // 最大连接数, 应用自己评估，不要超过AliCloudDB

	private static String url = "83cc322f531640d3.m.cnsza.kvstore.aliyuncs.com";

	private static int port = 6379;

	private String password = "83cc322f531640d3:YcsRedis2016";

	private int timeout = 30 * 60; // 30 minutes

	private JedisSentinelPool jedisSentinelPool;

	private JedisPool pool = null;

	private boolean isProduction = true; // false = 测试环境，true = 生产环境

	public RedisServiceImpl(String production, String redisUrl, int timeout, String authPassword) {
//		log.info("初始化Redis:" + redisUrl + ", isProduction:" + production + ",timeout:" + timeout + ",authPassword:"
//				+ authPassword);
		this.isProduction = Boolean.parseBoolean(production);
		this.timeout = timeout;
		this.password = authPassword;
		if (isProduction) {
			String[] tmp = redisUrl.split(":");
			url = tmp[0];
			port = Integer.parseInt(tmp[1]);
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(MAXIDLE);
			config.setMaxTotal(MAXTOTAL);
			config.setTestOnBorrow(false);
			config.setTestOnReturn(false);
			pool = new JedisPool(config, url, port, this.timeout, password);
		} else {
			Set<String> sentinels = new HashSet<String>();
			String[] ips = redisUrl.split(";");
			for (String ip : ips) {
				sentinels.add(ip);
			}
			JedisPoolConfig jedisConfig = new JedisPoolConfig();
			jedisConfig.setMaxIdle(MAXTOTAL);
			jedisConfig.setMaxTotal(MAXTOTAL);
			jedisConfig.setTestOnBorrow(true);
			jedisSentinelPool = new JedisSentinelPool("mymaster", sentinels, jedisConfig, password);
		}
	}

	@Override
	public void set(String key, String value, int expiredSeconds) {
		Jedis jedis = getJedis();
		jedis.setex(key, expiredSeconds, value);
		returnJedisResource(jedis);
	}

	@Override
	public void set(String key, String value) {
		Jedis jedis = getJedis();
		jedis.set(key, value);
		returnJedisResource(jedis);
	}
	
	@Override
	public long setnx(String key, String value) {
		Jedis jedis = getJedis();
		long result = jedis.setnx(key, value);
		returnJedisResource(jedis);
		return result;
	}

	@Override
	public void expire(String key, int expiredSeconds) {
		Jedis jedis = getJedis();
		jedis.expire(key, expiredSeconds);
		returnJedisResource(jedis);
	}

	@Override
	public String get(String key) {
		Jedis jedis = getJedis();
		String value = jedis.get(key);
		returnJedisResource(jedis);
		return value;
	}

	@Override
	public void del(String key) {
		Jedis jedis = getJedis();
		try {
			jedis.del(key);
		} finally {
			if (jedis != null)
				returnJedisResource(jedis);
		}
	}

	@Override
	public void setObject(String key, RedisObject object) {
		Jedis jedis = getJedis();
		try {
			byte[] value = SerializeUtil.serialize(object);
			jedis.set(key.getBytes(), value);
		} finally {
			if (jedis != null)
				returnJedisResource(jedis);
		}
	}

	@Override
	public void setObject(String key, RedisObject object, int seconds) {
		Jedis jedis = getJedis();
		try {
			byte[] value = SerializeUtil.serialize(object);
			jedis.setex(key.getBytes(), seconds, value);
		} finally {
			if (jedis != null)
				returnJedisResource(jedis);
		}
	}

	@Override
	public RedisObject getObject(String key) {
		Jedis jedis = getJedis();
		byte[] vo = null;
		try {
			vo = jedis.get(key.getBytes());
		} catch (ClassCastException ex) {
//			log.error(">>>无法读取到值，值失效:" + vo);
			vo = null;
		} finally {
			if (jedis != null)
				returnJedisResource(jedis);
		}
		if (vo == null || vo.length == 0) {
			return null;
		}
		RedisObject redisObject = (RedisObject) SerializeUtil.unserialize(vo);
		return redisObject;
	}

	/**
	 * 从连接池中获取jedis连接
	 * 
	 * @return
	 */
	private Jedis getJedis() {
		if (isProduction) {
			return pool.getResource();
		} else {
			return this.jedisSentinelPool.getResource();
		}
	}

	/**
	 * 把jedis连接返回连接池
	 * 
	 * @param jedis
	 */
	private void returnJedisResource(Jedis jedis) {
		if (isProduction) {
			this.pool.returnResource(jedis);
		} else {
			this.jedisSentinelPool.returnResource(jedis);
		}
	}
}