package com.example.librarymanagementbackend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.librarymanagementbackend.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Used for paginated + sorted listing
    Page<Book> findAll(Pageable pageable);

    // Case-insensitive search across title or author
    Page<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
            String title, String author, Pageable pageable);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
            String title, String author);

    // Filter by category (case-insensitive)
    List<Book> findByCategoryIgnoreCase(String category);

    Page<Book> findByCategoryIgnoreCase(String category, Pageable pageable);

    // Distinct list of categories for filter dropdown
    @Query("SELECT DISTINCT b.category FROM Book b ORDER BY b.category ASC")
    List<String> findDistinctCategories();

    long countByAvailable(boolean available);
}
