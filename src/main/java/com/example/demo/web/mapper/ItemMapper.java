package com.example.demo.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Item;
import com.example.demo.web.ba02.ItemSearchCriteria;

@Mapper
public interface ItemMapper {
    int insertItem(Item item);
    List<Item> findAll(ItemSearchCriteria criteria);
    long countAll(ItemSearchCriteria criteria);
    List<Item> findAllByItemName(String itemName);
}
