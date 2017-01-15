package jwcms.common.basequery;

public class Page<T> {

	private Long index;

	private Integer size;

	private T template;

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public T getTemplate() {
		return template;
	}

	public void setTemplate(T template) {
		this.template = template;
	}

}
