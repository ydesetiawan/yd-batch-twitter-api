package com.yd.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yd.persistence.repository.model.User;
import com.yd.security.AppsUserDetails;

/**
 * @author edys
 * @version 1.0, Apr 3, 2014
 * @since
 */
@Component
public class AppsUtil {

	public static User getActiveUser() {
		return getPrincipal().getUser();
	}

	public static String getActiveUserUuid() {
		return getActiveUser().getUuid();
	}

	public static Authentication getAuthentication() {
		return getContext().getAuthentication();
	}

	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static String getName() {
		return getAuthentication().getName();
	}

	public static AppsUserDetails getPrincipal() {
		return (AppsUserDetails) getContext().getAuthentication()
				.getPrincipal();
	}

	public static SecurityContextImpl getContext() {
		return (SecurityContextImpl) SecurityContextHolder.getContext();
	}

	public static HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

	public static boolean isSwitchedUser() {
		for (GrantedAuthority ga : getAuthentication().getAuthorities()) {
			if (ga instanceof SwitchUserGrantedAuthority)
				return true;
		}
		return false;
	}

	public Cookie constructCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(2592000);
		return cookie;
	}

	public Cookie fetchCookie(Cookie[] cookies, String name) {
		if (cookies == null || name == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie;
			}
		}
		return null;
	}

	public String getStackTrace(Throwable throwable) {
		Writer result = new StringWriter();
		PrintWriter printWriter = new PrintWriter(new StringWriter());
		throwable.printStackTrace(printWriter);
		return result.toString();
	}

	public String reverseOrder(String value) {
		if (StringUtils.equals(value, "asc"))
			return "desc";
		else
			return "asc";
	}
}