//package com.example.config;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class HeaderFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        // Apply only to API requests, not to OAuth2 redirect
//        if (!req.getRequestURI().contains("/login/oauth2/")) {
//            res.setHeader("Cross-Origin-Opener-Policy", "same-origin");
//            res.setHeader("Cross-Origin-Embedder-Policy", "require-corp");
//        }
//
//        chain.doFilter(request, response);
//    }
//}