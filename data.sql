-- Customers 초기 데이터
INSERT INTO customers (name, email, password, nickname)
VALUES 
  ('김성연', 'sykim@test.com', 'tjddusdldi', '성연'),
  ('홍길동', 'hong@test.com', '1234!!!', '길동씨');

-- Stores 초기 데이터 (logo_uri 추가)
INSERT INTO stores (name, description, address, latitude, longitude, phone, logo_uri)
VALUES
  ('이가네양꼬치', '양꼬치&고량주 전문점', '경기도 성남시 분당구 분당내곡로 155', 37.3976, 127.1124, '031-8017-9688', '/images/stores/store1.jpg');

INSERT INTO stores (name, description, address, latitude, longitude, phone)
VALUES
  ('족발의달인', '야식하기 좋은 족발 전문점', '서울 마포구 합정동 456', 37.5500, 126.9136, '02-765-4321');

-- Menus 초기 데이터 (image_uri 추가)
INSERT INTO menus (name, description, price, store_id, image_uri)
VALUES
  ('양등심꼬치', '이가네양꼬치 대표메뉴!', 23000, 1, '/images/menus/menu1.jpg'),
  ('양왕갈비', '한입가득 양갈비가 사르륵', 26000, 1, '/images/menus/menu2.jpeg'),
  ('양왕꼬치', '안먹어본 사람은 있어도 한번만 먹은 사람은 없다!', 18000, 1, '/images/menus/menu3.jpeg'),
  ('가지요리', '가지가지 하시네요.. 저 가지실래요?', 20000, 1, '/images/menus/menu4.jpeg'),
  ('토마토계란볶음', '몸에 조코 맛도 조은 토계볶', 17000, 1, '/images/menus/menu5.jpeg'),
  ('탕수육', '슈ㅠ슉 탕슈ㅠ슉, 이거슨 입에서 나는 소리가 아니여', 21000, 1, '/images/menus/menu6.jpeg'),
  ('마파두부', '마음이아파..마파..마파두부', 16000, 1, '/images/menus/menu7.jpeg');

-- Menus 초기 데이터 (족발의달인)
INSERT INTO menus (name, description, price, store_id)
VALUES
  ('족발 소', '소 사이즈 족발', 25000, 2),
  ('족발 대', '대 사이즈 족발', 35000, 2);

-- ✅ 주문 (홍길동이 이가네양꼬치에서 주문, 김영희가 족발의달인에서 주문)
INSERT INTO orders (customer_id, store_id, total_price, ordered_at)
VALUES 
  (1, 1, 67000, now()),   -- 홍길동, 이가네양꼬치
  (2, 2, 60000, now());   -- 김영희, 족발의달인

-- ✅ 주문 상세 데이터
INSERT INTO order_items (order_id, menu_id, quantity, price)
VALUES 
  (1, 1, 1, 23000),  -- 홍길동: 양등심꼬치 1개
  (1, 2, 1, 26000),  -- 홍길동: 양왕갈비 1개
  (1, 6, 1, 21000),  -- 홍길동: 탕수 1개

  (2, 8, 1, 25000),  -- 김영희: 족발 소 1개
  (2, 9, 1, 35000);  -- 김영희: 족발 대 1개

INSERT INTO orders (customer_id, store_id, total_price, ordered_at)
VALUES
  (1, 2, 25000, now());

INSERT INTO order_items (order_id, menu_id, quantity, price)
VALUES
  (3, 8, 1, 25000);

-- ✅ 수정된 리뷰 데이터 삽입 (order_item_id 기준)
INSERT INTO reviews (customer_id, order_item_id, rating, comment, created_at)
VALUES 
  (1, 1, 5, '양등심꼬치 정말 맛있어요!', now()),
  (1, 2, 4, '양왕갈비는 조금 질겼지만 맛있음', now()),
  (1, 3, 5, '탕수육 최고!', now()),

  (2, 4, 5, '족발 소 사이즈, 부드럽고 맛있습니다.', now()),
  (2, 5, 4, '족발 대 사이즈는 괜찮았지만 조금 짰음', now());

-- 예시: 홍길동이 이가네양꼬치에서 스탬프 10개 채워 쿠폰 발급
INSERT INTO stamps (customer_id, store_id, count, updated_at)
VALUES (1, 1, 0, now());

INSERT INTO coupons (customer_id, store_id, used, issued_at)
VALUES (1, 1, false, now());

-- 예시: 홍길동이 족발의달인에서 스탬프 2개만 모은 상태
INSERT INTO stamps (customer_id, store_id, count, updated_at)
VALUES (1, 2, 2, now());

-- 예시: 김영희는 족발의달인에서 스탬프 5개만 모은 상태
INSERT INTO stamps (customer_id, store_id, count, updated_at)
VALUES (2, 2, 5, now());