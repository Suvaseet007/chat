package com.yoctotta.train;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int msg_id;
	@Column
	private String content;
	@Column
	private Date dateandtime;
	@Column
	private Date future_date;
	@Column
	private String delete_in;
	@Column
	private String type;
	@Column
	private int from_user_id;
	@Column
	private int to_user_id;

	public int getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateandtime() {
		return dateandtime;
	}

	public void setDateandtime(Date dateandtime) {
		this.dateandtime = dateandtime;
	}

	public Date getFuture_date() {
		return future_date;
	}

	public void setFuture_date(Date future_date) {
		this.future_date = future_date;
	}

	public String getDelete_in() {
		return delete_in;
	}

	public void setDelete_in(String delete_in) {
		this.delete_in = delete_in;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFrom_user_id() {
		return from_user_id;
	}

	public void setFrom_user_id(int from_user_id) {
		this.from_user_id = from_user_id;
	}

	public int getTo_user_id() {
		return to_user_id;
	}

	public void setTo_user_id(int to_user_id) {
		this.to_user_id = to_user_id;
	}

}
