package com.example.restaurant.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



public class SerchForm {

	@Size(max = 11, message="予約IDは11桁以内で入力してください")
	@Pattern(regexp = "^[0-9]*$", message = "予約IDは半角英数字で入力してください")
	private String reservationId;
	
	@Size(max = 15, message="苗字は15文字以内で入力してください")
	private String lastName;
	
	@Size(max = 15, message="名前は15文字以内で入力してください")
	private String firstName;
	
	private String reservationDateFrom;
	private String reservationDateTo;
//	@AssertTrue(message = "開始日より前です。")
//	public boolean isDate() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date dateFrom = new Date();
//		Date dateTo = new Date();
//		if(reservationDateFrom != null && !reservationDateFrom.isEmpty()
//				&& reservationDateTo != null && !reservationDateTo.isEmpty()) {
//			try {
//				dateFrom = sdf.parse(reservationDateFrom);
//				dateTo = sdf.parse(reservationDateTo);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if(dateFrom.before(dateTo)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	
	
	private String reservationCount;
	
	@Size(max = 12, message="電話番号は12桁以内で入力してください")
	@Pattern(regexp = "^[0-9]*$", message = "電話番号は半角数字で入力してください")
	private String tell;
	
	private String receptionDate;
	/**
	 * @return reservationId
	 */
	public String getReservationId() {
		return reservationId;
	}
	/**
	 * @param reservationId セットする reservationId
	 */
	public void setReservationId(String reservationId) {
		this.reservationId = reservationId;
	}
	/**
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName セットする lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName セットする firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return reservationDateFrom
	 */
	public String getReservationDateFrom() {
		return reservationDateFrom;
	}
	/**
	 * @param reservationDateFrom セットする reservationDateFrom
	 */
	public void setReservationDateFrom(String reservationDateFrom) {
		this.reservationDateFrom = reservationDateFrom;
	}
	/**
	 * @return reservationDateTo
	 */
	public String getReservationDateTo() {
		return reservationDateTo;
	}
	/**
	 * @param reservationDateTo セットする reservationDateTo
	 */
	public void setReservationDateTo(String reservationDateTo) {
		this.reservationDateTo = reservationDateTo;
	}
	/**
	 * @return reservationCount
	 */
	public String getReservationCount() {
		return reservationCount;
	}
	/**
	 * @param reservationCount セットする reservationCount
	 */
	public void setReservationCount(String reservationCount) {
		this.reservationCount = reservationCount;
	}
	/**
	 * @return tell
	 */
	public String getTell() {
		return tell;
	}
	/**
	 * @param tell セットする tell
	 */
	public void setTell(String tell) {
		this.tell = tell;
	}
	/**
	 * @return receptionDate
	 */
	public String getReceptionDate() {
		return receptionDate;
	}
	/**
	 * @param receptionDate セットする receptionDate
	 */
	public void setReceptionDate(String receptionDate) {
		this.receptionDate = receptionDate;
	}
}
