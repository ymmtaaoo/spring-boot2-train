package com.example.demo.web.ba02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.Item;

/**
 * item検索画面コントローラ
 */
@Controller
@SessionAttributes(types=ItemSearchForm.class)
public class BA0201Controller {

    @Autowired
    private ItemSearchService itemSearchService;

    Logger logger = LoggerFactory.getLogger(BA0201Controller.class);

    /**
     * item検索画面を初期表示する。検索条件が未指定で検索した結果を指定件数分だけ表示する
     * @param model Model
     * @param form ItemSearchForm
     * @return item検索画面
     */
    @GetMapping("/WBA0201/index")
    public String index(ItemSearchForm form, Model model) {
        
        Page<Item> pages = itemSearchService.findAll(form.toCriteria(5));

        model.addAttribute("pages", pages);
        model.addAttribute("itemList", pages.getContent());
        form.clear();
        return "BA0201/search";
    }

    /**
     * item検索を行う。指定された検索条件で検索した結果を指定件数分だけ表示する
     * @param model Model
     * @param form ItemSearchForm
     * @param result BindingResult
     * @return item検索画面
     */
    @GetMapping("/WBA0201/search")
    public String search(Model model, @Validated ItemSearchForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "BA0201/search";
        }
        logger.info("item検索画面検索キー：item名称：" + form.getItemName());
        Page<Item> pages = itemSearchService.findAll(form.toCriteria(5));

        model.addAttribute("pages", pages);
        model.addAttribute("itemList", pages.getContent());
        
        return "BA0201/search";
    }
}
