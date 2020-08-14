package vip.bigeye.order.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.bigeye.order.Service.OrdersService;
import vip.bigeye.order.Service.ParcelService;
import vip.bigeye.order.Service.UserService;
import vip.bigeye.order.Utils.CreateFileController;
import vip.bigeye.order.Utils.DownController;
import vip.bigeye.order.entity.Orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-21 10:42
 */
@Controller
@RequestMapping(value = "export")
@Api(tags = "导出订单")
public class ExportController {

    @Autowired
    private UserService userService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private OrdersService ordersService;

    private CreateFileController createFileController;
    private DownController downController;
    @ApiOperation(value = "导出订单")
    @RequestMapping("/orders")
    public void exportOrders(HttpServletResponse response, HttpServletRequest request, String[] check, ModelMap modelMap) throws IOException {
        //创建订单集合
        List<Orders> list=new ArrayList<Orders>();

        for(int i=0;i<check.length;i++) {
            list.add(ordersService.findById(check[i]));
        }
        modelMap.put("o",list);
        //指定文件夹
        String folderName="file";
        String realPath = request.getSession().getServletContext().getRealPath("/")+folderName+"/";
       // 创建文件名
            String outputFile = "orders"+".xls";
            OutputStream out = new FileOutputStream(realPath+outputFile);
            createFileController.createFile(out, "/orders.ftl", modelMap);
            downController.down(request, response, realPath, outputFile);
    }

}
