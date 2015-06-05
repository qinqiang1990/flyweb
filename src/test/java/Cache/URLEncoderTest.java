import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.concurrent.CacheManager;
org.springframework.cache.ehcache.EhCacheCacheManager
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-context-test.xml" })
public class CachingTest {
	@Autowired
	ApplicationContext context;
	@Autowired
	CacheManager cacheManager;
	MessageStorage storage;
	MessageStorage storageDelegate;
	MessageController controller;

	@Before
	public void before() throws Exception {
		storageDelegate = Mockito.mock(MessageStorage.class);
		storage = (MessageStorage) context.getBean("messageStorage");
		storage.setDelegate(storageDelegate);
		controller = new MessageController(storage);
		cacheManager.clearAll();
	}

	@Test
	public void testCaching_MessagesCache() {
		controller.getAllMessages();
		controller.getAllMessages();
		verify(storageDelegate, times(1)).findAllMessages();
	}

	@Test
	public void testCaching_MessagesCacheRemove() {
		controller.getAllMessages();
		controller.getAllMessages();
		controller.addMessage(new Message());
		controller.getAllMessages();
		verify(storageDelegate, times(2)).findAllMessages();
		verify(storageDelegate, times(1)).addMessage(any(Message.class));
	}

	@Test
	public void testCaching_MessageCache() {
		controller.getMessageById(1);
		controller.getMessageById(1);
		controller.addMessage(new Message());
		controller.getMessageById(1);
		verify(storageDelegate, times(1)).findMessage(1);
		verify(storageDelegate, times(1)).addMessage(any(Message.class));
	}
}