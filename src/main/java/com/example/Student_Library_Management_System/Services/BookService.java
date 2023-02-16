package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public String createBook(BookRequestDto bookRequestDto) throws Exception{

        // I want to get the Author Entity
        int authorId = bookRequestDto.getAuthorId();

        // Now I will be fetching the author entity
        Author author = authorRepository.findById(authorId).get();

        // Do exception handling
        if(author==null){
            throw new Exception("Author is not present in data base");
        }

        // we have created this entity so that we can save it into the Db
        Book book = new Book();

        //  Basic attribute are being set from Dto to Entity layer
        book.setGenre(bookRequestDto.getGenre());
        book.setName(bookRequestDto.getName());
        book.setPages(bookRequestDto.getPages());
        book.setIssued(false);



        // Setting the foreign key attribute in the child class
        book.setAuthor(author);

        // We need to update the list of books written int the parent class
        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);
        author.setBooksWritten(currentBooksWritten);

        // Now the book is to be saved, but author is also to be saved
        // Because author entity has been updated, So we need to save author also.
        authorRepository.save(author); // Data was modified

        // .save function will add and update also

        // book.save is not required : because it will be auto called by cascading
        return "Book added successfully";
    }
}
