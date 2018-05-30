package com.imook.sell.service.impl;

import com.imook.sell.enums.ResultEnum;
import com.imook.sell.exception.SellException;
import com.imook.sell.service.RedisLock;
import com.imook.sell.service.SecKillService;
import com.imook.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 压测用逻辑
 * @author Lucifer
 * @date 2018/01/24 17:53
 */
@Service
@Slf4j
public class SecKillServiceImpl implements SecKillService{

    private static final int TIMEOUT = 10 * 1000;

    private static int LOCKNUMBER = 0;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisLock redisLock;

    /**
     * 国庆活动，皮蛋瘦肉粥特价，限量100000份
     */
    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    static {
        /**
         * 模拟多个表,商品表,库存表,秒杀成功订单表
         */
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456",100000);
        stock.put("123456",100000);
    }

    private String queryMap(String productId){
        return "国庆活动，皮蛋瘦肉粥特价，活动份数" + products.get(productId)
                + ",剩余份数" + stock.get(productId) +
                ",该商品成功下单用户数量" + orders.size() + "人";
    }

   /* @Override
    public String querySecKillProductInfo(String productId){
        return this.queryMap(productId);
    }*/

/*    @Override
    public void orderProductMockDiffUser(String productId){
        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId,String.valueOf(time))){
            log.error("【糟糕,哎呦,被锁住了怎么办】" + (LOCKNUMBER++));
            throw new SellException(101,"哎呦喂，人实在是太多了，换个姿势试试～");
        }
        //1.查询该商品库存，为0时活动结束
        int stockNum = stock.get(productId);
        if (stockNum == 0){
            throw new SellException(100,"活动结束");
        }else {
            //2.下单，模拟不同用户的openid不同
            orders.put(KeyUtil.getUniqueKey(),productId);
            stockNum -= 1;
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }
        //解锁
        redisLock.unlock(productId,String.valueOf(time));
    }*/

    @Override
    public String querySecKillProductInfo(String productId){
        return redisTemplate.opsForValue().get("dingmingyi");
    }

    @Override
    public void orderProductMockDiffUser(String productId){
        /*try{
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address);
            String hostAddress = address.getHostAddress();
            System.out.println(hostAddress);
        }catch (UnknownHostException e){

        }
        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        boolean lockSuccess = false;
        while (!lockSuccess){
            lockSuccess = redisLock.lock(productId,String.valueOf(time));
            log.info("【锁的结果】:{}",lockSuccess);
            try{
                Random rand = new Random();
                int randNum = rand.nextInt(10);
                Thread.sleep(randNum);
            }catch (InterruptedException e){
                log.error("【获取拼单号】线程等待异常: {}",e);
                return;
            }
        }*/
        //1.查询该商品库存，为0时活动结束
        //int stockNum = stock.get(productId);
        long chardNum = Long.parseLong(redisTemplate.opsForValue().get("dingmingyi"));
        if (chardNum > 99999999){
            throw new SellException(100,"活动结束");
        }else {
            //2.下单，模拟不同用户的openid不同
            chardNum += 1;
            redisTemplate.opsForValue().set("dingmingyi",String.valueOf(chardNum));
           /* try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }*/
        }
        //解锁
        //redisLock.unlock(productId,String.valueOf(time));
        log.info("【一次调用over】拼单号为: {}",chardNum);
    }

}
