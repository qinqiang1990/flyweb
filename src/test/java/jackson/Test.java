package jackson;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class Test {

	public void fastjson(List<?> list) {
		for (Object corp : list) {
			JSON.toJSONString(corp);
		}
	}

}
