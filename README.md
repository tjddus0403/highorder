# Highorder - 고객 주문 관리 시스템

## 📋 프로젝트 개요

Highorder는 고객의 주문, 리뷰, 스탬프 적립 및 쿠폰 발급을 관리하는 Spring Boot 기반의 웹 애플리케이션입니다.

## 🏗️ 프로젝트 구조

```
highorder/
├── src/main/java/alp/highorder/
│   ├── common/
│   │   └── config/
│   │       └── WebConfig.java          # 웹 설정
│   ├── customer/                       # 고객 관리
│   │   ├── api/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   └── service/
│   │   └── domain/
│   │       ├── entity/
│   │       └── repository/
│   ├── menu/                          # 메뉴 관리
│   ├── order/                         # 주문 관리
│   ├── review/                        # 리뷰 관리
│   ├── stamp/                         # 스탬프 적립
│   ├── store/                         # 가게 관리
│   └── HighorderApplication.java      # 메인 애플리케이션
├── src/main/resources/
│   ├── application.yml                # 설정 파일
│   ├── static/images/                 # 이미지 파일
│   │   ├── menus/                     # 메뉴 이미지
│   │   └── stores/                    # 가게 로고
│   └── templates/                     # 템플릿 파일
├── ddl.sql                           # 데이터베이스 스키마
├── data.sql                          # 초기 데이터
└── build.gradle                      # Gradle 빌드 설정
```

## 🗄️ 데이터베이스 구조

### 1. Customers (고객)
- **id**: 고객 고유 식별자 (BIGSERIAL)
- **name**: 고객 이름 (VARCHAR(100)) - 주문 시 배송 정보로 활용
- **email**: 이메일 주소 (VARCHAR(255), UNIQUE) - 로그인 및 알림 발송용
- **password**: 비밀번호 (VARCHAR(255)) - 보안을 위해 암호화 저장
- **nickname**: 닉네임 (VARCHAR(50), UNIQUE) - 커뮤니티 활동 시 사용
- **created_at/updated_at**: 생성/수정 시간 - 감사 추적 및 데이터 관리

### 2. Stores (가게)
- **id**: 가게 고유 식별자 (BIGSERIAL)
- **name**: 가게명 (VARCHAR(200)) - 검색 및 표시용
- **description**: 가게 설명 (TEXT) - 상세 정보 제공
- **address**: 주소 (VARCHAR(500)) - 배송 및 위치 정보
- **latitude/longitude**: 위도/경도 (DECIMAL) - 지도 표시 및 거리 계산
- **phone**: 전화번호 (VARCHAR(20)) - 연락처 정보
- **logo_uri**: 로고 이미지 경로 (VARCHAR(500)) - 브랜딩 및 UI 표시

### 3. Menus (메뉴)
- **id**: 메뉴 고유 식별자 (BIGSERIAL)
- **name**: 메뉴명 (VARCHAR(200)) - 주문 시 선택용
- **description**: 메뉴 설명 (TEXT) - 상세 정보 제공
- **price**: 가격 (INT) - 주문 금액 계산용
- **store_id**: 가게 ID (BIGINT, FK) - 메뉴 소속 가게
- **image_uri**: 메뉴 이미지 경로 (VARCHAR(500)) - 시각적 선택 도움
- **avg_rating**: 평균 평점 (DECIMAL(3,2)) - 고객 선택 기준
- **review_count**: 리뷰 수 (INT) - 인기도 지표

### 4. Orders (주문)
- **id**: 주문 고유 식별자 (BIGSERIAL)
- **customer_id**: 고객 ID (BIGINT, FK) - 주문자 정보
- **store_id**: 가게 ID (BIGINT, FK) - 주문 대상 가게
- **total_price**: 총 주문 금액 (INT) - 결제 금액
- **ordered_at**: 주문 시간 (TIMESTAMP) - 주문 이력 관리

### 5. OrderItems (주문 상세)
- **id**: 주문 상세 고유 식별자 (BIGSERIAL)
- **order_id**: 주문 ID (BIGINT, FK) - 상위 주문과 연결
- **menu_id**: 메뉴 ID (BIGINT, FK) - 주문된 메뉴
- **quantity**: 수량 (INT) - 주문 수량
- **price**: 가격 (INT) - 메뉴 단가 × 수량

