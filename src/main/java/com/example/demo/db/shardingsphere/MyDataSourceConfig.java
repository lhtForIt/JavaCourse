package com.example.demo.db.shardingsphere;

import com.alibaba.druid.pool.DruidDataSource;
import com.sun.javaws.security.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
//import org.apache.shardingsphere.api.config.sharding.strategy.NoneShardingStrategyConfiguration;
//import org.apache.shardingsphere.core.rule.ShardingRule;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.IdGenerator;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Leo liang
 * @Date 2022/3/1
 */
//@Configuration
public class MyDataSourceConfig {

    @Bean(name = "data0")
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.ds0")
    public DataSource dataSource0() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "data1")
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.ds1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(configuration);

        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager shardTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSource dataSource() throws SQLException {

        // ??????????????????
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//
//        // ?????????????????????
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//        dataSourceMap.put("data0", dataSource0());
//        dataSourceMap.put("data1", dataSource1());
//
//        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("course", "ds$->{0..1}.course_$->{1..2}");
//        // ???????????? + ????????????
//        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("cid", "ds$->{cid % 2}"));
//        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "course_$->{user_id % 2 + 1}"));
//
//
//        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
//
//        shardingRuleConfig.setDefaultDataSourceName("data0");
//
//        // ?????????????????????
//        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
//        return dataSource;

        return null;
    }

}
