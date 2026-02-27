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

insert into games (white, black, status, pgn, fen)
values (
           'Alehin',
           'Euwe',
           'INPROGRESS',
           '[Event "?"] [Site "?"] [Date "????.??.??"] [Round "?"] [White "?"] [Black "?"] [Result "*"] 1. d4 d5 2. c4 c6 3. Nf3 Nf6 4. Nc3 dxc4 5. a4 Bf5 6. Ne5 Nbd7 7. Nxc4 Qc7 8. g3 e5 9. dxe5 Nxe5 10. Bf4 Nfd7 11. Bg2 Be6 12. Nxe5 Nxe5 13. O-O Be7 14. Qc2 Rd8 15. Rfd1 O-O 16. Nb5 *',
           '3r1rk1/ppq1bppp/2p1b3/1N2n3/P4B2/6P1/1PQ1PPBP/R2R2K1 b - - 7 16'
       )
;


insert into games (white, black, status, pgn, fen)
values (
           'Bogoliubov',
           'Alehin',
           'INPROGRESS',
           '[Event "?"] [Site "?"] [Date "????.??.??"] [Round "?"] [White "?"] [Black "?"] [Result "*"] 1. d4 d5 2. c4 dxc4 3. Nf3 Nf6 4. Nc3 a6 5. e4 b5 6. e5 Nd5 7. Ng5 e6 8. Qf3 Qd7 9. Nxd5 exd5 10. a3 Nc6 11. Be3 Nd8 12. Be2 Qf5 13. Qg3 h6 14. Nh3 c6 15. f4 Qc2 16. Qf2 *',
           'r1bnkb1r/5pp1/p1p4p/1p1pP3/2pP1P2/P3B2N/1Pq1BQPP/R3K2R b KQkq - 2 16'
       )
;

insert into games (white, black, status, pgn, fen)
values (
           'Yates',
           'Alehin',
           'INPROGRESS',
           '[Event "?"] [Site "?"] [Date "????.??.??"] [Round "?"] [White "?"] [Black "?"] [Result "*"] 1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 4. Ba4 d6 5. Nc3 Bd7 6. d3 g6 7. Nd5 b5 8. Bb3 Na5 9. Bg5 f6 10. Bd2 c6 11. Ne3 Nxb3 12. axb3 Nh6 13. b4 f5 14. Qe2 Nf7 15. Nf1 Qe7 16. Ng3 f4 17. Nf1 g5 18. Bc3 h5 19. N3d2 Bg4 20. f3 Be6 21. d4 Bg7 22. Qd3 exd4 23. Bxd4 Ne5 *',
           'r3k2r/4q1b1/p1ppb3/1p2n1pp/1P1BPp2/3Q1P2/1PPN2PP/R3KN1R w KQkq - 1 24'
       )
;

insert into games (white, black, status, pgn, fen)
values (
           'Alehin',
           'Nimzovici',
           'INPROGRESS',
           '[Event "?"] [Site "?"] [Date "????.??.??"] [Round "?"] [White "?"] [Black "?"] [Result "*"] 1. e4 e6 2. d4 d5 3. Nc3 Bb4 4. e5 c5 5. Bd2 Ne7 6. Nb5 Bxd2+ 7. Qxd2 O-O 8. c3 b6 9. f4 Ba6 10. Nf3 Qd7 11. a4 Nbc6 12. b4 cxb4 13. cxb4 Bb7 14. Nd6 f5 15. a5 Nc8 16. Nxb7 Qxb7 17. a6 Qf7 18. Bb5 N8e7 19. O-O h6 20. Rfc1 Rfc8 21. Rc2 Qe8 22. Rac1 Rab8 23. Qe3 Rc7 24. Rc3 Qd7 25. R1c2 Kf8 26. Qc1 Rbc8 *',
           '2r2k2/p1rqn1p1/Ppn1p2p/1B1pPp2/1P1P1P2/2R2N2/2R3PP/2Q3K1 w - - 14 27'
       )
;
