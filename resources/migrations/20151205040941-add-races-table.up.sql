CREATE TABLE races
(id VARCHAR(20) PRIMARY KEY,
 state INTEGER);

CREATE TABLE users_races
(user_id VARCHAR(20),
 race_id VARCHAR(20),

 start_time TIME,
 
 time INTEGER,
 errors INTEGER,
 passed BOOLEAN);