package cache;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URLClassLoader classLoader = (URLClassLoader) Test.class
				.getClassLoader();
		URL[] urls = classLoader.getURLs();
		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].toString());
		}
	}

}
