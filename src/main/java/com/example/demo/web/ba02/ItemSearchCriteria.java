package com.example.demo.web.ba02;

import org.springframework.data.domain.Pageable;

import lombok.Data;

/**
 * 検索条件
 */
@Data
public class ItemSearchCriteria {
    private String itemName;
    private Integer price;
    private Pageable pageable;
}
