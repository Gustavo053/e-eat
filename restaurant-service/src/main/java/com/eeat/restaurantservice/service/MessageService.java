package com.eeat.restaurantservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageSource messageSource;

    public String get(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }
}
