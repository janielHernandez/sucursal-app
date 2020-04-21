package com.example.sucursalapp.exceptions;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class ExceptionDetail {

    private Integer status;
    @Temporal(value = TemporalType.DATE)
    private Date timestamp;
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}