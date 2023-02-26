create table DSA_Player
(
    P_ID   INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME   VARCHAR(20),
    PRIMARY KEY (P_ID)
);

create table DSA_Gametype
(
    GT_ID INTEGER,
    PRIMARY KEY(GT_ID)
);
create table DSA_Game
(
    G_ID           INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    CURRENT_PLAYER INTEGER,
    GAME_TYPE      INTEGER,
    PRIMARY KEY(G_ID),
    FOREIGN KEY (CURRENT_PLAYER) REFERENCES DSA_Player(P_ID),
    FOREIGN KEY (GAME_TYPE) REFERENCES DSA_Gametype(GT_ID)
);

create table DSA_GamePosition(
    P_ID INTEGER,
    G_ID INTEGER,
    Points INTEGER,
    PRIMARY KEY(G_ID, P_ID)
);
