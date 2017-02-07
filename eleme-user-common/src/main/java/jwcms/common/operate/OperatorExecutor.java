package jwcms.common.operate;

/**
 * <h1>Operator 执行器类</h1>
 * 
 * @author 谢永路（yonglu.xie@ele.me）
 * @date 2016/11/22
 * @since 2.1.0
 */
public class OperatorExecutor {

	private OperatorExecutor() {
	}
	
	public final OperateContext execute(IOperator operator, OperateContext context) throws Exception {
		OperateContext contextTemp = operator.perform(context);
		if(contextTemp != null) {
			return contextTemp;
		}
		
		return new OperateContext();
	}

}
