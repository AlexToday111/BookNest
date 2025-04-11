create table authors (
    id bigserial primary key,
    name varchar(160) not null unique,
    bio text
);
