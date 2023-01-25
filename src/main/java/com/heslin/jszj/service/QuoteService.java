package com.heslin.jszj.service;

import com.heslin.jszj.entity.Quote;
import com.heslin.jszj.entity.User;
import com.heslin.jszj.model.QuoteInfo;
import com.heslin.jszj.model.ResMessage;
import com.heslin.jszj.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserService userService;

    public ResMessage addQuote(Quote quote) {
        quoteRepository.save(quote);
        return new ResMessage(true,"quote successfully saved");
    }

    public ResMessage removeQuote(Long id) {
        if(quoteRepository.existsById(id)){
            quoteRepository.deleteById(id);
            return new ResMessage(true,"quote successfully deleted");
        }else {
            return new ResMessage(false, "quote-id doesn't exist");
        }

    }

    public List<QuoteInfo> getAllQuotes(User user) {
        return user.getQuotes().stream().map(Quote::toQuoteInfo).toList();
    }

    public QuoteInfo getQuote(User user) {
        var quotes = getAllQuotes(user);
        return quotes.get(new Random().nextInt(quotes.size()));
    }
}
