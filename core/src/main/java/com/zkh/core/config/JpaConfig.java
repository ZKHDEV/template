package com.zkh.core.config;

import com.zkh.core.model.User;
import com.zkh.core.utils.UserUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * JPA配置
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
    /**
     * @CreateBy 配置
     * @return
     */
    @Bean
    public AuditorAware<User> auditorAware() {
        return new AuditorAware<User>() {
            @Override
            public Optional<User> getCurrentAuditor() {
                return Optional.of(UserUtil.getUser());
            }
        };
    }
}
