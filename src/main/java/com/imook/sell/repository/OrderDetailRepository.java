package com.imook.sell.repository;

import com.imook.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情
 * @author Lucifer
 * @date 2017／12／30 21:58
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{

    List<OrderDetail> findByOrderId(String orderId);
}
