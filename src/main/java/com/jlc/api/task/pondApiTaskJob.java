package com.jlc.api.task;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import redis.clients.jedis.ShardedJedis;

import com.alibaba.fastjson.JSON;
import com.jlc.api.cache.redis.RedisDataSource;
import com.jlc.api.dto.CJActivite;
import com.jlc.api.service.SweepstakeService;


@Component("pondApiTaskJob")
public class pondApiTaskJob { 
	@Autowired
	private SweepstakeService sweepstakeService;
	
	@Autowired
    private RedisDataSource redisDataSource;
    
	@Scheduled(cron = "0 */1 * * * ?")
	public void insertPond() throws Exception{  
		System.out.println("奖池job开始=========================================");
		ShardedJedis shardedJedis = null;
		try{
			shardedJedis = redisDataSource.getRedisClient();
			if (shardedJedis == null) {
	            return;
	        }
			Long result = shardedJedis.setnx("insertPond_job","1");
			if(result == 0){
				return;
			}
	        //获取未开始的规则，以便生成奖池
//			String rid = shardedJedis.lindex("pondList", 0);
//	        if(rid == null) return;
//			boolean flag = false;
			//获取所有没有开始的规则
	        List<String> rids = shardedJedis.lrange("pondList", 0, -1);
			//正在进行的活动
			List<CJActivite> avtivities = sweepstakeService.getAllUsedZpActivity();
			//活动已匹配上奖池
			for(String rid:rids){
				for(CJActivite ac :avtivities){
					if(rid.equals(String.valueOf(ac.getRegulation1Id())) || 
							rid.equals(String.valueOf(ac.getRegulation2Id()))
							||rid.equals(String.valueOf(ac.getRegulation3Id()))){
						//已存在或 已生成
						String usedRid =  addPond(rid);
						if(StringUtils.isNoneBlank(usedRid)){
							shardedJedis.lrem("pondList", 0, usedRid);
						}
						
					}
				}
			}
			//奖池已放在redis中，更新缓存中活动绑定的规则
			try{
				for(CJActivite ac :avtivities){
		        	if(shardedJedis.exists("pondList-"+ac.getRegulation1Id()) &&
		        			shardedJedis.exists("pondList-"+ac.getRegulation2Id())&&
		        			shardedJedis.exists("pondList-"+ac.getRegulation3Id())){
		        		sweepstakeService.updateActivity(ac.getId());
		        		
		        	}
				}
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("更新缓存活动=====================================");
			}
	        
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			shardedJedis.del("insertPond_job");
			redisDataSource.returnResource(shardedJedis);
		}
		System.out.println("奖池job结束=========================================");
	}  
	
	
	private String addPond(String rid){
		ShardedJedis shardedJedis = null;
		
		shardedJedis = redisDataSource.getRedisClient();
		if (shardedJedis == null)  return null;
	        
		long regulationId = Long.parseLong(rid);
        shardedJedis.set("regulationId"+regulationId+"", "-");
//        String key = "pondList."+regulationId;
//        List<CJPond> list = new ArrayList<CJPond>();
        try{
//    		String json = "";
			//批量插入。。。。
			//获取redis中缓存的奖池数据
//			for(int i=0;i<100000;i++){
//				json = shardedJedis.lpop(key);
//				CJPond pond = JSON.parseObject(json, CJPond.class); 
//				if(pond == null){
//					if(!list.isEmpty()){
//						//批量添加奖池
//						sweepstakeService.insertPondBatch(list);
//						list.clear();
//					}	
//					//奖池清洗
//					sweepstakeService.clearPonds(regulationId);
//					//修改该规则为进行中
//					sweepstakeService.updateRegulationStatus(regulationId,1);
//					shardedJedis.del(key);
////					shardedJedis.lpop("pondList");
//					break;
//				}
//				list.add(pond);
//				if((i+1) % 100 == 0 && !list.isEmpty()){
//					sweepstakeService.insertPondBatch(list);
//					list.clear();
//				}
//			}
        	//插入缓存
        	if(shardedJedis.exists("pondList-"+regulationId)||shardedJedis.exists("pondList+"+regulationId)) return rid;
        	sweepstakeService.addRedisPonds(regulationId);
        	return rid;
        }catch(Exception e){
        	e.printStackTrace();
        }
        return null;
	}
	
	
}  