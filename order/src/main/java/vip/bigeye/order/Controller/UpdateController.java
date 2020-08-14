package vip.bigeye.order.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vip.bigeye.order.Service.OrdersService;
import vip.bigeye.order.Service.ParcelService;
import vip.bigeye.order.Service.UserService;
import vip.bigeye.order.entity.Orders;
import vip.bigeye.order.entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-20 15:01
 */
@Controller
@RequestMapping(value = "/update")
@Api(tags = "更新接口")
public class UpdateController {
    @Autowired
    private UserService userService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private OrdersService ordersService;

    @ApiOperation(value = "更新订单信息")
    @ResponseBody
    @PostMapping("/orders")
    public String updateParcel(@RequestParam String id, @RequestParam String parcel, @RequestParam String parcelNumber, @RequestParam String collect, @RequestParam String factoryNote, @RequestParam String returns
                               ){
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");

        Orders o=ordersService.findById(id);

        o.setCollect(collect);
        o.setSendDate(s.format(d));
        o.setFactoryNote(factoryNote);
        o.setReturns(returns);
        o.setParcelNumber(parcelNumber);
        o.setParcel(parcel);
        ordersService.update(o);
        return "index";


    }


    @ApiOperation(value = "更新用户")
    @ResponseBody
    @PostMapping("/user")
    public String insertUser(@RequestParam(name = "username") String username,@RequestParam String name,
                             @RequestParam String password,@RequestParam String type,@RequestParam long phone){

        User user=new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setType(type);
        userService.update(user);
        return "index";


    }



}
