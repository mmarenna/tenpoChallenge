package com.tenpo.adder.history.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="histories")
public class History {

    private Long id;
    private Date date;
    private String uri;
    private String response;

    public History() {
    }

    public History(String uri) {
        this.uri = uri;
    }

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
