package jwcms.common.util;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerStringUtils {
	
	/* 在整个应用的生命周期中，这个工作你应该只做一次。 */
	/* 创建和调整配置。 */
	private static Configuration config;

	static {
		config = new Configuration();
	}

	private synchronized static Template getTemplate(String tpl) throws Exception {
		config.setTemplateLoader(new StringTemplateLoader(tpl));
		config.setDefaultEncoding("UTF-8");
		Template template = config.getTemplate("");
		return template;
	}

	public static String getText(String tpl, Map<String, Object> root) throws Exception {
		Template template = getTemplate(tpl);
		StringWriter writer = new StringWriter();
		template.process(root, writer);
		return writer.toString();
	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> root = new HashMap<>();
		root.put("user", "test");
		FreemarkerStringUtils.getText("${user}:1111", root);
		
		//
		URL url = FreemarkerStringUtils.class.getClassLoader().getResource("send_stock_email.tpl.html");
		if(url == null) {
			//throw new ServiceException(SkuExceptionCode.CANNOT_FOUND_RESOURCE_FILE);
		}
		File conf = new File(url.getFile());
		String template = FileUtils.readContent(conf.getPath());
		Map<String, Object> params = new HashMap<>();
		params.put("skus", null);
		params.put("date", null);
		
		String message = FreemarkerStringUtils.getText(template, params);
		System.out.println(message);
	}
}
