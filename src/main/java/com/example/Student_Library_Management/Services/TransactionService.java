package com.example.Student_Library_Management.Services;

import com.example.Student_Library_Management.DTOs.IssuedBookRequestDto;
import com.example.Student_Library_Management.Enums.CardStatus;
import com.example.Student_Library_Management.Enums.TransactionStatus;
import com.example.Student_Library_Management.Models.Book;
import com.example.Student_Library_Management.Models.Card;
import com.example.Student_Library_Management.Models.Transactions;
import com.example.Student_Library_Management.Repositories.BookRepository;
import com.example.Student_Library_Management.Repositories.CardRepository;
import com.example.Student_Library_Management.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
public String issuebook(@RequestBody IssuedBookRequestDto issuedBookRequestDto) throws Exception {
    int bookId= issuedBookRequestDto.getBookId();
    int carId= issuedBookRequestDto.getCardId();
    Book book=bookRepository.findById(bookId).get();
    Card card=cardRepository.findById(carId).get();
    Transactions transactions=new Transactions();
    transactions.setBook(book);
    transactions.setCard(card);
    transactions.setTransactionId(UUID.randomUUID().toString());
    transactions.setIssueOperation(true);

    transactions.setTransactionStatus(TransactionStatus.PENDING);
    if(book==null||book.isIssued()==true)
    {
        transactions.setTransactionStatus(TransactionStatus.FAILED);
        throw new Exception("Book is not available");
    }
    if(card==null||(card.getCardStatus()!= CardStatus.ACTIVATED))
    {
        transactions.setTransactionStatus((TransactionStatus.FAILED));
        transactionRepository.save(transactions);
        throw new Exception("Card is not valid");
    }
    transactions.setTransactionStatus(TransactionStatus.SUCCESS);
    book.setIssued(true);
    //Btw the book and transaction : bidirectional
    List<Transactions> listOfTransactionForBook = book.getListOfTransaction();
    listOfTransactionForBook.add(transactions);
    book.setListOfTransaction(listOfTransactionForBook);
    List<Book> issuedBooksForCard = card.getBookIssued();
    issuedBooksForCard.add(book);
    card.setBookIssued(issuedBooksForCard);

    for(Book b: issuedBooksForCard){
        System.out.println(b.getName());
    }

    //Card and the Transaction : bidirectional (parent class)
    List<Transactions> transactionsListForCard = card.getTransactionList();
    transactionsListForCard.add(transactions);
    card.setTransactionList(transactionsListForCard);

    //save the parent.
    cardRepository.save(card);
    //automatically by cascading : book and transaction will be saved.
    //Saving the parent

    return "Book issued successfully";
}
    public String getTransactions(int bookId,int cardId){

        List<Transactions> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId,cardId);

        String transactionId = transactionsList.get(0).getTransactionId();

        return transactionId;
    }
}
