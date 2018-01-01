package com.imook.sell.service.impl;

import com.imook.sell.converter.OrderMasterToOrderDtoConverter;
import com.imook.sell.dataobject.OrderDetail;
import com.imook.sell.dataobject.OrderMaster;
import com.imook.sell.dataobject.ProductInfo;
import com.imook.sell.dto.CartDTO;
import com.imook.sell.dto.OrderDto;
import com.imook.sell.enums.OrderStatusEnum;
import com.imook.sell.enums.PayStatusEnum;
import com.imook.sell.enums.ResultEnum;
import com.imook.sell.exception.SellException;
import com.imook.sell.repository.OrderDetailRepository;
import com.imook.sell.repository.OrderMasterRepository;
import com.imook.sell.service.OrderService;
import com.imook.sell.service.ProductInfoService;
import com.imook.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单处理
 * @author Lucifer
 * @date 2017／12／31 01:10
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1.查询商品,单价和库存
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (null == productInfo){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                                                        .add(orderAmount);
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        //3.存库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4.减少对应的库存
        List<CartDTO> cartDTOList = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (null == orderMaster){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid(buyerOpenId,pageable);
        List<OrderDto> orderDtos = OrderMasterToOrderDtoConverter.converter(orderMasters.getContent());
        return new PageImpl<OrderDto>(orderDtos,pageable,orderMasters.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //1.判断订单状态
        if (!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())){
            log.info("【取消订单】订单状态不正确,orderId = {},orderStatus = {}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.更改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (null == updateResult){
            log.error("【取消订单】更新失败,orderId = {},orderStatus = {}",orderMaster.getOrderId(),orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3.返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【取消订单】无订单详情,orderMaster = {}",orderMaster);
            throw new SellException(ResultEnum.ORDER_DETAIL_ENTITY);
        }
        List<CartDTO> cartDTOList = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);
        //4.如果已支付，则退款
        if (PayStatusEnum.SUCCESS.equals(orderDto.getPayStatus())){
            //TODO 增加退款逻辑
        }
        return orderDto;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        //1.判断订单状态
        if (!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())){
            log.info("【完结订单】订单状态不正确,orderId = {},orderStatus = {}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //2.更改状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (null == updateResult){
            log.error("【完结订单】更新失败,orderId = {},orderStatus = {}",orderMaster.getOrderId(),orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        //1.判断订单状态
        if (!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())){
            log.error("【订单支付】订单状态不正确,orderId = {},orderStatus = {}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.判断支付状态
        if (!PayStatusEnum.WAIT.getCode().equals(orderDto.getPayStatus())){
            log.error("【订单支付】订单支付状态不正确,orderId = {},",orderDto.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //3.更新支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (null == updateResult){
            log.error("【订单支付】更新失败,orderId = {},orderStatus = {}",orderMaster.getOrderId(),orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }
}
