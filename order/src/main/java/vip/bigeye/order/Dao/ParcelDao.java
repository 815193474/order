package vip.bigeye.order.Dao;

import org.apache.ibatis.annotations.Mapper;
import vip.bigeye.order.entity.Parcel;

import java.util.List;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-15 12:07
 */
@Mapper
public interface ParcelDao {
    void insert(Parcel parcel);
   List<Parcel> findAll(Integer currIndex,Integer pageSize);
   List<Parcel> find();
   void deleteByName(String name);

}
