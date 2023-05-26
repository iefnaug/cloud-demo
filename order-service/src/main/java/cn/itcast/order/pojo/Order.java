package cn.itcast.order.pojo;

import cn.itcast.feign.pojo.User;
import cn.itcast.order.serialize.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private Long price;
    private String name;
    private Integer num;
    private Long userId;
    private User user;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime date;
}