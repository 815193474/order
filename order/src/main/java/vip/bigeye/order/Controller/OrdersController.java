package vip.bigeye.order.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.bigeye.order.Service.OrdersService;
import vip.bigeye.order.Service.ParcelService;
import vip.bigeye.order.Service.UserService;
import vip.bigeye.order.Utils.PageController;
import vip.bigeye.order.entity.Orders;
import vip.bigeye.order.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-24 12:50
 */
@Controller
@RequestMapping(value = "/orders")
@Api(tags = "订单接口")
public class OrdersController {

    @Autowired
    private UserService userService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private OrdersService ordersService;


    @RequestMapping(value = "/select")
    @ApiOperation(value = "订单")
    public String Orders(ModelMap modelMap, @RequestParam(required = false,defaultValue = "") String factoryUsername,@RequestParam(required = false,defaultValue = "0") int by,
                             @RequestParam int pageNo, @RequestParam(required = false,defaultValue = "") String businessUsername, @RequestParam String returns, @RequestParam(required = false,defaultValue = "") String startTime,
                             @RequestParam(required = false,defaultValue = "") String stopTime, HttpServletRequest request) {

        int pageSize = 2;//每页显示记录数
        String username = (String) request.getSession().getAttribute("username");//读取username
        Map map = new HashMap<>();//新建map用来传参
     //判断时间是否为空
        if (startTime.equals("")){
                startTime=null;
            System.out.println("时间没有被存储");
        }else {
        map.put("startTime", startTime);
            System.out.println("时间被存储");
        }
         if (stopTime.equals("")){
            stopTime=null;
         }else {
        map.put("stopTime", stopTime);
         }

        //判断用户类型
        if (userService.findByUsername(username).getType().equals("business")) {
            map.put("businessUsername", username);
            if (factoryUsername==null||factoryUsername.length()==0){

                factoryUsername=null;
            }else {
                map.put("factoryUsername",factoryUsername);
            }
                    if (businessUsername==null||businessUsername.length()==0){
                        businessUsername=null;
                    }
        } else if (userService.findByUsername(username).getType().equals("factory")) {
            map.put("factoryUsername", username);
            if (businessUsername==null||businessUsername.length()==0){
                businessUsername=null;
            }else {
                map.put("businessUsername", businessUsername);
            }
                    if (factoryUsername==null||factoryUsername.length()==0){
                        factoryUsername=null;
                    }

        } else {
            if (factoryUsername==null||factoryUsername.length()==0){
                factoryUsername=null;
            }else {
                map.put("factoryUsername",factoryUsername);
            }
            if (businessUsername==null||businessUsername.length()==0){
                businessUsername=null;
            }else {
                map.put("businessUsername", businessUsername);
            }
        }

        map.put("returns", returns);
        //通过页码控制类获取上一页、下一页、总页数、总条数
        PageController p = new PageController();
        p.setPageNo(pageNo);
        p.setPageSize(pageSize);
        p.setTotalRecords(ordersService.findByParam(map).size());//根据现有条件查询出订单记录数
        if (ordersService.findByParam(map).size()>0){


        map.put("currIndex",p.getCurrIndex());
        map.put("pageSize", pageSize);
        List<Orders> list = ordersService.findByMap(map);//查询出单页数据
        //查询商家和厂家

        List<User> business=userService.find("business");
        List<User> factory=userService.find("factory");

        modelMap.put("businesses",business);
        modelMap.put("factorys",factory);
        modelMap.put("orders", list);//存订单
        modelMap.put("returns", returns);//存订单状态
        modelMap.put("bottomPageNo", p.getBottomPageNo());//存尾页
        modelMap.put("totalPages", p.getTotalPages());//一共多少页
        modelMap.put("previousPageNo", p.getPreviousPageNo());//上一页
        modelMap.put("topPageNo", p.getTopPageNo());//首页
        modelMap.put("nextPageNo", p.getNextPageNo());//下一页
        modelMap.put("totalRecords", p.getTotalRecords());//总条数
        modelMap.put("startTime",startTime);
        modelMap.put("stopTime",stopTime);
        modelMap.put("businessUsername",businessUsername);
        modelMap.put("factoryUsername",factoryUsername);

        //如果查询日期，厂家、商家均为null，则返回初始订单页面，否则返回订单表
    if (businessUsername==null&&factoryUsername==null&&startTime==null&&stopTime==null&&by==0){

            if (userService.findByUsername(username).getType().equals("business")) {

                return "business_orders";

            } else if (userService.findByUsername(username).getType().equals("factory")) {
                return "factory_orders";
            } else {
                return "super_orders";

            }
        }else {
            return "table";
        }
        }else {
            return "no";
        }


    }



    @RequestMapping(value = "/findOne")
    @ResponseBody
    @ApiOperation(value = "订单")
    public Orders findOne(@RequestParam  String id){
       return ordersService.findById(id);
    }
}
