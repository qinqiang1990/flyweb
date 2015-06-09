package qq.security.dao.sdjpa.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import qq.security.model.Controller;

public interface ControllerDaov1 extends JpaRepository<Controller, Long> {

	Page<Controller> findByUrl(String url, Pageable pageable);

	/*
	 * List<Booking> findByUrl(String restid, String mealType, Status
	 * castStatus);
	 * 
	 * 
	 * @Query(
	 * "select a from Booking a where  a.status in ?1 and a.userid = ?2  and a.updatedate>?3 and a.updatedate<?4 "
	 * ) List<Booking> findCustom(List<Status> status,String userid,Date
	 * begin,Date end, Pageable pageable);
	 */

}
