package com.example.demo.core.handler;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.core.exception.SystemException;

/**
 * WEB例外ハンドラー
 */
@ControllerAdvice
public class WebExceptionHandler {

    /** ロガー */
    Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    @Autowired
    MessageSource messages;

    /**
     * システム例外処理
     * @param t システム例外
     * @return エラー画面
     */
    @ExceptionHandler(SystemException.class)
    public String handleSystemException(SystemException t) {
        logger.error(messages.getMessage(t.getMessageId(), null, Locale.getDefault()), t);
        // 本来はerror.htmlに遷移させる
        return "syserror.html";
    }

    /**
     * 例外処理
     * @param t 例外
     * @return エラー画面
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception t) {
        logger.error(t.getMessage(), t);
        // 本来はerror.htmlに遷移させる
        return "syserror.html";
    }
    
}