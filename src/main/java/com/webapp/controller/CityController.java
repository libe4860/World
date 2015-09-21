package com.webapp.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webapp.command.CityCommand;
import com.webapp.command.Code;
import com.webapp.mapper.CityMapper;
import com.webapp.mapper.CountryMapper;
import com.webapp.model.City;
import com.webapp.validator.CityCommandValidator;

@Controller
@RequestMapping("/city")
public class CityController {
   
   static Log log = LogFactory.getLog(CityController.class);
   
   @Autowired
   CountryMapper countryMapper;
   @Autowired
   CityMapper cityMapper;
   @Autowired
   CityCommandValidator validator;
   
   @RequestMapping("/district/{countrycode:[A-Z]{3}}") //A~Z까지 3자리 숫자까지 가능함(정규표현식-유효성 검증시 유용함)
   String getDistricts(@PathVariable String countrycode, Model model){//@PathVariable 값 전달수단으로 사용
      log.info("getDistricts() ... countryCode = " + countrycode); 
      
      List<String> districts = cityMapper.selectDistricts(countrycode);
      
      model.addAttribute("districts", districts);
      
      return "city/districts";
   }
   
//   or
   
   @RequestMapping
   String listByParameter(String countrycode, Model model){
      
      model.addAttribute("countrycode", countrycode);
      
      List<Code> list = countryMapper.selectCodes();
      for(Code c : list){
         log.info("code = " + c.getCode() + " " + "name = " + c.getName());
      }
      
      return "city/list";
   }
   
//   or
   
   @RequestMapping("/list")
   void listByParameter(HttpServletRequest request, Model model){
      
      String countrycode = request.getParameter("countrycode");
      log.info("requestURI = " + request.getRequestURI());
      model.addAttribute("countrycode", countrycode);
   }
   
//   @RequestMapping("/register")
//   String register(CityCommand command){
////      model.addAttribute("cityCommand", command); 자동으로 이리 만들어줌
//      
//      return "city/register";
//   }
   
   //or
   
   @ModelAttribute("countryCode")
   List<Code> getCountryCode(){
//      Map<String, String> countryCode = new HashMap<String, String>();
//      countryCode.put("KOR", "대한민국");
//      countryCode.put("USA", "미국");
//      countryCode.put("JPA", "일본");
//      countryCode.put("CHA", "중국");
      
      List<Code> model = countryMapper.selectCodes();
      log.info("getCountryCode()... size = " + model.size());

      
      return model;
   }
   
   @ModelAttribute("districts")
   List<String> getDistricts(String countryCode){
      
      List<String> model = cityMapper.selectDistricts(countryCode);
      log.info("getDistricts()... size = " + model.size());
//      List<District> list = new ArrayList<District>();
//      list.add(new District("Seoul", "서울"));
//      list.add(new District("Pusan", "부산"));
//      list.add(new District("Inchon", "인천"));
//      list.add(new District("Taegu", "대구"));
//      list.add(new District("Taejon", "대전"));
//      list.add(new District("Kwangju", "광주"));
//      list.add(new District("Kyongsangnam", "경상남도"));
//      list.add(new District("Kyonggi", "경기"));
//      list.add(new District("Chollabuk", "전라북도"));
//      list.add(new District("Chungchongbuk", "충청북도"));
//      list.add(new District("Kyongsangbuk", "경상북도"));
//      list.add(new District("Chungchongnam", "충청남도"));
//      list.add(new District("Cheju", "제주"));
//      list.add(new District("Chollanam", "전라남도"));
//      list.add(new District("Kang-won", "강원도"));
      return model;
   }
   
   @RequestMapping(value="/register", method=RequestMethod.GET)
   String registerForm(@ModelAttribute("city") CityCommand command){
      log.info("registerForm()...");
//      model.addAttribute("cityCommand", command); 자동으로 이리 만들어줌
      
      return "city/registerForm";
   }
   
   @RequestMapping(value="/register", method=RequestMethod.POST)
   String register(@ModelAttribute("city") CityCommand command, Errors errors, Model model){
//      model.addAttribute("cityCommand", command); 자동으로 이리 만들어줌
      log.info("register()... Post");
      
      /*
       * CityCommand Validation : command값에 대한 validation 처리 
       */
      validator.validate(command, errors);
      
      //에러코드 확인(스터디차원에서 확인)
      if(errors.hasFieldErrors("name")){
	      FieldError e = errors.getFieldError("name");
	      String[] codes = e.getCodes();
	      for(String s : codes)
	    	  log.info("[" + s + "]" );
   	  }
      
      
      
      
      if(errors.hasErrors()){
         errors.reject("city.register", new Object[]{command.getName()}, "City Global Error");
         return "city/registerForm";
      }
      
      /*
       * DB Register ==> Service를 사용해서 처리
       */
      
      City city = command.getCity();
      cityMapper.insert(city);
      //윗부분과 city이름은 동일하나 오버라이딩 되므로 상관없음!
      model.addAttribute("city", city);
      
      return "city/registerSuccess";
   }
   
   @RequestMapping("/modify")
   String modify(@ModelAttribute("city") CityCommand command, Errors errors){
      if(errors.hasFieldErrors()){
         List<FieldError> fieldErrors = errors.getFieldErrors();
         for(FieldError fe : fieldErrors){
            log.info("field = " + fe.getField());
            log.info("ObjectName = " + fe.getObjectName());
            log.info("Code = " + fe.getCode());
            log.info("Code = " + Arrays.toString(fe.getCodes()));
            log.info("RejectedValue = " + fe.getRejectedValue());
         }
      }
      return "city/modify";
   }
   
}