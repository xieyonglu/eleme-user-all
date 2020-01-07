package jwcms.common.operate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <h1>OperateContext 上下文类</h1>
 * 
 * @author 谢永路（573546261@qq.com）
 * @date 2016/11/16
 * @since 2.1.0
 */
public class OperateContext {

	private Map<String, Object> parameterMap = new LinkedHashMap<>();
	
	/**
	 * 往Context里设置参数
	 * @param key Key值
	 * @param value Value值
	 */
	public void putParameter(String key, Object value) {
		parameterMap.put(key, value);
	}
	
	/**
	 * 往Context里取参数
	 * @param key Key值
	 * @return
	 */
	public Object getParameter(String key) {
		return parameterMap.get(key);
	}
	
	/**
	 * 根据参数名称和类获取参数里的某个对象
	 * @param paramName 参数名称
	 * @param clazz 类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <O> O getOperateObject(String paramName, Class<O> clazz) {
		Object object = this.getParameter(paramName);
		if(object != null) {
			return (O)object;
		}
		
		return null;
	}
	
}
