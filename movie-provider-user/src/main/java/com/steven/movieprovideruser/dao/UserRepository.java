package com.steven.movieprovideruser.dao;

import com.steven.movieprovideruser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Steven
 * @since 2018年9月21日 14:56:04
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
