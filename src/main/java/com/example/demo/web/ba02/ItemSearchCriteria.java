package com.example.demo.web.ba02;

import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class ItemSearchCriteria {
    private String itemName;
    private Integer price;
    private Pageable pageable;
}
