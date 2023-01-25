package com.heslin.jszj.repository;

import com.heslin.jszj.entity.Quote;
import com.heslin.jszj.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteRepository extends CrudRepository<Quote,Long > {
}
