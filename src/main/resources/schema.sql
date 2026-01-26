drop table if exists rooms;

create table rooms (
    id integer primary key,
    name text not null
);


drop table if exists players;

create table players (
     id integer primary key,
     email text not null unique,
     username text not null,
     full_name text,
     image blob,
     room integer,
     foreign key (room) references rooms(id)
);


drop table if exists games;

create table games (
    id integer unique,
    white integer,
    black integer,
    room integer not null,
    status text,
    moves text,
    result text,
    foreign key (white) references players(id),
    foreign key (black) references players(id),
    check (
        status in ('PENDING', 'INPROGRESS', 'FINISHED')
        and result in ('', 'WHITE WON', 'BLACK WON', 'DRAW')
    )
);
