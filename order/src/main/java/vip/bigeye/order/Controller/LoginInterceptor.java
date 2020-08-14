package vip.bigeye.order.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Configuration
public class LoginInterceptor implements WebMvcConfigurer {

    private static final Logger logger= LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {

            @Override
            public boolean preHandle(HttpServletRequest request,
                                     HttpServletResponse response, Object handler) throws Exception {
                String requestUri = request.getRequestURI(); //请求完整路径，可用于登陆后跳转
                String contextPath = request.getContextPath();  //项目下完整路径
                String url = requestUri.substring(contextPath.length()); //请求页面
                // System.out.print("发生拦截..."+requestUri);
                //   System.out.println("来自："+requestUri+"的请求");
                String user = (String) request.getSession().getAttribute("username");
                System.out.println(user);
                //  System.out.println(request.getSession());
                if (user == null || user.equals(" ")) {  //判断用户是否存在，不存在返回登录界面，继续拦截，存在通过拦截，放行到访问页面
                    /**
                     * 拦截目录下请求，是否为ajax请求
                     *   是：无需登录，直接访问（因为我是用于首页的ajax登录请求）
                     *   否：跳转至登录界面
                     */
                    System.out.println("返回主页...");
                    //request.getRequestDispatcher("../action/login").forward(request, response);//转发到登录界面
                    response.sendRedirect(request.getContextPath()+"/action/login");
                    return false;
                    /**
                     * System.out.println(request.getHeader("x-requested-with"));

                     if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
                     //如果是ajax请求响应头会有，x-requested-with
                     System.out.print("发生ajax请求...");
                     response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
                     return true;
                     }else{
                     System.out.print("返回主页...");
                     request.getRequestDispatcher("/index.jsp").forward(request, response);//转发到登录界面
                     return false;
                     }
                     * */

                }
                return true;
            }

            /**
             * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
             * 可在modelAndView中加入数据，比如当前时间
             */
            @Override
            public void postHandle(HttpServletRequest request,
                                   HttpServletResponse response, Object handler,
                                   ModelAndView modelAndView) throws Exception {
                if (modelAndView != null) {  //加入当前时间
                    modelAndView.addObject("status", "off");
                }
            }

            /**
             * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
             *
             * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
             */
            @Override
            public void afterCompletion(HttpServletRequest request,
                                        HttpServletResponse response, Object handler, Exception ex)
                    throws Exception {
            }
        })
                //不需要拦截的路径
        .excludePathPatterns("/css/**")
        .excludePathPatterns("/img/**")
        .excludePathPatterns("/js/**")
        .excludePathPatterns("/action/login")
        .excludePathPatterns("/login")
        .excludePathPatterns("/check/*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {

    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}









