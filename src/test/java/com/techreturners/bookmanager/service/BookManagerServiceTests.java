package com.techreturners.bookmanager.service;

import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.model.Genre;

import com.techreturners.bookmanager.repository.BookManagerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class BookManagerServiceTests {

    @Mock
    private BookManagerRepository mockBookManagerRepository;

    @InjectMocks
    private BookManagerServiceImpl bookManagerServiceImpl;

    @Test
    public void testGetAllBooksReturnsListOfBooks() {

        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book One", "This is the description for Book One", "Person One", Genre.Education));
        books.add(new Book(2L, "Book Two", "This is the description for Book Two", "Person Two", Genre.Education));
        books.add(new Book(3L, "Book Three", "This is the description for Book Three", "Person Three", Genre.Education));

        when(mockBookManagerRepository.findAll()).thenReturn(books);

        List<Book> actualResult = bookManagerServiceImpl.getAllBooks();

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(books);
    }

    @Test
    public void testAddABook() {

        var book = new Book(4L, "Book Four", "This is the description for Book Four", "Person Four", Genre.Fantasy);

        when(mockBookManagerRepository.save(book)).thenReturn(book);

        Book actualResult = bookManagerServiceImpl.insertBook(book);

        assertThat(actualResult).isEqualTo(book);
    }

}