package com.example.demo.web.ba01;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;

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

    public Item toItem() {
        // ItemFormからItemエンティティへデータコピー
        // 同じ名称のフィールドは自動的にコピーしてくれる。
        Item item = new Item();
        BeanUtils.copyProperties(this, item);
        return item;
    }
}
