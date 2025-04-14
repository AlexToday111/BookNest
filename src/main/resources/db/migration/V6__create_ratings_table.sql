create table ratings (
    id bigserial primary key,
    user_id bigint not null references users(id),
    book_id bigint not null references books(id),
    score integer not null check (score between 1 and 5),
    created_at timestamp not null default now(),
    constraint uk_ratings_user_book unique (user_id, book_id)
);
