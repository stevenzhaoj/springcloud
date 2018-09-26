package com.steven.movieconsumeruser.fegin;

import com.steven.movieconsumeruser.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    @GetMapping("/{id}") User findById(@PathVariable("id") Long id);
}
