package vip.bigeye.order.Service;

import org.springframework.stereotype.Service;
import vip.bigeye.order.entity.Parcel;

import java.util.List;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-15 12:01
 */
@Service
public interface ParcelService {
    void insert(Parcel parcel);
    List<Parcel> findAll(Integer currIndex, Integer pageSize);
    void deleteByName(String name);
    List<Parcel> find();
}
