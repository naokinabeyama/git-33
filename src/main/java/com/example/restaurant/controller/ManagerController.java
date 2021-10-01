package com.example.restaurant.controller;



import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.restaurant.entity.ReservationsEntity;
import com.example.restaurant.entity.UsersEntity;
//import com.example.restaurant.entity.UsersEntity;
import com.example.restaurant.form.SerchForm;
import com.example.restaurant.form.UpdateForm;
import com.example.restaurant.service.ManagerService;


@Controller	
public class ManagerController {
	Timestamp time = new Timestamp(System.currentTimeMillis());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm");
	
	@Autowired
	ManagerService service;
	
	// ログイン画面
	@RequestMapping("/manage/")
	public String getlogin() {
		return "/login.html";
	}
	
	//予約一覧
	@RequestMapping("/manage/reservationList")
	public ModelAndView reservationList(ModelAndView mv, @AuthenticationPrincipal UsersEntity usersEntity, @RequestParam(value="serch", defaultValue="0") int serchButton,
			@RequestParam(value="page", defaultValue="0") int pageNum, 
			 @ModelAttribute("form") @Validated SerchForm form, BindingResult result) {
		String day = sdf.format(time);
		Page<ReservationsEntity> ReservationsEntityList = null;
		if(form.getReservationDateFrom() == null) {
				ReservationsEntityList = service.getPage(pageNum, new SerchForm());
				form.setReservationDateFrom(day);
		} else if(result.hasErrors() || serchButton == 0) {
			ReservationsEntityList = service.getPage(pageNum, new SerchForm());
		} else if(serchButton == 1) {
			ReservationsEntityList = service.getPage(pageNum, form);
		}
		
		// 時間表示
		List<String> timeList = new ArrayList<String>();
		String t = "";
		String tt = "";
		Calendar cal = Calendar.getInstance();
		for(ReservationsEntity re : ReservationsEntityList) {
			t = sdftime.format(re.getReservationDate());
			Date d = new Date();
			try {
				d = sdftime.parse(t);
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			cal.setTime(d);
			cal.add(Calendar.HOUR_OF_DAY, 2);
			tt = sdftime.format(cal.getTime());
			timeList.add(t + "~" + tt);
		}
		
//		UsersEntity usersEntity = (UsersEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		
		mv.addObject("usersEntity", usersEntity);
		mv.addObject("timeList", timeList);
		mv.addObject("serchButton", serchButton);
		mv.addObject("form", form);
		mv.addObject("entityList", ReservationsEntityList);
		mv.setViewName("/reservationList.html");
		return mv;
	}
	
	// 予約詳細
	@RequestMapping("/manage/reservationDetail")
	public ModelAndView reservationDetail(ModelAndView mv, 
			@RequestParam("reservationId") int reservationId) {

		ReservationsEntity reservationsEntity = service.getRecord(reservationId);
		
		String year = new SimpleDateFormat("yyyy/MM/dd").format(reservationsEntity.getReservationDate());
		
		String totalTime = service.totalTime(reservationsEntity.getReservationDate());
		
		String celebration = service.celebration(reservationsEntity.getCelebrationExistence());
		
		boolean bool = service.date(reservationsEntity.getReservationDate());
		
		String comment = service.demand(reservationsEntity.getDemand());
		
		
		mv.addObject("demand", comment);
		mv.addObject("bool", bool);
		mv.addObject("year", year);
		mv.addObject("celebration", celebration);
		mv.addObject("time", totalTime);
		mv.addObject("entity", reservationsEntity);
		mv.setViewName("/reservationDetail.html");
		return mv;
	}
	
	// 予約更新
	@RequestMapping("/manage/reservationUpdate")
	public ModelAndView reservationUpdate(ModelAndView mv, @RequestParam("reservationId") int reservationId,
			@ModelAttribute ("form") UpdateForm form) {

		ReservationsEntity reservationsEntity = service.getRecord(reservationId);

		String year = new SimpleDateFormat("yyyy/MM/dd").format(reservationsEntity.getReservationDate());
		
		// 時間
		String totalTime = service.totalTime(reservationsEntity.getReservationDate());
		
		// ２週間
		String[] timeList = service.twoWeek();

		mv.addObject("timeList", timeList);
		mv.addObject("totalTime", totalTime);
		mv.addObject("year", year);
		mv.addObject("entity", reservationsEntity);
		mv.setViewName("/reservationUpdate.html");
		return mv;
	}
	
	// 更新処理
	@RequestMapping("/manage/doUpdate")
	public ModelAndView doUpdate(ModelAndView mv, RedirectAttributes attributes,
			@ModelAttribute ("form") @Validated UpdateForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			ReservationsEntity reservationsEntity = service.getRecord(form.getReservationId());
			String year = new SimpleDateFormat("yyyy/MM/dd").format(reservationsEntity.getReservationDate());
			
			// 時間
			String totalTime = service.totalTime(reservationsEntity.getReservationDate());
			
			// ２週間
			String[] timeList = service.twoWeek();
			
			mv.addObject("totalTime", totalTime);
			mv.addObject("year", year);
			mv.addObject("entity", reservationsEntity);
			mv.addObject("timeList", timeList);
      mv.setViewName("/reservationUpdate.html");
      return mv;
      
		}else {
			
			service.updateDB(form);
			attributes.addAttribute("reservationId", form.getReservationId());
//		mv.addObject("reservationId", form.getReservationId());
			mv.setViewName("redirect:/manage/reservationDetail");
			return mv;
		}
		
	}
	
	// 削除画面
	@RequestMapping("/manage/reservationDeleteConfirm")
	public ModelAndView delete(ModelAndView mv, @RequestParam("reservationId") int reservationId) {
		ReservationsEntity reservationsEntity = service.getRecord(reservationId);
		String year = new SimpleDateFormat("yyyy/MM/dd").format(reservationsEntity.getReservationDate());
		// 時間
		String totalTime = service.totalTime(reservationsEntity.getReservationDate());
		
		String celebration = service.celebration(reservationsEntity.getCelebrationExistence());
		
		String comment = service.demand(reservationsEntity.getDemand());

		mv.addObject("demand", comment);
		mv.addObject("year", year);
		mv.addObject("celebration", celebration);
		mv.addObject("time", totalTime);
		mv.addObject("entity", reservationsEntity);
		mv.setViewName("/reservationDeleteConfirm.html");
		return mv;
	}
	
	// 削除処理
	@RequestMapping("/manage/dodelete")
	public ModelAndView dodelete(ModelAndView mv, @RequestParam("reservationId") int reservationId) {
		service.deleteDB(reservationId);
		mv.setViewName("redirect:/manage/reservationList");
		return mv;
	}
	
}
