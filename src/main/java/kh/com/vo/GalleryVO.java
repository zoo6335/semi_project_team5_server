package kh.com.vo;

import java.sql.Date;

public class GalleryVO {
	
	private int gal_id;
	private String title;
	private String content;
	private String image_url;
	private Date create_date;
	private Date update_date;
	
	
	public int getGal_id() {
		return gal_id;
	}
	public void setGal_id(int gal_id) {
		this.gal_id = gal_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
}
