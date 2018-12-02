package com.test.helloworld.test;

import com.test.helloworld.MainActivity;

import android.test.AndroidTestCase;

public class Test extends AndroidTestCase{
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("测试开始...");
	}
	
	public void testH(){
		assertEquals("hello world", MainActivity.test());
		assertEquals("hello world..", MainActivity.test());
	}
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("测试结束...");
	}
}
