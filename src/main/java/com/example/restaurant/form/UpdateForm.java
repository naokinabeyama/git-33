package com.example.restaurant.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateForm {
	
	private int reservationId;
	
	@NotBlank(message = "苗字を入力してください")
	@Size(max=15, message="苗字は15文字以内で入力してください")
	private String lastName;
	
	@NotBlank(message = "名前を入力してください")
	@Size(max=15, message="名前は15文字以内で入力してください")
	private String firstName;
	
	private String reservationDate;
	private String reservationTime;
	private int reservationCount;
	
	@NotBlank(message = "電話番号を入力してください")
	@Size(max = 12, message = "電話番号は12桁以内で入力してください")
	@Pattern(regexp = "^[0-9]*$", message = "電話番号は半角数字で入力してください")
	
	private String tell;
	
	private boolean celebrationExistence;
	
	@Size(max = 1000, message = "要望は1000文字以内で入力してください")
	private String demand;
	@AssertTrue(message = "要望を入力してください")
	public boolean isValidDemand() {
		if(celebrationExistence == true && demand == "") {
			return false;
		} else {
			return true;
		}
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
	 * @return reservationDate
	 */
	public String getReservationDate() {
		return reservationDate;
	}
	/**
	 * @param reservationDate セットする reservationDate
	 */
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	/**
	 * @return reservationTime
	 */
	public String getReservationTime() {
		return reservationTime;
	}
	/**
	 * @param reservationTime セットする reservationTime
	 */
	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}
	/**
	 * @return reservationCount
	 */
	public int getReservationCount() {
		return reservationCount;
	}
	/**
	 * @param reservationCount セットする reservationCount
	 */
	public void setReservationCount(int reservationCount) {
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
	 * @return celebrationExistence
	 */
	public boolean getCelebrationExistence() {
		return celebrationExistence;
	}
	/**
	 * @param celebrationExistence セットする celebrationExistence
	 */
	public void setCelebrationExistence(boolean celebrationExistence) {
		this.celebrationExistence = celebrationExistence;
	}
	/**
	 * @return demand
	 */
	public String getDemand() {
		return demand;
	}
	/**
	 * @param demand セットする demand
	 */
	public void setDemand(String demand) {
		this.demand = demand;
	}
	/**
	 * @return reservationId
	 */
	public int getReservationId() {
		return reservationId;
	}
	/**
	 * @param reservationId セットする reservationId
	 */
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

}
