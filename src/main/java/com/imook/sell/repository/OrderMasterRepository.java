package com.imook.sell.repository;

import com.imook.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单
 * @author Lucifer
 * @date 2017／12／30 21:55
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenId, Pageable pageable);
}
