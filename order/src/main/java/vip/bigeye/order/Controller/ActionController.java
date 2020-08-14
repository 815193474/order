package vip.bigeye.order.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import vip.bigeye.order.Service.OrdersService;
import vip.bigeye.order.Service.ParcelService;
import vip.bigeye.order.Service.UserService;
import vip.bigeye.order.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-02-24 10:45
 */
@Controller
@RequestMapping(value = "/action")
@Api(tags = "跳转接口")
public class ActionController {

    @Autowired
    private UserService userService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private OrdersService ordersService;

    //项目首页跳转接口
    @GetMapping(value = "login")
    @ApiOperation(value = "首页访问")
    public String index(){
        return "login";
    }

    @PostMapping(value = "insertParcel")
    @ApiOperation(value = "新增快递公司")
    public String insertParcel(){
        return "insert_parcel";
    }


    @PostMapping(value = "insertOrders")
    @ApiOperation(value = "新增订单")
    public String insertOrders(){
        return "insert_orders";
    }

    @PostMapping(value = "insertFactory")
    @ApiOperation(value = "新增厂家")
    public String insertFactory(){
        return "insert_factory";
    }

    @PostMapping(value = "insertBusiness")
    @ApiOperation(value = "新增商家")
    public String insertBusiness(){
        return "insert_business";
    }

    @PostMapping(value = "findOne")
    @ResponseBody
    @ApiOperation(value="查找单个用户")
    public User findOne(@RequestParam(name="username") String username){
        return userService.findByUsername(username);
    }






    @RequestMapping(value = "factoryIndex")
    @ApiOperation(value="厂家首页")
    public String factoryIndex(){
        return "factory_index";
    }

}
