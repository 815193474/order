package vip.bigeye.order.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vip.bigeye.order.Service.OrdersService;
import vip.bigeye.order.Service.ParcelService;
import vip.bigeye.order.Service.UserService;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-20 23:44
 */
@Controller
@RequestMapping(value = "/delete")
@Api(tags = "删除接口")
public class DeleteController {

    @Autowired
    private UserService userService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private OrdersService ordersService;

    @ApiOperation(value = "删除用户")

    @ResponseBody
    @PostMapping("/user")
    public void deleteUser(@RequestParam String username){
        userService.deleteByUsername(username);
    }

    @ApiOperation(value = "删除快递公司")

    @ResponseBody
    @PostMapping("/parcel")
    public void deleteParcel(@RequestParam String name){
        parcelService.deleteByName(name);
    }


    @ApiOperation(value = "删除订单")

    @ResponseBody
    @PostMapping("/orders")
    public void deleteOrders(@RequestParam String id){
        ordersService.deleteById(id);
    }
}
