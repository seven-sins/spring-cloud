package com.scsoft.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PrivilegeFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PrivilegeFilter.class);

	@Override
	public boolean shouldFilter() {
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

		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
