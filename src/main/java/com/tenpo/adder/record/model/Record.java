package com.tenpo.adder.record.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String uri;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String response;

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }

}
