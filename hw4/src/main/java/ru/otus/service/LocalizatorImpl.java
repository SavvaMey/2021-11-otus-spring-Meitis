package ru.otus.service;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.LocaleConfig;

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
