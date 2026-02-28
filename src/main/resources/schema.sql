drop table if exists rooms;

create table rooms (
    id integer primary key,
    name text not null
);


drop table if exists players;

create table players (
     id integer primary key,
     email text not null unique,
     username text not null unique,
     password text not null,
     full_name text,
     image blob,
     room integer default 1 not null,
     foreign key (room) references rooms(id)
);


drop table if exists games;

create table games (
    id integer primary key,
    white text default '',
    white_id integer default 0,
    black text default '',
    black_id integer default 0,
    status text default 'OPEN',
    pgn text default '',
    fen text default 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1',
    result text default '',
    foreign key (white) references players(id),
    foreign key (black) references players(id),
    check (
        status in ('OPEN', 'INPROGRESS', 'FINISHED')
        and result in ('', 'WHITE WON', 'BLACK WON', 'DRAW')
    )
);
