package cn.itcast.feign.clients;

import cn.itcast.feign.pojo.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/*
 * @author GF
 * @since 2023/2/25
 */
@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public User findById(Long id) {
                log.error("查询用户异常", throwable);
                return new User();
            }

            @Override
            public User post(User user) {
                log.error("post error", throwable);
                return new User();
            }
        };
    }
}
