package qq.security.dao.sdjpa.v2;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import qq.security.model.Controller;

public interface ControllerDaov2 extends CrudRepository<Controller, Long> {

	/*
	 * public Ordering findByBookingId(String bookingId);
	 * 
	 * @Query("select a from Ordering a where a.bookingId = ?1 and a.status is null"
	 * ) public Ordering findByBookingIdAndStatus(String bookingId);
	 * 
	 * public List<Ordering> findByBookingIdOrderByUpdatedateDesc(String
	 * bookingId);
	 * 
	 * 
	 * @Query(
	 * "select a from Ordering a where a.bookingId = ?1 and a.status='SUCCESS'")
	 * public Ordering findByBookingIdSuccess(String bookingId);
	 * 
	 * @Query("select a from Ordering a where a.bookingId = ?1 and a.status in ?2"
	 * ) public Ordering findByBookingIdSuccess(String bookingId,List<Status>
	 * status);
	 */
}
