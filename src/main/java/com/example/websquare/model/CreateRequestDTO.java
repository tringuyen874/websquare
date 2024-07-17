package com.example.websquare.model;

import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
public class CreateRequestDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date birthday;
    private String address;
    private String gender;
    private Integer phone;
    private String name;
    private String action;
    private String team;
    private String email;
    private String status;

    public void setBirthday(String birthdayString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        System.out.println(birthdayString);

        try {
            this.birthday = formatter.parse(birthdayString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
