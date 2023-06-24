package com.example.demo.web.ba02;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Item;

/**
 * item検索サービス
 */
@Service
public class ItemSearchService {
    
    /**
     * 全件検索
     * @param criteria 検索条件
     * @return 検索結果リスト
     */
    public Page<Item> findAll(ItemSearchCriteria criteria){

        // System.out.println("PageSize:" + criteria.getPageable().getPageSize() + ", Offset:" + criteria.getPageable().getOffset() + ", ItemName:" + criteria.getItemName() + ", Price:" + criteria.getPrice());

        // ダミー
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(1, "ペン", 100, "cdg01"));
        itemList.add(new Item(2, "ノート", 200, "cdg01"));
        itemList.add(new Item(3, "コンパス", 300, "cdg01"));
        itemList.add(new Item(4, "消しゴム", 100, "cdg01"));
        itemList.add(new Item(5, "えんぴつ", 100, "cdg01"));

        return new PageImpl<Item>(itemList, criteria.getPageable(), 15);
    }
}
