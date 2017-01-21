package jwcms.server;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Server启动类
 * @author yonglu.xie
 *
 */
public class Bootstrap {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-context.xml");
		context.start();

		System.out.println("服务已经启动...");
		System.in.read();
	}

}
