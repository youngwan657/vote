package com.reddit.vote.support;

import java.util.Map;

public class Validator {
	public static final boolean validateTop20(Map<String, String> param) {
		if (!param.containsKey("top20")) return false;
		if (param.get("orderBy") == null) return false;
		if (!param.get("orderBy").equals("popularity")) return false;

		return true;
	}
}
