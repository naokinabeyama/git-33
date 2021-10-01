package com.example.restaurant.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import com.example.restaurant.entity.ReservationsEntity;
import com.example.restaurant.form.SerchForm;
import com.example.restaurant.form.UpdateForm;
import com.example.restaurant.repository.ManagerRepository;

@Service
public class ManagerService {
	
	@Autowired
	ManagerRepository repository;
	
	
public Page<ReservationsEntity> getPage(int pageNum, SerchForm form) {
		
		 //Where句の検索条件を管理できる箱を作成　where(null)は箱を作るための仕様
		Specification<ReservationsEntity> searchConditions = Specification.where(null);
		
		/* IDで検索 */
    if (form.getReservationId() != null && !form.getReservationId().isEmpty()) {
    	// root: 検索するテーブルの情報
    	// query: 自分でSQLを作成できる(今回は使わないが必ず記述)
    	// cb: どんな検索をしたいか設定する
    	// 第一引数にどこのなんのカラムに対して検索を行うか
    	// 第二引数は実際に検索したい値
    	int id = Integer.parseInt(form.getReservationId());
    	
    	Specification<ReservationsEntity> searchId = (root, query, cb) -> cb.equal(root.get("id"), id);
    	searchConditions = searchConditions.and(searchId);// andメソッドを使用して検索条件を追加
    }
    
		/* 苗字で検索 */
    if (form.getLastName() != null && !form.getLastName().isEmpty()) {
    	Specification<ReservationsEntity> searchLastName = (root, query, cb) -> cb.like(root.get("lastName"), "%" + form.getLastName() + "%");
      searchConditions = searchConditions.and(searchLastName);
    }

		/* 名前で検索 */
    if (form.getFirstName() != null && !form.getFirstName().isEmpty()) {
    	Specification<ReservationsEntity> searchFirstName = (root, query, cb) -> cb.like(root.get("firstName"), "%" + form.getFirstName() + "%");
      searchConditions = searchConditions.and(searchFirstName);
    }
    
    /* 予約日fromで検索 */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (form.getReservationDateFrom() != null  && !form.getReservationDateFrom().isEmpty()) {
  	 String from = form.getReservationDateFrom() + " "+ "00:00:00";
  	 Date dateFrom = new Date();
  	 try {
  		 dateFrom = sdf.parse(from);
  	 } catch (ParseException e) {
  		 e.printStackTrace();
  	 }
  	 	Timestamp timeFrom = new Timestamp(dateFrom.getTime());
      Specification<ReservationsEntity> searchReservationDateFromTo = (root, query, cb) -> cb.greaterThanOrEqualTo((root.get("reservationDate")), (timeFrom));
      searchConditions = searchConditions.and(searchReservationDateFromTo);
    }
   
   // 予約日toで検索
   if(form.getReservationDateTo() != null  && !form.getReservationDateTo().isEmpty()) {
  	 String to = form.getReservationDateTo() + " " + "23:59:59";
  	 Date dateTo = new Date();
  	 try {
  		 dateTo = sdf.parse(to);
  	 } catch (ParseException e) {
  		 e.printStackTrace();
  	 }
  	 Timestamp timeTo = new Timestamp(dateTo.getTime());
  	 Specification<ReservationsEntity> searchReservationDateFromTo = (root, query, cb) -> cb.lessThanOrEqualTo((root.get("reservationDate")), (timeTo));
     searchConditions = searchConditions.and(searchReservationDateFromTo);
   }
    
    /* 予約人数で検索 */
    if (form.getReservationCount() != null && !form.getReservationCount().isEmpty()) {
    	int count = Integer.parseInt(form.getReservationCount());
    	Specification<ReservationsEntity> searchCount = (root, query, cb) -> cb.equal(root.get("reservationCount"), count);
      searchConditions = searchConditions.and(searchCount);
    }
    
    /* 電話番号で検索 */
    if (form.getTell() != null && !form.getTell().isEmpty()) {
    	Specification<ReservationsEntity> searchTell = (root, query, cb) -> cb.like(root.get("tell"), "%"+ form.getTell() + "%");
      searchConditions = searchConditions.and(searchTell);
    }
    
    /* 予約受付日で検索 */
    if (form.getReceptionDate() != null && !form.getReceptionDate().isEmpty()) {
    	String timeFirst = form.getReceptionDate() + " " + "00:00:00";
    	Date dateFirst = new Date();
    	try {
    		dateFirst = sdf.parse(timeFirst);
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	Timestamp createdTimeFirst = new Timestamp(dateFirst.getTime()); 
    	
    	String timeFinish = form.getReceptionDate() + " " + "23:59:59";
    	Date dateFinish = new Date();
    	try {
    		dateFinish = sdf.parse(timeFinish);
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	Timestamp createdTimeFinish = new Timestamp(dateFinish.getTime());
    	Specification<ReservationsEntity> searchReceptionDate = (root, query, cb) -> cb.between((root.get("createdAt")), (createdTimeFirst),(createdTimeFinish));
      searchConditions = searchConditions.and(searchReceptionDate);
    }

    Sort sort = Sort.by("id").ascending(); //idで昇順
    PageRequest pageable = PageRequest.of(pageNum, 5, sort);
    // 必ず検索（searchConditions）を先頭に記述
    Page<ReservationsEntity> entityPageList = repository.getPage(searchConditions,pageable);
    return entityPageList;
	}
	
	// 全データ
	public List<ReservationsEntity> getAll() {
		List<ReservationsEntity> reservationsEntityList =repository.selectAll();
		return reservationsEntityList;
	}
	
	// データ一件
	public ReservationsEntity getRecord(int id) {
		ReservationsEntity reservationsEntity = repository.getRecord(id);
		return reservationsEntity;
	}
	
	// 更新処理
	public void updateDB(UpdateForm form) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		//int id = Integer.parseInt(form.getReservationId());
		ReservationsEntity reservationsEntity = repository.getRecord(form.getReservationId());
		reservationsEntity.setLastName(form.getLastName());
		reservationsEntity.setFirstName(form.getFirstName());
		reservationsEntity.setReservationCount(form.getReservationCount());
		reservationsEntity.setTell(form.getTell());
		reservationsEntity.setDemand(form.getDemand());
		reservationsEntity.setUpdatedAt(timestamp);
		
		Date date = new Date();
		String[] t = form.getReservationTime().split("~");
		String total = form.getReservationDate() + " " + t[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			date = sdf.parse(total);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp time = new Timestamp(date.getTime());
		reservationsEntity.setReservationDate(time);
		int celebration;
		if(form.getCelebrationExistence() == true) {
			celebration = 1;
		} else {
			celebration = 0;
		}
		reservationsEntity.setCelebrationExistence(celebration);
		repository.updateDB(reservationsEntity);
	}
	
	public void deleteDB(int reservationId) {
		repository.deleteDB(reservationId);
	}
	
	
	// 時間の作成
	public String totalTime(Timestamp timestamp) {
		String timeFrom = new SimpleDateFormat("HH:mm").format(timestamp);
		SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();
		Date d = new Date();
		try {
			d = sdftime.parse(timeFrom);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		cal.setTime(d);
		cal.add(Calendar.HOUR_OF_DAY, 2);
		String timeTo = sdftime.format(cal.getTime());
		String totalTime = (timeFrom + "~" + timeTo);
		return totalTime;
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
	
	// お祝い
	public String celebration(int celebrationExistence) {
		String celebration;
		if(celebrationExistence == 0) {
			celebration = "なし";
		} else {
			celebration = "あり";
		}
		return celebration;
	}
	
	// 非活性
	public boolean date(Timestamp timestamp) {
		Timestamp timeNow = new Timestamp(System.currentTimeMillis());
		boolean bool;
		if(timestamp.toLocalDateTime().plusDays(1).isBefore(timeNow.toLocalDateTime())) {
			bool = false;
		} else {
			bool = true;
		}
		return bool;
	}
	
	// 要望
	public String demand(String demand) {
		String comment;
		if(demand == null || demand.isEmpty()) {
			comment = "なし";
		} else {
			comment = demand;
		}
		return comment;
	}
	
}
