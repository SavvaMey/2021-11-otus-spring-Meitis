package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.LocaleConfig;

import javax.swing.text.TabableView;

@Service
@AllArgsConstructor
public class LocalizatorImpl implements Localizator {

    private final MessageSource messageSource;
    private final LocaleConfig localeConfig;

    @Override
    public String getMsg(String code, String[] arg) {
        if (arg.length == 0) {
            arg = null;
        }
        return messageSource.getMessage(code, arg, localeConfig.getLocale());

    }
}
