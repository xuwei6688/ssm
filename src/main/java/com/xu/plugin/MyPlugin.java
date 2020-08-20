package com.xu.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/**
 * 自定义Mybatis插件
 **/
@Intercepts({
@Signature(type = StatementHandler.class,//指定拦截的接口
        method = "prepare",//指定拦截该接口的具体方法
        args = { Connection.class, Integer.class}),//拦截方法的入参，参数数量顺序号要准确
        })
public class MyPlugin implements Interceptor {
        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        @Override
        public Object intercept(Invocation invocation) throws Throwable {
                logger.info("增强逻辑");
                return invocation.proceed();
        }

        /**
         * 生成代理对象放入拦截器链中
         * @param target
         * @return
         */
        @Override
        public Object plugin(Object target) {
                logger.info("将要包装目标对象");
                return Plugin.wrap(target, this);
        }

        /**
         * 获取插件配置属性
         * 仅在插件初始化时会被调用
         */
        @Override
        public void setProperties(Properties properties) {
                logger.info("插件初始化参数:{}", properties);
        }
}
