package cn.itcast.order.config.feign;

import feign.Retryer;

/**
 * @author GF
 * @since 2023/5/24
 */
public class OnceRetryer extends Retryer.Default{


    /**
     * 重试一次
     */
    public OnceRetryer() {
        super(100, 1000, 2);
    }

}
