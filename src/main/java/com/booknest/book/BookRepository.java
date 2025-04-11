package com.booknest.book;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genre"})
    @Query("""
            select b from Book b
            where (:title is null or lower(b.title) like lower(concat('%', :title, '%')))
              and (:author is null or lower(b.author.name) like lower(concat('%', :author, '%')))
              and (:genre is null or lower(b.genre.name) like lower(concat('%', :genre, '%')))
            """)
    Page<Book> search(
            @Param("title") String title,
            @Param("author") String author,
            @Param("genre") String genre,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findTop10ByGenreIdAndIdNotInOrderByCreatedAtDesc(Long genreId, List<Long> excludedIds);

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findTop10ByAuthorIdAndIdNotInOrderByCreatedAtDesc(Long authorId, List<Long> excludedIds);

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findTop10ByOrderByCreatedAtDesc();

    @EntityGraph(attributePaths = {"author", "genre"})
    @Query("""
            select b from Book b
            left join Rating r on r.book = b
            group by b
            order by coalesce(avg(r.score), 0) desc, b.createdAt desc
            """)
    List<Book> findTopRated(Pageable pageable);
}
