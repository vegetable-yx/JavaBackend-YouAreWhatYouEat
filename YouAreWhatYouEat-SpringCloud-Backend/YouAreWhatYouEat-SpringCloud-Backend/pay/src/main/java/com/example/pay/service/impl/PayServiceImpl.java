package com.example.pay.service.impl;

import com.example.pay.dto.OrderStatusOutDto;
import com.example.pay.dto.PayQROutDto;
import com.example.pay.entity.OrderlistEntity;
import com.example.pay.repository.OrderRepository;
import com.example.pay.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.factory.Factory.Payment;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;

import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class PayServiceImpl implements PayService {
    @Resource
    private OrderRepository orderRepository;

    @Value("${alipay.gatewayHost}")
    private String gatewayHost;

    @Value("${alipay.appId}")
    private String appId;

    @Value("${alipay.privateKey}")
    private String privateKey;

    @Value("${alipay.publicKey}")
    private String publicKey;

    @Value("${alipay.signType}")
    private String signType;

    public Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = this.gatewayHost;
        config.signType = this.signType;
        config.appId = this.appId;
        config.merchantPrivateKey = this.privateKey;
        config.alipayPublicKey = this.publicKey;
        return config;
    }

    @Override
    public PayQROutDto pay(String orderId, Double price) {
        Factory.setOptions(getOptions());
        PayQROutDto result = new PayQROutDto();
        Optional<OrderlistEntity> orderlist = orderRepository.findByOrderId(orderId);
        if (orderlist.isEmpty()) return null;
        if (orderlist.get().getOrderStatus().equals("已支付")) return null;

        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
        String oid = ft.format(orderlist.get().getCreationTime());

        try {
            AlipayTradePrecreateResponse response = Payment.FaceToFace()
                    .preCreate("Apple iPhone11 128G", "2234567890", "5799.00");
            result.setQrcode(response.getQrCode());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderStatusOutDto getOrderPayStatus(String id) {
        Optional<OrderlistEntity> orderlist = orderRepository.findByOrderId(id);
        if (orderlist.isEmpty()) return null;

        OrderStatusOutDto result = new OrderStatusOutDto();
        result.setOrder_status(orderlist.get().getOrderStatus());
        return result;
    }
}
