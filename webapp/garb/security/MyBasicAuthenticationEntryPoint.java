package com.deshand.adc.web.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
 
    @Override
    public void commence
      (HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) 
      throws IOException, ServletException {
    	
    	System.out.println(request);
    	System.out.println(response);
    	System.out.println("commence");

    	

    	
        response.addHeader("WWW-Authenticate ", "Basic realm=MyBasicRealm " + getRealmName() + "");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authEx.getMessage());}
 
    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Ajaks");
        super.afterPropertiesSet();
    }
}