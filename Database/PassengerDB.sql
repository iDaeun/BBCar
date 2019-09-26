
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
update ycar.PASSENGER set verify='Y' where id='passengerfff';
update ycar.PASSENGER set pw='aaaa' where id='passengerA';
update ycar.PASSENGER set type='S' where id='passengerB';
update ycar.PASSENGER set type='S' where id='passengerC';
update ycar.PASSENGER set type='S' where id='passengerA';
select * from ycar.PASSENGER where id='passengerA';
update ycar.PASSENGER set name='카카오 사용자' where id='1165366390';

update ycar.PASSENGER set name='testtest' where id='passengerfff';

insert into ycar.PASSENGER (id,nickname,email,verify,type) values ('passengerKakao','kakaokakao','ekdms2309@naver.com','Y','kakao');
update ycar.PASSENGER set id='1165366390' where id='passengerKakao';

drop table P_COMPANY;

select name, id, nickname, email from ycar.PASSENGER where p_idx = '1';

-- 13	H	서울시 마포구 합정동
-- 13	C	서울시 종로구 종로2가
SELECT * FROM ycar.P_ROUTE;
insert into ycar.P_ROUTE (p_idx,type,place) values ('10','H','서울시 마포구 합정동');
insert into ycar.P_ROUTE (p_idx,type,place) values ('10','C','서울시 종로구 종로2가');

-- 탑승자 메모 만들기
-- 메모
CREATE TABLE ycar.MEMO (
	m_idx   INT(10)      NOT NULL, -- 메모번호
	p_idx   INT(10)      NOT NULL, -- 회원번호
	dr_idx  INT(10)      NOT NULL, -- 카풀등록번호
	context VARCHAR(100) NULL      -- 메모내용
);

-- 메모
ALTER TABLE ycar.MEMO
	ADD CONSTRAINT PK_MEMO -- 메모 기본키
		PRIMARY KEY (
			m_idx -- 메모번호
		);

-- 메모
ALTER TABLE ycar.MEMO
	ADD CONSTRAINT FK_PASSENGER_TO_MEMO -- 탑승자 -> 메모
		FOREIGN KEY (
			p_idx -- 회원번호
		)
		REFERENCES ycar.PASSENGER ( -- 탑승자
			p_idx -- 회원번호
		);

-- 메모
ALTER TABLE ycar.MEMO
	ADD CONSTRAINT FK_D_CARPOOL_TO_MEMO -- 운전자카풀등록 -> 메모
		FOREIGN KEY (
			dr_idx -- 카풀등록번호
		)
		REFERENCES ycar.D_CARPOOL ( -- 운전자카풀등록
			dr_idx -- 카풀등록번호
		);
        
SELECT * FROM ycar.P_MEMO;
SELECT * FROM D_CARPOOL;
SELECT * FROM RESERVATION;

update ycar.RESERVATION set r_confirm = null where r_idx= 29;

select cp.d_idx, cp.d_date, cp.d_starttime, cp.d_endtime, cp.d_startpoint, cp.d_endpoint, cp.d_fee, cp.d_distance from D_CARPOOL as cp
join RESERVATION as rsv 
where cp.dr_idx = rsv.dr_idx and rsv.r_confirm is null 
order by cp.d_date desc;

select * from D_CARPOOL d join RESERVATION r on d.dr_idx = r.dr_idx where  r.r_confirm is null order by d.d_date desc;

select * from D_CARPOOL as cp
join RESERVATION as rsv 
where cp.dr_idx = rsv.dr_idx and rsv.r_confirm = 'B' 
order by cp.d_date desc;

insert into MemoEntity p_idx = 10, dr_idx = 15, context = 'texttext';

