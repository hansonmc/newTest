//package com.jlc.api.task;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import redis.clients.jedis.ShardedJedis;
//
//import com.alibaba.fastjson.JSON;
//import com.jlc.api.cache.redis.RedisDataSource;
//import com.jlc.api.dto.CJPond;
//import com.jlc.api.service.SweepstakeService;
//
//@Component("pondInsertDBJob")
//public class pondInsertDBJob {
//	private static Logger log = Logger.getLogger(pondInsertDBJob.class);
//	
//	@Autowired
//	private SweepstakeService sweepstakeService;
//	
//	@Autowired
//    private RedisDataSource redisDataSource;
//    
////	@Scheduled(cron = "0 */1 * * * ?")
//	public void insertPondDB() throws Exception{  
//		log.info("insertPondDBjob开始=========================================");
//		ShardedJedis shardedJedis = null;
//		List<String> rids = null;
//		try{
//			shardedJedis = redisDataSource.getRedisClient();
//			if (shardedJedis == null) {
//	            return;
//	        }
//			Long result = shardedJedis.setnx("insertPond_DB_job","1");
//			if(result == 0 ){
//				return;
//			}
//			
//	        List<CJPond> list = new ArrayList<CJPond>();
//	        //获取所有没有开始的规则
//	        rids = shardedJedis.lrange("pondList", 0, -1);
//	      
//			for(String rid:rids){
//				long regulationId = Long.parseLong(rid);
//		        String key = "pondList."+regulationId;
//		        try{
//		        	
//		        	//先判断数据库奖池中是否有奖池
//		        	Integer pcount = sweepstakeService.getPondCountByRid(regulationId);
//			 		if (pcount>0) {
//			 			log.info("数据库中奖池已存在 rid="+regulationId);
//			 			shardedJedis.del(key);
//			 			continue;
//					}
//		    		String json = "";
//					//批量插入。。。。
//					//获取redis中缓存的奖池数据
//					for(int i=0;i<100000;i++){
//						json = shardedJedis.lpop(key);
//						CJPond pond = JSON.parseObject(json, CJPond.class); 
//						if(pond == null){
//							if(!list.isEmpty()){
//								//批量添加奖池
//								sweepstakeService.insertPondBatch(list);
//								list.clear();
//							}	
//							//奖池清洗
//							sweepstakeService.clearPonds(regulationId);
//							//修改该规则为进行中
//							sweepstakeService.updateRegulationStatus(regulationId,1);
//							shardedJedis.del(key);
////							shardedJedis.lpop("pondList");
//							break;
//						}else{
//							list.add(pond);
//							if((i+1) % 100 == 0 && !list.isEmpty()){
//								sweepstakeService.insertPondBatch(list);
//								list.clear();
//							}	
//						}
//						
//					}
//		        	
//		        }catch(Exception e){
//		        	CJPond pond = new CJPond();
//		        	for(int i=0,l=list.size();i<l;i++){
//		        		pond = list.get(i);
//		        		shardedJedis.rpush("pondList."+regulationId, JSON.toJSONString(pond));
//		        	}
//		        	log.error(e.getMessage());
//		        }
//			}
//	        
//		}catch(Exception e){
//			log.error(e.getMessage());
//		}finally{
//			//给pondList重新赋值
//			shardedJedis.del("insertPond_DB_job");
//			redisDataSource.returnResource(shardedJedis);
//		}
//		log.info("insertPondDBjob结束=========================================");
//	}  
//	
//}  