package jwcms.startup;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Server启动类
 * 
 * @author yonglu.xie
 *
 */
public class Bootstrap {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// ClassPathXmlApplicationContext context = new
		// ClassPathXmlApplicationContext("classpath*:application-context.xml");
		// context.start();
		//
		// System.out.println("服务已经启动...");
		// System.in.read();

		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:application-context.xml");
			// spring-context.xml配置加入
			/*
			 * <import resource="dubbo-provider.xml" />
			 */
			context.start();
			System.out.println("Start Success");
		} catch (Exception e) {
			System.out.println("== DubboProvider context start error:");
			System.out.println(e);
			// log.error("== DubboProvider context start error:", e);
		}
		synchronized (Bootstrap.class) {
			while (true) {
				try {
					Bootstrap.class.wait();
				} catch (InterruptedException e) {
					// log.error("== synchronized error:", e);
					System.out.println("== synchronized error:");
					System.out.println(e);
				}
			}
		}
	}

//	public static final String CONTAINER_KEY = "dubbo.container";
//
//	public static final String SHUTDOWN_HOOK_KEY = "dubbo.shutdown.hook";
//
//	private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);
//
//	private static final ExtensionLoader<Container> loader = ExtensionLoader.getExtensionLoader(Container.class);
//
//	private static volatile boolean running = true;

//	public static void main(String[] args) {
//		try {
//			if (args == null || args.length == 0) {
//				String config = ConfigUtils.getProperty(CONTAINER_KEY, loader.getDefaultExtensionName());
//				args = Constants.COMMA_SPLIT_PATTERN.split(config);
//			}
//
//			final List<Container> containers = new ArrayList<Container>();
//			for (int i = 0; i < args.length; i++) {
//				containers.add(loader.getExtension(args[i]));
//			}
//			logger.info("Use container type(" + Arrays.toString(args) + ") to run dubbo serivce.");
//
//			if ("true".equals(System.getProperty(SHUTDOWN_HOOK_KEY))) {
//				Runtime.getRuntime().addShutdownHook(new Thread() {
//					public void run() {
//						for (Container container : containers) {
//							try {
//								container.stop();
//								logger.info("Dubbo " + container.getClass().getSimpleName() + " stopped!");
//							} catch (Throwable t) {
//								logger.error(t.getMessage(), t);
//							}
//							synchronized (Bootstrap.class) {
//								running = false;
//								Bootstrap.class.notify();
//							}
//						}
//					}
//				});
//			}
//
//			for (Container container : containers) {
//				container.start();
//				logger.info("Dubbo " + container.getClass().getSimpleName() + " started!");
//			}
//			System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date())
//					+ " Dubbo service server started!");
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//			logger.error(e.getMessage(), e);
//			System.exit(1);
//		}
//		synchronized (Bootstrap.class) {
//			while (running) {
//				try {
//					Bootstrap.class.wait();
//				} catch (Throwable e) {
//				}
//			}
//		}
//	}

}
