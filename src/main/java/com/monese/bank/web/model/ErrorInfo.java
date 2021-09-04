package com.monese.bank.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorInfo {

    private String timestamp;

    private Integer status;

    private String error;

    private String message;

    private String path;

}
