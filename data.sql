-- Customers 초기 데이터
INSERT INTO customers (name, email, password, nickname)
VALUES 
  ('홍길동', 'hong@test.com', '1234', '길동이'),
  ('김영희', 'kim@test.com', 'abcd', '영희짱');

-- Stores 초기 데이터
INSERT INTO stores (name, description, address, latitude, longitude, phone)
VALUES
  ('김밥천국', '저렴하고 맛있는 분식집', '서울 강남구 테헤란로 123', 37.5010, 127.0396, '02-123-4567'),
  ('족발의달인', '야식하기 좋은 족발 전문점', '서울 마포구 합정동 456', 37.5500, 126.9136, '02-765-4321');

-- Menus 초기 데이터 (김밥천국)
INSERT INTO menus (name, description, price, store_id)
VALUES
  ('김밥', '기본 김밥', 3000, 1),
  ('라면', '얼큰한 라면', 4000, 1);

-- Menus 초기 데이터 (족발의달인)
INSERT INTO menus (name, description, price, store_id)
VALUES
  ('족발 소', '소 사이즈 족발', 25000, 2),
  ('족발 대', '대 사이즈 족발', 35000, 2);

-- 주문 (홍길동이 김밥천국에서 주문)
INSERT INTO orders (customer_id, store_id, total_price)
VALUES (1, 1, 7000);

-- 주문 상세 (김밥 1개, 라면 1개)
INSERT INTO order_items (order_id, menu_id, quantity, price)
VALUES (1, 1, 1, 3000), (1, 2, 1, 4000);

-- 주문 데이터 (예시)
INSERT INTO orders (customer_id, store_id, total_price, ordered_at)
VALUES (1, 2, 85000, now()),   -- 홍길동, 족발의달인
       (2, 1, 7000, now());    -- 김영희, 김밥천국

-- 주문 상세 데이터 (예시)
INSERT INTO order_items (order_id, menu_id, quantity, price)
VALUES (1, 3, 2, 50000),  -- 족발 소 2개
       (1, 4, 1, 35000),  -- 족발 대 1개
       (2, 1, 1, 3000),   -- 김밥 1개
       (2, 2, 1, 4000);   -- 라면 1개

-- 리뷰 데이터 (예시)
INSERT INTO reviews (customer_id, order_id, menu_id, rating, comment)
VALUES (1, 1, 3, 5, '족발 소 사이즈, 정말 맛있어요!'),
       (1, 1, 4, 4, '족발 대 사이즈도 괜찮았지만 조금 짰음'),
       (2, 2, 1, 5, '김밥이 신선하고 맛있습니다'),
       (2, 2, 2, 4, '라면 국물이 시원하네요');
