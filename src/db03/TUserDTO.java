package db03;
/*
아이디  문자(20)  필수입력  중복방지
이름    문자(30)  필수입력
이메일  문자(320) 중복방지

CREATE TABLE TUSER (
 ID    VARCHAR2(20)  PRIMARY KEY, -- PRIMARY KEY 이거랑 NOT NULL 같이 쓸 수 있음 > 오류 안남
 NAME  VARCHAR2(30)  NOT NULL,
 EMAIL VARCHAR2(320) UNIQUE
);

INSERT INTO TUSER VALUES('AA', 'John', 'John@naver.com');
INSERT INTO TUSER VALUES('BB', 'Maria', 'Maria@naver.com');
INSERT INTO TUSER VALUES('CC', 'Camile', 'Camile@naver.com');
INSERT INTO TUSER VALUES('DD', 'Kennen', 'Kennen@naver.com');
INSERT INTO TUSER VALUES('EE', 'Jhin', 'Jhin@naver.com');
*/

public class TUserDTO {
	
	private String id;
	private String name;
	private String email;
	//Constructor
	public TUserDTO() {
	}
	public TUserDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}
	public TUserDTO(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	//Getter / Setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	// toString
	@Override
	public String toString() {
		return "TUser [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
	
	
	

}
























