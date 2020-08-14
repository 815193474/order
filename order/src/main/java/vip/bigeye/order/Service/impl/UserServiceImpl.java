package vip.bigeye.order.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.bigeye.order.Dao.UserDao;
import vip.bigeye.order.Service.UserService;
import vip.bigeye.order.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-15 12:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserDao userDao;
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> findAll(Map map) {
        return userDao.findAll(map);
    }

    @Override
    public List<User> find(String type) {
        return userDao.find(type);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteByUsername(String username) {
        userDao.deleteByUsername(username);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
}
