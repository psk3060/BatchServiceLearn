### 1. 목적
<code>
	스프링 배치 학습용
</code>

### 2. 연동
<ul>
	<li>Spring Batch</li>
	<li>MariaDB</li>
	<li>MyBatis</li>
	<li>Redis, JMS, RabbitMQ : 별도의 URL로 메시지 전달할 때 사용(의존성 주입으로 수행 결정, 일단 모두 구현)</li>
</ul>

### 3. 기능

<ol>
	<li>스프링IO 스케줄러 가이드 직접 확인</li>
	<li>휴면계정 전환 : 별도의 RESTful API 호출(휴면 계정으로 전환하고, 별도의 리턴값 전달)</li>
	<li>휴면계정 안내 : 안내용 메시지, 카카오톡, 이메일 보관은 배치 자체에서 처리(발송 내역 DB 보관)</li>
</ol>

### 4. 참조 사이트

<ul>
	<li>배치 학습 스프링IO : https://spring.io/guides/gs/scheduling-tasks</li>
	<li>배치 학습 GITHUB : https://github.com/spring-guides/gs-scheduling-tasks</li>
	<li>배치 생성 스프링IO : https://spring.io/guides/gs/batch-processing</li>
	<li>배치 생성 GITHUB : https://github.com/spring-guides/gs-batch-processing</li>	
	<li>REDIS 메시지 전달 스프링IO : https://spring.io/guides/gs/messaging-redis</li>	
	<li>JMS 메시지 전달 스프링IO : https://spring.io/guides/gs/messaging-jms</li>
	<li>RABBITMQ 메시지 전달 스프링IO : https://spring.io/guides/gs/messaging-rabbitmq</li>	
</ul>

### 5. 학습 중 정리사항

<ul>
	<li>spring-test-starter 포함 시 반드시 DB 드라이버 의존성(임베디드 데이터베이스 또는 기타 DBMS) 추가하고, DB 접속 정보 추가해 둘것</li>
	<li>awaitility : 폴링 간격 조절이나 비동기 테스트 시</li>
</ul>

### 6. 테스트


