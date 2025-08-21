-- jpa 스키마 생성
CREATE SCHEMA IF NOT EXISTS jpa;

-- jpa 스키마에 대한 권한 설정
GRANT ALL ON SCHEMA jpa TO postgres;
GRANT USAGE ON SCHEMA jpa TO postgres;
