package spring.mvc.session10.entity;

import java.util.Arrays;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
	private String name; // 姓名
	private Integer age; // 年齡
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GNT+8") // 定義返回的日期格式 (yyyy-MM-dd)
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 接收的日期格式 (yyyy-MM-dd) 預設時區會按照 server 主機的時區
	private Date birth; // 生日
	
	private String education; // 教育程度
	private String sex;
	private String[] interest; // 興趣
	private String resume; // 履歷
	
	public User() {
		
	}
	
	public User(String name, Integer age, Date birth, String education, String sex, String[] interest, String resume) {
		super();
		this.name = name;
		this.age = age;
		this.birth = birth;
		this.education = education;
		this.sex = sex;
		this.interest = interest;
		this.resume = resume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String[] getInterest() {
		return interest;
	}
	//將 insert String 陣列內容(物件型) 轉成字串
	public String getInterestToString() {
		return Arrays.toString(interest);
	}

	public void setInterest(String[] interest) {
		this.interest = interest;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", birth=" + birth + ", education=" + education + ", sex=" + sex
				+ ", interest=" + Arrays.toString(interest) + ", resume=" + resume + "]";
	}
	
	
}
