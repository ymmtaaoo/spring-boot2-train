package com.example.demo.web.ba03;
import org.springframework.stereotype.Service;

import com.example.demo.core.exception.AppException;
import com.example.demo.entity.Item;
import com.example.demo.web.mapper.ItemMapper;

import lombok.RequiredArgsConstructor;

/**
 * item詳細画面サービス
 */
@Service
@RequiredArgsConstructor
public class ItemDetailService {
    
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

}
