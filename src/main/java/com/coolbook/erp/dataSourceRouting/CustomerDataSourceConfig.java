package com.coolbook.erp.dataSourceRouting;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix="datasource.customerDataSources")
@Getter
@Setter
public class CustomerDataSourceConfig {

    Map<String, String> url;

    Map<String,String> username;

    Map<String,String> password;

}
