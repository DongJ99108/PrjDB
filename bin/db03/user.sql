/*
계정 SKY
TMEMBER
회원관리
번호    숫자(6_)  기본키  자동증가
이름    문자(30)  필수입력
아이디  문자(20)  필수입력  중복방지
암호    문자(20)  필수입력
이메일  문자(320) 중복방지
가입일  날짜      오늘

TUSER
아이디  문자(20)  필수입력  중복방지
이름    문자(30)  필수입력
이메일  문자(320) 중복방지
*/

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
COMMIT;