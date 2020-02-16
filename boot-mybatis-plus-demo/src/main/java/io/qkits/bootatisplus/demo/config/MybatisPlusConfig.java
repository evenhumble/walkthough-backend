package io.qkits.bootatisplus.demo.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import io.qkits.bootatisplus.demo.base.mthods.MyLogicSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * @author: patrick on 2020/1/6
 * @Description:
 */
@Configuration
public class MybatisPlusConfig {
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
    dynamicTableNameParser.setTableNameHandlerMap(new HashMap<String, ITableNameHandler>(2) {{
      put("big_user", (metaObject, sql, tableName) -> {
        // metaObject 可以获取传入参数，这里实现你自己的动态规则
        String year = "_2018";
        int random = new Random().nextInt(10);
        if (random % 2 == 1) {
          year = "_2019";
        }
        return tableName + year;
      });
    }});
    paginationInterceptor.setSqlParserList(Collections.singletonList(dynamicTableNameParser));
    return paginationInterceptor;
  }

  @Bean
  // add sql injector into mybatis handlers
  public MyLogicSqlInjector myLogicSqlInjector() {
    return new MyLogicSqlInjector();
  }

  @Bean
  public SqlExplainInterceptor sqlExplainInterceptor(){
    SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
    List<ISqlParser> sqlParserList = new ArrayList<>();
    sqlParserList.add(new BlockAttackSqlParser());
    sqlExplainInterceptor.setSqlParserList(sqlParserList);
    return sqlExplainInterceptor;
  }

  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {
    return new OptimisticLockerInterceptor();
  }

}
