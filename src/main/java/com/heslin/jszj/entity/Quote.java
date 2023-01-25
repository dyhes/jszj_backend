package com.heslin.jszj.entity;

import com.heslin.jszj.model.QuoteInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
    @Id
    @SequenceGenerator(
            name = "quote_sequence",
            sequenceName = "quote_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "quote_sequence"
    )
    @Column(
            name = "quote_id",
            updatable = false
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String content;
    private String dynasty;
    private String author;
    @Column(
            name = "uploaded_time"
    )
    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
    private String note;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public QuoteInfo toQuoteInfo(){
        return new QuoteInfo(id,content,dynasty,author,user.getName());
    }
}
