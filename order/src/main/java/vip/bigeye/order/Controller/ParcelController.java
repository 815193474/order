package vip.bigeye.order.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.bigeye.order.Service.ParcelService;
import vip.bigeye.order.Utils.PageController;
import vip.bigeye.order.entity.Parcel;
import vip.bigeye.order.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-26 12:21
 */
@Controller
@RequestMapping("/parcel")
@Api(tags = "快递公司")
public class ParcelController {

    @Autowired
    private ParcelService parcelService;

    @ApiOperation(value = "select all parcel")
    @RequestMapping("/list")
    public  String listParcel(@RequestParam int pageNo, ModelMap modelMap){

        int pageSize=2;
        //分页
        PageController p=new PageController();
        p.setTotalRecords(parcelService.find().size());
        p.setPageNo(pageNo);
        p.setPageSize(pageSize);
        List<Parcel> list=parcelService.findAll(p.getCurrIndex(),pageSize);
        modelMap.put("parcel", list);
        modelMap.put("bottomPageNo", p.getBottomPageNo());//存尾页
        modelMap.put("totalPages", p.getTotalPages());//一共多少页
        modelMap.put("previousPageNo", p.getPreviousPageNo());//上一页
        modelMap.put("topPageNo", p.getTopPageNo());//首页
        modelMap.put("nextPageNo", p.getNextPageNo());//下一页
        modelMap.put("totalRecords", p.getTotalRecords());//总条数

        return "parcel_list";
    }

}
