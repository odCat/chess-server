insert into rooms (name)
values ('Main');

insert into players (email, username, password)
values ('gigel@test.net', 'gigel', 'gigel');

insert into players (email, username, password)
values ('andrei@test.net', 'andrei', 'andrei');

insert into players (email, username, password)
values ('dorel@test.net', 'dorel', 'dorel');

insert into games (white, black, status, pgn, fen)
values (
        'Alehin',
        'Casablanca',
        'INPROGRESS',
        '[Event "?"] [Site "?"] [Date "????.??.??"] [Round "?"] [White "?"] [Black "?"] [Result "*"] 1. d4 d5 2. c4 e6 3. Nc3 Nf6 4. Bg5 Nbd7 5. e3 c6 6. a3 Be7 7. Nf3 O-O 8. Bd3 dxc4 9. Bxc4 Nd5 10. Bxe7 Qxe7 11. Ne4 N5f6 12. Ng3 c5 13. O-O Nb6 14. Ba2 cxd4 15. Nxd4 g6 16. Rc1 Bd7 17. Qe2 Rac8 18. e4 e5 19. Nf3 Kg7 20. h3 h6 21. Qd2 *',
        '2r2r2/pp1bqpk1/1n3npp/4p3/4P3/P4NNP/BP1Q1PP1/2R2RK1 b - - 1 21'
       )
;
