package vip.bigeye.order.Dao;


import org.apache.ibatis.annotations.Mapper;
import vip.bigeye.order.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-15 12:07
 */
@Mapper
public interface UserDao {
    User findByUsername(String username);
    List<User> findAll(Map map);
    void update(User user);
    void deleteByUsername(String username);
    void insert(User user);
    List<User> find(String type);
}
