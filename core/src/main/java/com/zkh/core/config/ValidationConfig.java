package com.zkh.core.config;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * 参数校验配置
 */
@Configuration
public class ValidationConfig {

    @Bean
    public Validator getValidator() {
        return Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("i18n/messages"))) //配置校验国际化信息路径
                .buildValidatorFactory()
                .getValidator();
    }
}
