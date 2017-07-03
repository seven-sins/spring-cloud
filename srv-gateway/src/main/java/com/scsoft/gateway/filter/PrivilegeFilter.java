package com.scsoft.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PrivilegeFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PrivilegeFilter.class);

	@Override
	public boolean shouldFilter() {
		// shouldFilter：返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		String ip = ctx.getRequest().getRemoteAddr();

		log.info(ip);

		return null;
	}

	@Override
	public String filterType() {
		// filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
		// pre：可以在请求被路由之前调用
		// routing：在路由请求时候被调用
		// post：在routing和error过滤器之后被调用
		// error：处理请求时发生错误时被调用
		return "pre";
	}

	@Override
	public int filterOrder() {
		// filterOrder：通过int值来定义过滤器的执行顺序
		return 0;
	}

}
