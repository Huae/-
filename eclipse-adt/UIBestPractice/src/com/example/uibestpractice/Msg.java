package com.example.uibestpractice;

public class Msg {
	public static final int TYPE_RECEIVED = 0;
	public static final int TYPE_SEND = 1;
	private String msg;
	private int type;

	public Msg(String msg, int type) {
		super();
		this.msg = msg;
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
