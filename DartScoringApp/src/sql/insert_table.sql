INSERT INTO Player (NAME, POINTS)
VALUES ('Franz', 100),
       ('Gustav', 200),
       ('Tim', 150);

-- Populate Game table
INSERT INTO Game (PLAYER_AMOUNT, CURRENT_PLAYER)
VALUES (3, 1),
       (2, 2),
       (4, 3);

-- Populate Gametype table
INSERT INTO Gametype (GAME_TYPE)
VALUES ('Action'),
       ('Strategy'),
       ('Sports');