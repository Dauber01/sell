package com.imook.sell.service;

import com.imook.sell.dataobject.SellerInfo;

/**
 * 卖家service
 * @author Lucifer
 * @date 2018/01/19 9:45
 */
public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
