package com.example.librarymanagementbackend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.librarymanagementbackend.dto.DashboardStatsDTO;
import com.example.librarymanagementbackend.dto.PagedResponse;
import com.example.librarymanagementbackend.entity.Book;
import com.example.librarymanagementbackend.exception.ResourceNotFoundException;
import com.example.librarymanagementbackend.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // GET all books (unpaginated - kept for backward compatibility)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // GET a single book, or throw 404
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    // POST add a book
    public Book addBook(Book book) {
        book.setId(null); // ensure a new row is always created
        return bookRepository.save(book);
    }

    // PUT update a book - FIX: original implementation never updated category/available
    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setCategory(updatedBook.getCategory());
        existingBook.setAvailable(updatedBook.getAvailable());
        return bookRepository.save(existingBook);
    }

    // DELETE a book
    public void deleteBook(Long id) {
        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);
    }

    // Search by title or author (simple, unpaginated - used by quick search bar)
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);
    }

    // Filter by category
    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByCategoryIgnoreCase(category);
    }

    // Distinct categories for filter dropdown
    public List<String> getAllCategories() {
        return bookRepository.findDistinctCategories();
    }

    // Paginated + sorted + optionally searched/filtered listing for the View Books page
    public PagedResponse<Book> getPagedBooks(int page, int size, String sortBy, String sortDir,
                                              String keyword, String category) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Book> result;
        if (keyword != null && !keyword.isBlank()) {
            result = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword, pageable);
        } else if (category != null && !category.isBlank() && !category.equalsIgnoreCase("all")) {
            result = bookRepository.findByCategoryIgnoreCase(category, pageable);
        } else {
            result = bookRepository.findAll(pageable);
        }

        return new PagedResponse<>(
                result.getContent(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isLast()
        );
    }

    // Aggregated stats for the Dashboard page
    public DashboardStatsDTO getDashboardStats() {
        long total = bookRepository.count();
        long available = bookRepository.countByAvailable(true);
        long issued = bookRepository.countByAvailable(false);
        long categories = bookRepository.findDistinctCategories().size();
        List<Book> recent = bookRepository
                .findAll(PageRequest.of(0, 5, Sort.by("id").descending()))
                .getContent();
        return new DashboardStatsDTO(total, available, issued, categories, recent);
    }
}
