package com.yishi.mall.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

/**
 * redis 工具类
 */
@SuppressWarnings({"rawtypes", "ConstantConditions", "unchecked"})
@Component
public class RedisUtils {

  @Resource
  private RedisTemplate redisTemplate;

  /**
   * 是否存在
   */
  public <K> boolean exist(K k) {
    return redisTemplate.persist(k);
  }

  /**
   * 删除一个
   */
  public <K> void delete(K k) {
    redisTemplate.delete(k);
  }

  /**
   * 同时删除多个
   */
  public <K> Long delete(Collection<K> keys) {
    return redisTemplate.delete(keys);
  }

  /**
   * 获得缓存的数据
   */
  public <K, V> V get(K key) {
    ValueOperations<K, V> operation = redisTemplate.opsForValue();
    return operation.get(key);
  }

  /**
   * 设置缓存数据
   */
  public <K, V> void set(K key, V value) {
    redisTemplate.opsForValue().set(key, value);
  }

  /**
   * 设置缓存的值，key已经存在返回false
   */
  public <K, V> Boolean setIfAbsent(K k, V v) {
    return redisTemplate.opsForValue().setIfAbsent(k, v);
  }

  /**
   * 设置过期时间，timeout秒后过期
   */
  public <K> boolean expireAfter(K k, long timeout) {
    return redisTemplate.expire(k, timeout, TimeUnit.SECONDS);
  }

  /**
   * 设置过期时间，指定时间过期
   */
  public <K> boolean expireAt(K k, LocalDateTime date) {
    LocalDateTime now = LocalDateTime.now();
    if (date.compareTo(now) > 0) {
      return redisTemplate.expire(k, Duration.between(now, date).getSeconds(), TimeUnit.SECONDS);
    } else {
      delete(k);
      return true;
    }
  }

  /**
   * 设置缓存的值，该值在timeout秒后过期
   */
  public <K, V> void setExpireAfter(K k, V v, long timeout) {
    redisTemplate.opsForValue().set(k, v, timeout, TimeUnit.SECONDS);
  }

  /**
   * 设置缓存的值，该值在指定时间过期
   */
  public <K, V> void setExpireAt(K k, V v, LocalDateTime date) {
    LocalDateTime now = LocalDateTime.now();
    if (date.compareTo(now) > 0) {
      redisTemplate.opsForValue().set(k, v, Duration.between(now, date));
    } else {
      delete(k);
    }
  }

  /**
   * 同时设置多个缓存
   */
  public <K, V> void multiSet(Map<K, V> map) {
    redisTemplate.opsForValue().multiSet(map);
  }

  /**
   * 同时获取多个缓存
   */
  public <K, V> List<V> multiGet(Collection<K> collection) {
    return redisTemplate.opsForValue().multiGet(collection);
  }

  /**
   * 设置第offset位的值
   */
  public <K> Boolean setBit(K k, long offset, boolean v) {
    return redisTemplate.opsForValue().setBit(k, offset, v);
  }

  /**
   * 获取第offset位的值
   */
  public <K> Boolean getBit(K k, long offset) {
    return redisTemplate.opsForValue().getBit(k, offset);
  }

  /**
   * 自增
   */
  public <K> Long increment(K k, long v) {
    return redisTemplate.opsForValue().increment(k, v);
  }

  /**
   * 自增
   */
  public <K> Double increment(K k, double v) {
    return redisTemplate.opsForValue().increment(k, v);
  }

  /**
   * 列表头部添加数据
   */
  public <K, V> void listLeftPush(K k, V v) {
    ListOperations listOperation = redisTemplate.opsForList();
    listOperation.leftPush(k, v);
  }

  /**
   * 列表尾部添加数据
   */
  public <K, V> void listRightPush(K k, V v) {
    ListOperations listOperation = redisTemplate.opsForList();
    listOperation.rightPush(k, v);
  }

  /**
   * 列表头部取出数据
   */
  public <K, V> V listLeftPop(K k) {
    ListOperations listOperation = redisTemplate.opsForList();
    return (V) listOperation.leftPop(k);
  }

  /**
   * 列表尾部取出数据
   */
  public <K, V> V listRightPop(K k) {
    ListOperations listOperation = redisTemplate.opsForList();
    return (V) listOperation.rightPop(k);
  }

  /**
   * 列表长度
   */
  public <K> Long listSize(K k) {
    ListOperations listOperation = redisTemplate.opsForList();
    return listOperation.size(k);
  }

  /**
   * 设置hash值
   */
  public <K, HK, HV> void hashPut(K k, HK hk, HV hv) {
    HashOperations hashOperations = redisTemplate.opsForHash();
    hashOperations.put(k, hk, hv);
  }

  /**
   * 删除hash值
   */
  public <K, HK> Long hashDelete(K k, HK... hk) {
    HashOperations hashOperations = redisTemplate.opsForHash();
    return hashOperations.delete(k, hk);
  }

  /**
   * 获取hash值
   */
  public <H, HK, HV> HV hashGet(H h, HK hk) {
    HashOperations hashOperations = redisTemplate.opsForHash();
    return (HV) hashOperations.get(h, hk);
  }

  /**
   * 获取hash大小
   */
  public <H> Long hashSize(H h) {
    HashOperations hashOperations = redisTemplate.opsForHash();
    return hashOperations.size(h);
  }

  /**
   * 集合添加数据，可一次添加多个
   */
  public <K, V> Long setAdd(K k, V... v) {
    SetOperations setOperations = redisTemplate.opsForSet();
    return setOperations.add(k, v);
  }

  /**
   * 集合删除数据，可一次删除多个
   */
  public <K, V> Long setRemove(K k, V... v) {
    SetOperations setOperations = redisTemplate.opsForSet();
    return setOperations.remove(k, v);
  }

  /**
   * 取出集合数据
   */
  public <K, V> V setPop(K k) {
    SetOperations setOperations = redisTemplate.opsForSet();
    return (V) setOperations.pop(k);
  }

  /**
   * 集合大小
   */
  public <K> Long setSize(K k) {
    SetOperations setOperations = redisTemplate.opsForSet();
    return setOperations.size(k);
  }

  /**
   * 有序集合添加数据
   */
  public <K, V> Boolean zsetAdd(K k, V v, double score) {
    ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    return zSetOperations.add(k, v, score);
  }

  /**
   * 有序集合删除数据
   */
  public <K, V> Long zsetRemove(K k, V... v) {
    ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    return zSetOperations.remove(k, v);
  }

  /**
   * 获取值在有序集合中的排名
   */
  public <K, V> Long zsetRank(K k, V v) {
    ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    return zSetOperations.rank(k, v);
  }

  /**
   * 根据分值获取有序集合数据列表
   */
  public <K, V> Set<V> zsetRangeByScore(K k, double min, double max) {
    ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    return zSetOperations.rangeByScore(k, min, max);
  }

  /**
   * 有序集合大小
   */
  public <K> Long zsetSize(K k) {
    ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    return zSetOperations.size(k);
  }

  /**
   * 根据分值删除有序集合数据
   */
  public <K> Long zsetRemoveRangeByScore(K k, double min, double max) {
    ZSetOperations zSetOperations = redisTemplate.opsForZSet();
    return zSetOperations.removeRangeByScore(k, min, max);
  }
}
