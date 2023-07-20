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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /**
     * 変更登録確認
     * @param form ItemUpdateDeleteForm
     * @param result BindingResult
     * @return 正常なら確認画面 or 入力エラーなら変更・削除画面
     */
    @PostMapping(path="/WBA0501/confirm", params="updateConfirm")
    public String confirm(@Validated ItemUpdateDeleteForm form, BindingResult result) {
        
        if (result.hasErrors()) {
            // フォームクラスによる単項目チェックエラーがある場合、登録画面を表示
            return "/BA0501/index";
        }
        // 確認画面を表示
        return "BA0501/confirm";
    }

    /**
     * 変更登録実行
     * @param form ItemUpdateDeleteForm
     * @param result BindingResult
     * @return　完了画面 or 変更・削除画面
     */
    @PostMapping(path="/WBA0501/index", params="update")
    public String update(@Validated ItemUpdateDeleteForm form, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // フォームクラスによる単項目チェックエラーがある場合、変更・削除画面を表示
            return "BA0501/index";
        }

        try {
            // 更新処理
            itemUpdateDeleteService.updateItem(form.toItem());
        } catch(AppException e) {
            if (e.getField() != null) {
                result.rejectValue(e.getField(), e.getMessageId());
            } else {
                result.reject(e.getMessageId());
            }
            return "BA0501/index";
        }
        
        redirectAttributes.addFlashAttribute("message","変更登録が完了しました。");
        return "redirect:/WBA0501/complete";
    }

    /**
     * 削除
     * @param form ItemUpdateDeleteForm
     * @param result BindingResult
     * @return　完了画面 or 変更・削除画面
     */
    @PostMapping(path="/WBA0501/confirm", params="delete")
    public String delete(ItemUpdateDeleteForm form, BindingResult result, RedirectAttributes redirectAttributes) {

        //パラメータが設定されていない場合エラー
        if (form.getId() == null || form.getVersionNo() == null) {
            throw new AppException("ME999");
       }

        try {
            // 削除処理
            itemUpdateDeleteService.deleteItem(form.getId(), form.getVersionNo());
        } catch(AppException e) {
            if (e.getField() != null) {
                result.rejectValue(e.getField(), e.getMessageId());
            } else {
                result.reject(e.getMessageId());
            }
            return "BA0501/index";
        }
        
        redirectAttributes.addFlashAttribute("message","削除が完了しました。");
        return "redirect:/WBA0501/complete";
    }

    /**
     * 戻る
     * @param form ItemUpdateDeleteForm
     * @return 登録画面
     */
    @PostMapping(path="/WBA0501/index", params="back")
    public String back(ItemUpdateDeleteForm form) {
        return "BA0501/index";
    }

    /**
     * 完了
     * @return 変更完了画面
     */
    @GetMapping("/WBA0501/complete")
    public String complete(@ModelAttribute("message") String message,Model model) {
        model.addAttribute("message",message);
        return "BA0501/complete";
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
