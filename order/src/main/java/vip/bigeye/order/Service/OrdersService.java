package vip.bigeye.order.Service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import vip.bigeye.order.entity.Orders;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-15 12:00
 */
@Service
public interface OrdersService {
    Orders findById(String id);
    List<Orders> findByParam(Integer currIndex, Integer pageSize, String factoryUsername,
                             String businessUsername, String returns, Date startTime,Date stopTime );
    void update(Orders orders);
    void insert(Orders orders);
    void deleteById(String id);
    List<Orders> findAll();
    List<Orders> findByMap(Map map);
    List<Orders> findByParam(Map map);
    List<Orders> findByReturns(String returns);
    List<Orders> findByTime(String time);
    List<Orders> findByBusiness(String business);
    List<Orders> findByFactory(String factory);
    List<Orders> findByTimeAndFactory(String time,String factory);
    List<Orders> findByTimeAndBusiness(String time,String business);
    List<Orders> findByReturnsAndBusiness(String returns,String business);
    List<Orders> findByReturnsAndFactory(String returns,String factory);
}
