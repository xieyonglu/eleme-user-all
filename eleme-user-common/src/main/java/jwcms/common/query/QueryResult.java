package jwcms.common.query;

import java.util.List;

public class QueryResult<T> {

	private List<T> result;
	
	private Long totalCount;

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
}
