package jwcms.common.operate;

/**
 * <h1>IOperator 解释器接口类</h1>
 * 
 * @author 谢永路（yonglu.xie@ele.me）
 * @date 2016/11/16
 * @since 2.1.0
 */
@FunctionalInterface
public interface IOperator {

	/**
	 * 接口函数的唯一方法
	 * @param context 操作上下文
	 * @return
	 * @throws Exception
	 */
	public abstract OperateContext perform(OperateContext context) throws Exception;
	
}
