package jwcms.common.query;

public class QueryCriteria<T> {

	private Long pageIndex;

	private Integer pageSize;

	private T template;

	public Long getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Long pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public T getTemplate() {
		return template;
	}

	public void setTemplate(T template) {
		this.template = template;
	}

}
