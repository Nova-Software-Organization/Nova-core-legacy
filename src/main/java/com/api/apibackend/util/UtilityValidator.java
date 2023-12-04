package com.api.apibackend.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import io.micrometer.common.util.StringUtils;

@Component
public class UtilityValidator {

	public static boolean isValidString(String input) {
		if (StringUtils.isNotBlank(input)) {
			String regex = "^[a-zA-Z0-9\\s]+$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(input);
			
			return matcher.matches();
		}
		
		return false;
	}

	public static String cleanString(String input) {
		String cleanedString = input.replaceAll("[^a-zA-Z0-9\\s]+", "");

		return cleanedString;
	}
}
