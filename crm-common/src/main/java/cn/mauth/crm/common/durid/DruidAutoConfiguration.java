package cn.mauth.crm.common.durid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;

@Configuration
@ConditionalOnClass({DruidDataSource.class})
@EnableConfigurationProperties({DruidProperties.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
public class DruidAutoConfiguration {
    private final DruidProperties properties;

    public DruidAutoConfiguration(DruidProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Primary
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(this.properties.getUrl());
        dataSource.setUsername(this.properties.getUsername());
        dataSource.setPassword(this.properties.getPassword());
        if (this.properties.getName() != null) {
            dataSource.setName(this.properties.getName());
        }

        if (this.properties.getDriverClass() != null) {
            dataSource.setDriverClassName(this.properties.getDriverClass());
        }

        if (this.properties.getInitialSize() > 0) {
            dataSource.setInitialSize(this.properties.getInitialSize());
        }

        if (this.properties.getMinIdle() > 0) {
            dataSource.setMinIdle(this.properties.getMinIdle());
        }

        if (this.properties.getMaxActive() > 0) {
            dataSource.setMaxActive(this.properties.getMaxActive());
        }

        if (this.properties.getMaxWait() > 0) {
            dataSource.setMaxWait((long)this.properties.getMaxWait());
        }

        if (this.properties.getPoolPreparedStatements() != null) {
            dataSource.setPoolPreparedStatements(this.properties.getPoolPreparedStatements());
        }

        if (this.properties.getMaxPoolPreparedStatementPerConnectionSize() > 0) {
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.properties.getMaxPoolPreparedStatementPerConnectionSize());
        }

        if (this.properties.getValidationQuery() != null) {
            dataSource.setValidationQuery(this.properties.getValidationQuery());
        }

        if (this.properties.getTestOnBorrow() != null) {
            dataSource.setTestOnBorrow(this.properties.getTestOnBorrow());
        }

        if (this.properties.getTestWhileIdle() != null) {
            dataSource.setTestWhileIdle(this.properties.getTestWhileIdle());
        }

        if (this.properties.getTestOnReturn() != null) {
            dataSource.setTestOnReturn(this.properties.getTestOnReturn());
        }

        if (this.properties.getConnectionProperties() != null) {
            dataSource.setConnectionProperties(this.properties.getConnectionProperties());
        }

        if (this.properties.getFilters() != null) {
            try {
                dataSource.setFilters(this.properties.getFilters());
            } catch (SQLException var4) {
                var4.printStackTrace();
            }
        }

        if (this.properties.getMinEvictableIdleTimeMillis() > 0) {
            dataSource.setMinEvictableIdleTimeMillis((long)this.properties.getMinEvictableIdleTimeMillis());
        }

        if (this.properties.getTimeBetweenEvictionRunsMillis() > 0) {
            dataSource.setTimeBetweenEvictionRunsMillis((long)this.properties.getTimeBetweenEvictionRunsMillis());
        }

        try {
            dataSource.init();
            return dataSource;
        } catch (SQLException var3) {
            throw new RuntimeException(var3);
        }
    }
}

