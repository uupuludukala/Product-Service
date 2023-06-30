package com.coolbook.erp.dataSourceRouting;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSourceRouting extends AbstractRoutingDataSource {


    private DataSourceContextHolder dataSourceContextHolder;
    private CustomerDataSourceConfig customerDataSourceConfig;

    public DataSourceRouting(DataSourceContextHolder dataSourceContextHolder ,CustomerDataSourceConfig customerDataSourceConfig) {
        this.customerDataSourceConfig=customerDataSourceConfig;
        this.dataSourceContextHolder = dataSourceContextHolder;

        Map<Object, Object> dataSourceMap = new HashMap<>();
        populateDataSources(customerDataSourceConfig.getUrl().keySet(),dataSourceMap);
        this.setTargetDataSources(dataSourceMap);
        this.setDefaultTargetDataSource(createDatasource("coop"));
    }

    private void populateDataSources(Set<String> keySet,Map<Object, Object> dataSourceMap){
        for(String key:keySet){
            dataSourceMap.put(key, createDatasource(key));
        }
    }

    private DataSource createDatasource(String key){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(customerDataSourceConfig.getUrl().get(key));
        dataSource.setUsername(customerDataSourceConfig.getUsername().get(key));
        dataSource.setPassword(customerDataSourceConfig.getPassword().get(key));
        return dataSource;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceContextHolder.getDataSourceContext();
    }

}
