package Encoder;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class URLEncoderTest {

	//编码
	@Test
	public void Encode() { 
		try {
			System.out.println(java.net.URLEncoder.encode("苏州","UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
