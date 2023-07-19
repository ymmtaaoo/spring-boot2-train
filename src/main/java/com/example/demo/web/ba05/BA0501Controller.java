package com.example.demo.web.ba05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.common.code.ItemGroup;
import com.example.demo.core.exception.AppException;
import com.example.demo.entity.Item;

import lombok.RequiredArgsConstructor;

/**
 * item変更・削除画面コントローラ
 */
@Controller
@RequiredArgsConstructor
public class BA0501Controller {

    /** 変更・削除画面サービス */
    private final ItemUpdateDeleteService itemUpdateDeleteService;

    /** ロガー */
    private static final Logger logger = LoggerFactory.getLogger(BA0501Controller.class);

    /**
     * item変更・削除画面表示する。
     * @param form form
     * @param bindingResult bindingResult
     * @param model Model
     * @return item変更・削除画面
     */
    @GetMapping("/WBA0501/index")
    public String index(ItemUpdateDeleteForm form, BindingResult bindingResult) {
        logger.info("変更削除画面表示");

        Item item = itemUpdateDeleteService.findItemById(form.getId());
        if (item == null) {
            throw new AppException("ME999");
        }
        form.copyFrom(item);
        return "BA0501/index";
    }

    public String confirm(@Validated ItemUpdateDeleteForm form, BindingResult result) {
        
        if (result.hasErrors()) {
            // フォームクラスによる単項目チェックエラーがある場合、登録画面を表示
            return "/BA0501/index";
        }
        // 確認画面を表示
        return "BA0501/confirm";
    }

    /**
     * 分類コードのプルダウンリスト
     * @return プルダウンリスト
     */
    @ModelAttribute("groups")
    public List<ItemGroup> getGroupid() {
        return Arrays.stream(ItemGroup.values())
        .collect(Collectors.toList());
    }
}
