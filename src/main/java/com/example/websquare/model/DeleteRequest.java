package com.example.websquare.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteRequest {
    private List<DeleteRequestDTO> searchResults;

}
