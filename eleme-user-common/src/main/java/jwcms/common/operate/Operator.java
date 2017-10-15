package jwcms.common.operate;

/**
 * <h1>Operator 解释器抽象类</h1>
 * 
 * @author 谢永路（yonglu.xie@ele.me）
 * @date 2016/11/16
 * @since 2.1.0
 */
public abstract class Operator implements IOperator {
	
	/**
	 * 开始执行操作
	 * @param context 操作上下文
	 * @return
	 * @throws Exception
	 */
	public final OperateContext startPerform(OperateContext context) throws Exception {
		OperateContext contextTemp = this.perform(context);
		if(contextTemp != null) {
			return contextTemp;
		}
		
		return new OperateContext();
	}
	
}
