create index idx_books_title on books using btree (lower(title));
create index idx_books_author_id on books(author_id);
create index idx_books_genre_id on books(genre_id);
create index idx_reading_progress_user_id on reading_progress(user_id);
create index idx_ratings_book_id on ratings(book_id);
