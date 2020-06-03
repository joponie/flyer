package com.github.joponie.flyer.portal.dal.plugins;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author kain
 * @since 2020-01-13
 */
@Slf4j
@Intercepts({@Signature(method = "update", args = {MappedStatement.class, Object.class}, type = Executor.class),
        @Signature(method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class}, type = Executor.class)})
public class CustomInterceptor implements Interceptor {

    private static final String SUBJECT = "sv";

    private SubjectMap subjectMap = new SubjectMap();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        SqlSource sqlSource = mappedStatement.getSqlSource();
        // 只拦截动态sql
        if (sqlSource instanceof DynamicSqlSource) {
            // 获取到sqlNode对象
            Field field = DynamicSqlSource.class.getDeclaredField("rootSqlNode");
            field.setAccessible(true);
            SqlNode sqlnode = (SqlNode) field.get(sqlSource);
            SqlNode proxyNode = new CustomSqlNode(sqlnode);
            field.set(sqlSource, proxyNode);
//            if (!(sqlnode instanceof CustomSqlNode)) {
//                SqlNode proxyNode = new CustomSqlNode(sqlnode);
//                field.set(sqlSource, proxyNode);
//            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        log.debug("setProperties====>[{}]", properties);
    }

    public class CustomSqlNode implements SqlNode {

        private SqlNode target;

        CustomSqlNode(SqlNode target) {
            this.target = target;
        }

        @Override
        public boolean apply(DynamicContext context) {
            context.getBindings().put(SUBJECT, subjectMap);
            return target.apply(context);
        }
    }
}
