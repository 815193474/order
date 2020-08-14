package vip.bigeye.order.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import vip.bigeye.order.Service.OrdersService;
import vip.bigeye.order.Service.ParcelService;
import vip.bigeye.order.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-22 17:27
 */
@Controller
@RequestMapping(value = "/super")
@Api(tags = "平台管理员跳转接口")
public class SuperIndexController {


    @Autowired
    private UserService userService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private OrdersService ordersService;


    @RequestMapping(value = "/superIndex")
    @ApiOperation(value="平台管理员首页")
    public String superIndex( HttpServletRequest request, ModelMap modelMap){
        //保存session信息
        String username= (String)request.getSession().getAttribute("username");
        //历史订单总数

        modelMap.put("ordersNumber", ordersService.findAll().size());

        //获取当前时间，并格式化
        Date d = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        //今日订单总数
        modelMap.put("todayOrdersNumber", ordersService.findByTime(f.format(d)).size());

        //累计发货订单总数
        modelMap.put("sendOrdersNumber", ordersService.findByReturns("send").size());
        //累计未发货订单总数
        modelMap.put("notSendOrdersNumber", ordersService.findByReturns("notSend").size());

        //累退货订单总数
        modelMap.put("returnedOrdersNumber", ordersService.findByReturns("returned").size());
        modelMap.put("user",userService.findByUsername(username));
        return "super_index";
    }
}
