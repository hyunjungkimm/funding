--USER 데이터 생성
INSERT INTO MEMBER (MEMBER_ID,  MEMBER_NAME) VALUES (1, 'jung');
INSERT INTO MEMBER (MEMBER_ID, MEMBER_NAME) VALUES (2, 'hong');
INSERT INTO MEMBER (MEMBER_ID, MEMBER_NAME) VALUES (3, 'kim');

--PRODUCT 데이터 생성
INSERT INTO PRODUCT (PRODUCT_NAME, TARGET_FUNDING_PRICE, START_DATE, FINISH_DATE, FUNDING_STATUS, TOTAL_FUNDING_PRICE, FUNDING_MEMBER_NUMBER)
VALUES ('환절기 토탈케어 펀딩1', 500000, '2022-03-17', '2022-05-28', '펀딩완료', 250000, 0);
INSERT INTO PRODUCT (PRODUCT_NAME, TARGET_FUNDING_PRICE, START_DATE, FINISH_DATE, FUNDING_STATUS, TOTAL_FUNDING_PRICE, FUNDING_MEMBER_NUMBER)
VALUES ('환절기 토탈케어 펀딩2', 500000, '2024-03-17', '2025-05-28', '펀딩중', 250000, 0);
INSERT INTO PRODUCT (PRODUCT_NAME, TARGET_FUNDING_PRICE, START_DATE, FINISH_DATE, FUNDING_STATUS, TOTAL_FUNDING_PRICE, FUNDING_MEMBER_NUMBER)
VALUES ('환절기 토탈케어 펀딩3', 500000, '2024-03-17 00:00:00', '2024-05-28 23:59:59', '펀딩완료', 250000, 0);
INSERT INTO PRODUCT (PRODUCT_NAME, TARGET_FUNDING_PRICE, START_DATE, FINISH_DATE, FUNDING_STATUS, TOTAL_FUNDING_PRICE, FUNDING_MEMBER_NUMBER)
VALUES ('환절기 토탈케어 펀딩4', 500000, '2024-03-17 00:00:00', '2025-05-28 23:59:59', '펀딩중', 250000, 0);
INSERT INTO PRODUCT (PRODUCT_NAME, TARGET_FUNDING_PRICE, START_DATE, FINISH_DATE, FUNDING_STATUS, TOTAL_FUNDING_PRICE, FUNDING_MEMBER_NUMBER)
VALUES ('환절기 토탈케어 펀딩5', 500000, '2024-03-17 00:00:00', '2024-05-28 23:59:59', '펀딩완료', 250000, 0);
