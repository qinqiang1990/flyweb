package qq.security.dao.sdjpa.v3;

import org.springframework.data.repository.PagingAndSortingRepository;

import qq.security.model.Controller;

public interface ControllerDaov3 extends
		PagingAndSortingRepository<Controller, Long>, ControllerDaov3Plus {

	// public Controller findByAccountId(Long id);
}