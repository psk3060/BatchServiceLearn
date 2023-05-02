package com.learn.batch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.batch.util.StringUtil;

@SpringBootTest
public class StringUtilTest {
	
	@Test
	public void isEmptyTest() throws Exception {
		
		// space * 4
		String temp1 = "    ";
		
		assertTrue(StringUtil.isEmpty(temp1));
		
		// tab
		String temp2 = "	";
		
		assertTrue(StringUtil.isEmpty(temp2));
		
		String temp3 = "가나다라마바";
		
		assertFalse(StringUtil.isEmpty(temp3));
		
		String temp4 = "\n\t";
		
		assertTrue(StringUtil.isEmpty(temp4));
		
	}
	
	
	
}
