package com.heslin.jszj.controller;

import com.heslin.jszj.entity.Quote;
import com.heslin.jszj.entity.User;
import com.heslin.jszj.model.QuoteInfo;
import com.heslin.jszj.model.ResMessage;
import com.heslin.jszj.service.QuoteService;
import com.heslin.jszj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/quote")
public class QuoteController {
    private final QuoteService quoteService;
    private final UserService userService;

    @PostMapping("add")
    public ResponseEntity<ResMessage> addQuote(@RequestHeader("Authorization") String authorization, @RequestBody Quote quote) {
        quote.setUser(userService.extractUser(authorization));
        return ResponseEntity.ok(quoteService.addQuote(quote));
    }

    @PostMapping("remove")
    public ResponseEntity<ResMessage> removeQuote(@RequestBody Quote quote) {
        return ResponseEntity.ok(quoteService.removeQuote(quote.getId()));
    }

    @GetMapping("get-all")
    public  ResponseEntity<List<QuoteInfo>> getAllQuotes(@RequestHeader("Authorization") String authorization) {
        User user = userService.extractUser(authorization);
        return ResponseEntity.ok(quoteService.getAllQuotes(user));
    }

    @GetMapping("get-one")
    public  ResponseEntity<QuoteInfo> getQuote(@RequestHeader("Authorization") String authorization) {
        User user = userService.extractUser(authorization);
        return ResponseEntity.ok(quoteService.getQuote(user));
    }
}
