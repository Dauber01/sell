package com.imook.sell.service;

/**
 * 压测用service
 * @author Lucifer
 * @date 2018/01/24 17:51
 */
public interface SecKillService {

    public String querySecKillProductInfo(String productId);

    public void orderProductMockDiffUser(String productId);

}
