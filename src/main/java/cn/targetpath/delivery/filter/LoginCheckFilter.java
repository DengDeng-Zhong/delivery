package cn.targetpath.delivery.filter;

import cn.targetpath.delivery.common.BaseContext;
import cn.targetpath.delivery.common.R;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录过滤器,检查用户是否登录
 *
 * @author DengBo_Zhong
 * @version V1.0
 * @date 2022/11/20 11:12
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    /**
     * 路径匹配器,支持通配符
     */
    public static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取本次请求的uri
        String requestURI = request.getRequestURI();

        log.info("拦截到请求: {}", requestURI);

        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/common/**",
                "/user/sendMsg",
                "/user/login",
                "/user/logout",
//                "/backend/page/login/**",
//                "/backend/api/**",
//                "/backend/images/**",
//                "/backend/js/**",
//                "/backend/plugins/**",
//                "/backend/styles/**",
//                "/backend/favicon.ico",
                "/backend/**",
                "/front/**"
        };


        //判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //如果不需要处理,则直接放行
        if (check) {
            log.info("本次请求{},不需要处理",requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        //判断登录状态,如果已登录,则直接放行
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户已登录,用户ID为 {}",request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getSession().getAttribute("user") != null) {
            log.info("用户已登录,用户ID为 {}",request.getSession().getAttribute("user"));

            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request, response);
            return;
        }

        log.info("用户未登录");
        //如果未登录,则返回未登录结果
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 路径匹配,检查本次请求是否需要放行
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = antPathMatcher.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
