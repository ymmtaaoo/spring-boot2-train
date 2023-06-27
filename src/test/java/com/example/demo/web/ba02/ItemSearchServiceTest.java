package com.example.demo.web.ba02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.demo.core.exception.AppException;
import com.example.demo.entity.Item;
import com.example.demo.web.mapper.ItemMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ItemSearchServiceTest {
    
    @InjectMocks
    private ItemSearchService target;

    @Mock
    private ItemMapper itemMapper;

    @Test
    public void testCountZero() {

        when(itemMapper.countAll(any())).thenReturn(0L);

        ItemSearchCriteria criteria = new ItemSearchCriteria("a", 1000, PageRequest.of(0, 3));
        try {
            target.findAll(criteria);
            assertFalse(true);
        } catch (AppException e) {
            assertEquals("ME003", e.getMessageId());
        }
    }

    @Test
    public void testCountOne() {

        when(itemMapper.countAll(any())).thenReturn(1L);
        List<Item> list = new ArrayList<>();
        list.add(new Item(1, "ペン", 100, "CD-A01", LocalDate.now()));
        when(itemMapper.findAll(any())).thenReturn(list);

        ItemSearchCriteria criteria = new ItemSearchCriteria("a", 1000, PageRequest.of(0, 5));
        Page<Item> pages = target.findAll(criteria);
        assertEquals(0,pages.getPageable().getPageNumber());
    }
}
