package com.example.demo.web.ba01;

import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.common.code.ItemGroup;
import com.example.demo.entity.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * itemフォーム
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm {
    @NotNull
    private Integer id;

    @Length(max=10)
    private String itemName;

    @Range(max=10000)
    private Integer price;

    @Length(max=6)
    private String groupid;

    @DateTimeFormat(pattern = "uuuu-MM-dd")
    private LocalDate registDate;

    @AssertTrue(message = "{MC001}")
    public boolean isBunguPrice() {
        if (ItemGroup.BUNGU.getCode().equals(groupid)) {
            if (price >= 5000) {
                return false;
            }
        }
        return true;
    }

    /**
     * Item生成
     * @return Item
     */
    public Item toItem() {
        // ItemFormからItemエンティティへデータコピー
        // 同じ名称のフィールドは自動的にコピーしてくれる。
        Item item = new Item();
        BeanUtils.copyProperties(this, item);
        return item;
    }
}
