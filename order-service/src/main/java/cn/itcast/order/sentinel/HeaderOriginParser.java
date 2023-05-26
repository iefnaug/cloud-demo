package cn.itcast.order.sentinel;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GF
 * @since 2023/2/26
 */
@Component
public class HeaderOriginParser /*implements RequestOriginParser*/ {
//    @Override
//    public String parseOrigin(HttpServletRequest httpServletRequest) {
//        String origin = httpServletRequest.getHeader("origin");
//        if (StringUtils.isEmpty(origin)) {
//            origin = "blank";
//        }
//        return origin;
//    }
}
