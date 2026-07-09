package com.example.librarymanagementbackend.dashboard;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.librarymanagementbackend.dto.DashboardStatsDTO;
import com.example.librarymanagementbackend.service.BookService;

/**
 * New endpoint (did not exist in the original backend) required by the
 * React Dashboard page: statistics cards + recent books.
 */
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final BookService bookService;

    public DashboardController(BookService bookService) {
        this.bookService = bookService;
    }

    // GET /api/dashboard/stats
    @GetMapping("/stats")
    public DashboardStatsDTO getStats() {
        return bookService.getDashboardStats();
    }
}
