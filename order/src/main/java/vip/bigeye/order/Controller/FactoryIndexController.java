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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-22 17:29
 */
@Controller
@RequestMapping(value = "/factory")
@Api(tags = "厂家跳转接口")
public class FactoryIndexController {


    @Autowired
    private UserService userService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "/factoryIndex")
    @ApiOperation(value="商家首页")
    public String factoryIndex(HttpServletRequest request, ModelMap modelMap){

        //保存session信息
        String username= (String)request.getSession().getAttribute("username");
        //历史订单总数
        modelMap.put("ordersNumber", ordersService.findByFactory(username).size());

        //获取当前时间，并格式化
        Date d = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        //今日订单总数
        modelMap.put("todayOrdersNumber", ordersService.findByTimeAndFactory(f.format(d),username).size());

        //累计发货订单总数
        modelMap.put("sendOrdersNumber", ordersService.findByReturnsAndFactory("send",username).size());
        //累计未发货订单总数
        modelMap.put("notSendOrdersNumber", ordersService.findByReturnsAndFactory("notSend",username).size());

        //累退货订单总数
        modelMap.put("returnedOrdersNumber",ordersService.findByReturnsAndFactory("returned",username).size());
        modelMap.put("user",userService.findByUsername(username));

        return "factory_index";
    }
}
