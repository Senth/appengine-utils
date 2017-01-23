package com.spiddekauga.servlet;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/**
 * Tests the App servlet class
 */
public class AppServletTest {
@Test
public void getServiceRootUrl_defaultService() throws NoSuchFieldException, IllegalAccessException {
	AppServlet appServlet = new AppServlet() {
	};

	Field fRootUrl = AppServlet.class.getDeclaredField("mRootUrl");
	fRootUrl.setAccessible(true);

	// Test with default service
	fRootUrl.set(appServlet, "https://test-app.appspot.com");
	String correctUrl = "https://service-dot-test-app.appspot.com";
	String serviceUrl = appServlet.getServiceRootUrl("service");
	assertEquals(correctUrl, serviceUrl);
}

@Test
public void getServiceRootUrl_existingService() throws NoSuchFieldException, IllegalAccessException {
	AppServlet appServlet = new AppServlet() {
	};

	Field fRootUrl = AppServlet.class.getDeclaredField("mRootUrl");
	fRootUrl.setAccessible(true);

	// Test with existing service
	String correctUrl = "https://service-dot-test-app.appspot.com";
	fRootUrl.set(appServlet, "https://existing-dot-test-app.appspot.com");
	String serviceUrl = appServlet.getServiceRootUrl("service");
	assertEquals("Existing service", correctUrl, serviceUrl);
}
}
