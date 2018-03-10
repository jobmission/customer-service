package com.revengemission.customerservice.utils;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class ClientIPUtil {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ClientIPUtil.class);

    public static String getIpAddress(HttpServletRequest request) {
        String ip = "";
        try {
            ip = request.getHeader("X-Forwarded-For");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_FORWARDED");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_VIA");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("REMOTE_ADDR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("getIpAddress", e);
            }
        }
        return ip;
    }

    public static String getServerHost(HttpServletRequest request) {
        try {
            String scheme = request.getScheme();
            String serverName = request.getServerName();
            Integer serverPort = request.getServerPort();
            if (serverPort != null && (serverPort == 80 || serverPort == 443)) {
                return scheme + "://" + serverName;
            } else {
                return scheme + "://" + serverName + ":" + serverPort;
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("getServerHost", e);
            }
        }
        return "";
    }

}
