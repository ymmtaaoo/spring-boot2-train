package com.example.demo.web.ba02;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.example.demo.core.exception.AppException;
import com.example.demo.entity.Item;
import com.example.demo.web.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

/**
 * item検索サービス
 */
@Service
@RequiredArgsConstructor
public class ItemSearchService {
    
    /** itemMapper */
    private final ItemMapper mapper;

    /** 最大検索結果件数 */
    private static final int SEARCH_MAX = 1000;

    /**
     * 全件検索
     * @param criteria 検索条件
     * @return 検索結果リスト
     */
    public Page<Item> findAll(ItemSearchCriteria criteria){

        long total = mapper.countAll(criteria);
        if (total > SEARCH_MAX) {
            // 最大検索結果件数を超過エラー
            throw new AppException("ME002");
        } else if (total == 0) {
            // 検索結果0件エラー
            throw new AppException("ME003");
        }

        List<Item> itemList = mapper.findAll(criteria);

        // ダミー結果    
        // List<Item> itemList = new ArrayList<>();
        // itemList.add(new Item(1, "ペン", 100, "CD-A01", LocalDate.now()));
        // itemList.add(new Item(2, "ノート", 200, "CD-A01", LocalDate.now()));
        // itemList.add(new Item(3, "コンパス", 300, "CD-A01", LocalDate.now()));
        // itemList.add(new Item(4, "消しゴム", 100, "CD-A01", LocalDate.now()));
        // itemList.add(new Item(5, "えんぴつ", 100, "CD-A01", LocalDate.now()));

        return new PageImpl<Item>(itemList, criteria.getPageable(), total);
    }
}
