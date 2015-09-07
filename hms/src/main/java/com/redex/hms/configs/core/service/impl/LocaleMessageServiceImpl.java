
package com.redex.hms.configs.core.service.impl;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.redex.hms.configs.core.service.LocaleMessageService;

/**
 *
 * @author Justin
 */
@Service("localeMessageService")
public class LocaleMessageServiceImpl implements LocaleMessageService {

    @Autowired
    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String string, Object[] os, Locale locale) {
        return this.messageSource.getMessage(string, os, locale);
    }
}
