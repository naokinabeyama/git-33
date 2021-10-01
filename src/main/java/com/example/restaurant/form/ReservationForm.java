package com.example.restaurant.form;

import java.sql.Timestamp;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class ReservationForm {
	
	@NotBlank(message = "苗字を入力してください")
	@Size(max = 10, message = "15文字以内で入力してください")
	private String lastName;
	
	@NotBlank(message = "名前を入力してください")
	@Size(max = 10, message = "15文字以内で入力してください")
	private String firstName;
	
	private String visitsDate;
	@Size(min = 4 ,message = "来店時間を選択してください")
	private String visitsTime;
	
	@Pattern(regexp = "^[0-9]*$", message = "来店人数を選択してください")
	private String visitsNumber;
	
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
	
	private Timestamp updateDatetime;
	private Timestamp insertDatetime;
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
	 * @return updateDatetime
	 */
	public Timestamp getUpdateDatetime() {
		return updateDatetime;
	}
	/**
	 * @param updateDatetime セットする updateDatetime
	 */
	public void setUpdateDatetime(Timestamp updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	/**
	 * @return insertDatetime
	 */
	public Timestamp getInsertDatetime() {
		return insertDatetime;
	}
	/**
	 * @param insertDatetime セットする insertDatetime
	 */
	public void setInsertDatetime(Timestamp insertDatetime) {
		this.insertDatetime = insertDatetime;
	}
	/**
	 * @return visitNumber
	 */
	public String getVisitsNumber() {
		return visitsNumber;
	}
	/**
	 * @param visitNumber セットする visitNumber
	 */
	public void setVisitsNumber(String visitsNumber) {
		this.visitsNumber = visitsNumber;
	}
	/**
	 * @return visitsDate
	 */
	public String getVisitsDate() {
		return visitsDate;
	}
	/**
	 * @param visitsDate セットする visitsDate
	 */
	public void setVisitsDate(String visitsDate) {
		this.visitsDate = visitsDate;
	}
	/**
	 * @return visitsTime
	 */
	public String getVisitsTime() {
		return visitsTime;
	}
	/**
	 * @param visitsTime セットする visitsTime
	 */
	public void setVisitsTime(String visitsTime) {
		this.visitsTime = visitsTime;
	}
	
	
}
