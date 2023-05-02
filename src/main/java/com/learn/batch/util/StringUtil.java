package com.learn.batch.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	
	private static final String DEFAULT_SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static boolean isEmpty(String value) {
		
		boolean result = false;
		
		if( value == null ) {
			value = "";
			
		}
		
		value = value.trim();
		
		// 탭, 개행, 공백 제거
		value = value.replaceAll("[\\t\\n\\r ]+", ""); 
		
		if( value.length() <= 0 ) {
			result = true;
			
		}
		
		return result;
		
	}
	
	public static String getStrDateDefaultNow() {
		return getStrDateFormat(new Date(), DEFAULT_SIMPLE_DATE_FORMAT);
		
	}
	
	public static String getStrDateDefaultPattern(Date date) {
		return getStrDateFormat(date, DEFAULT_SIMPLE_DATE_FORMAT);
	}
	
	
	/**
	 * 현재 날짜
	 * @param pattern
	 * @return
	 */
	public static String getStrDateFormatNow(String pattern) {
		return getStrDateFormat(new Date(), pattern);
	}
	
	/**
	 * 입력받은 날짜를 패턴으로 변환하여 문자열 반환
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getStrDateFormat(Date date, String pattern) {
		
		String str = "";
		
		try {
			
			if( date == null ) {
				throw new Exception("Date Is Null");
				
			}

			str = new SimpleDateFormat(pattern).format(date);
			
		} catch(Exception e) {
			str = "";
			
		}
		
		return str;
		
	}
	
	
}
