package com.example.service_hi.user.dao;

import com.example.service_hi.user.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-30 11:29:14
 */
@Mapper
@Repository(value="userDao")
public interface UserDao {
    /**
     * 采用mapper文件查询
     */

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    User queryById(String userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(String userId);

    /***
     * 2、采用注解的方式查询
     */
    @Select("select * from user where user_name =#{userName} and password =#{password}")
    User findUserByNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    @Insert("insert into user(user_name,password) values(#userName,#password)")
    int insertUserByNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    @Update({ "update user set user_name = #{userName},password = #{password},user_phone = #{userPhone} where user_id = #{userId}" })
    int updateUserById(User user);

    @Delete("delete from user where user_id = #{userId}")
    int deleteUserById(String userId);

    @Select("select * from user where user_name =#{userName}")
    User selectUserByUsername(String userName);
}