### 6. Reviews (리뷰)
- **id**: 리뷰 고유 식별자 (BIGSERIAL)
- **customer_id**: 고객 ID (BIGINT, FK) - 리뷰 작성자
- **order_item_id**: 주문 상세 ID (BIGINT, FK) - 리뷰 대상 주문
- **rating**: 평점 (INT, 1-5) - 메뉴 품질 평가
- **comment**: 리뷰 내용 (TEXT) - 상세 의견

### 7. Stamps (스탬프)
- **id**: 스탬프 고유 식별자 (BIGSERIAL)
- **customer_id**: 고객 ID (BIGINT, FK) - 스탬프 소유자
- **store_id**: 가게 ID (BIGINT, FK) - 스탬프 적립 가게
- **count**: 스탬프 개수 (INT) - 적립 현황
- **UNIQUE(customer_id, store_id)**: 고객당 가게별 하나의 스탬프 카드만 보유

### 8. Coupons (쿠폰)
- **id**: 쿠폰 고유 식별자 (BIGSERIAL)
- **customer_id**: 고객 ID (BIGINT, FK) - 쿠폰 소유자
- **store_id**: 가게 ID (BIGINT, FK) - 쿠폰 사용 가능 가게
- **used**: 사용 여부 (BOOLEAN) - 쿠폰 상태 관리
- **issued_at**: 발급 시간 (TIMESTAMP) - 유효기간 관리

## 🚀 실행 방법

### 1. 사전 요구사항
- Java 17 이상
- Gradle 7.x 이상
- PostgreSQL 12 이상 또는 H2 Database

### 2. 데이터베이스 설정
#### PostgreSQL 사용 시
```bash
# PostgreSQL 설치 및 실행
# 데이터베이스 생성
createdb pg_sample

# application.yml에서 PostgreSQL 설정 활성화
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/pg_sample
    username: postgres
    password: postgres
```

#### H2 Database 사용 시 (개발용)
```bash
# application.yml에서 H2 설정 활성화
spring:
  datasource:
    url: jdbc:h2:mem:testdb;MODE=MySQL;DB_CLOSE_DELAY=-1
    username: sa
    password:
    driver-class-name: org.h2.Driver
```

### 3. 애플리케이션 실행
```bash
# 프로젝트 루트 디렉토리에서
./gradlew bootRun

# 또는 빌드 후 실행
./gradlew build
java -jar build/libs/highorder-0.0.1-SNAPSHOT.jar
```

### 4. 데이터베이스 초기화
```bash
# PostgreSQL 접속
psql -U postgres -d pg_sample

# DDL 실행
\i ddl.sql

# 초기 데이터 삽입
\i data.sql
```

## 🛠️ 기술 스택

- **Backend**: Spring Boot 3.5.4, Java 17
- **Database**: PostgreSQL, H2 (개발용)
- **ORM**: Spring Data JPA, QueryDSL
- **Build Tool**: Gradle
- **Monitoring**: P6Spy (SQL 로깅)
- **Architecture**: Layered Architecture (Controller-Service-Repository)

## 📱 주요 기능

1. **고객 관리**: 회원가입, 로그인, 프로필 관리
2. **가게 관리**: 가게 정보 등록, 위치 기반 검색
3. **메뉴 관리**: 메뉴 등록, 이미지 관리, 가격 설정
4. **주문 관리**: 장바구니, 주문 처리, 주문 이력
5. **리뷰 시스템**: 메뉴별 평점 및 리뷰
6. **스탬프 적립**: 주문 시 스탬프 적립
7. **쿠폰 발급**: 스탬프 적립 완료 시 쿠폰 발급

## 🔧 개발 환경 설정

### IDE 설정
- IntelliJ IDEA 또는 Eclipse STS 권장
- Lombok 플러그인 설치 필요
- QueryDSL 코드 생성 설정

### 로깅 설정
```yaml
logging:
  level:
    org.hibernate.SQL: debug
    p6spy: debug
```

## 📝 API 문서

각 도메인별로 REST API가 구현되어 있습니다:
- `CustomerController`: 고객 관련 API
- `StoreController`: 가게 관련 API
- `MenuController`: 메뉴 관련 API
- `OrderController`: 주문 관련 API
- `ReviewController`: 리뷰 관련 API
- `StampCouponController`: 스탬프/쿠폰 관련 API


## 📁 이미지 파일 관리

- **메뉴 이미지**: `src/main/resources/static/images/menus/`
- **가게 로고**: `src/main/resources/static/images/stores/`
- 이미지 파일은 URI 경로로 데이터베이스에 저장

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.
