package com.example.restaurant.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.restaurant.form.ReservationForm;
import com.example.restaurant.service.CustomerService;

@Controller
public class CustomerController {
	
	
	@Autowired
	CustomerService service;
	
	// top画面
	@RequestMapping("/store/top")
	public String init() {
		return "/top.html";
	}
	
	// 予約登録画面
	@RequestMapping("/store/reservation")
	public ModelAndView reservation(ModelAndView mv,
			@ModelAttribute ("form") ReservationForm form) {
		String[] timeList = service.twoWeek();
		mv.addObject("timeList", timeList);
		mv.setViewName("/reservation.html");
		return mv;
	}
	
	// 予約処理
	@RequestMapping("/store/doReservation")
	public ModelAndView doresarvation(ModelAndView mv, 
			@ModelAttribute ("form") @Validated ReservationForm form,
			BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			String[] timeList = service.twoWeek();
			mv.addObject("timeList", timeList);
      mv.setViewName("/reservation.html");
      return mv;
    /* not error */
    } else {
    	attributes.addFlashAttribute("message", "予約を受付ました。");
    	service.reservationDB(form);
  		mv.setViewName("redirect:/store/reservation");
  		return mv;
     }
		
	}
}
