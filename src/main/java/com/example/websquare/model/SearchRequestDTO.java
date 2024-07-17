package com.example.websquare.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDTO {
    private String gender;
    private Integer phone;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date birthdayTo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date birthdayFrom;

    public void setBirthdayFrom(String birthdayString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        System.out.println(birthdayString);

        try {
            this.birthdayFrom = formatter.parse(birthdayString);
        } catch (ParseException e) {
            System.out.println("birthday is null");
        }
    }

    public void setBirthdayTo(String birthdayString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        System.out.println(birthdayString);

        try {
            this.birthdayTo = formatter.parse(birthdayString);
        } catch (ParseException e) {
            System.out.println("birthday is null");
        }
    }
}
