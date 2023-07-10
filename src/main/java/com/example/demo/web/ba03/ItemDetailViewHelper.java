package com.example.demo.web.ba03;

import org.springframework.stereotype.Component;
import com.example.demo.common.code.ItemGroup;

/**
 * viewヘルパー
 */
@Component
public class ItemDetailViewHelper {
    /**
     * 分類名取得
     * @param groupid 分類ID
     * @return 分類名
     */
    public String getGroupName(String groupid) {
        // コードクラスで管理しているIDからラベルを取得
        return ItemGroup.getLabel(groupid);
    }
}
