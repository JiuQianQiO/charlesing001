package com.zhi.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

//@Configuration
public class DruidDataSourceConfig {
    /**
     * springBoot 底层配置数据源的前提是，没有配置过数据源
     * 我们配置了 DataSources ,则底层的配置就不再生效
     * @return
     */
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        // 配置监控页，使监控页生效
        druidDataSource.setFilters("stat");
        return druidDataSource;
    }

    /**
     * 配置 druid 的监控页功能
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");

        return servletRegistrationBean;
    }
}
