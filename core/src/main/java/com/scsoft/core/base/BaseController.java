package com.scsoft.core.base;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

	protected Map<String, Object> message(int code, String message) {
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("message", message);

		return map;
	}
}
