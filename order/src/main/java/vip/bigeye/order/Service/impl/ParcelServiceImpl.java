package vip.bigeye.order.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.bigeye.order.Dao.ParcelDao;
import vip.bigeye.order.Service.ParcelService;
import vip.bigeye.order.entity.Parcel;

import java.util.List;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-15 12:04
 */
@Service
public class ParcelServiceImpl implements ParcelService {
    @Autowired(required = false)
    private ParcelDao parcelDao;

    @Override
    public void insert(Parcel parcel) {
        parcelDao.insert(parcel);
    }

    @Override
    public List<Parcel> findAll(Integer currIndex, Integer pageSize) {
        return parcelDao.findAll(currIndex,pageSize);
    }

    @Override
    public void deleteByName(String name) {
        parcelDao.deleteByName(name);
    }

    @Override
    public List<Parcel> find() {
        return parcelDao.find();
    }
}
