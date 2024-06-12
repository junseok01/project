package com.example.project.login;

public class UserDTO {
	private String loginId;
	private String loginPw;
	private String loginPwConfirm;
	private String name;
	private String email;
	private String nickname;
	private String cellphoneNo;
	private String job;
	public UserDTO(String loginId, String loginPw, String loginPwConfirm, String name, String email, String nickname,
			String cellphoneNo, String job) {
		super();
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.loginPwConfirm = loginPwConfirm;
		this.name = name;
		this.email = email;
		this.nickname = nickname;
		this.cellphoneNo = cellphoneNo;
		this.job = job;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPw() {
		return loginPw;
	}
	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}
	public String getLoginPwConfirm() {
		return loginPwConfirm;
	}
	public void setLoginPwConfirm(String loginPwConfirm) {
		this.loginPwConfirm = loginPwConfirm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCellphoneNo() {
		return cellphoneNo;
	}
	public void setCellphoneNo(String cellphoneNo) {
		this.cellphoneNo = cellphoneNo;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}

	

}
