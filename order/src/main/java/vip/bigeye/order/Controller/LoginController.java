package vip.bigeye.order.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.bigeye.order.Service.OrdersService;
import vip.bigeye.order.Service.ParcelService;
import vip.bigeye.order.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wolf  VX:a815193474
 * @Data 2020-03-21 21:11
 */

@Controller
@RequestMapping(value = "/")
@Api(tags = "登录接口")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private ParcelService parcelService;
    @Autowired
    private OrdersService ordersService;

    @ApiOperation(value = "登录")
    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,@RequestParam String type, HttpServletRequest request, HttpServletResponse response, @RequestParam String yan) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String clientCheckcode = yan.toUpperCase();//接收客户端提交上来的参数，使用toUpperCase()可以实现不区分大小写
        String serverCheckcode = (String) request.getSession().getAttribute("checkcode");//从session中提取验证码

        if (clientCheckcode.equals(serverCheckcode)) {
            //judge account is or not exist
            if (userService.findByUsername(username) != null) {
                if (userService.findByUsername(username).getPassword().equals(password)) {
                    request.getSession().setAttribute("username", username);
                    if (type.equals(userService.findByUsername(username).getType())&&type.equals("admin")){
                        return  "successSuper";
                    }else if(type.equals(userService.findByUsername(username).getType())&&type.equals("business")){
                        return "successBusiness";
                    }else if (type.equals(userService.findByUsername(username).getType())&&type.equals("factory")){
                        return "successFactory";
                    }else {
                        return "登录身份与账号不匹配";
                    }

                } else {
                    //密码错误
                    return "密码错误";
                }
            } else {
                //用户不存在
                return "用户不存在";
            }
        }else {
            return "验证码错误";
    }
    }


    @RequestMapping("/loginout")
    public String quitUser(HttpServletRequest request){
        request.getSession().removeAttribute("username");
return "redirect:../action/login";
    }
}

