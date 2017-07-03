package com.scsoft.base.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.scsoft.core.base.BaseController;

@RestController
public class MainController extends BaseController {

	@Value("${test.file}")
	String test;

	@GetMapping("/test")
	public Object test() {
		return test;
	}

	@RequestMapping("/test1")
	public Object test1() {
		return test;
	}

	@GetMapping("/rest/info")
	public Object getUrlMapping(HttpServletRequest request) {
		WebApplicationContext wc = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getSession().getServletContext());
		RequestMappingHandlerMapping requestMappingHandlerMapping = wc.getBean(RequestMappingHandlerMapping.class);

		List<HashMap<String, String>> urlList = new ArrayList<HashMap<String, String>>();

		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

		for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			RequestMappingInfo info = m.getKey();
			HandlerMethod method = m.getValue();
			PatternsRequestCondition p = info.getPatternsCondition();
			for (String url : p.getPatterns()) {
				hashMap.put("url", url);
			}
			hashMap.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
			hashMap.put("method", method.getMethod().getName()); // 方法名

			RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
			if (methodsCondition.isEmpty()) {
				hashMap.put("type", "未指定");
			} else {
				String type = methodsCondition.toString();
				if (type != null && type.startsWith("[") && type.endsWith("]")) {
					type = type.substring(1, type.length() - 1);
					type = type.toLowerCase();
					hashMap.put("type", type);
				}
			}
			urlList.add(hashMap);
		}

		return urlList;
	}

}
