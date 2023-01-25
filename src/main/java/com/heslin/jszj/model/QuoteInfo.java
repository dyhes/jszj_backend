package com.heslin.jszj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteInfo {
    private Long id;
    private String content;
    private String dynasty;
    private String author;
    private String username;
}
