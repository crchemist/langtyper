-- name: create-user!
-- creates a new user record
INSERT INTO users
(id, first_name, last_name, email, pass)
VALUES (:id, :first_name, :last_name, :email, :pass)

-- name: update-user!
-- update an existing user record
UPDATE users
SET first_name = :first_name, last_name = :last_name, email = :email
WHERE id = :id

-- name: get-user
-- retrieve a user given the id.
SELECT * FROM users
WHERE id = :id

-- name: delete-user!
-- delete a user given the id
DELETE FROM users
WHERE id = :id

-- name: get-user
-- retrieve a user given the id.
SELECT * FROM users
WHERE id = :id


-- name: get-new-race
SELECT * FROM races
WHERE state = 0 LIMIT 1

-- name: create-new-race!
INSERT INTO races
(id, state, track)
VALUES (:id, 0, :track)

-- name: join-user-race!
INSERT INTO users_races 
(user_id, race_id)
VALUES (:user_id, :race_id)

--name: leave-user-race!
DELETE FROM users_races
WHERE user_id = :user_id AND race_id = :race_id

--name: get-current-race
SELECT races.id, races.state, races.track FROM users_races
                                          JOIN races ON (users_races.race_id = races.id)
WHERE users_races.user_id = :user_id AND races.state = 1


--name: finish-user-race!
UPDATE users_races
SET passed = :passed
WHERE user_id = :user_id AND race_id = :race_id
