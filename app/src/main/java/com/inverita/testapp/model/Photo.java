package com.inverita.testapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Photo implements Serializable {

	@SerializedName("pid")
	@Expose
	private Integer pid;
	@SerializedName("aid")
	@Expose
	private Integer aid;
	@SerializedName("owner_id")
	@Expose
	private Integer ownerId;
	@SerializedName("src")
	@Expose
	private String src;
	@SerializedName("src_big")
	@Expose
	private String srcBig;
	@SerializedName("src_small")
	@Expose
	private String srcSmall;
	@SerializedName("src_xbig")
	@Expose
	private String srcXbig;
	@SerializedName("src_xxbig")
	@Expose
	private String srcXxbig;
	@SerializedName("width")
	@Expose
	private Integer width;
	@SerializedName("height")
	@Expose
	private Integer height;
	@SerializedName("text")
	@Expose
	private String text;
	@SerializedName("created")
	@Expose
	private Integer created;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getSrcBig() {
		return srcBig;
	}

	public void setSrcBig(String srcBig) {
		this.srcBig = srcBig;
	}

	public String getSrcSmall() {
		return srcSmall;
	}

	public void setSrcSmall(String srcSmall) {
		this.srcSmall = srcSmall;
	}

	public String getSrcXbig() {
		return srcXbig;
	}

	public void setSrcXbig(String srcXbig) {
		this.srcXbig = srcXbig;
	}

	public String getSrcXxbig() {
		return srcXxbig;
	}

	public void setSrcXxbig(String srcXxbig) {
		this.srcXxbig = srcXxbig;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}

}