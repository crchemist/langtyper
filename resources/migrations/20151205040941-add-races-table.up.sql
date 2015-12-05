CREATE TABLE races
(id VARCHAR(20) PRIMARY KEY,
 /* NEW=0, IN-PROGRESS=1, FINISHED=2 */
 state INTEGER);

CREATE TABLE users_races
(user_id VARCHAR(20),
 race_id VARCHAR(20),

 start_time TIME,
 end_time TIME,
 
 time INTEGER,
 errors INTEGER,
 passed BOOLEAN);
