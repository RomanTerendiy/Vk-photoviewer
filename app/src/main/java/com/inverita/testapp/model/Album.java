package com.inverita.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

	@SerializedName("aid")
	@Expose
	private Integer aid;
	@SerializedName("thumb_id")
	@Expose
	private String thumbId;
	@SerializedName("owner_id")
	@Expose
	private String ownerId;
	@SerializedName("title")
	@Expose
	private String title;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("created")
	@Expose
	private String created;
	@SerializedName("updated")
	@Expose
	private String updated;
	@SerializedName("size")
	@Expose
	private Integer size;
	@SerializedName("thumb_src")
	@Expose
	private String thumbSrc;

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getThumbId() {
		return thumbId;
	}

	public void setThumbId(String thumbId) {
		this.thumbId = thumbId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getThumbSrc() {
		return thumbSrc;
	}

	public void setThumbSrc(String thumbSrc) {
		this.thumbSrc = thumbSrc;
	}

}