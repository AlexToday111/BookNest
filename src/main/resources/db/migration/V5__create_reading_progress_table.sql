create table reading_progress (
    id bigserial primary key,
    user_id bigint not null references users(id),
    book_id bigint not null references books(id),
    status varchar(32) not null,
    current_page integer not null default 0 check (current_page >= 0),
    updated_at timestamp not null default now(),
    constraint uk_reading_progress_user_book unique (user_id, book_id)
);
