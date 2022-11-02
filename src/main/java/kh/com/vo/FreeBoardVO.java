package kh.com.vo;

import java.sql.Date;

public class FreeBoardVO {
	private int fb_id;
	private String fb_category;
	private String fb_user_id;
	private String fb_title;
	private String fb_content;
	private Date fb_c_date;
	private Date fb_u_date;
	private int fb_recommend;
	private int fb_hit;
	
	public int getFb_id() {
		return fb_id;
	}
	public void setFb_id(int fb_id) {
		this.fb_id = fb_id;
	}
	public String getFb_category() {
		return fb_category;
	}
	public void setFb_category(String fb_category) {
		this.fb_category = fb_category;
	}
	public String getFb_user_id() {
		return fb_user_id;
	}
	public void setFb_user_id(String fb_user_id) {
		this.fb_user_id = fb_user_id;
	}
	public String getFb_title() {
		return fb_title;
	}
	public void setFb_title(String fb_title) {
		this.fb_title = fb_title;
	}
	public String getFb_content() {
		return fb_content;
	}
	public void setFb_content(String fb_content) {
		this.fb_content = fb_content;
	}
	public Date getFb_c_date() {
		return fb_c_date;
	}
	public void setFb_c_date(Date fb_c_date) {
		this.fb_c_date = fb_c_date;
	}
	public Date getFb_u_date() {
		return fb_u_date;
	}
	public void setFb_u_date(Date fb_u_date) {
		this.fb_u_date = fb_u_date;
	}
	public int getFb_recommend() {
		return fb_recommend;
	}
	public void setFb_recommend(int fb_recommend) {
		this.fb_recommend = fb_recommend;
	}
	public int getFb_hit() {
		return fb_hit;
	}
	public void setFb_hit(int fb_hit) {
		this.fb_hit = fb_hit;
	}
};
	