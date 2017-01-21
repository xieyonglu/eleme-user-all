package jwcms.common.model;

import java.sql.Timestamp;

public abstract class TBaseModel {
	
	/**
	 * 软删除标志位，0：未删除，1：删除
	 */
	private Integer isDelete;
	
	/**
	 * 创建人员
	 */
	private Long createdBy;
	
	/**
	 * 创建时间
	 */
	private Timestamp createdAt;
	
	/**
	 * 更新人员
	 */
	private Long updatedBy;
	
	/**
	 * 更新时间
	 */
	private Timestamp updatedAt;
	
	/**
	 * 版本号
	 */
	private Integer version;

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
