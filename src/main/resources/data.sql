INSERT INTO GAMES (ID, NAME, ABBREVIATION) VALUES (1, 'CARDFIGHT VANGUARD', 'CFV');
INSERT INTO GAMES (ID, NAME, ABBREVIATION) VALUES (2, 'MAGIC THE GATHERING', 'MTG');
INSERT INTO EXPANSION (ID, NAME, ABBREVIATION, RELEASE_DATE, RELEASED, GAME_ID) VALUES (1, 'D Booster Set 08: Minerva Rising', 'DBT08', {ts '2023-02-23 00.00.00'}, FALSE, 1);
INSERT INTO EXPANSION (ID, NAME, ABBREVIATION, RELEASE_DATE, RELEASED, GAME_ID) VALUES (2, 'La Guerra de los Hermanos', 'BROTH', {ts '2022-11-15 00.00.00'}, TRUE, 2);
INSERT INTO PRODUCT (ID, NAME, RARITY, QUANTITY, IMAGE) VALUES (1, 'Peak Personage Stealth Rogue, Shojodoji', 'RRR', 10, null);
INSERT INTO PRODUCT (ID, NAME, RARITY, QUANTITY, IMAGE) VALUES (2, 'Stealth Dragon, Unpreceden', 'RRR', 17, null);
INSERT INTO PRODUCT (ID, NAME, RARITY, QUANTITY, IMAGE) VALUES (3, 'Silver Thorn Dragon Tamer, Luquier', 'RRR', 15, null);
INSERT INTO PRODUCT_EXPANSION(PRODUCT_ID, EXPANSION_ID) VALUES (1, 1);
INSERT INTO PRODUCT_EXPANSION(PRODUCT_ID, EXPANSION_ID) VALUES (2, 2);
INSERT INTO SALES (ID, SALE_DATE, SALE_PRICE) VALUES (1, {ts '2022-11-15 00.00.00'}, 200);
INSERT INTO SALE_DETAILS (ID, PRODUCT_ID, EXPANSION_ID, SALE_ID, QUANTITY, UNITARY_PRICE) VALUES (1, 1, 1, 1, 4, 50);

