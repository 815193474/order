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
import vip.bigeye.order.entity.Parcel;
import vip.bigeye.order.entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author wolf  VX:a815193474
 * @Data 2019-11-22 14:40
 */
@Controller
@RequestMapping(value = "/insert")
@Api(tags = "插入接口")
public class InsertController {
    @Autowired
    private UserService userService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private OrdersService ordersService;
    @ApiOperation(value = "插入用户")
    @ResponseBody
    @PostMapping("/user")
    public String insertUser(@RequestParam(name = "username") String username,@RequestParam String name,
                         @RequestParam String password,@RequestParam String type,@RequestParam(required = false) long phone){
if (userService.findByUsername(username)== null){

    User user=new User();
    user.setName(name);
    user.setUsername(username);
    user.setPassword(password);
    user.setPhone(phone);
    user.setType(type);
    userService.insert(user);
    return "success";
}else {
    return "用户名已存在";
}



    }

    @ApiOperation(value = "插入订单信息")
    @ResponseBody
    @PostMapping("/orders")
    public String insertParcel(@RequestParam String client,@RequestParam String product,
                               @RequestParam String ordersRequire,
                               @RequestParam String clientNote,@RequestParam int number,@RequestParam String collect,
                               @RequestParam String person,
                             @RequestParam String factoryUsername,
                               @RequestParam String businessName,@RequestParam String businessUsername){
String factoryName=userService.findByUsername(factoryUsername).getName();
      Orders o=new Orders();
      o.setBusinessName(businessName);
      o.setBusinessUsername(businessUsername);
      o.setClient(client);
      o.setClientNote(clientNote);
      o.setCollect(collect);
        Date d = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
      o.setOrdersDate(f.format(d));
      o.setFactoryName(factoryName);
      o.setFactoryUsername(factoryUsername);
      o.setOrdersRequire(ordersRequire);
      o.setReturns("notSend");
      o.setProduct(product);
      o.setPerson(person);
      o.setOrdersNumber(number);
      ordersService.insert(o);
        return "index";


    }

    @ApiOperation(value = "插入快递信息")
    @ResponseBody
    @PostMapping("/parcel")
    public String insertOrders(@RequestParam String name){

        Parcel p=new Parcel();
        p.setName(name);
        parcelService.insert(p);
        return "index";

    }




}


