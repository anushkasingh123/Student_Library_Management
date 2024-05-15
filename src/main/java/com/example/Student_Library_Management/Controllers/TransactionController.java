package com.example.Student_Library_Management.Controllers;

import com.example.Student_Library_Management.DTOs.IssuedBookRequestDto;
import com.example.Student_Library_Management.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @PostMapping("/issueBook")
    public String issuebook(@RequestBody IssuedBookRequestDto issuedBookRequestDto)
        {
            try{
                return transactionService.issuebook(issuedBookRequestDto);
            }catch (Exception e){
                return e.getMessage();
            }

        }
    @GetMapping("/getTxnInfo")
    public String getTransactionEntry(@RequestParam("bookId")Integer bookId,@RequestParam
            ("cardId")Integer cardId) {

        return transactionService.getTransactions(bookId, cardId);
    }

}
