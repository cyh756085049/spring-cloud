package com.gateway.gatewayzuulfilter.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


/**
 * 编写自定义的Zuul过滤器
 */
public class PreRequestLogFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreRequestLogFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request =ctx.getRequest();
        PreRequestLogFilter.LOGGER.info(String.format("send %s to %s", request.getMethod(), request.getRequestURI().toString()));
        return null;
    }
}
