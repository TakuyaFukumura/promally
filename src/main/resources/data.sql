DELETE FROM message;
INSERT INTO message (text) VALUES ('Hello World!');

DELETE FROM property;
INSERT INTO property (name, address, rent, area, building_age, layout, status, description, created_at, updated_at) VALUES 
('サンプル戸建A', '東京都世田谷区桜丘1-1-1', 150000, 85.50, 15, '3LDK', 'CONSIDERING', '駅から徒歩10分の閑静な住宅街にある戸建て物件です。', NOW(), NOW()),
('中古戸建B', '神奈川県横浜市港北区新横浜2-2-2', 120000, 75.25, 20, '2LDK', 'OWNED', '新横浜駅近くの利便性の良い立地です。リフォーム済み。', NOW(), NOW()),
('投資物件C', '東京都品川区大井町3-3-3', 180000, 95.80, 10, '4LDK', 'CONSIDERING', '大井町駅徒歩5分。ファミリー向けの広い間取り。', NOW(), NOW());
