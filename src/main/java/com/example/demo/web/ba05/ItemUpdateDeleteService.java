package com.example.demo.web.ba05;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.core.exception.AppException;
import com.example.demo.entity.Item;
import com.example.demo.web.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

/**
 * item変更・削除画面サービス
 */
@Service
@RequiredArgsConstructor
public class ItemUpdateDeleteService {
    
    /** itemMapper */
    private final ItemMapper mapper;

    /**
     * itemの情報を取得する
     * @param id ID
     */
    public Item findItemById(Integer id){

        Item item = mapper.findItemById(id);

        if (item == null) {
            throw new AppException("ME006");
        }
        return item;
    }

    /**
     * itemを更新登録する
     * @param item Item
     */
    @Transactional
    public void updateItem(Item item) {
        
        // 同一ItemNameの合計priceが3000円以上の場合、業務エラー
        List<Item> list = mapper.findAllByItemName(item.getItemName());
        Integer totalPrice = item.getPrice();
        for (Item item2 : list) {
            totalPrice += item2.getPrice();
        }
        if (totalPrice >= 3000) {
            throw new AppException("ME001");
        }
        // 更新処理
        int updateCount = mapper.updateItem(item);
        if (updateCount == 0) {
            throw new AppException("ME007");
        }
    }

    /**
     * itemを削除する
     * @param id ID
     * @param versionNo バージョンNo
     */
    @Transactional
    public void deleteItem(Integer id, Integer versionNo) {

        int deleteCount = mapper.deleteItem(id, versionNo);
        if (deleteCount == 0) {
            throw new AppException("ME008");
        }
    }
}
