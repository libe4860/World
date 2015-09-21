package com.webapp.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

@Controller
@RequestMapping("/locale")
public class LocaleController {
   
   static Log log = LogFactory.getLog(LocaleController.class);
   
   /* DI방법 
    * 1. 생성자 주입
    * 2. Property 주입
    * 3. 필드 주입
    */ 
   
   @Autowired //필드주입 : setter없이 필드에 바로 주입, 타입으로 동작
   LocaleResolver localeResolver;
   
   @RequestMapping("/{language:[a-z]{2}}")
   void setLocale(@PathVariable String language,HttpServletRequest request, HttpServletResponse response) throws IOException{
	   log.info("language = " + language);
	   
	   Locale locale = new Locale(language);
	   localeResolver.setLocale(request, response, locale);
	   
	   
	   
	   PrintWriter out = response.getWriter();
	   out.println("language = " + language + "<br>");
	   out.println("language = " + locale.getLanguage());
   }
   
}