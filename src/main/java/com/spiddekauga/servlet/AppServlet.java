package com.spiddekauga.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Base class for all Voider servlets.
 */
@SuppressWarnings("serial")
public abstract class AppServlet extends HttpServlet {
protected Logger mLogger = Logger.getLogger(getClass().getSimpleName());
private HttpSession mSession = null;
private HttpServletRequest mRequest = null;
private HttpServletResponse mResponse = null;

@Override
protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	handleRequest(req, resp);
	onGet();
	onCleanup();
}

@Override
protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	handleRequest(req, resp);
	onPost();
	onCleanup();
}

/**
 * Called when a POST request has been sent
 * @throws ServletException
 * @throws IOException
 */
protected void onPost() throws ServletException, IOException {
}

/**
 * Handle request here first
 * @throws ServletException
 * @throws IOException
 */
private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	mRequest = request;
	mResponse = response;

	initSession(request);
	onInit();
}

/**
 * Called when a GET request has been sent
 * @throws ServletException
 * @throws IOException
 */
protected void onGet() throws ServletException, IOException {
}

/**
 * Called after {@link #onGet()} and {@link #onPost()}.
 */
protected void onCleanup() throws ServletException, IOException {

}

/**
 * Initializes the session and all it's variables
 * @param request server request
 */
private void initSession(HttpServletRequest request) {
	mSession = request.getSession();

}

/**
 * Called after the session has been initialized and before {@link #onGet()} and {@link #onPost()}.
 * {@link HttpServletRequest} and {@link HttpServletResponse} has been before this method is called.
 * They can be fetched using {@link #getRequest()} and {@link #getResponse()}.
 */
protected void onInit() throws ServletException, IOException {
}

/**
 * Sets a session variable
 * @param name the session's variable name
 * @param variable the variable to set in the session
 */
protected void setSessionVariable(String name, Object variable) {
	mSession.setAttribute(name, variable);
}

/**
 * Gets a session variable
 * @param name the session's variable name
 * @return the variable stored in this place, or null if not found
 */
protected Object getSessionVariable(String name) {
	return mSession.getAttribute(name);
}

/**
 * @return current request
 */
protected HttpServletRequest getRequest() {
	return mRequest;
}

/**
 * @return current response
 */
protected HttpServletResponse getResponse() {
	return mResponse;
}
}
