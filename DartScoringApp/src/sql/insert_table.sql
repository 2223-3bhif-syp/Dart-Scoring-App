INSERT INTO DSA_Player (NAME)
VALUES ('Franz'),
       ('Gustav'),
       ('Tim'),
       ('Robert');

INSERT INTO DSA_GameTyp (POINTS)
VALUES (301),
       (501);

INSERT INTO DSA_Game (CURRENT_PLAYER, GAME_TYPE_ID)
VALUES (1, 501),
       (3, 301);

INSERT INTO DSA_GameParticipation (P_ID, G_ID, POINTS)
VALUES (1, 1, 501),
       (2, 1, 501),
       (3, 2, 301),
       (4, 2, 301);