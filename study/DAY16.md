
#### 1. h2 데이터베이스 설치

- 먼저, h2 데이터베이스를 설치하여야 한다.
- **강의자료 6**. 의 내용을 확인하여 설치를 진행한 다음, 아래의 내용을 진행하자.


#### 2. 테이블 생성하기

- 먼저, 테이블을 생성할 것이다. 
- H2 데이터베이스를 Socket을 열어 실행시킨 다음, 아래의 명령어를 입력해보자.
	- [JDBC URL](http://localhost:8082/login.jsp?jsessionid=9f5e4d03275bfb72dea7ed2202f5b46a#)를 `jdbc:h2:tcp://localhost/~/test`로 접속하라는 뜻이다.

```sql
drop table if exists member CASCADE;
create table member (
id bigint generated by default as identity,
name varchar(255),
primary key (id)
);
```

- 아래는 `member`라는 테이블이 이미 존재하면, 해당 테이블을 삭제하는 명령어이다.
	- 이 때, 해당 테이블과 연결된 객체가 있으면 연결된 모든 객체를 삭제한다.
```sql
DROP TABLE IF EXISTS member CASCADE;
```

- 다음으로, `member`라는 테이블을 만든다. 이 테이블에는 아래의 컬럼이 존재한다.
	1. `id`
		- 64비트 정수형(`bigint`)이다. 
		- `generated by default as identity`: 열이 자동으로 생성되고, 그 값을 자동으로 증가시킨다. 즉, 데이터를 삽입할 때마다 데이터베이스가 해당 열의 값을 자동으로 채우고, 그 값은 이전에 삽입된 데이터의 값보다 크게끔 구성된다. 
		- 이것은 일반적으로 기본 키`primary key`로 사용되는 열에서 많이 사용되며,이러한 속성은 주로 데이터베이스에서 시퀀스나 `IDENTITY` 열을 사용하여 구현된다.
	2. `name`
		- 가변 길이를 가진다
		- 최대 255자의 문자열을 저장할 수 있다.

- 추가적으로, 기본키로 `id`를 지정한다.
```sql
create table member (
id bigint generated by default as identity,
name varchar(255),
primary key (id)
);
```


#### 3. 데이터 Insert 해보기

```sql
/* id를 추가하지 않은 경우: id bigint generated by default as identity */
insert into member(name) values('user1');
insert into member(name) values('user2');
insert into member(name) values('user3');

/* id를 추가해준 경우 */
insert into member values('4','user4');

select * from member;
```

![[insert 결과.png]]


#### 4. DDL 저장

- DDL은 "Data Definition Language"의 약자로, 데이터베이스 구조를 정의하고 관리하기 위해 사용되는 SQL의 하위 언어이다.
	- DDL은 데이터베이스 객체를 생성, 수정 및 삭제하는 데 사용된다.
	- 일반적인 DDL 명령에는 아래와 같은 것들이 포함된다.

- 참고로, DML은 데이터를 쿼리하고 조작하는 데 사용된다.

> [!note] DDL 명령어
> 1. **CREATE**: 데이터베이스 객체를 생성한다. 예를 들어, 테이블, 뷰, 인덱스 등을 생성할 때 사용된다. 
> 2. **ALTER**: 데이터베이스 객체를 수정한다.  예를 들어, 테이블에 새 열을 추가하거나, 기존 열의 데이터 유형을 변경하는 데 사용된다.
> 3. **DROP**: 데이터베이스 객체를 삭제한다. 예를 들어, 테이블이나 뷰를 삭제할 때 사용된다. 
> 4. **TRUNCATE**: 테이블의 모든 데이터를 삭제한다.  DROP과 유사하지만, 테이블 구조는 유지된다. 
> 5. **COMMENT**: 데이터베이스 객체에 대한 주석을 추가한다. 

- 앞서 member table을 생성한 DDL문을, 프로젝트에서 관리해보자.
	- `프로젝트명/sql` 경로 하위에 `ddl.sql`이라 파일을 생성한 후, DDL문을 저장하면 된다.
![[ddl.sql 최초저장.png]]