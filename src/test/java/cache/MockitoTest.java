package cache;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class MockitoTest {

	@Test
	public void Empty() {

		Assert.assertEquals(true, StringUtils.isEmpty(""));

	}

	@Test
	public void SimpleTest() {

		// 创建mock对象，参数可以是类，也可以是接口
		List<String> list = mock(List.class);

		// 设置方法的预期返回值
		when(list.get(0)).thenReturn("helloworld");

		String result = list.get(0);

		// 验证方法调用(是否调用了get(0))
		verify(list).get(any(Integer.class));

		// junit测试
		Assert.assertEquals("helloworld", result);
	}

	@Test
	public void VerifyInvocate() {

		List<String> mockedList = mock(List.class);
		// using mock
		mockedList.add("once");
		mockedList.add("twice");
		mockedList.add("twice");

		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");

		/** 
		  * 基本的验证方法 
		  * verify方法验证mock对象是否有没有调用mockedList.add("once")方法 
		  * 不关心其是否有返回值，如果没有调用测试失败。 
		  */
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");// 默认调用一次,times(1)可以省略

		verify(mockedList, times(2)).add("twice");
		verify(mockedList, times(3)).add("three times");

		verify(mockedList, times(6)).add(any(String.class));
	}

}
