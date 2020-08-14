package vip.bigeye.order.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.bigeye.order.Service.UserService;
import vip.bigeye.order.Utils.PageController;
import vip.bigeye.order.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-26 10:43
 */
@Controller
@RequestMapping("/user")
@Api(tags = "用户查找接口")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "select all factory/business")
    @RequestMapping("/list")
    public  String listFactory(@RequestParam int pageNo, ModelMap modelMap,@RequestParam String type){

        int pageSize=2;
        //分页
        PageController p=new PageController();
        p.setTotalRecords(userService.find(type).size());
        p.setPageNo(pageNo);
        p.setPageSize(pageSize);
        //create param map
        Map map =new HashMap();
        map.put("pageSize",pageSize);
        map.put("currIndex",p.getCurrIndex());
        map.put("type",type);
        //select
        List<User> list=userService.findAll(map);



        modelMap.put("user", list);
        modelMap.put("bottomPageNo", p.getBottomPageNo());//存尾页
        modelMap.put("totalPages", p.getTotalPages());//一共多少页
        modelMap.put("previousPageNo", p.getPreviousPageNo());//上一页
        modelMap.put("topPageNo", p.getTopPageNo());//首页
        modelMap.put("nextPageNo", p.getNextPageNo());//下一页
        modelMap.put("totalRecords", p.getTotalRecords());//总条数
        modelMap.put("type",type);
       if (type.equals("business")){
           modelMap.put("title", "商家信息管理");
       }else {
           modelMap.put("title", "厂家信息管理");
       }
       return "list";
    }

}
