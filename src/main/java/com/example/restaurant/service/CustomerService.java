package com.example.restaurant.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.entity.ReservationsEntity;
import com.example.restaurant.form.ReservationForm;
import com.example.restaurant.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository repository;
	
	
	public void reservationDB(ReservationForm form) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date();
		ReservationsEntity reservationEntity = new ReservationsEntity();
		
		reservationEntity.setLastName(form.getLastName());
		reservationEntity.setFirstName(form.getFirstName());
		
		String[] t = form.getVisitsTime().split("~");
		String total = form.getVisitsDate() + " " + t[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			date = sdf.parse(total);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp time = new Timestamp(date.getTime());
		reservationEntity.setReservationDate(time);

		int number = Integer.parseInt(form.getVisitsNumber());
		reservationEntity.setReservationCount(number);
		reservationEntity.setTell(form.getTell());
		int celebration;
		if(form.getCelebrationExistence() == true) {
			celebration = 1;
		} else {
			celebration = 0;
		}
		reservationEntity.setCelebrationExistence(celebration);
		reservationEntity.setDemand(form.getDemand());
		reservationEntity.setCreatedAt(timestamp);
		reservationEntity.setUpdatedAt(timestamp);
		repository.reservationDB(reservationEntity);
	}
	
	// 2週間
	public String[] twoWeek() {
		String[] timeList = new String[14];
		for(int i = 0; i < 14; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, i);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			timeList[i] = sdf.format(calendar.getTime());
		}
		return timeList;
	}
}
