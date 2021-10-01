package com.example.restaurant.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")
public class ReservationsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String lastName;
	private String firstName;
	private Timestamp reservationDate;
	private int reservationCount;
	private String tell;
	private int celebrationExistence;
	private String demand;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
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
	public Timestamp getReservationDate() {
		return reservationDate;
	}
	/**
	 * @param reservationDate セットする reservationDate
	 */
	public void setReservationDate(Timestamp reservationDate) {
		this.reservationDate = reservationDate;
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
	public int getCelebrationExistence() {
		return celebrationExistence;
	}
	/**
	 * @param celebrationExistence セットする celebrationExistence
	 */
	public void setCelebrationExistence(int celebrationExistence) {
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
	 * @return createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt セットする createdAt
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return updatedAt
	 */
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	/**
	 * @param updatedAt セットする updatedAt
	 */
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
