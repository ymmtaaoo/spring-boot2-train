package com.example.demo.web.ba01;

import org.springframework.stereotype.Service;

import com.example.demo.core.exception.AppException;
import com.example.demo.core.exception.SystemException;
import com.example.demo.entity.Item;

/**
 * item登録サービス
 */
@Service
public class ItemRegistService {
    
    /**
     * itemを登録する
     * @param item Item
     */
    public void registItem(Item item) {
        
        if (item.getItemName().startsWith("1")) {
            // ダミー例外スロー
            throw new AppException("ME001", "itemName");
        }

        if (item.getItemName().startsWith("2")) {
            // ダミー例外スロー
            throw new SystemException("ME999");
        }

        String a = null;
        if (item.getItemName().startsWith("3")) {
            // ダミー例外スロー
            System.out.println(a.equals("a"));
        }

        //Itemクラスにsetter,getterは実装していないが「item.getItemName()」のビルドができる。
        //Lombokの機能で「@Data」アノテーションをItemクラスに付けることでsetter,getterが生成されている。
        System.out.println(item.getItemName());

    }
}
