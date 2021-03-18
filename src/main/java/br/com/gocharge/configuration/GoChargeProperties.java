package br.com.gocharge.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Getter
@Setter
@Valid
@ConfigurationProperties("gocharge")
public class GoChargeProperties implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @EventListener(RefreshScopeRefreshedEvent.class)
    public void onRefresh() throws Exception {
        afterPropertiesSet();
    }
}
