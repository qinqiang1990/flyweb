package mongodb.driver.util;
 
import com.mongodb.*;
import com.mongodb.util.JSON;

public class MongoDBUtil {
	private static Mongo mongo = null;

	private static String DBString = "test";// 数据库名
	private static String hostName = "localhost";// 主机名
	private static int port = 27017;// 端口号
	private static int poolSize = 10;// 连接池大小

	private MongoDBUtil() {

	}

	// 获取数据库连接
	public static DB getDB() {
		if (mongo == null) {
			init();
		}

		return mongo.getDB(DBString);
	}

	// 初始化数据库
	private static void init() {
		// 实例化Mongo
		mongo = new Mongo(hostName, port);
		MongoOptions opt = mongo.getMongoOptions();
		// 设置连接池大小
		opt.connectionsPerHost = poolSize;
	}
}