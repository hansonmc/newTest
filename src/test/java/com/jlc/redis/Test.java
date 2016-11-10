package com.jlc.redis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test {

	private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
	
    public Test() 
    { 
        initialPool(); 
        jedis = jedisPool.getResource(); 
    } 
    
    /**
     * 初始化非切片池
     */
    private void initialPool() 
    { 
        // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxActive(20); 
        config.setMaxIdle(5); 
        config.setMaxWait(1000l); 
        config.setTestOnBorrow(false); 
        
        jedisPool = new JedisPool(config,"127.0.0.1",6379);
    }
    
    private void ListOperate() {
  	  System.out.println("======================list=========================="); 
        // 清空数据 
        System.out.println("清空库中所有数据："+jedis.flushDB()); 

        System.out.println("=============增=============");
        jedis.lpush("stringlists", "vector"); 
        jedis.lpush("stringlists", "ArrayList"); 
        jedis.lpush("stringlists", "vector");
        jedis.lpush("stringlists", "vector");
        jedis.lpush("stringlists", "LinkedList");
        jedis.lpush("stringlists", "MapList");
        jedis.lpush("stringlists", "SerialList");
        jedis.lpush("stringlists", "HashList");
        jedis.rpush("stringlists", "HashList111");
        System.out.println("所有元素-stringlists："+jedis.lrange("stringlists", 0, -1));
        String s = jedis.lpop("stringlists");
        System.out.println(s);
        System.out.println("所有元素-stringlists："+jedis.lrange("stringlists", 0, -1));
        jedis.del("stringlists");
        System.out.println("所有元素-stringlists："+jedis.lrange("stringlists", 0, -1));
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Test().ListOperate();
		
	}

}
