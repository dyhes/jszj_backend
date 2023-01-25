package com.heslin.jszj.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResMessage {
    private boolean success;
    private String message;
}
