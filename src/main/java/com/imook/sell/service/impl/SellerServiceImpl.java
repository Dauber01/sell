package com.imook.sell.service.impl;

import com.imook.sell.dataobject.SellerInfo;
import com.imook.sell.repository.SellerInfoRepository;
import com.imook.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 卖家service
 * @author Lucifer
 * @date 2018/01/19 9:46
 */
@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    /**
     * 通过openid查询卖家信息
     * @param openid
     * @return
     */
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
