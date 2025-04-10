create table users (
    id bigserial primary key,
    username varchar(80) not null unique,
    email varchar(255) not null unique,
    created_at timestamp not null default now()
);
