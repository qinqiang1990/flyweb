package fastjson;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public void jackson(List<?> list) throws Exception {
		for (Object corp : list) {
			new ObjectMapper().writeValueAsString(corp);
		}
	}

}
