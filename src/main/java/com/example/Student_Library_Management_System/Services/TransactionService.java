package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{

        int bookId = issueBookRequestDto.getBookId();
        int cardId = issueBookRequestDto.getCardId();

        Book book = bookRepository.findById(bookId).get();

        Card card = cardRepository.findById(cardId).get();


        // make transaction Entity, set its attribute and save it
        Transactions transaction = new Transactions();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setIssueOperation(true);
        transaction.setTransactionStatus(TransactionStatus.PENDING);


        // attribute left is success/failure
        // check for validations

        if(book==null || book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");
        }


        if(card==null || (card.getCardStatus()!= CardStatus.ACTIVATED)){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is not Valid");
        }

        // We have reached a success case
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);


        // set attribute of book
        book.setIssued(true);
        List<Transactions> transactionsListForBook = book.getListOfTransactions();
        transactionsListForBook.add(transaction);
        book.setListOfTransactions(transactionsListForBook);


        // I need to make changes in the card
        List<Book> issuedBooksForCard = card.getBookIssued();
        issuedBooksForCard.add(book);
        card.setBookIssued(issuedBooksForCard);

        // Card and Transaction : bidirectional(parent class)
        List<Transactions> transactionsListForCard = card.getListOfTransaction();
        transactionsListForCard.add(transaction);
        card.setListOfTransaction(transactionsListForCard);

        // save the parent
        cardRepository.save(card);
        // automatically by cascading : book and transaction will be saved.


        return "Book Issued Successfully";
    }

    public String getTransaction(int bookId, int cardId){

        List<Transactions> transactionsList = transactionRepository.getTransactionForBookAndCard(bookId, cardId);

        String transactionId = transactionsList.get(0).getTransactionId();

        return transactionId;
    }
}
