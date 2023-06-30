package com.coolbook.erp.dataSourceRouting;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DataSourceContextHolder {
    private static ThreadLocal<String> threadLocal;

    public DataSourceContextHolder() {
        threadLocal = new ThreadLocal<>();
    }

    public void setDataSourceContext(String customer) {
        threadLocal.set(customer);
    }

    public String getDataSourceContext() {
        return threadLocal.get();
    }

    public static void clearDataSourceContext() {
        threadLocal.remove();
    }
}
