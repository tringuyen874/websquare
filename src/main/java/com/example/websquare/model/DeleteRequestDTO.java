package com.example.websquare.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteRequestDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") //"birthday": "2024-07-10T17:00:00.000+00:00",
    private Date birthday;
    private String address;
    private String gender;
    private Integer phone;
    private String name;
    private String action;
    private String checked;
    private String team;
    private String email;
    private boolean status;
    private String rowStatus;
    public void setBirthday(String birthdayString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        System.out.println(birthdayString);

        try {
            this.birthday = formatter.parse(birthdayString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
}
