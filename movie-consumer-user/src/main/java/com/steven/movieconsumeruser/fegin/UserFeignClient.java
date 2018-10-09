package com.steven.movieconsumeruser.fegin;

import com.steven.movieconsumeruser.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign接口
 *
 * @author Steven
 * @since 2018/9/26 026 15:17
 */
@FeignClient(name = "movie-provider-user")
public interface UserFeignClient {

    /**
     * 根据ID查到用户信息
     *
     * @param id ID
     * @return 用户
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);
}
