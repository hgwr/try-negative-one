# try-negative-one

EclipseLink を使用している時、 JPQL の中で `-1` を使うと JPQLException Syntax error が発生することを確認するためのサンプルです。

## 再現方法

下記のように JPQL の中で数字リテラルの `-1` を使用します。

src/main/java/com/example/demo/dao/repository/MemoRepository.java

```java
  @Query(value = "SELECT m FROM Memo m WHERE m.categoryId = -1")
  List<Memo> findByCategoryIdIsNegativeOne();
```

コマンド `bash ./gradlew build` でビルドします。

コマンド `java -jar build/libs/demo-0.0.1-SNAPSHOT.war` で実行します。

別のターミナルからコマンド `curl "http://127.0.0.1:8080/memo/category/negative-one"` を実行します。

下記のような例外が得られます。

```
ERROR 86400 --- [nio-8080-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.dao.InvalidDataAccessApiUsageException: An exception occurred while creating a query in EntityManager: 
Exception Description: Syntax error parsing [SELECT m FROM Memo m WHERE m.categoryId = - 1]. 
[27, 45] The expression is not a valid conditional expression.] with root cause

org.eclipse.persistence.exceptions.JPQLException: 
Exception Description: Syntax error parsing [SELECT m FROM Memo m WHERE m.categoryId = - 1]. 
[27, 45] The expression is not a valid conditional expression.
        at org.eclipse.persistence.internal.jpa.jpql.HermesParser.buildException(HermesParser.java:159) ~[org.eclipse.persistence.core-4.0.2.jar!/:na]
        at org.eclipse.persistence.internal.jpa.jpql.HermesParser.validate(HermesParser.java:324) ~[org.eclipse.persistence.core-4.0.2.jar!/:na]
        at org.eclipse.persistence.internal.jpa.jpql.HermesParser.populateQueryImp(HermesParser.java:271) ~[org.eclipse.persistence.core-4.0.2.jar!/:na]
        at org.eclipse.persistence.internal.jpa.jpql.HermesParser.buildQuery(HermesParser.java:164) ~[org.eclipse.persistence.core-4.0.2.jar!/:na]
        at org.eclipse.persistence.internal.jpa.EJBQueryImpl.buildEJBQLDatabaseQuery(EJBQueryImpl.java:141) ~[org.eclipse.persistence.jpa-4.0.2.jar!/:na]
        at org.eclipse.persistence.internal.jpa.EJBQueryImpl.buildEJBQLDatabaseQuery(EJBQueryImpl.java:117) ~[org.eclipse.persistence.jpa-4.0.2.jar!/:na]
        at org.eclipse.persistence.internal.jpa.EJBQueryImpl.<init>(EJBQueryImpl.java:104) ~[org.eclipse.persistence.jpa-4.0.2.jar!/:na]
        at org.eclipse.persistence.internal.jpa.EJBQueryImpl.<init>(EJBQueryImpl.java:88) ~[org.eclipse.persistence.jpa-4.0.2.jar!/:na]
        at org.eclipse.persistence.internal.jpa.EntityManagerImpl.createQuery(EntityManagerImpl.java:1726) ~[org.eclipse.persistence.jpa-4.0.2.jar!/:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
        at org.springframework.orm.jpa.ExtendedEntityManagerCreator$ExtendedEntityManagerInvocationHandler.invoke(ExtendedEntityManagerCreator.java:364) ~[spring-orm-6.1.3.jar!/:6.1.3]
        at jdk.proxy2/jdk.proxy2.$Proxy72.createQuery(Unknown Source) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
        at org.springframework.orm.jpa.SharedEntityManagerCreator$SharedEntityManagerInvocationHandler.invoke(SharedEntityManagerCreator.java:319) ~[spring-orm-6.1.3.jar!/:6.1.3]
        at jdk.proxy2/jdk.proxy2.$Proxy72.createQuery(Unknown Source) ~[na:na]
        at org.springframework.data.jpa.repository.query.AbstractStringBasedJpaQuery.createJpaQuery(AbstractStringBasedJpaQuery.java:157) ~[spring-data-jpa-3.2.2.jar!/:3.2.2]
        at org.springframework.data.jpa.repository.query.AbstractStringBasedJpaQuery.doCreateQuery(AbstractStringBasedJpaQuery.java:99) ~[spring-data-jpa-3.2.2.jar!/:3.2.2]
        at org.springframework.data.jpa.repository.query.AbstractJpaQuery.createQuery(AbstractJpaQuery.java:239) ~[spring-data-jpa-3.2.2.jar!/:3.2.2]
        at org.springframework.data.jpa.repository.query.JpaQueryExecution$CollectionExecution.doExecute(JpaQueryExecution.java:129) ~[spring-data-jpa-3.2.2.jar!/:3.2.2]
        at org.springframework.data.jpa.repository.query.JpaQueryExecution.execute(JpaQueryExecution.java:92) ~[spring-data-jpa-3.2.2.jar!/:3.2.2]
        at org.springframework.data.jpa.repository.query.AbstractJpaQuery.doExecute(AbstractJpaQuery.java:149) ~[spring-data-jpa-3.2.2.jar!/:3.2.2]
        at org.springframework.data.jpa.repository.query.AbstractJpaQuery.execute(AbstractJpaQuery.java:137) ~[spring-data-jpa-3.2.2.jar!/:3.2.2]
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker.doInvoke(RepositoryMethodInvoker.java:170) ~[spring-data-commons-3.2.2.jar!/:3.2.2]
        at org.springframework.data.repository.core.support.RepositoryMethodInvoker.invoke(RepositoryMethodInvoker.java:158) ~[spring-data-commons-3.2.2.jar!/:3.2.2]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.doInvoke(QueryExecutorMethodInterceptor.java:164) ~[spring-data-commons-3.2.2.jar!/:3.2.2]
        at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.invoke(QueryExecutorMethodInterceptor.java:143) ~[spring-data-commons-3.2.2.jar!/:3.2.2]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.3.jar!/:6.1.3]
        at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:70) ~[spring-data-commons-3.2.2.jar!/:3.2.2]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.3.jar!/:6.1.3]
        at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123) ~[spring-tx-6.1.3.jar!/:6.1.3]
        at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:385) ~[spring-tx-6.1.3.jar!/:6.1.3]
        at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119) ~[spring-tx-6.1.3.jar!/:6.1.3]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.3.jar!/:6.1.3]
        at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:137) ~[spring-tx-6.1.3.jar!/:6.1.3]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.3.jar!/:6.1.3]
        at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:135) ~[spring-data-jpa-3.2.2.jar!/:3.2.2]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.3.jar!/:6.1.3]
        at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97) ~[spring-aop-6.1.3.jar!/:6.1.3]
        at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.3.jar!/:6.1.3]
        at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:220) ~[spring-aop-6.1.3.jar!/:6.1.3]
        at jdk.proxy2/jdk.proxy2.$Proxy83.findByCategoryIdIsNegativeOne(Unknown Source) ~[na:na]
        at com.example.demo.controller.MemoController.getMemoByCategoryIsNegativeOne(MemoController.java:60) ~[!/:0.0.1-SNAPSHOT]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
        at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:261) ~[spring-web-6.1.3.jar!/:6.1.3]
        at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:189) ~[spring-web-6.1.3.jar!/:6.1.3]
        at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118) ~[spring-webmvc-6.1.3.jar!/:6.1.3]
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:917) ~[spring-webmvc-6.1.3.jar!/:6.1.3]
        at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:829) ~[spring-webmvc-6.1.3.jar!/:6.1.3]
        at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-6.1.3.jar!/:6.1.3]
        at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089) ~[spring-webmvc-6.1.3.jar!/:6.1.3]
        at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979) ~[spring-webmvc-6.1.3.jar!/:6.1.3]
        at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014) ~[spring-webmvc-6.1.3.jar!/:6.1.3]
        at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:903) ~[spring-webmvc-6.1.3.jar!/:6.1.3]
        at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885) ~[spring-webmvc-6.1.3.jar!/:6.1.3]
        at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:205) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51) ~[tomcat-embed-websocket-10.1.18.jar!/:na]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-6.1.3.jar!/:6.1.3]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.1.3.jar!/:6.1.3]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-6.1.3.jar!/:6.1.3]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.1.3.jar!/:6.1.3]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-6.1.3.jar!/:6.1.3]
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.1.3.jar!/:6.1.3]
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:482) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:340) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:391) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:896) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1744) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) ~[tomcat-embed-core-10.1.18.jar!/:na]
        at java.base/java.lang.Thread.run(Thread.java:842) ~[na:na]
```
