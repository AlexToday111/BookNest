create table books (
    id bigserial primary key,
    title varchar(255) not null,
    description text,
    author_id bigint not null references authors(id),
    genre_id bigint not null references genres(id),
    page_count integer not null check (page_count > 0),
    published_year integer check (published_year >= 0),
    created_at timestamp not null default now()
);
