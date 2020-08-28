package com.github.user.manager.security.repository;

import com.github.user.manager.security.pojo.bo.PasswordBO;
import com.github.user.manager.security.pojo.orm.SystemUserDO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author 石少东
 * @date 2020-08-22 17:18
 * @since 1.0
 */

public interface IUserRepository extends JpaRepository<SystemUserDO, Long> {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return SystemUserDO
     */
    Optional<SystemUserDO> findByUsernameEquals(String username);

    /**
     * 根据用户的ID 更新密码
     *
     * @param password 用户密码
     * @param userId   用户的 ID
     */
    @Modifying
    @Query("UPDATE SystemUserDO user SET user.password = :password WHERE user.id = :userId")
    void updatePassword(@Param("password") PasswordBO password, @Param("userId") Long userId);

    /**
     * 获取当前登录的用户密码（加密过后的密码）
     *
     * @return String
     */
    @Query("SELECT user.password FROM SystemUserDO AS user WHERE user.username = :#{principal.username}")
    String findCurrentPassword();

    /**
     * 更新当前登录用户的密码，会自动加密
     *
     * @param password 密码
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("UPDATE SystemUserDO AS user SET user.password = :password WHERE user.username = :#{principal.username}")
    void updateCurrentPassword(String password);

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return boolean
     */
    boolean existsByUsername(String username);

    /**
     * 判断手机号码是否存在
     *
     * @param mobile 手机号码
     * @return boolean
     */
    boolean existsByMobile(String mobile);

    /**
     * 获取当前用户的信息
     *
     * @param clz 泛型类型
     * @param <V> 泛型
     * @return v
     */
    @Query("SELECT user FROM SystemUserDO AS user WHERE user.username = :#{principal.username}")
    <V> V findCurrentUser(Class<V> clz);

    /**
     * 查找所有的用户
     *
     * @param pageable 分页
     * @param clz      泛型
     * @param <V>      泛型
     * @return Page
     */
    <V> Page<V> findAllBy(Pageable pageable, Class<V> clz);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @param clz      泛型
     * @param <V>      泛型
     * @return Optional
     */
    <V> Optional<V> findByUsernameEquals(String username, Class<V> clz);

    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     * @param clz      泛型类型
     * @param <V>      泛型
     * @return v
     */
    <V> V findByUsernameIs(String username, Class<V> clz);

    /**
     * 根据当前用户的 ID 查找用户
     *
     * @param id 用户的 ID
     * @return 用户信息
     */
    SystemUserDO findById(long id);

    /**
     * 更新用户最后登录时间id
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("UPDATE SystemUserDO AS user SET user.lastLoginDate = CURRENT_TIMESTAMP WHERE user.username = :#{principal.username}")
    void updateLastLoginDate();
}
