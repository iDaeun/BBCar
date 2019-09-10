
/* 탑승자 */
CREATE TABLE ycar.Passenger (
	p_idx INT(10) NOT NULL, /* 회원번호 */
	id VARCHAR(50) NOT NULL, /* 아이디 */
	pw VARCHAR(50) NOT NULL, /* 비밀번호 */
	name VARCHAR(20) NOT NULL, /* 이름 */
	nickname VARCHAR(50), /* 닉네임 */
	email VARCHAR(100), /* 이메일 */
	gender VARCHAR(1), /* 성별 */
	verify VARCHAR(1), /* 직장인 인증여부 */
	code VARCHAR(50) /* 인증코드 */
);

RENAME TABLE ycar.Passenger TO ycar.PASSENGER;

/* 탑승자-직장인증 */
CREATE TABLE ycar.P_COMPANYP_COMPANY (
	p_idx INT(10) NOT NULL, /* 회원번호 */
	company VARCHAR(50) NOT NULL, /* 회사명 */
	cemail VARCHAR(100), /* 회사이메일 */
	cnum INT(20), /* 사업자등록번호 */
	type VARCHAR(1) NOT NULL /* 인증타입 */
);

drop table ycar.P_COMPANYP_COMPANY;

ALTER TABLE ycar.P_COMPANY
   DROP PRIMARY KEY; 

ALTER TABLE ycar.P_COMPANY
   ADD CONSTRAINT FK_Passenger_TO_p_company 
      FOREIGN KEY (
         p_idx -- 회원번호
      )
      REFERENCES PASSENGER ( -- 탑승자
         p_idx -- 회원번호
      );

/* 탑승자-선호운행옵션 */
CREATE TABLE ycar.P_OPTION (
	p_idx INT(10) NOT NULL, /* 회원번호 */
	p_option VARCHAR(1) /* 옵션 */
);

ALTER TABLE ycar.P_OPTION
	ADD
		CONSTRAINT FK_PASSENGER_TO_P_OPTION
		FOREIGN KEY (
			p_idx
		)
		REFERENCES PASSENGER (
			p_idx
		);

/* 탑승자-선호경로 */
CREATE TABLE ycar.P_ROUTE (
	p_idx INT(10) NOT NULL, /* 회원번호 */
	type VARCHAR(1), /* 타입 */
	place VARCHAR(50) /* 장소명 */
);

ALTER TABLE ycar.P_ROUTE
	ADD
		CONSTRAINT FK_PASSENGER_TO_P_ROUTE
		FOREIGN KEY (
			p_idx
		)
		REFERENCES PASSENGER (
			p_idx
		);
        
SELECT * FROM ycar.PASSENGER;
update ycar.PASSENGER set verify='Y' where id='passengerA';
select * from ycar.PASSENGER where id='passengerA';

insert into ycar.PASSENGER (id,nickname,email,verify,type) values ('passengerKakao','kakaokakao','ekdms2309@naver.com','Y','kakao');
update ycar.PASSENGER set id='1165366390' where id='passengerKakao';

drop table P_COMPANY;

