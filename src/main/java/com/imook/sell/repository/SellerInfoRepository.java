package com.imook.sell.repository;

import com.imook.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 卖家信息查询
 * @author Lucifer
 * @date 2018／01／19 00:55
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String>{

    SellerInfo findByOpenid(String openid);

}
