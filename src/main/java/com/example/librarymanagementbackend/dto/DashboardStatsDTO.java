package com.example.librarymanagementbackend.dto;

import java.util.List;

import com.example.librarymanagementbackend.entity.Book;

/**
 * Aggregated data for the Dashboard page's statistics cards + recent books list.
 */
public class DashboardStatsDTO {
    private long totalBooks;
    private long availableBooks;
    private long issuedBooks;
    private long totalCategories;
    private List<Book> recentBooks;

    public DashboardStatsDTO(long totalBooks, long availableBooks, long issuedBooks,
                              long totalCategories, List<Book> recentBooks) {
        this.totalBooks = totalBooks;
        this.availableBooks = availableBooks;
        this.issuedBooks = issuedBooks;
        this.totalCategories = totalCategories;
        this.recentBooks = recentBooks;
    }

    public long getTotalBooks() { return totalBooks; }
    public long getAvailableBooks() { return availableBooks; }
    public long getIssuedBooks() { return issuedBooks; }
    public long getTotalCategories() { return totalCategories; }
    public List<Book> getRecentBooks() { return recentBooks; }
}
