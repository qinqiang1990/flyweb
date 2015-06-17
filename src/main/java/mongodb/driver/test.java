package mongodb.driver;

import java.util.List;

import com.mongodb.BasicDBObject;

public class test {
	public static BaseDAOImpl baseDAOImpl = new BaseDAOImpl();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		find();
	}

	public static void insert() {

		BasicDBObject beanOne = new BasicDBObject();
		beanOne.put("name", "kakakaka");
		beanOne.put("sex", "男");
		beanOne.put("age", 20);
		baseDAOImpl.insert("reboot", beanOne);
	}

	@SuppressWarnings("unchecked")
	public static void find() {
		List<BasicDBObject> list = baseDAOImpl.find("reboot", new BasicDBObject("name", "gugugugu"));
		for (BasicDBObject i : list) {
			System.out.println(i.get("name"));
			System.out.println(i.get("sex"));
			System.out.println(i.get("age"));
		}
	}

	public static void update() {
		BasicDBObject oldBean = (BasicDBObject) baseDAOImpl.find("reboot",
				new BasicDBObject("name", "kakakaka")).get(0);
		BasicDBObject newBean = (BasicDBObject) oldBean.clone();
		newBean.put("name", "gugugugu");
		System.out.println(oldBean.get("name"));
		System.out.println(newBean.get("name"));
		baseDAOImpl.update("reboot", oldBean, newBean);
	}
	
	public static void delete()
	{
		baseDAOImpl.delete("reboot", new BasicDBObject("name","gugugugu"));
	}
}
