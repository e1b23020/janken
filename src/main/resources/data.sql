INSERT INTO users(id, userName) VALUES (1, 'CPU');
INSERT INTO users(id, userName) VALUES (2, 'ほんだ');
INSERT INTO matches(id, user1, user2, user1Hand, user2Hand) VALUES (1,2,1,'Gu','Choki');
INSERT INTO matches(id, user1, user2, user1Hand, user2Hand) VALUES (2,2,1,'Gu','Gu');
INSERT INTO matches(id, user1, user2, user1Hand, user2Hand) VALUES (3,2,1,'Gu','Pa');
ALTER TABLE matches ALTER COLUMN id RESTART WITH 4;
