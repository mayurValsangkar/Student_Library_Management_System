package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorEntryDto authorEntryDto){

        // Important step is : in the params : the object i
        // of type DTO but the repository interacts only with entities

        // Solution : convert authorEntryDto --> Author

        // We are setting its attribute so that we can save
        // correct entity
        Author author = new Author();
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setRatings(authorEntryDto.getRatings());
        author.setCountry(authorEntryDto.getCountry());

        authorRepository.save(author);
        return "Author added successfully";
    }

}
