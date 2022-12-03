package com.example.service_hi.user.service.impl;

import com.example.service_hi.user.dao.UserDao;
import com.example.service_hi.user.entity.User;
import com.example.service_hi.user.service.UserService;
import com.example.service_hi.user.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-11-30 11:29:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String userId) {
        return userDao.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return queryById(user.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userId) {
        return userDao.deleteById(userId) > 0;
    }

    /**
     * 以下为查询注解方式
     * @param userName
     * @param password
     * @return
     */

    @Override
    public User findUserByNameAndPassword(String userName, String password) {
        return userDao.findUserByNameAndPassword(userName, password);
    }

    @Override
    public int insertUserByNameAndPassword(String userName, String password) {
        return userDao.insertUserByNameAndPassword(userName, password);
    }

    @Override
    public int updateUserById(User user) {
        return userDao.updateUserById(user);
    }

    @Override
    public int deleteUserById(String userId) {
        return userDao.deleteUserById(userId);
    }


    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public int login(User user) {
        Subject subject = SecurityUtils.getSubject();
        User user_db = userDao.selectUserByUsername(user.getUserName());
        // 如果没有认证过，那么我们就构建一个token, 而且要记住我
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), MD5Util.getMD5(user.getPassword(),user_db.getSalt()));
        try {
            // 如果登陆没有发生异常，说明登陆成功，就转发到main页面
            subject.login(token);
            subject.checkPermissions();
            return 200;
        } catch (AuthenticationException e) {
            System.err.println("用户名或密码不对");
            return 500;
        }
//        return 200;
    }

    @Override
    public User selectUserByUserName(String userName) {
        return userDao.selectUserByUsername(userName);
    }


}