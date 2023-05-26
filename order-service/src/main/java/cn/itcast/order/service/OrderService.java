package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
//import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        // 2.用Feign远程调用
        User user = userClient.findById(order.getUserId());
        // 3.封装user到Order
        order.setUser(user);
        // 4.返回
        return order;
    }

//    @Autowired
//    private RestTemplate restTemplate;
//
//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        // 2.利用RestTemplate发起http请求，查询用户
//        // 2.1.url路径
//        String url = "http://userservice/user/" + order.getUserId();
//        // 2.2.发送http请求，实现远程调用
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        User user = new User();
//        try {
//            user = restTemplate.getForObject(url, User.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        stopWatch.stop();
//        System.out.println(stopWatch.getTotalTimeMillis());
//        // 3.封装user到Order
//        order.setUser(user);
//        // 4.返回
//        return order;
//    }

//    @SentinelResource("goods")
    public void queryGoods() {
        System.out.printf("查询商品");
    }
}
