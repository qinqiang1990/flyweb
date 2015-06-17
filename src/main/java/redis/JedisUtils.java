package redis;

import java.io.Serializable;

import com.qq.utils.SerializeUtil;

import redis.clients.jedis.Jedis;

//http://javacrazyer.iteye.com/blog/1840161
public class JedisUtils {

	public static Jedis jedis = new Jedis("192.168.1.250");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// jedis.del("foo");
		// Spring Data Redis
		/*
		 * jedis.setex("foo", 20, "{'sno':'2011266765','name':'qinq'}"); String
		 * value = jedis.get("foo");
		 */
		// 很直观，类似map 将jintao append到已经有的value之后
		// 向key-->name中放入了value-->minxr
		// 添加数据
		/*
		 * jedis.lpush("lists", "vector"); jedis.lpush("lists", "ArrayList");
		 * jedis.lpush("lists", "LinkedList");
		 */
		// 数组长度
		// System.out.println(jedis.llen("lists"));
		// System.out.println();
		// System.out.println(jedis.lrange("lists",0,3));
		// 整个列表值

		// System.out.println(jedis.lrange("lists", 0, -1));
		// System.out.println(value);

		setObject();
		jedis.lpush("lists", "vector");
		jedis.lpush("lists", "ArrayList");
		jedis.lpush("lists", "LinkedList");
		// jedis.expire(key, seconds)
		Person person = getObject(100);
		System.out.println(person.getId());
		System.out.println(person.getName());
		person = getObject(101);
		System.out.println(person.getId());
		System.out.println(person.getName());
	}

	public static void setObject() {
		Person person = new Person(100, "alan");
		jedis.set("person:100".getBytes(), SerializeUtil.serialize(person));
		person = new Person(101, "bruce");
		jedis.set("person:101".getBytes(), SerializeUtil.serialize(person));
	}

	public static Person getObject(int id) {
		byte[] person = jedis.get(("person:" + id).getBytes());
		return (Person) SerializeUtil.unserialize(person);
	}
}

class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6189577708881621252L;
	private int id;
	private String name;

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
