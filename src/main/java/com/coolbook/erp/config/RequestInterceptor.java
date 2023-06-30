package com.coolbook.erp.config;

import com.coolbook.erp.dataSourceRouting.CustomerDataSourceConfig;
import com.coolbook.erp.dataSourceRouting.DataSourceContextHolder;
import com.coolbook.erp.security.SecurityFacade;
import com.coolbook.erp.security.exception.NoUserAuthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    private DataSourceContextHolder dataSourceContextHolder;

    @Autowired
    private SecurityFacade securityFacade;

    @Autowired
    private CustomerDataSourceConfig customerDataSourceConfig;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            dataSourceContextHolder.setDataSourceContext(securityFacade.getCurrentUser().getCompanyCode().toLowerCase());
            String path = (String) request.
                    getAttribute(HandlerMapping.
                            BEST_MATCHING_PATTERN_ATTRIBUTE);
        if(path.contains("co-opData")){
            dataSourceContextHolder.setDataSourceContext("coop");
        }
        }catch(NoUserAuthenticatedException e){
            dataSourceContextHolder.setDataSourceContext("coop");
        }
        System.out.println("Pre Handle method is Calling");
        return true;
    }
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {}
}
