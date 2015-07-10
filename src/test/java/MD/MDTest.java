package MD;

import org.junit.Test;

import com.jiangge.utils.algorithm.MD5;
import com.qq.utils.MD5Util;

public class MDTest {

	@Test
	public void MD() {

		System.out.println(MD5Util.encodePassword("12345678"));
		System.out.println(MD5.GetMD5Code("12345678"));
		System.out.println(RSAUtil.decryptByPrivateKey("12345678"));

	}

}
