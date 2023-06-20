package com.example.demo.web.ba02;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class ItemSearchForm implements Serializable {

    @Length(max=10)
    private String itemName;

    @Range(max=10000)
    private Integer price;

    private Integer page;

    public ItemSearchCriteria toCriteria(int size) {
        
        ItemSearchCriteria criteria = new ItemSearchCriteria();
        BeanUtils.copyProperties(this, criteria);

        Pageable pageable = PageRequest.of(page != null ? page : 0, size);
        criteria.setPageable(pageable);
        return criteria;
    }

    public void clear() {
        BeanUtils.copyProperties(new ItemSearchForm(), this);
    }
}
