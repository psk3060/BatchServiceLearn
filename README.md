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
	<li>휴면계정에 관련된 기능은 모두 JobExecutionListener를 구현한 클래스에서 처리</li>
	<li>휴면계정 안내, 전환 모두 별도의 Job에서 진행</li>
</ol>

### 4. 참조 사이트

<ul>
	<li>배치 학습 스프링IO : https://spring.io/guides/gs/scheduling-tasks</li>
	<li>배치 학습 GITHUB : https://github.com/spring-guides/gs-scheduling-tasks</li>
	<li>배치 생성 스프링IO : https://spring.io/guides/gs/batch-processing</li>
	<li>배치 생성 GITHUB : https://github.com/spring-guides/gs-batch-processing</li>	
	<li>REDIS 메시지 전달 스프링IO : https://spring.io/guides/gs/messaging-redis</li>	
	<li>REDIS 메시지 전달 GITHUB : https://github.com/spring-guides/gs-messaging-redis</li>
	<li>(스프링 Data 사용) REDIS, Lettuce 연동 스프링IO : https://spring.io/guides/gs/spring-data-reactive-redis</li>
	<li>(스프링 Data 사용) REDIS, Lettuce 연동 GITHUB : https://github.com/spring-guides/gs-spring-data-reactive-redis</li>	
	<li>JMS 메시지 전달 스프링IO : https://spring.io/guides/gs/messaging-jms</li>
	<li>JMS 메시지 전달 GITHUB : https://github.com/spring-guides/gs-messaging-jms</li>	
	<li>RABBITMQ 메시지 전달 스프링IO : https://spring.io/guides/gs/messaging-rabbitmq</li>	
</ul>

### 5. 학습 중 정리사항

<ul>
	<li>spring-test-starter 포함 시 반드시 DB 드라이버 의존성(임베디드 데이터베이스 또는 기타 DBMS) 추가하고, DB 접속 정보 추가해 둘것</li>
	<li>awaitility : 폴링 간격 조절이나 비동기 테스트 시 이용할 수 있는 라이브러리</li>
	<li>Lettuce : redis 클라이언트 api(스프링부트 2.0부터 기본). 발행자-구독자 패턴 이용. https://lettuce.io/core/release/reference/index.html</li>
	<li>JmsListener 참조 : https://docs.spring.io/spring-framework/reference/integration/jms/annotated.html#jms-annotated-method-signature</li>
	<li>ActiveMQ : JMS 구현한 메시지 브로커(메시지 기반 통신)</li>
	<li>ActiveMQ.Connection : 클라이언트와 JMS 기반의 메시지 브로커간의 연결. Connection에서 Session 생성</li>
	<li>ActiveMQ.Session : 메시지를 생성, 송수신, 커밋 또는 롤백. Connection 내에서 여러 개의 Session 생성 가능. 메시지 송수신에 대한 트랜잭션, 메시지의 송신과 수신에 사용되는 Producer와 Consumer의 생성 및 관리 담당</li>
	<li>ActiveMQ.Producer : 메시지브로커로 메시지를 송신. Session 내에서 생성되며, 특정 대상(destination, Queue 또는 Topic)에 메시지 전달. </li>
	<li>메시지 송신 : ConnectionFactory 생성 => Connection 생성 => Session 생성 => Destination 설정(@JmsListener, 메시지를 보낼 대상) => Message 생성 => MessageProducer 생성 => 메시지 전송</li>
	<li>ActiveMQ.Consumer : 메시지브로커에서 메시지를 수신. Session 내에서 생성되며, 메시지를 동기 또는 비동기적으로 수신. </li>
	<li>메시지 수신 : ConnectionFactory 생성 => Connection 생성 => Session 생성 => Destination 설정(@JmsListener, 메시지를 받아올 대상) => Message 생성 => MessageConsumer 생성 => 메시지 수신(MessageConsumer의 receive(), JmsTemplate.receive())</li>
</ul>

### 6. 테스트

<ul>
	<li>mvnw clean test -Dtest="BatchServiceLearnApplicationTests"</li>
	<li>mvnw clean test -Dtest="ReceiverTaskTest"</li>
	<li>mvnw clean test -Dtest="JmsReceiverTaskTest"</li>
</ul>

