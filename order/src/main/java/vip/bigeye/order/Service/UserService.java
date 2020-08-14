package vip.bigeye.order.Service;

import org.springframework.stereotype.Service;
import vip.bigeye.order.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-15 12:03
 */

public interface UserService {
    User findByUsername(String username);
    List<User> findAll(Map map);
    List<User> find(String type);
    void update(User user);
    void deleteByUsername(String username);
    void insert(User user);
}
