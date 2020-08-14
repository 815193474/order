package vip.bigeye.order.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import vip.bigeye.order.Dao.OrdersDao;
import vip.bigeye.order.Service.OrdersService;
import vip.bigeye.order.entity.Orders;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-15 12:04
 */
@Service
public class OrdersServiceImpl  implements OrdersService {
    @Autowired(required = false)
    private OrdersDao ordersDao;
    @Override
    public Orders findById(String id) {
        return ordersDao.findById(id);
    }

    @Override
    public List<Orders> findByParam(Integer currIndex, Integer pageSize, String factoryUsername, String businessUsername, String returns, Date startTime, Date stopTime) {
        return ordersDao.findByParam( currIndex, pageSize,  factoryUsername, businessUsername,returns, startTime,   stopTime);
    }


    @Override
    public void update(Orders orders) {
        ordersDao.update(orders);

    }

    @Override
    public void insert(Orders orders) {
        ordersDao.insert(orders);

    }

    @Override
    public void deleteById(String id) {
        ordersDao.deleteById(id);

    }

    @Override
    public List<Orders> findAll() {
        return ordersDao.findAll();
    }

    @Override
    public List<Orders> findByMap(Map map) {
        return ordersDao.findByMap(map);
    }

    @Override
    public List<Orders> findByParam(Map map) {
        return ordersDao.findByParam(map);
    }

    @Override
    public List<Orders> findByReturns(String returns) {
        return ordersDao.findByReturns(returns);
    }

    @Override
    public List<Orders> findByTime(String time) {
        return ordersDao.findByTime(time);
    }

    @Override
    public List<Orders> findByBusiness(String business) {
        return ordersDao.findByBusiness(business);
    }

    @Override
    public List<Orders> findByFactory(String factory) {
        return ordersDao.findByFactory(factory);
    }

    @Override
    public List<Orders> findByTimeAndFactory(String time, String factory) {
        return ordersDao.findByTimeAndFactory(time,factory);
    }

    @Override
    public List<Orders> findByTimeAndBusiness(String time, String business) {
        return ordersDao.findByTimeAndBusiness(time,business);
    }

    @Override
    public List<Orders> findByReturnsAndBusiness(String returns, String business) {
        return ordersDao.findByReturnsAndBusiness(returns,business);
    }

    @Override
    public List<Orders> findByReturnsAndFactory(String returns, String factory) {
        return ordersDao.findByReturnsAndFactory(returns,factory);
    }
}
