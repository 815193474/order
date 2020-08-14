package vip.bigeye.order.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.bigeye.order.Service.OrdersService;
import vip.bigeye.order.Service.ParcelService;
import vip.bigeye.order.Service.UserService;
import vip.bigeye.order.Utils.PageController;
import vip.bigeye.order.entity.Orders;
import vip.bigeye.order.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-22 17:27
 */
@Controller
@RequestMapping(value = "/business")
@Api(tags = "商家跳转接口")
public class BusinessIndexController {


    @Autowired
    private UserService userService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private OrdersService ordersService;


    @RequestMapping(value = "/businessIndex")
    @ApiOperation(value="商家首页")
    public String businessIndex( HttpServletRequest request, ModelMap modelMap){

        List<User> factory=userService.find("factory");

        //保存session信息
        String username= (String)request.getSession().getAttribute("username");
        //历史订单总数
        modelMap.put("ordersNumber", ordersService.findByBusiness(username).size());

        //获取当前时间，并格式化
        Date d = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        //今日订单总数
        modelMap.put("todayOrdersNumber", ordersService.findByTimeAndBusiness(f.format(d),username).size());

        //累计发货订单总数
        modelMap.put("sendOrdersNumber", ordersService.findByReturnsAndBusiness("send",username).size());
        //累计未发货订单总数
        modelMap.put("notSendOrdersNumber", ordersService.findByReturnsAndBusiness("notSend",username).size());

        //累退货订单总数
        modelMap.put("returnedOrdersNumber",ordersService.findByReturnsAndBusiness("returned",username).size());
        modelMap.put("user",userService.findByUsername(username));
        modelMap.put("factorys",factory);
        return "business_index";
    }




}
