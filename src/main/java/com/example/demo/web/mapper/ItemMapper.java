package com.example.demo.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Item;
import com.example.demo.web.ba02.ItemSearchCriteria;

/**
 * ItemMapper
 */
@Mapper
public interface ItemMapper {

    /**
     * 登録
     * @param item Item
     * @return 件数
     */
    int insertItem(Item item);

    /**
     * 全件検索
     * @param criteria 検索条件
     * @return itemリスト
     */
    List<Item> findAll(ItemSearchCriteria criteria);

    /**
     * 全件カウント
     * @param criteria 検索条件
     * @return 件数
     */
    long countAll(ItemSearchCriteria criteria);

    /**
     * 全件検索item名称指定
     * @param itemName item名称
     * @return itemリスト
     */
    List<Item> findAllByItemName(String itemName);
}
