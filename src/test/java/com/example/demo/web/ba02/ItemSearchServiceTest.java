package com.example.demo.web.ba02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.demo.entity.Item;

@SpringBootTest
public class ItemSearchServiceTest {
    
    @Autowired
    private ItemSearchService target;

    @Test
    public void test() {
        ItemSearchCriteria criteria = new ItemSearchCriteria("a", 1000, PageRequest.of(0, 3));
        Page<Item> actual = target.findAll(criteria);
        assertEquals(3, actual.getSize());
    }
}
