package cn.itcast.order.web;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;

   @Resource
   private UserClient userClient;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        Order order = orderService.queryOrderById(orderId);
        order.setDate(LocalDateTime.now());
        return order;
    }

    @GetMapping("/query")
    public String queryOrder() {
        orderService.queryGoods();
        return "查询订单成功";
    }

    @GetMapping("/save")
    public String saveOrder() {
        orderService.queryGoods();
        System.out.println("新增订单");
        return "新增订单成功";
    }

    @GetMapping("/update")
    public String updateOrder() {
        return "更新订单成功";
    }


    /**
     * 测试feign post
     */
    @GetMapping("/user")
    public User queryUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("aaa");
        user.setAddress("bbb");
        return userClient.post(user);
    }

    @GetMapping("/lock")
    public void lockTest() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RLock lock = redissonClient.getLock("product:1");
        lock.lock(100, TimeUnit.SECONDS);
        System.out.println("do...");
        lock.unlock();
        System.out.println("finish...");

    }
}
