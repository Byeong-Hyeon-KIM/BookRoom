package com.bookroom.user.vo;

public class UserVo {
	String userid;
	String username;
	String email;
	String mobile;
	String gender;
	String age;
	String birthyear;

	public UserVo() {
	}

	public UserVo(String userid, String username, String email, String mobile, String gender, String age,
			String birthyear) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.age = age;
		this.birthyear = birthyear;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthyear() {
		return birthyear;
	}

	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}

	@Override
	public String toString() {
		return "UserVo [userid=" + userid + ", username=" + username + ", email=" + email + ", mobile=" + mobile
				+ ", gender=" + gender + ", age=" + age + ", birthyear=" + birthyear + "]";
	}

}
