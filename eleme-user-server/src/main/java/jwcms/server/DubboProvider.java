package jwcms.server;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProvider {
	
	private static ApplicationContext applicationContext;

	public static void main(String[] args) throws IOException {
		//applicationContext = new FileSystemXmlApplicationContext("classpath*:application-context.xml");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-context.xml");
		context.start();

		System.out.println("服务已经启动...");
		System.in.read();
	}

}
