package com.example.demo.web.ba01;

import org.springframework.stereotype.Component;

import com.example.demo.common.code.ItemGroup;

@Component
public class ItemRegistViewHelper {
    public String getGroupName(String groupid) {
        return ItemGroup.getLabel(groupid);
    }
}
