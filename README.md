# JavaWEB_heimamm

## Web项目实战-黑马面面

**前台**

*注册*

![](https://s2.loli.net/2022/06/04/hKv1I5PYSo8Nbat.png)

*登录*

![](https://s2.loli.net/2022/06/04/rE5gydLvDINhfC4.png)

*首页*

![](https://s2.loli.net/2022/06/04/ipWObZ59ysFE4tI.png)

*试卷*

![](https://s2.loli.net/2022/06/04/Q6OKgxVC2Z4FTw8.png)

**后台**

*登录*

![](https://s2.loli.net/2022/06/04/wv4NdXbCqBlThmK.png)

*首页*

![](https://s2.loli.net/2022/06/04/hO9oXrP7UZTkbfe.png)

*管理*

![image-20220604215417014](C:\Users\lihao\AppData\Roaming\Typora\typora-user-images\image-20220604215417014.png)

![image-20220604215427493](C:\Users\lihao\AppData\Roaming\Typora\typora-user-images\image-20220604215427493.png)

![image-20220604215440137](C:\Users\lihao\AppData\Roaming\Typora\typora-user-images\image-20220604215440137.png)

*编辑*

![](https://s2.loli.net/2022/06/04/jzYIAu38P7VoKHv.png)

![](https://s2.loli.net/2022/06/04/izSTeMdxF4qKgor.png)

### 工程搭建

#### 系统架构

![](https://s2.loli.net/2022/05/16/6HsJe71vTQSZq2h.png)

- 前后台共用一组数据（存储到MySQL）
- 均采用三层架构（表现层、业务层、持久层）

#### 技术架构

![](https://s2.loli.net/2022/05/16/qDgGZQAajOoRv5Y.png)

#### 需求分析

![](https://s2.loli.net/2022/05/16/9MYKh6juQGHgbVc.png)

> 图中连线表示一对多的情况，有原点的一方为多

- 日志应该是遍布整个系统的，各个操作都可以留下操作日志
- ss——系统模块，st——后台业务模块，tr——前台业务模块
- 这三个模块名称直接对应数据库中各个表的表前缀

#### 工程结构

- 创建Maven工程（web工程）
- 导入项目依赖的 坐标
- 补全目录结构
  - src
    - java
    - resources
    - webapp
  - test
    - java
    - resources

![](https://s2.loli.net/2022/05/16/2kPaUvNJoDyuMLY.png)

- 创建三层架构开发的包层次结构
  - domain（实体类）
  - dao（持久层）
  - service（业务层）
  - web（表现层）
    - controller
    - filters
  - utils（工具类）
  - factory（工厂类）

![](https://s2.loli.net/2022/05/16/GNE2XIFbxd3QWvs.png)

**pom.xml**

![](https://s2.loli.net/2022/05/16/ezgJl1sjtZfqS8Q.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">

  <modelVersion>4.0.0</modelVersion>
  <packaging>war</packaging>

  <name>heimamm</name>
  <groupId>top.hellocode</groupId>
  <artifactId>heimamm</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <dependencies>
    <!--mybatis_-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.3</version>
    </dependency>
    <!--分页插件-->
    <dependency>
      <groupId>com.github.pagehelper</groupId>
      <artifactId>pagehelper</artifactId>
      <version>5.1.2</version>
    </dependency>
    <!--mysql-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.46</version>
    </dependency>
    <!--druid数据源-->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.1.21</version>
    </dependency>
    <!--junit-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
    <!-- servlet3.0 -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
    <!--jsp-->
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>javax.servlet.jsp-api</artifactId>
      <version>2.3.3</version>
      <scope>provided</scope>
    </dependency>
    <!--bean-utils-->
    <dependency>
      <groupId>commons-beanutils</groupId>
      <artifactId>commons-beanutils</artifactId>
      <version>1.9.4</version>
    </dependency>
    <!--apache工具包-->
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-lang3</artifactId>
      <version>3.9</version>
    </dependency>
    <!--jstl-->
    <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>
    <!--jackson-->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-annotations</artifactId>
      <version>2.9.0</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.9.0</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.9.0</version>
    </dependency>
    <!--文件上传-->
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.1</version>
    </dependency>
    <!--POI-->
    <dependency>
      <groupId>org.apache.poi</groupId>
      <artifactId>poi</artifactId>
      <version>4.0.1</version>
    </dependency>
    <dependency>
      <groupId>org.apache.poi</groupId>
      <artifactId>poi-ooxml</artifactId>
      <version>4.0.1</version>
    </dependency>
    <dependency>
      <groupId>org.apache.poi</groupId>
      <artifactId>poi-ooxml-schemas</artifactId>
      <version>4.0.1</version>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <!--tomcat插件-->
      <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.1</version>
        <configuration>
          <port>80</port>
          <path>/</path>
        </configuration>
      </plugin>
    </plugins>
  </build>

</project>
```

**sql导入**

> 内容有点多，直接给大家sql文件，大家直接下载吧

[heima_mm.sql](http://www.hellocode.top/update/heima_mm.sql )

#### 页面结构

![](https://s2.loli.net/2022/05/16/tJQ37Ol8cBvWdoE.png)

![](https://s2.loli.net/2022/05/16/or451jwBWsuQReC.png)

- AdminLTE后台框架是一款建立在bootstrap 和 jQuery 之上的开源模板主题工具，其中内置了多个模块页面，可以用于快速创建响应式Html5网站，并免去了书写大量的CSS与JS的工作
- 后台框架很多，大家选择自己喜欢的进行删减修改即可

### 企业模块

- 先从简单的模块开始做（企业模块——单表查询）
- Company归属于后台模块，所以在对应的包下再建一个包store

#### 数据层

**domain**

```java
private String id;
private String name;	// 名称
private Date expirationDate;	// 注册日期
private String address;		// 地址
private String licenseId;		// 营业执照编号
private String representative;		// 法人
private String phone;		// 电话
private String companySize;		// 规模
private String industry;		// 所属行业
private String remarks;			// 备注 / 描述
private String state;			// 状态
private String city;			// 所在地
```

**dao**

- 因为是使用MaBatis，所以只写接口即可，实现使用动态代理方式
- 同样在dao下再建一个store包

```java
public interface CompanyDao {
    int save(Company company);		// 保存
    int delete(Company company);    // 删除
    int update(Company company);    // 修改
    Company findById(String id);    // 通过id查询
    List<Company> findAll();        // 查询全部
}
```

**配置文件**

- 配置文件统一放到资源目录下(resources)
- 需要的配置文件
  - MyBatis总配置文件(SqlMapConfig.xml)
  - 每个模块的映射配置文件(CompanyDao.xml)
  - 数据库配置文件(jdbc.properties)

> `resources`的配置文件所在目录应该和上面的dao等工程目录一致
>
> 在`resources`中新建`top.hellocode.dao.store`，然后把`CompanyDao.xml`放进去

*SqlMapConfig.xml*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--mybatis的主配置文件-->
<configuration>
    <!--定义property的配置-->
    <properties resource="jdbc.properties"></properties>


    <!--配置别名:别名配置是给实体类用的，不能用在dao上-->
    <typeAliases>
        <!--指定实体类所在的包，此时包下所有类都会注册别名，别名就是类名称-->
        <package name="top.hellocode.domain"></package>
    </typeAliases>

    <!--配置分页插件，plugins标签有出现顺序要求，必须在environments之前，同时要在properties和typeAliases之后 -->
    <plugins>
        <!-- com.github.pagehelper.PageInterceptor -->
        <plugin interceptor="com.github.pagehelper.PageInterceptor">
            <property name="helperDialect" value="mysql" />
            <property name="reasonable" value="true" />
        </plugin>
    </plugins>

    <!--配置默认环境-->
    <environments default="mysql">
        <!--配置mysql的环境-->
        <environment id="mysql">
            <!--配置事务的类型-->
            <transactionManager type="JDBC"></transactionManager>
            <!--配置数据源信息-->
            <dataSource type="POOLED" >
                <!--配置连接数据库的四个基本信息-->
                <property name="driver" value="${jdbc.driver}"></property>
                <property name="url" value="${jdbc.url}"></property>
                <property name="username" value="${jdbc.username}"></property>
                <property name="password" value="${jdbc.password}"></property>
            </dataSource>
        </environment>
    </environments>



    <!--配置映射配置文件的位置-->
    <mappers>
        <!--指定实体映射配置文件所在的包，指定的是dao接口所在的包-->
        <package name="top.hellocode.dao"></package>
    </mappers>


</configuration>
```

*jdbc.properties*

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://192.168.23.129/heima_mm
jdbc.username=root
jdbc.password=密码
```

*CompanyDao.xml*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.itheima.dao.store.CompanyDao">

    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="com.itheima.domain.store.Company">
        <id column="id" jdbcType="VARCHAR" property="id"/>
        <result column="name" jdbcType="VARCHAR" property="name"/>
        <result column="expiration_date" jdbcType="TIMESTAMP" property="expirationDate"/>
        <result column="address" jdbcType="VARCHAR" property="address"/>
        <result column="license_id" jdbcType="VARCHAR" property="licenseId"/>
        <result column="representative" jdbcType="VARCHAR" property="representative"/>
        <result column="phone" jdbcType="VARCHAR" property="phone"/>
        <result column="company_size" jdbcType="VARCHAR" property="companySize"/>
        <result column="industry" jdbcType="VARCHAR" property="industry"/>
        <result column="remarks" jdbcType="VARCHAR" property="remarks"/>
        <result column="state" jdbcType="INTEGER" property="state"/>
        <result column="city" jdbcType="VARCHAR" property="city"/>
    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        id, name, expiration_date, address, license_id, representative, phone, company_size,
        industry, remarks, state, city
    </sql>

    <!--配置查询所有，带条件-->
    <select id="findAll" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from st_company
    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from st_company
        where id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from st_company where id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入-->
    <insert id="save" parameterType="com.itheima.domain.store.Company">
        insert into st_company (id, name, expiration_date,
        address, license_id, representative,
        phone, company_size, industry,
        remarks, state,
        city)
        values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{expirationDate,jdbcType=TIMESTAMP},
        #{address,jdbcType=VARCHAR}, #{licenseId,jdbcType=VARCHAR}, #{representative,jdbcType=VARCHAR},
        #{phone,jdbcType=VARCHAR}, #{companySize,jdbcType=VARCHAR}, #{industry,jdbcType=VARCHAR},
        #{remarks,jdbcType=VARCHAR}, #{state,jdbcType=INTEGER},
        #{city,jdbcType=VARCHAR})
    </insert>

    <!--配置全字段更新-->
    <update id="update" parameterType="com.itheima.domain.store.Company">
        update st_company
        set name = #{name,jdbcType=VARCHAR},
        expiration_date = #{expirationDate,jdbcType=TIMESTAMP},
        address = #{address,jdbcType=VARCHAR},
        license_id = #{licenseId,jdbcType=VARCHAR},
        representative = #{representative,jdbcType=VARCHAR},
        phone = #{phone,jdbcType=VARCHAR},
        company_size = #{companySize,jdbcType=VARCHAR},
        industry = #{industry,jdbcType=VARCHAR},
        remarks = #{remarks,jdbcType=VARCHAR},
        state = #{state,jdbcType=INTEGER},
        city = #{city,jdbcType=VARCHAR}
        where id = #{id,jdbcType=VARCHAR}
    </update>
</mapper>
```

**工具类**

- MapperFactory：负责创建mapper工厂的工具类
  - 读取配置文件
  - 返回sqlSession对象
  - 返回mapper对象

```java
package top.hellocode.factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 用于生成dao接口代理实现类的工厂
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class MapperFactory {

    private static SqlSessionFactory factory;
    private static ThreadLocal<SqlSession> tl = new ThreadLocal<SqlSession>();

    static {
        InputStream in = null;
        try {
            //1.读取mybatis主配置文件
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            //2.创建构建者对象
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //3.使用构建者创建SqlSessionFactory工厂
            factory = builder.build(in);
        }catch (Exception e){
            //打印异常信息到控制台
            e.printStackTrace();
            //抛出错误提示程序终止执行
            throw new ExceptionInInitializerError("初始化SqlSessionFactory失败");
        }finally {
            //释放流对象
            if(in != null){
                try{
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取SqlSession对象
     * @return
     * 保留此方法是为了后面对业务层方法增强，利用AOP添加事务
     */
    public static SqlSession getSqlSession(){
         return factory.openSession(false);
    }

    /**
     * 获取Dao接口的代理实现类
     * @param daoClass dao接口字节码
     * @return
     */
    public static <T> T getMapper(SqlSession sqlSession,Class<T> daoClass){
        //1.判断传入的SqlSession是否为null
        if(sqlSession == null){
            return null;
        }
        //2.不为null，创建代理实现类
        return sqlSession.getMapper(daoClass);
    }
}
```

- TransactionUtil：事务管理工具类
  - 提交事务
  - 回滚事务
  - 关闭事务

```java
package top.hellocode.utils;

import org.apache.ibatis.session.SqlSession;

/**
 * 事务控制类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class TransactionUtil {

    /**
     * 提交释放
     * @param sqlSession
     */
    public static void commit(SqlSession sqlSession){
        if(sqlSession!=null) {
            sqlSession.commit();
        }
    }

    /**
     * 回滚释放
     * @param sqlSession
     */
    public static void rollback(SqlSession sqlSession){
        if(sqlSession!=null) {
            sqlSession.rollback();
        }
    }

    /**
     * 单独释放
     * @param sqlSession
     */
    public static void close(SqlSession sqlSession){
        if(sqlSession!=null) sqlSession.close();
    }
}
```

#### 业务层

> 对于任意一个模块来说，当你不清楚具体功能时，可以先把它的基础功能做出来

**基础功能**

- 增、删、改、查
- 查单个
- 查全部
- 分页查（分页插件）

```java
int save(Company company);		// 保存
int delete(Company company);    // 删除
int update(Company company);    // 修改
Company findById(String id);    // 通过id查询
List<Company> findAll();        // 查询全部
PageInfo findAll(int page, int size);   // 分页查询
```

**CompanyServiceImpl**

```java
package top.hellocode.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.store.CompanyDao;
import top.hellocode.domain.store.Company;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.store.CompanyService;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月16日 20:20
 */
public class CompanyServiceImpl implements CompanyService {

    @Override
    public void save(Company company) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            company.setId(id);

            // 调用Dao层操作
            companyDao.save(company);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Company company) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // 调用Dao层操作
            companyDao.delete(company);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Company company) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // 调用Dao层操作
            companyDao.update(company);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Company findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // 调用Dao层操作
            return companyDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Company> findAll() {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // 调用Dao层操作
            return companyDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            CompanyDao companyDao = MapperFactory.getMapper(sqlSession,CompanyDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<Company> all = companyDao.findAll();
            PageInfo pageInfo = new PageInfo<>(all);
            return pageInfo;
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```

> 实现分页功能时，`startPage`需要写在查询之前

**测试用例**(CompanyServiceTest)

```java
package top.hellocode.service.store;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import top.hellocode.domain.store.Company;
import top.hellocode.service.store.impl.CompanyServiceImpl;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月16日 20:27
 */
public class CompanyServiceTest {
    private static CompanyService companyService = null;
    @BeforeClass
    public static void init(){
        companyService = new CompanyServiceImpl();
    }
    @Test
    public void testSave(){
        Company company = new Company();
        company.setName("测试数据");
        companyService.save(company);
    }
    @Test
    public void testFindAll(){
        Company company = new Company();
        System.out.println(companyService.findAll(1, 100));
    }
    @AfterClass
    public static void destory(){
        companyService = null;
    }
}
```

- 在实际开发时，每写一个方法最好写一个测试用例，来及时的排错
- 配置resources中的文件夹结构时，不要直接新建一个多级目录，例如`top.hellocode.dao`
  系统会直接创建一个名为`top.hellocode.dao`的文件夹，而不是多级目录

#### 表现层

- 页面保存位置：WEB-INFO下 
- 功能访问：使用请求参数operation的值区分是何种操作
- 功能分发：使用if...else...进行功能分发，根据operation的参数值转向不同的操作
- 数据获取：调用业务层接口
- 数据共享：request范围数据共享
- 分页功能实现：按照规范开发，名称与common.jsp文件命名的相同即可（快速开发）

```java
```

**列表页面**

*list.jsp*

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
    <script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/store/company?operation=delete&id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        企业管理
        <small>企业列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
<section class="content">

    <!-- .box-body -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/store/company?operation=toAdd"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">

                        </th>
                        <th class="sorting">企业名称</th>
                        <th class="sorting">所在地</th>
                        <th class="sorting">地址</th>
                        <th class="sorting">企业法人</th>
                        <th class="sorting">联系方式</th>
                        <th class="sorting">所属行业</th>
                        <th class="sorting">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="item">
                    <tr>
                        <td><input name="ids" value="${item.id}" type="checkbox"></td>
                        <td>
                            ${item.name}
                        </td>
                        <td>${item.city}</td>
                        <td>${item.address}</td>
                        <td>${item.representative}</td>
                        <td>${item.phone}</td>
                        <td>${item.industry}</td>
                        <td>${item.state ==0?'未审核':'已审核'}</td>
                        <td class="text-center">
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/store/company?operation=toEdit&id=${item.id}"'>编辑</button>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- /.box-body -->


         <div class="box-footer">
             <jsp:include page="../../common/page.jsp">
                 <jsp:param value="${ctx}/store/company?operation=list" name="pageUrl"/>
             </jsp:include>
         </div>
        <!-- /.box-footer-->

    </div>
</section>
</div>
</body>

</html>
```

```java
private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 获取数据
    CompanyService companyService = new CompanyServiceImpl();
    int page = 1;
    int size = 5;
    if(StringUtils.isNotBlank(request.getParameter("page"))){
        page = Integer.parseInt(request.getParameter("page"));
    }
    if(StringUtils.isNotBlank(request.getParameter("size"))){
        page = Integer.parseInt(request.getParameter("size"));
    }

    PageInfo all = companyService.findAll(page, size);
    // 将数据保存到指定的位置
    request.setAttribute("page",all);
    // 跳转页面
    request.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(request,response); //将页面放到web-info下（安全）
}
```

**添加页面**

- 跳转到添加页面（toAdd）、保存操作（save）

*toAdd*

- 跳转到add.jsp页面

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent">
    <!-- 内容头部 -->
    <section class="content-header" class="content-wrapper" style="margin-left:0px;">
        <h1>
            企业管理
            <small>企业表单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">企业管理</a></li>
            <li class="active">企业表单</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--添加内容信息-->
        <div class="panel panel-default">
            <div class="panel-heading">企业信息</div>
            <form id="editForm" action="${ctx}/store/company?operation=save" method="post">
                <input type="hidden" name="id" value="${company.id}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">企业名称</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="企业名称" name="name" value="${company.name}">
                    </div>

                    <div class="col-md-2 title">营业执照</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="营业执照" name="licenseId" value="${company.licenseId}">
                    </div>

                    <div class="col-md-2 title">所在城市</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="所在地" name="city" value="${company.city}">
                    </div>

                    <div class="col-md-2 title">企业地址</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="企业地址" name="address" value="${company.address}">
                    </div>

                    <div class="col-md-2 title">法人代表</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="法人代表" name="representative" value="${company.representative}">
                    </div>

                    <div class="col-md-2 title">联系电话</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="联系电话" name="phone" value="${company.phone}">
                    </div>

                    <div class="col-md-2 title">公司规模</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="公司规模" name="companySize" value="${company.companySize}">
                    </div>

                    <div class="col-md-2 title">所属行业</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="所属行业" name="industry" value="${company.industry}">
                    </div>
                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data">
                        <select class="form-control select2" name="state" style="width: 100%;">
                            <option value="0" ${company.state==0 ? 'selected':''}>未审核</option>
                            <option value="1" ${company.state==1 ? 'selected':''}>已审核</option>
                        </select>
                        <input type="text" class="form-control" placeholder="状态" name="state" value="${company.state}">
                    </div>


                    <div class="col-md-2 title">到期时间</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="到期时间"  name="expirationDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${company.expirationDate}" pattern="yyyy-MM-dd"/>" id="expirationDate">
                        </div>
                    </div>

                    <div class="col-md-2 title rowHeight2x">备注</div>
                    <div class="col-md-10 data rowHeight2x">
                        <textarea class="form-control" rows="3" name="remarks">${company.remarks}</textarea>
                    </div>
                </div>
            </form>
        </div>
        <!--添加内容信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="${ctx}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${ctx}/css/style.css">
<script>
    $('#expirationDate').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>

</html>
```

```java
private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 跳转页面
    request.getRequestDispatcher("/WEB-INF/pages/store/company/add.jsp").forward(request,response); //将页面放到web-info下（安全）
}
```

*save*

1. 获取到表单中提交的数据，封装成一个对象（BeanUtils）
2. 数据持久化：调用业务层接口save
3. 跳转回list页面

```java
package top.hellocode.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * 填充表单数据到javabean的工具类
 * @author zhy
 *
 */
public class BeanUtil {
	/**
	 * 封装表单中的数据到javabean中
	 * @param request	表单中的数据
	 * @param clazz		封装到哪个javabean
	 * @return	封装好的javabean对象
	 * 使用的是泛型。泛型必须先声明再使用。声明必须在返回值之前
	 * T指的就是泛型，它可以是任意字符，只是作为一个占位符。
	 * 声明时用什么字符，使用时就得用什么
	 */
	public static <T> T fillBean(HttpServletRequest request,Class<T> clazz){
		//1.定义一个T类型的javabean
		T bean = null;
		try{
			//2.实例化bean对象
			bean = clazz.newInstance();
			//3.使用BeanUtils的方法进行封装
			BeanUtils.populate(bean, request.getParameterMap());
			//4.返回
			return bean;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}



	/**
	 * 封装表单中的数据到javabean中,带有日期格式数据
	 * @param request	表单中的数据
	 * @param clazz		封装到哪个javabean
	 * @return	封装好的javabean对象
	 * 使用的是泛型。泛型必须先声明再使用。声明必须在返回值之前
	 * T指的就是泛型，它可以是任意字符，只是作为一个占位符。
	 * 声明时用什么字符，使用时就得用什么
	 */
	public static <T> T fillBean(HttpServletRequest request,Class<T> clazz,String datePattern){
		//1.定义一个T类型的javabean
		T bean = null;
		try{
			//2.实例化bean对象
			bean = clazz.newInstance();
			//3.创建日期转换器对象
			DateConverter converter = new DateConverter();
			converter.setPattern(datePattern);
			//4.设置转换器
			ConvertUtils.register(converter, Date .class);
			//5.使用BeanUtils的方法进行封装
			BeanUtils.populate(bean, request.getParameterMap());
			//6.返回
			return bean;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 文件上传的表单填充
	 * @param items	文件上传的表单项
	 * @param clazz	要封装的实体类字节码
	 * @param <T>	泛型
	 * @return		返回封装好的对象
	 */
	public static <T> T fillBean(List<FileItem> items,Class<T> clazz){
		//1.定义一个T类型的javabean
		T bean = null;
		try{
			//2.实例化Bean
			bean = clazz.newInstance();
			//3.遍历文件项集合
			for(FileItem item : items){
				//4.判断是不是文件
				if(item.isFormField()){//表单字段，不是文件
					//5.取出表单中的name属性取值
					String fieldName = item.getFieldName();
					//6.使用UTF-8字符集取出表单数据
					String fieldValue = item.getString("UTF-8");
					//7.创建属性描述器对象
					PropertyDescriptor pd = new PropertyDescriptor(fieldName,clazz);
					//8.获取写方法(setXXX方法）
					Method method = pd.getWriteMethod();
					//9.把数据填充到bean中
					method.invoke(bean,fieldValue);
				}
			}
			//10.返回
			return bean;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
```

> 上面的 BeanUtil是对BeanUtils的一个再封装，简化操作

```java
private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // 获取数据并封装对象
    Company company = BeanUtil.fillBean(request,Company.class,"yyyy-MM-dd");
    // 调用业务层接口
    CompanyService companyService = new CompanyServiceImpl();
    companyService.save(company);
    // 跳转页面
    response.sendRedirect(request.getContextPath() + "/store/company?operation=list");
}
```

**修改和删除功能**

*update*

1. 查询要修改的ID
2. 将数据加载到指定区域，供页面获取
3. 跳转页面

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            企业管理
            <small>编辑企业信息</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">企业管理</a></li>
            <li class="active">编辑企业信息</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">企业信息</div>
            <form id="editForm" action="${ctx}/store/company?operation=edit" method="post">
                <input type="hidden" name="id" value="${company.id}">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">企业名称</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="企业名称" name="name" value="${company.name}">
                    </div>

                    <div class="col-md-2 title">营业执照</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="营业执照" name="licenseId" value="${company.licenseId}">
                    </div>

                    <div class="col-md-2 title">所在城市</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="所在地" name="city" value="${company.city}">
                    </div>

                    <div class="col-md-2 title">企业地址</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="企业地址" name="address" value="${company.address}">
                    </div>

                    <div class="col-md-2 title">法人代表</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="法人代表" name="representative" value="${company.representative}">
                    </div>

                    <div class="col-md-2 title">联系电话</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="联系电话" name="phone" value="${company.phone}">
                    </div>

                    <div class="col-md-2 title">公司规模</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="公司规模" name="companySize" value="${company.companySize}">
                    </div>

                    <div class="col-md-2 title">所属行业</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="所属行业" name="industry" value="${company.industry}">
                    </div>
                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data">
                        <select class="form-control select2" name="state" style="width: 100%;">
                            <option value="0" ${company.state==0 ? 'selected':''}>未审核</option>
                            <option value="1" ${company.state==1 ? 'selected':''}>已审核</option>
                        </select>
                        <input type="text" class="form-control" placeholder="状态" name="state" value="${company.state}">
                    </div>

                    <div class="col-md-2 title rowHeight2x">备注</div>
                    <div class="col-md-4 data rowHeight2x">
                        <textarea class="form-control" rows="3" name="remarks">${company.remarks}</textarea>
                    </div>
                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>

</html>
```

```java
private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 拿到要编辑的数据
    String id = request.getParameter("id");
    // 调用业务层方法查询数据
    CompanyService companyService = new CompanyServiceImpl();
    Company company = companyService.findById(id);
    request.setAttribute("company",company);
    // 跳转页面
    request.getRequestDispatcher("/WEB-INF/pages/store/company/update.jsp").forward(request,response); //将页面放到web-info下（安全）
}

private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // 获取数据并封装对象
    Company company = BeanUtil.fillBean(request,Company.class,"yyyy-MM-dd");
    // 调用业务层接口
    CompanyService companyService = new CompanyServiceImpl();
    companyService.update(company);
    // 跳转页面
    response.sendRedirect(request.getContextPath() + "/store/company?operation=list");
}
```

**删除**

```jsp
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/store/company?operation=delete&id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>

<div class="pull-left">
    <div class="form-group form-inline">
        <div class="btn-group">
            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/store/company?operation=toAdd"'><i class="fa fa-file-o"></i> 新建</button>
            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
        </div>
    </div>
</div>
```

```java
private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // 获取数据并封装对象
    Company company = BeanUtil.fillBean(request,Company.class);
    // 调用业务层接口
    CompanyService companyService = new CompanyServiceImpl();
    companyService.delete(company);
    // 跳转页面
    response.sendRedirect(request.getContextPath() + "/store/company?operation=list");
}
```

**优化**

> 通过上面的代码我们可以发现，Servlet中每个方法都需要new一个CompanyService对象，因此我们可以把它抽取出来

- 在controller包下创建一个BaseServlet类，让它继承HttpServlet
- 成员变量就可以写上需要反复创建的对象
- 继承了HttpServlet类，所以可以重写init方法，在init方法中完成对成员变量的赋值
- 让CompanyServlet继承BaseServlet（因为BaseServlet继承了HttpServlet，所以CompanyServlet同样可以使用HttpServlet中的方法）

*BaseServlet*

```java
package top.hellocode.web.controller;

import top.hellocode.service.store.CompanyService;
import top.hellocode.service.store.impl.CompanyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月19日 22:04
 */
public class BaseServlet extends HttpServlet {
    protected CompanyService companyService;

    @Override
    public void init() throws ServletException {
        companyService = new CompanyServiceImpl();
    }
}
```

*CompanyServlet*

```java
package top.hellocode.web.controller.company;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.store.Company;
import top.hellocode.service.store.CompanyService;
import top.hellocode.service.store.impl.CompanyServiceImpl;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月16日 21:34
 */

// uri:/store/company?operation=list

@WebServlet("/store/company")
public class CompanyServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            this.save(request,response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        CompanyService companyService = new CompanyServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = companyService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/company/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Company company = BeanUtil.fillBean(request,Company.class,"yyyy-MM-dd");
        // 调用业务层接口
//        CompanyService companyService = new CompanyServiceImpl();
        companyService.save(company);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/store/company?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");
        // 调用业务层方法查询数据
//        CompanyService companyService = new CompanyServiceImpl();
        Company company = companyService.findById(id);
        request.setAttribute("company",company);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/company/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Company company = BeanUtil.fillBean(request,Company.class,"yyyy-MM-dd");
        // 调用业务层接口
//        CompanyService companyService = new CompanyServiceImpl();
        companyService.update(company);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/store/company?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Company company = BeanUtil.fillBean(request,Company.class);
        // 调用业务层接口
//        CompanyService companyService = new CompanyServiceImpl();
        companyService.delete(company);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/store/company?operation=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
```

### 部门模块

- 在企业中，部门可能存在一级部门、二级部门
- 在开发时，可以先按单表进行开发，后面再把相应的关系加上（自关联）
- 用户模块属于系统功能，放在system下

#### 分析

```java
private String id;
private String deptName;	// 部门名称
private String parentId;	// 所属部门id
private Integer state;		// 状态
```

- 基本操作：参看单表增删改查
- 注意
  - 替换时注意大小写匹配
  - 替换前要清晰认知替换的内容是什么，同时规范命名习惯，避免出错

**部门模块自连接**

- 在domain中加一个字段
  `private Dept parent`

- 在映射配置文件中配置关系映射

  ```xml
  <association
  	property="parent"
      javaType="top.hellocode.domain.system.Dept"
      column="parent_id"
      select="top.hellocode.dao.system.DeptDao.findById"
               />
  ```

> 注意：添加页和修改页进入前需要加载到列表数据

#### 代码

**add**

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                系统管理
                <small>部门管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">编辑部门</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/system/dept?operation=save" method="post">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">部门名称</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="部门名称" name="deptName" value="${dept.deptName}">
                                    </div>
                                    <div class="col-md-2 title">所属部门</div>
                                    <div class="col-md-10 data line-height36">
                                        <select class="form-control" name="parentId">
                                            <option value="">请选择</option>
                                            <c:forEach items="${deptList}" var="item">
                                                <option ${dept.parent.id == item.id ?'selected':''} value="${item.id}">${item.deptName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-2 title">状态</div>
                                    <div class="col-md-10 data">
                                        <div class="form-group form-inline">
                                            <div class="radio"><label><input type="radio" ${dept.state==0?'checked':''} name="state" value="0">停用</label></div>
                                            <div class="radio"><label><input type="radio" ${dept.state==1?'checked':''} name="state" value="1">启用</label></div>
                                        </div>
                                    </div>
                                    <div class="col-md-2 title"></div>
                                    <div class="col-md-10 data text-center">
                                        <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</body>

</html>
```

**list**

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/system/dept?operation=delete&id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        系统管理
        <small>部门管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
<section class="content">

    <!-- .box-body -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">部门列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/system/dept?operation=toAdd"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">部门名称</th>
                        <th class="sorting">所属部门</th>
                        <th class="sorting">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="dept">
                        <tr>
                            <td><input type="checkbox" name="id" value="${dept.id }"/></td>
                            <td>${dept.deptName }</td>
                            <td>${dept.parent.deptName }</td>
                            <td>${dept.state ==0?'未启用':'使用中'}</td>

                            <th class="text-center">
                                <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/system/dept?operation=toEdit&id=${dept.id}"'>编辑</button>
                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/system/dept?operation=list" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>
</section>
</div>
</body>
</html>
```

**update**

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                系统管理
                <small>部门管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">编辑部门</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/system/dept?operation=edit" method="post">
                            <input type="hidden" name="id" value="${dept.id}">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">部门名称</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="部门名称" name="deptName" value="${dept.deptName}">
                                    </div>
                                    <div class="col-md-2 title">所属部门</div>
                                    <div class="col-md-10 data line-height36">
                                        <select class="form-control" name="parentId">
                                            <option value="">请选择</option>
                                            <c:forEach items="${deptList}" var="item">
                                                <option ${dept.parent.id == item.id ?'selected':''} value="${item.id}">${item.deptName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-2 title">状态</div>
                                    <div class="col-md-10 data">
                                        <div class="form-group form-inline">
                                            <div class="radio"><label><input type="radio" ${dept.state==0?'checked':''} name="state" value="0">停用</label></div>
                                            <div class="radio"><label><input type="radio" ${dept.state==1?'checked':''} name="state" value="1">启用</label></div>
                                        </div>
                                    </div>
                                    <div class="col-md-2 title"></div>
                                    <div class="col-md-10 data text-center">
                                        <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</body>

</html>
```

**DeptDao.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.system.DeptDao">
    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.system.Dept">
        <id column="dept_id" jdbcType="VARCHAR" property="id"/>
        <result column="dept_name" jdbcType="VARCHAR" property="deptName"/>
        <result column="parent_id" jdbcType="VARCHAR" property="parentId"/>
        <result column="state" jdbcType="DECIMAL" property="state"/>
        <association
            property="parent"
            javaType="top.hellocode.domain.system.Dept"
            column="parent_id"
            select="top.hellocode.dao.system.DeptDao.findById"
            />
    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        dept_id, dept_name, parent_id, state
    </sql>

    <!--配置查询所有，带条件-->
    <select id="findAll"  resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_dept
    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_dept
        where dept_id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from ss_dept  where dept_id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="save" parameterType="top.hellocode.domain.system.Dept">
        insert into ss_dept (dept_id, dept_name, parent_id,state)
        values (#{id,jdbcType=VARCHAR}, #{deptName,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR},#{state,jdbcType=DECIMAL})
    </insert>

    <!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
    <update id="update" parameterType="top.hellocode.domain.system.Dept">
        update ss_dept
        set dept_name = #{deptName,jdbcType=VARCHAR},
        parent_id = #{parentId,jdbcType=VARCHAR},
        state = #{state,jdbcType=DECIMAL}
        where dept_id = #{id,jdbcType=VARCHAR}
    </update>

</mapper>
```

**DeptServlet**

```java
package top.hellocode.web.controller.system;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.system.Dept;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:56
 */


// uri:/system/dept?operation=list

@WebServlet("/system/dept")
public class DeptServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            this.save(request,response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        DeptService deptService = new DeptServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = deptService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Dept dept = BeanUtil.fillBean(request,Dept.class,"yyyy-MM-dd");
        // 调用业务层接口
//        DeptService deptService = new DeptServiceImpl();
        deptService.save(dept);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/dept?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");

        // 加载数据
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        // 调用业务层方法查询数据
        Dept dept = deptService.findById(id);
        request.setAttribute("dept",dept);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/dept/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Dept dept = BeanUtil.fillBean(request,Dept.class,"yyyy-MM-dd");
        // 调用业务层接口
//        DeptService deptService = new DeptServiceImpl();
        deptService.update(dept);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/dept?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Dept dept = BeanUtil.fillBean(request,Dept.class);
        // 调用业务层接口
//        DeptService deptService = new DeptServiceImpl();
        deptService.delete(dept);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/dept?operation=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
```

#### 用户模块

- 同样属于系统模块(system)

```java
private String id;
private String email;   // 邮箱
private String username;    // 姓名
private String password;    // 密码
private Long state;         // 状态
private String gender;      // 性别
private String telephone;   // 电话
private Date birthday;  // 出生年月
private Date joinDate;  // 入职时间
private String deptId;  // 部门id

private Dept dept;
```

**保存**

- 在业务层save之前，需要对密码进行加密（MD5）
- 使用工具类：MD5Util

```java
package top.hellocode.utils;

import java.security.MessageDigest;
import sun.misc.BASE64Encoder;

/**
 * 密码加密工具类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class MD5Util {

    /**
     * 密码加密
     * @param password
     * @return
     * @throws Exception
     */
    public static String  md5(String password){
        try {
            //1.创建加密对象
            MessageDigest md5 = MessageDigest.getInstance("md5");
            //2.加密密码
            byte[] by = md5.digest(password.getBytes());
            //3.创建编码对象
            BASE64Encoder encoder = new BASE64Encoder();
            //4.对结果编码
            return encoder.encode(by);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
```

**修改**

- 需要对能修改的内容进行限制
- 比如：性别、出生日期、入职日期等并不值得修改
- 三种方案
  - 读取库中的信息，覆盖现有的数据
  - 修改update语句（只对需要修改的进行操作）
  - 不显示不需要修改的数据

```xml
<update id="update" parameterType="top.hellocode.domain.system.User">
    update ss_user
    user_name = #{userName,jdbcType=VARCHAR},
    state = #{state,jdbcType=DECIMAL},
    gender = #{gender,jdbcType=CHAR},
    telephone = #{telephone,jdbcType=VARCHAR},
    dept_id = #{deptId,jdbcType=VARCHAR}
    where user_id = #{id,jdbcType=VARCHAR}
</update>
```

> 理论上应该提供两个update方法，一个全，一个部分

*list*

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/system/user?operation=delete&id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function roleList() {
        var id = getCheckId()
        if(id) {
            location.href="${ctx}/system/user?operation=userRoleList&id="+id;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        系统管理
        <small>用户管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">用户列表</h3>
        </div>
        <div class="box-body">
            <div class="table-box">
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/system/user?operation=toAdd"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                            <button type="button" class="btn btn-default" title="角色" onclick="roleList()"><i class="fa fa-user-circle-o"></i> 角色</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;"></th>
                        <th class="sorting">邮箱</th>
                        <th class="sorting">用户名</th>
                        <th class="sorting">性别</th>
                        <th class="sorting">所属部门</th>
                        <th class="sorting">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="item" varStatus="status">
                    <tr>
                        <td><input name="ids" value="${item.id}" type="checkbox"></td>
                        <td>${item.email }</td>
                        <td>${item.userName}</td>
                        <td>${item.gender ==0?'男':'女'}</td>
                        <td>${item.dept.deptName }</td>
                        <td>${item.state  ==0?'停用':'启用'}</td>
                        <th class="text-center">
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/system/user?operation=toEdit&id=${item.id}"'>编辑</button>
                        </th>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/system/user?operation=list" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>

</section>
</div>
</body>

</html>
```

*add*

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            系统管理
            <small>用户管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${ctx}/system/user?operation=list">用户列表</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">用户信息</div>
            <form id="editForm" action="${ctx}/system/user?operation=save" method="post">
                <input type="hidden" id="id" name="id" value="">
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-2 title">邮箱</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="邮箱" name="email">
                    </div>

                    <div class="col-md-2 title">密码</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="密码" name="password">
                    </div>

                    <div class="col-md-2 title">姓名</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="姓名" name="userName">
                    </div>

                    <div class="col-md-2 title">所在部门</div>
                    <div class="col-md-4 data">
                        <select class="form-control" onchange="document.getElementById('deptName').value=this.options[this.selectedIndex].text" name="deptId">
                            <option value="">请选择</option>
                            <c:forEach items="${deptList}" var="item">
                                <option ${requestScope.user.deptId == item.id ?'selected':''} value="${item.id}">${item.deptName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-2 title">性别</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${requestScope.user.gender==0?'checked':''} name="gender" value="0">男</label></div>
                            <div class="radio"><label><input type="radio" ${requestScope.user.gender==1?'checked':''} name="gender" value="1">女</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title">出生年月</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="出生年月" class="form-control pull-right" name="birthday"
                                   value="${requestScope.user.birthday}" id="datepicker1">
                        </div>
                    </div>

                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${requestScope.user.state==0?'checked':''} name="state" value="0">停用</label></div>
                            <div class="radio"><label><input type="radio" ${requestScope.user.state==1?'checked':'checked'} name="state" value="1">启用</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title">电话</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="电话" name="telephone" value="${requestScope.user.telephone}">
                    </div>

                    <div class="col-md-2 title">入职时间</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="入职时间"  name="joinDate" class="form-control pull-right"
                                   value="${requestScope.user.joinDate}" id="datepicker">
                        </div>
                    </div>

                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="${ctx}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${ctx}/css/style.css">
<script>
    $('#datepicker').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#datepicker1').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>
```

*update*

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            系统管理
            <small>用户管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${ctx}/system/user/list.do">用户列表</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">用户信息</div>
            <form id="editForm" action="${ctx}/system/user?operation=edit" method="post">
                <div class="row data-type" style="margin: 0px">
                    <input type="hidden" name="id" value="${user.id}">

                    <div class="col-md-2 title">邮箱</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="邮箱" name="email" value="${user.email}">
                    </div>

                    <div class="col-md-2 title">密码</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="密码" name="password" value="${user.password}">
                    </div>

                    <div class="col-md-2 title">姓名</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="用户名称" name="userName" value="${user.userName}">
                    </div>

                    <div class="col-md-2 title">所在部门</div>
                    <div class="col-md-4 data">
                        <select class="form-control" onchange="document.getElementById('deptName').value=this.options[this.selectedIndex].text" name="deptId">
                            <option value="">请选择</option>
                            <c:forEach items="${deptList}" var="item">
                                <option ${user.deptId == item.id ?'selected':''} value="${item.id}">${item.deptName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-md-2 title">性别</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${user.gender==0?'checked':''} name="gender" value="0">男</label></div>
                            <div class="radio"><label><input type="radio" ${user.gender==1?'checked':''} name="gender" value="1">女</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title">出生日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="出生年月" class="form-control pull-right" name="birthday"
                                   value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>" id="datepicker1">
                        </div>
                    </div>

                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${user.state==0?'checked':''} name="state" value="0">停用</label></div>
                            <div class="radio"><label><input type="radio" ${user.state==1?'checked':''} name="state" value="1">启用</label></div>
                        </div>
                    </div>

                    <div class="col-md-2 title">电话</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="电话" name="telephone" value="${user.telephone}">
                    </div>

                    <div class="col-md-2 title">入职日期</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="入职时间"  name="joinDate" class="form-control pull-right"
                                   value="<fmt:formatDate value="${user.joinDate}" pattern="yyyy-MM-dd"/>" id="2">
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="${ctx}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${ctx}/css/style.css">
<script>
    $('#datepicker').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#datepicker1').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>
```

*UserDao.xml*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.system.UserDao">
    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.system.User">
        <id column="user_id" jdbcType="VARCHAR" property="id"/>
        <result column="email" jdbcType="VARCHAR" property="email"/>
        <result column="user_name" jdbcType="VARCHAR" property="userName"/>
        <result column="password" jdbcType="VARCHAR" property="password"/>
        <result column="state" jdbcType="DECIMAL" property="state"/>
        <result column="gender" jdbcType="CHAR" property="gender"/>
        <result column="telephone" jdbcType="VARCHAR" property="telephone"/>
        <result column="birthday" jdbcType="VARCHAR" property="birthday"/>
        <result column="join_date" jdbcType="VARCHAR" property="joinDate"/>
        <result column="dept_id" jdbcType="VARCHAR" property="deptId"/>
        <association
            property="dept"
            column="dept_id"
            javaType="top.hellocode.domain.system.Dept"
            select="top.hellocode.dao.system.DeptDao.findById"
            />
    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        user_id, email, user_name,  password, state, gender, telephone, birthday, join_date, dept_id
    </sql>

    <!--配置查询所有，带条件-->
    <select id="findAll" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_user

    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_user
        where user_id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from ss_user where user_id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="save" parameterType="top.hellocode.domain.system.User">
        insert into ss_user (user_id, email, user_name,  password, state,
        gender, telephone, birthday, join_date, dept_id
        )
        values (#{id,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR},
        #{password,jdbcType=VARCHAR}, #{state,jdbcType=DECIMAL}, #{gender,jdbcType=CHAR},
        #{telephone,jdbcType=VARCHAR}, #{birthday,jdbcType=VARCHAR}, #{joinDate,jdbcType=VARCHAR},
        #{deptId,jdbcType=VARCHAR}
        )
    </insert>

    <!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
    <update id="update" parameterType="top.hellocode.domain.system.User">
        update ss_user
        SET
        user_name = #{userName,jdbcType=VARCHAR},
        state = #{state,jdbcType=DECIMAL},
        gender = #{gender,jdbcType=CHAR},
        telephone = #{telephone,jdbcType=VARCHAR},
        dept_id = #{deptId,jdbcType=VARCHAR}
        where user_id = #{id,jdbcType=VARCHAR}
    </update>

</mapper>
```

*UserServiceImpl*

```java
package top.hellocode.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.system.UserDao;
import top.hellocode.domain.system.User;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.system.UserService;
import top.hellocode.utils.MD5Util;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:54
 */

public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            user.setId(id);
            // 密码加密
            user.setPassword(MD5Util.md5(user.getPassword()));

            // 调用Dao层操作
            userDao.save(user);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(User user) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession,UserDao.class);

            // 调用Dao层操作
            userDao.delete(user);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(User user) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession,UserDao.class);

            // 调用Dao层操作
            userDao.update(user);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession,UserDao.class);

            // 调用Dao层操作
            return userDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession,UserDao.class);

            // 调用Dao层操作
            return userDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession,UserDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<User> all = userDao.findAll();
            PageInfo pageInfo = new PageInfo<>(all);
            return pageInfo;
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```

*UserServlet*

```java
package top.hellocode.web.controller.system;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.system.Dept;
import top.hellocode.domain.system.User;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:56
 */


// uri:/system/user?operation=list

@WebServlet("/system/user")
public class UserServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            this.save(request,response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        UserService userService = new UserServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = userService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        User user = BeanUtil.fillBean(request,User.class,"yyyy-MM-dd");
        // 调用业务层接口
//        UserService userService = new UserServiceImpl();
        userService.save(user);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");

        // 加载数据
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList",all);
        // 调用业务层方法查询数据
        User user = userService.findById(id);
        request.setAttribute("user",user);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        User user = BeanUtil.fillBean(request,User.class,"yyyy-MM-dd");
        // 调用业务层接口
//        UserService userService = new UserServiceImpl();
        userService.update(user);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        User user = BeanUtil.fillBean(request,User.class);
        // 调用业务层接口
//        UserService userService = new UserServiceImpl();
        userService.delete(user);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
```

### 题目模块

#### 数据库设计范式

- 范式：规范的形式，应用于各个行业的标准化
- 数据库具有五种设计范式，常用第一范式（1NF）、第二范式（2NF）、第三范式（3NF）
  - 一范式：列的原子性，每个列都应该不可再分
  - 二范式：表中只有唯一一个主键，不存在多主键
  - 三范式：不依赖非主键字段及更深层次的依赖
- 反三范式
  - 允许少量数据的冗余，提高查询速度

> 每一种范式都有对应的使用场景，具体应该根据业务需求选择合适的范式

#### 数据库设计工具--PD

- PowerDesigner是一款功能强大的建模软件，提供强大的元数据管理功能，可以帮助用户构建关键信息的全方位视图，创建多种类型的模型，包括概念数据模型、物理数据模型、面向对象模型等等，同时集成了数据管理、BI、数据集成和数据整合多种功能
- 具体下载安装步骤可以在网上搜索
- 主要用来设计数据库，可以帮我们建立各个表之间的关系，快速梳理表间关系
- 它在设计完对应的表之后，还可以自动生成对应的SQL语句，帮助我们快速构建数据库表结构

#### 学科模块

**字段**

```java
private String id;
private String name;	// 学科名称
private String remark;	// 描述
private String state;	// 状态
private Date createTime;	// 创建时间
```

**过滤器**

```java
package top.hellocode.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(value = "/*",initParams={@WebInitParam(name = "encoding",value = "UTF-8")})
public class CharacterEncodingFilter implements Filter {

    private FilterConfig filterConfig;

    /**
     * 初始化方法，获取过滤器的配置对象
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //1.定义和协议相关的请求和响应对象
        HttpServletRequest request ;
        HttpServletResponse response;
        try{
            //2.把参数转换成协议相关的对象
            request = (HttpServletRequest)req;
            response = (HttpServletResponse)resp;
            //3.获取配置的字符集
            String encoding = filterConfig.getInitParameter("encoding");
            //4.设置请求参数的字符集
            request.setCharacterEncoding(encoding);
            //5.设置响应对象输出响应正文时的字符集
            response.setContentType("text/html;charset=UTF-8");
            //6.放行
            chain.doFilter(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        //可以做一些清理操作
    }
}
```

**add.jsp**

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                题库管理
                <small>学科管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">新增学科</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/store/course?operation=save" method="post">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">学科名称</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="学科名称" name="name">
                                    </div>
                                    <div class="col-md-2 title">学科简介</div>
                                    <div class="col-md-10 data line-height36">
                                        <input type="text" class="form-control" placeholder="学科简介" name="remark">
                                    </div>
                                    <div class="col-md-2 title">是否显示</div>
                                    <div class="col-md-10 data line-height36">
                                        <select class="form-control" name="state">
                                            <option value="">请选择</option>
                                            <option value="1" selected>启用</option>
                                            <option value="0">禁用</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2 title"></div>
                                    <div class="col-md-10 data text-center">
                                        <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>

</html>
```

**list.jsp**

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->

</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/store/course?operation=delete&id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        题库管理
        <small>学科管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
<section class="content">

    <!-- .box-body -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">学科列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/store/course?operation=toAdd"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">序号</th>
                        <th class="sorting">学科名称</th>
                        <th class="sorting">学科简介</th>
                        <th class="sorting">学科状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="course" varStatus="status">
                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                        <td><input type="checkbox" name="id" value="${course.id}"/></td>
                        <td>${status.index+1}</td>
                        <td>${course.name}</td>
                        <td>${course.remark}</td>
                        <td>${course.state eq "1" ? "<font color='green'>正常</font>" : "<font color='red'>禁用</font>"}</td>
                        <th class="text-center">
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/store/course?operation=toEdit&id=${course.id}"'>编辑</button>
                        </th>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/store/course?operation=list" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>

</section>
</div>
</body>

</html>
```

**update.jsp**

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            题库管理
            <small>学科管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <section class="content">
        <div class="box-body">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#tab-form" data-toggle="tab">编辑学科</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <form id="editForm" action="${ctx}/store/course?operation=edit" method="post">
                        <input type="hidden" name="id" value="${course.id}">
                        <div class="tab-pane active" id="tab-form">
                            <div class="row data-type">
                                <div class="col-md-2 title">学科名称</div>
                                <div class="col-md-10 data">
                                    <input type="text" class="form-control" placeholder="学科名称" name="name" value="${course.name}">
                                </div>
                                <div class="col-md-2 title">学科简介</div>
                                <div class="col-md-10 data line-height36">
                                    <input type="text" class="form-control" placeholder="学科简介" name="remark" value="${course.remark}">
                                </div>
                                <div class="col-md-2 title">是否显示</div>
                                <div class="col-md-10 data line-height36">
                                    <select class="form-control" name="state">
                                        <option value="">请选择</option>
                                        <option value="1" ${course.state == "1" ? 'selected':''}>启用</option>
                                        <option value="0" ${course.state == "0" ? 'selected':''}>禁用</option>
                                    </select>
                                </div>
                                <div class="col-md-2 title"></div>
                                <div class="col-md-10 data text-center">
                                    <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                    <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
</body>

</html>
```

**CourseDao.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.store.CourseDao">
    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.store.Course">
        <id column="id" jdbcType="VARCHAR" property="id"/>
        <result column="name" jdbcType="VARCHAR" property="name"/>
        <result column="remark" jdbcType="VARCHAR" property="remark"/>
        <result column="state" jdbcType="VARCHAR" property="state"/>
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime"/>
    </resultMap>


    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        id, name, state, remark, create_time
    </sql>

    <!--配置查询所有，带条件-->
    <select id="findAll"  resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from st_course
    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from st_course
        where id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from st_course where id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="save" parameterType="top.hellocode.domain.store.Course">
        insert into st_course (id, name, remark,
        state, create_time)
        values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR},
        #{state,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}
        )
    </insert>

    <!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
    <update id="update" parameterType="top.hellocode.domain.store.Course">
        update st_course
        set name = #{name,jdbcType=VARCHAR},
        remark = #{remark,jdbcType=VARCHAR},
        state = #{state,jdbcType=VARCHAR}
        where id = #{id,jdbcType=VARCHAR}
    </update>
</mapper>
```



**注意：**

- 创建时间、操作时间、修改时间等时效性日期或时间数据不从页面收集，直接在业务层添加
- 时间类数据通常用于数据记录留痕，此类数据不允许修改
- 最后一次登录时间类的数据也不允许修改，每次读取登录信息中最后一条数据的时间即可

#### 目录模块

- 同用户模块开发（一对多）

**字段**

```java
private String id;
private String name;		// 名称
private String remark;		// 描述
private String state;		// 状态
private Date createTime;	// 创建时间
private String courseId;	// 学科id

private Course course;
```

**页面**

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                题库管理
                <small>目录管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">新增目录</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/store/catalog?operation=save" method="post">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">所属学科</div>
                                    <div class="col-md-10 data line-height36">
                                        <select class="form-control" name="courseId" onchange="document.getElementById('courseName').value=this.options[this.selectedIndex].text">
                                            <option value="">请选择</option>
                                            <c:forEach items="${courseList}" var="item">
                                                <option ${catalog.courseId == item.id ?'selected':''} value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-2 title">目录名称</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="目录名称" name="name">
                                    </div>
                                    <div class="col-md-2 title">目录简介</div>
                                    <div class="col-md-10 data line-height36">
                                        <input type="text" class="form-control" placeholder="目录简介" name="remark">
                                    </div>
                                    <div class="col-md-2 title">状态</div>
                                    <div class="col-md-10 data line-height36">
                                        <select class="form-control" name="state">
                                            <option value="">请选择</option>
                                            <option value="1" selected>启用</option>
                                            <option value="0">禁用</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2 title"></div>
                                    <div class="col-md-10 data text-center">
                                        <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>

</html>
```

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->

</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/store/catalog?operation=delete&id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        题库管理
        <small>目录管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
<section class="content">

    <!-- .box-body -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">目录列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/store/catalog?operation=toAdd"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">目录名称</th>
                        <th class="sorting">目录简介</th>
                        <th class="sorting">当前状态</th>
                        <th class="sorting">所属学科</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="catalog" varStatus="status">
                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                        <td><input type="checkbox" name="id" value="${catalog.id}"/></td>
                        <td>${catalog.name}</td>
                        <td>${catalog.remark}</td>
                        <td>${catalog.state eq "1" ? "<font color='green'>使用中</font>" : "<font color='red'>已禁用</font>"}</td>
                        <td>${catalog.course.name}</td>
                        <th class="text-center">
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/store/catalog?operation=toEdit&id=${catalog.id}"'>编辑</button>
                        </th>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/store/catalog?operation=list" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>

</section>
</div>
</body>

</html>
```

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            题库管理
            <small>目录管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <section class="content">
        <div class="box-body">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#tab-form" data-toggle="tab">编辑目录</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <form id="editForm" action="${ctx}/store/catalog?operation=edit" method="post">
                        <input type="hidden" name="id" value="${catalog.id}">
                        <div class="tab-pane active" id="tab-form">
                            <div class="row data-type">
                                <div class="col-md-2 title">所属学科</div>
                                <div class="col-md-10 data line-height36">
                                    <select class="form-control" name="courseId" onchange="document.getElementById('courseName').value=this.options[this.selectedIndex].text">
                                        <option value="">请选择</option>
                                        <c:forEach items="${courseList}" var="item">
                                            <option ${catalog.courseId == item.id ?'selected':''} value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-2 title">目录名称</div>
                                <div class="col-md-10 data">
                                    <input type="text" class="form-control" placeholder="目录名称" name="name" value="${catalog.name}">
                                </div>
                                <div class="col-md-2 title">目录简介</div>
                                <div class="col-md-10 data line-height36">
                                    <input type="text" class="form-control" placeholder="目录简介" name="remark" value="${catalog.remark}">
                                </div>
                                <div class="col-md-2 title">状态</div>
                                <div class="col-md-10 data line-height36">
                                    <select class="form-control" name="state">
                                        <option value="">请选择</option>
                                        <option value="1" ${catalog.state == "1" ? 'selected':''}>启用</option>
                                        <option value="0" ${catalog.state == "0" ? 'selected':''}>禁用</option>
                                    </select>
                                </div>
                                <div class="col-md-2 title"></div>
                                <div class="col-md-10 data text-center">
                                    <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                    <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
</body>

</html>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.store.CatalogDao">
    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.store.Catalog">
        <id column="id" jdbcType="VARCHAR" property="id"/>
        <result column="name" jdbcType="VARCHAR" property="name"/>
        <result column="remark" jdbcType="VARCHAR" property="remark"/>
        <result column="state" jdbcType="VARCHAR" property="state"/>
        <result column="course_id" jdbcType="VARCHAR" property="courseId"/>
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime"/>
        <association property="course" column="course_id" javaType="top.hellocode.domain.store.Course"
                     select="top.hellocode.dao.store.CourseDao.findById"></association>
    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        id, name, remark , state , create_time , course_id
    </sql>

    <!--配置查询所有-->
    <select id="findAll" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from st_catalog
    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from st_catalog
        where id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from st_catalog where id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="save" parameterType="top.hellocode.domain.store.Catalog">
        insert into st_catalog (id, name, remark , state , create_time , course_id)
        values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR},
        #{state,jdbcType=VARCHAR} , #{createTime,jdbcType=TIMESTAMP}, #{courseId,jdbcType=VARCHAR})
    </insert>

    <!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
    <update id="update" parameterType="top.hellocode.domain.store.Catalog">
        update st_catalog
        set name = #{name,jdbcType=VARCHAR},
        remark = #{remark,jdbcType=VARCHAR},
        state = #{state,jdbcType=VARCHAR},
        course_id = #{courseId,jdbcType=VARCHAR}
        where id = #{id,jdbcType=VARCHAR}
    </update>
</mapper>
```

#### 题目模块

**题目/题干模块快速开发**

*字段*

```java
private String id;		// 题目id
private String companyId;		// 所属企业
private String catalogId;		// 所属目录
private String remark;		// 题目简介/题目描述
private String subject;		// 题干正文
private String picture;		// 图片（可选，快速开发中先不添加此字段）
private String analysis;	// 题目分析
private String type;		// 题目类型 1：单选  2：多选   3：简答
private String difficulty;		// 难易程度  1：极易  2：容易  3：普通  4：困难  5：极难
private String isClassic;		// 是否经典面试题    0：否    1：是
private String state;		// 题目状态   0：不可用     1：可用（只有审核通过的题目可以设置）
private String reviewStatus;		// 审核状态  -1：审核不通过    1：审核通过
private Date createTime;		// 创建时间

private Company company;
private Catalog catalog;
```

*页面*

```java
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                题库管理
                <small>题目管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">新增题目</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/store/question?operation=save" method="post" enctype="multipart/form-data">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">

                                    <div class="col-md-2 title">所属企业</div>
                                    <div class="col-md-4 data">
                                        <select class="form-control" name="companyId" onchange="document.getElementById('courseName').value=this.options[this.selectedIndex].text">
                                            <option value="">请选择</option>
                                            <c:forEach items="${companyList}" var="item">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-md-2 title">所属类别</div>
                                    <div class="col-md-4 data">
                                        <select class="form-control" name="catalogId" onchange="document.getElementById('catalogName').value=this.options[this.selectedIndex].text">
                                            <option value="">请选择</option>
                                            <c:forEach items="${catalogList}" var="item">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-md-2 title rowHeight2x">题目简介</div>
                                    <div class="col-md-10 data rowHeight2x">
                                        <textarea class="form-control" rows="3" name="remark"></textarea>
                                    </div>

                                    <div class="col-md-2 title rowHeight2x">题干</div>
                                    <div class="col-md-10 data rowHeight2x">
                                        <textarea class="form-control" rows="3" name="subject"></textarea>
                                    </div>

                                    <div class="col-md-2 title">题干图片</div>
                                    <div class="col-md-10 data ">
                                        <input type="file" class="form-control" placeholder="题干图片" name="picture">
                                    </div>

                                    <div class="col-md-2 title rowHeight2x">题目分析</div>
                                    <div class="col-md-10 data rowHeight2x">
                                        <textarea class="form-control" rows="3" name="analysis"></textarea>
                                    </div>

                                    <div class="col-md-2 title">题目类型</div>
                                    <div class="col-md-4 data">
                                        <select class="form-control" name="type">
                                            <option value="">请选择</option>
                                            <option value="1" selected>单选题</option>
                                            <option value="2">多选题</option>
                                            <option value="3">简答题</option>
                                        </select>
                                    </div>

                                    <div class="col-md-2 title">难易程度</div>
                                    <div class="col-md-4 data">
                                        <select class="form-control" name="difficulty">
                                            <option value="">请选择</option>
                                            <option value="1" selected>★</option>
                                            <option value="2">★★</option>
                                            <option value="3">★★★</option>
                                            <option value="4">★★★★</option>
                                            <option value="5">★★★★★</option>
                                        </select>
                                    </div>

                                    <div class="col-md-2 title">是否经典</div>
                                    <div class="col-md-4 data">
                                        <select class="form-control" name="isClassic">
                                            <option value="">请选择</option>
                                            <option value="1" selected>经典题</option>
                                            <option value="0">普通题</option>
                                        </select>
                                    </div>

                                    <div class="col-md-2 title">题目状态</div>
                                    <div class="col-md-4 data">
                                        <select class="form-control" name="state">
                                            <option value="">请选择</option>
                                            <option value="1" selected>启用</option>
                                            <option value="0">禁用</option>
                                        </select>
                                    </div>

                                </div>
                                <!--工具栏-->
                                <div class="box-tools text-center">
                                    <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
                                    <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>

</html>
```

```java
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->

</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/store/question?operation=delete&id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        题库管理
        <small>题目管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
<section class="content">

    <!-- .box-body -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">题目列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/store/question?operation=toAdd"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="测试文件上传" onclick='location.href="${ctx}/store/question?operation=toTestUpload"'><i class="fa fa-file-o"></i> 测试文件上传</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
<%--
                            <button type="button" class="btn btn-default" title="上传题目" onclick='location.href="${ctx}/store/question?operation=toImport"'><i class="fa fa-adn"></i> 上传题目</button>
--%>
                            <button type="button" class="btn btn-default" title="导出题目" onclick=location.href="${ctx}/store/question?operation=toExport"> <i class="fa fa-download"></i>导出题目</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">企业</th>
                        <th class="sorting">类别</th>
                        <th class="sorting">题目</th>
                        <th class="sorting">类型</th>
                        <th class="sorting">难度</th>
                        <th class="sorting">经典面试题</th>
                        <th class="sorting">状态</th>
                        <th class="sorting">审核结果</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="o" varStatus="status">
                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                        <td><input type="checkbox" name="id" value="${o.id}"/></td>
                        <td>${o.company.name}</td>
                        <td>${o.catalog.name}</td>
                        <td>${o.subject}</td>
                        <td>
                            <c:choose>
                                <c:when test="${o.type eq '1'}">单选</c:when>
                                <c:when test="${o.type eq '2'}">多选</c:when>
                                <c:when test="${o.type eq '3'}">简答</c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:forEach begin="1" end="${o.difficulty}">
                                ★
                            </c:forEach>
                        </td>
                        <td>${o.isClassic eq "1" ? "经典题":"普通题"}</td>
                        <td>${o.state eq "1" ? "<font color='green'>启用</font>" : "<font color='red'>禁用</font>"}</td>
                        <td>
                            <c:choose>
                                <c:when test="${o.reviewStatus eq '1'}"><font color="green">审核通过</font></c:when>
                                <c:when test="${o.reviewStatus eq '0'}">审核中</c:when>
                                <c:when test="${o.reviewStatus eq '-1'}"><font color="red">审核不通过</font></c:when>
                            </c:choose>
                        </td>
                        <th class="text-center">
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/store/question?operation=toEdit&id=${o.id}"'>编辑</button>
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/store/questionItem?operation=list&questionId=${o.id}"'>配置选项</button>
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/store/question?operation=toExamine&id=${o.id}"'>审核</button>
                        </th>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/store/question?operation=list" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>

</section>
</div>
</body>

</html>
```

```java
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            题库管理
            <small>题目管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <section class="content">
        <div class="box-body">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#tab-form" data-toggle="tab">编辑题目</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <form id="editForm" action="${ctx}/store/question?operation=edit" method="post" enctype="multipart/form-data">

                        <input type="hidden" name="id" value="${question.id}">

                        <div class="tab-pane active" id="tab-form">
                            <div class="row data-type">

                                <div class="col-md-2 title">所属企业</div>
                                <div class="col-md-4 data">
                                    <select class="form-control" name="companyId" onchange="document.getElementById('courseName').value=this.options[this.selectedIndex].text">
                                        <option value="">请选择</option>
                                        <c:forEach items="${companyList}" var="item">
                                            <option value="${item.id}" ${question.companyId eq item.id ? 'selected' : ''}>${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-md-2 title">所属类别</div>
                                <div class="col-md-4 data">
                                    <select class="form-control" name="catalogId" onchange="document.getElementById('catalogName').value=this.options[this.selectedIndex].text">
                                        <option value="">请选择</option>
                                        <c:forEach items="${catalogList}" var="item">
                                            <option value="${item.id}" ${question.catalogId eq item.id ? 'selected' : ''}>${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-md-2 title rowHeight2x">题目简介</div>
                                <div class="col-md-10 data rowHeight2x">
                                    <textarea class="form-control" rows="3" name="remark">${question.remark}</textarea>
                                </div>

                                <div class="col-md-2 title rowHeight2x">题干</div>
                                <div class="col-md-10 data rowHeight2x">
                                    <textarea class="form-control" rows="3" name="subject">${question.subject}</textarea>
                                </div>

                                <div class="col-md-2 title">题干图片</div>
                                <div class="col-md-10 data ">
                                    <input type="file" class="form-control" placeholder="题干图片" name="picture" value="${question.picture}">
                                </div>

                                <c:if test="${question.picture.length() > 0}">
                                    <div class="col-md-2 title">题干图片</div>
                                    <div class="col-md-10 data ">
                                        <img src="${ctx}/upload/${question.picture}"/>
                                    </div>
                                </c:if>

                                <div class="col-md-2 title rowHeight2x">题目分析</div>
                                <div class="col-md-10 data rowHeight2x">
                                    <textarea class="form-control" rows="3" name="analysis">${question.analysis}</textarea>
                                </div>

                                <div class="col-md-2 title">题目类型</div>
                                <div class="col-md-4 data">
                                    <select class="form-control" name="type">
                                        <option value="">请选择</option>
                                        <option value="1" ${question.type eq '1' ? 'selected' : ''}>单选题</option>
                                        <option value="2" ${question.type eq '2' ? 'selected' : ''} >多选题</option>
                                        <option value="3" ${question.type eq '3' ? 'selected' : ''}>简答题</option>
                                    </select>
                                </div>

                                <div class="col-md-2 title">难易程度</div>
                                <div class="col-md-4 data">
                                    <select class="form-control" name="difficulty">
                                        <option value="">请选择</option>
                                        <option value="1" ${question.difficulty eq '1' ? 'selected' : ''}>★</option>
                                        <option value="2" ${question.difficulty eq '2' ? 'selected' : ''}>★★</option>
                                        <option value="3" ${question.difficulty eq '3' ? 'selected' : ''}>★★★</option>
                                        <option value="4" ${question.difficulty eq '4' ? 'selected' : ''}>★★★★</option>
                                        <option value="5" ${question.difficulty eq '5' ? 'selected' : ''}>★★★★★</option>
                                    </select>
                                </div>

                                <div class="col-md-2 title">是否经典</div>
                                <div class="col-md-4 data">
                                    <select class="form-control" name="isClassic">
                                        <option value="">请选择</option>
                                        <option value="1" ${question.isClassic eq '1' ? 'selected' : ''}>经典题</option>
                                        <option value="0" ${question.isClassic eq '0' ? 'selected' : ''}>普通题</option>
                                    </select>
                                </div>

                                <div class="col-md-2 title">题目状态</div>
                                <div class="col-md-4 data">
                                    <select class="form-control" name="state">
                                        <option value="">请选择</option>
                                        <option value="1" ${question.state eq '1' ? 'selected' : ''}>启用</option>
                                        <option value="0" ${question.state eq '0' ? 'selected' : ''}>禁用</option>
                                    </select>
                                </div>

                            </div>
                            <!--工具栏-->
                            <div class="box-tools text-center">
                                <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
                                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
</body>

</html>
```

```java
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                题库管理
                <small>题目管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">文件上传测试</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/store/question?operation=testUpload" method="post" enctype="multipart/form-data">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">

                                    <div class="col-md-2 title">题干图片</div>
                                    <div class="col-md-10 data ">
                                        <input type="file" class="form-control" placeholder="题干图片" name="picture">
                                    </div>

                                </div>
                                <!--工具栏-->
                                <div class="box-tools text-center">
                                    <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
                                    <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>

</html>
```

*QuestionDao.xml*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.store.QuestionDao">
    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.store.Question">
        <id column="id" jdbcType="VARCHAR" property="id"/>
        <result column="company_id" jdbcType="VARCHAR" property="companyId"/>
        <result column="catalog_id" jdbcType="VARCHAR" property="catalogId"/>
        <result column="remark" jdbcType="VARCHAR" property="remark"/>
        <result column="subject" jdbcType="VARCHAR" property="subject"/>
        <result column="analysis" jdbcType="VARCHAR" property="analysis"/>
        <result column="type" jdbcType="VARCHAR" property="type"/>
        <result column="difficulty" jdbcType="VARCHAR" property="difficulty"/>
        <result column="is_classic" jdbcType="VARCHAR" property="isClassic"/>
        <result column="state" jdbcType="VARCHAR" property="state"/>
        <result column="review_status" jdbcType="VARCHAR" property="reviewStatus"/>
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime"/>
        <result column="picture" jdbcType="VARCHAR" property="picture"/>

        <association
            property="company"
            column="company_id"
            javaType="top.hellocode.domain.store.Course"
            select="top.hellocode.dao.store.CompanyDao.findById"/>

        <association
            property="catalog"
            column="catalog_id"
            javaType="top.hellocode.domain.store.Course"
            select="top.hellocode.dao.store.CatalogDao.findById"
            />

    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        id, catalog_id, company_id, remark,subject,analysis,type, difficulty, is_classic,
        state, review_status, create_time, picture
    </sql>

    <!--配置查询所有，带条件-->
    <select id="findAll" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from st_question
        order by create_time desc
    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from st_question
        where id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from st_question where id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="save" parameterType="top.hellocode.domain.store.Question">
        insert into st_question(id, company_id, catalog_id, remark, subject, analysis, type,
                difficulty, is_classic, state, review_status, create_time ,picture )
            values (#{id,jdbcType=VARCHAR}, #{companyId,jdbcType=VARCHAR}, #{catalogId,jdbcType=VARCHAR},
                #{remark,jdbcType=VARCHAR}, #{subject,jdbcType=VARCHAR}, #{analysis,jdbcType=VARCHAR},
                #{type,jdbcType=VARCHAR}, #{difficulty,jdbcType=VARCHAR}, #{isClassic,jdbcType=VARCHAR},
                #{state,jdbcType=VARCHAR}, #{reviewStatus,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP},
                #{picture,jdbcType=VARCHAR} )
    </insert>

    <!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
    <update id="update" parameterType="top.hellocode.domain.store.Question">
        update
            st_question
        set
            company_id = #{companyId,jdbcType=VARCHAR},
            catalog_id = #{catalogId,jdbcType=VARCHAR},
            remark = #{remark,jdbcType=VARCHAR},
            subject = #{subject,jdbcType=VARCHAR},
            analysis = #{analysis,jdbcType=VARCHAR},
            difficulty = #{difficulty,jdbcType=VARCHAR},
            is_classic = #{isClassic,jdbcType=VARCHAR},
            picture = #{picture,jdbcType=VARCHAR},
            state = #{state,jdbcType=VARCHAR}
        where
            id = #{id,jdbcType=VARCHAR}
    </update>

</mapper>
```

*java*

```java
package top.hellocode.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.store.QuestionDao;
import top.hellocode.domain.store.Question;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.store.QuestionService;
import top.hellocode.service.store.QuestionService;
import top.hellocode.utils.TransactionUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 11:05
 */

public class QuestionServiceImpl implements QuestionService {

    @Override
    public String save(Question question,boolean flag) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            question.setId(id);

            // 添加审核状态和创建时间
            question.setReviewStatus("0");
            question.setCreateTime(new Date());

            // 检测到前端上传文件了，设置当前存储的图片名称为id值(保证唯一)
            if(flag){
                question.setPicture(id);
            }

            // 调用Dao层操作
            questionDao.save(question);
            // 提交事务
            TransactionUtil.commit(sqlSession);
            return id;
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Question question) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);

            // 调用Dao层操作
            questionDao.delete(question);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Question question, boolean flag) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);

            // 检测到前端上传文件了，设置当前存储的图片名称为id值(保证唯一)
            if(flag){
                question.setPicture(question.getId());
            }

            // 调用Dao层操作
            questionDao.update(question);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Question findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);

            // 调用Dao层操作
            return questionDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Question> findAll() {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);

            // 调用Dao层操作
            return questionDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<Question> all = questionDao.findAll();
            PageInfo pageInfo = new PageInfo<>(all);
            return pageInfo;
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```

```java
package top.hellocode.web.controller.store;

import com.github.pagehelper.PageInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.store.Catalog;
import top.hellocode.domain.store.Company;
import top.hellocode.domain.store.Question;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 11:06
 */

// uri:/store/question?operation=list

@WebServlet("/store/question")
public class QuestionServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            try {
                this.save(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            try {
                this.edit(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }else if("toTestUpload".equals(operation)){
            this.toTestUpload(request,response);
        }else if("testUpload".equals(operation)){
            try {
                this.testUpload(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void toTestUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/testFileUpload.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void testUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 确认该操作是否支持文件上传操作,enctype="multipart/form-data"
        if(ServletFileUpload.isMultipartContent(request)){
            // 2. 创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 3. Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            // 4. 从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(request);
            for(FileItem item : fileItems){
                // 判断当前表单是否是文件表单
                if(!item.isFormField()){
                    item.write(new File(this.getServletContext().getRealPath("upload"),item.getName()));
                }
            }
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        QuestionService questionService = new QuestionServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = questionService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Company> companyList = companyService.findAll();
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("companyList",companyList);
        request.setAttribute("catalogList",catalogList);

        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 确认该操作是否支持文件上传操作,enctype="multipart/form-data"
        if(ServletFileUpload.isMultipartContent(request)){
            // 2. 创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 3. Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            // 4. 从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            // 创建标记位，标记是否上传图片
            boolean flag = false;
            for(FileItem item : fileItems){
                if(StringUtils.isNotBlank(item.getName())){     // 上传了文件
                    flag = true;
                    break;
                }
            }

            // 处理普通数据
            Question question = BeanUtil.fillBean(fileItems,Question.class);
            // 调用业务层接口
            String picture = questionService.save(question, flag);

            // 处理文件数据
            for(FileItem item : fileItems){
                // 判断当前表单是否是文件表单
                if(!item.isFormField()){
                    item.write(new File(this.getServletContext().getRealPath("upload"),picture));
                }
            }
        }

        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Company> companyList = companyService.findAll();
        List<Catalog> catalogList = catalogService.findAll();
        request.setAttribute("companyList",companyList);
        request.setAttribute("catalogList",catalogList);

        // 拿到要编辑的数据
        String id = request.getParameter("id");

        Question question = questionService.findById(id);
        request.setAttribute("question",question);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 1. 确认该操作是否支持文件上传操作,enctype="multipart/form-data"
        if(ServletFileUpload.isMultipartContent(request)){
            // 2. 创建磁盘工厂对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 3. Servlet文件上传核心对象
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            // 4. 从request中读取数据
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            // 创建标记位，标记是否上传图片
            boolean flag = false;
            for(FileItem item : fileItems){
                if(StringUtils.isNotBlank(item.getName())){     // 上传了文件
                    flag = true;
                    break;
                }
            }

            // 处理普通数据
            Question question = BeanUtil.fillBean(fileItems,Question.class);
            // 调用业务层接口
            questionService.update(question, flag);

            // 处理文件数据
            for(FileItem item : fileItems){
                // 判断当前表单是否是文件表单
                if(!item.isFormField()){
                    item.write(new File(this.getServletContext().getRealPath("upload"),question.getId()));
                }
            }
        }

        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Question question = BeanUtil.fillBean(request,Question.class);
        // 调用业务层接口
//        QuestionService questionService = new QuestionServiceImpl();
        questionService.delete(question);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
```

**文件上传**

- 使用apache提供的commons-fileupload组件完成文件上传操作

- 页面操作步骤

  1. 页面提供文件上传的表单

     ```html
     <input type="file" class="form-control" placeholder="题干图片" name="picture">
     ```

  2. 修改form表单的enctype属性，使其支持文件上传操作

     ```html
     <form id="editForm" action="....." method="post" enctype="multipart/form-data">
     </form>
     ```

- servlet操作步骤
  1. 确认请求操作是否支持文件上传
  2. 创建磁盘工厂对象，用于将页面上传的文件保存到磁盘中
  3. 获取Servlet文件上传核心对象
  4. 读取数据
  5. 对读取数据中的文件表单进行操作，并将内容写入到指定位置

```java
private void testUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 1. 确认该操作是否支持文件上传操作,enctype="multipart/form-data"
    if(ServletFileUpload.isMultipartContent(request)){
        // 2. 创建磁盘工厂对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 3. Servlet文件上传核心对象
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        // 4. 从request中读取数据
        List<FileItem> fileItems = fileUpload.parseRequest(request);
        for(FileItem item : fileItems){
            // 判断当前表单是否是文件表单
            if(!item.isFormField()){
                item.write(new File(this.getServletContext().getRealPath("upload"),item.getName()));
            }
        }
    }
}
```

*题目模块添加功能加入文件上传*

- 表单提交方式需要修改：`enctype=multipart/form-data`
- 当普通表单与文件表单混合提交时，普通表单的数据与文件表单的数据组成了一个整体进行上传，普通表单数据值的提取由request中获取转换为FileItem对象中获取
- 调式程序时，使用`if(true) return;`形式暂时忽略后方代码的执行，快速调试

```java
private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 1. 确认该操作是否支持文件上传操作,enctype="multipart/form-data"
    if(ServletFileUpload.isMultipartContent(request)){
        // 2. 创建磁盘工厂对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 3. Servlet文件上传核心对象
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        // 4. 从request中读取数据
        List<FileItem> fileItems = fileUpload.parseRequest(request);

        // 处理普通数据
        Question question = BeanUtil.fillBean(fileItems,Question.class);
        // 调用业务层接口
        questionService.save(question);

        // 处理文件数据
        for(FileItem item : fileItems){
            // 判断当前表单是否是文件表单
            if(!item.isFormField()){
                item.write(new File(this.getServletContext().getRealPath("upload"),item.getName()));
            }
        }
    }

    // 跳转页面
    response.sendRedirect(request.getContextPath() + "/store/question?operation=list");
}
```

**解决上传文件重名问题**

- 使用唯一编号重新定义文件名
- 使用原始文件名+唯一目录名保存文件

**修改功能加入文件上传**

- 修改图片通常是使用新上传的文件替换原始上传的图片，数据库中保存的文件名不进行更新

**兼容图片上传可选操作与图片显示问题**

- 在进行图片保存前，要确认是否存在图片上传功能，不区分添加还是修改操作
- 图片展示前要进行图片有效性判定，确认是否加载图片信息
- 企业开发通常采用按钮的形式控制是否加载图片

#### 题目选项模块

**字段**

```java
private String id;
private String questionId;		// 所属题目id
private String content;			// 选项内容
private String picture;			// 选项图片
private String isRight;			// 是否是正确答案
```

**注意**

- 类似于题目与选项这种紧绑定的数据关系，通常依赖方没有独立的入口页面，依赖于被依赖的模块
- 多功能融合在一起制作时（例如添加和列表），先忽略掉其中大部分功能，从任意功能开始制作，降低制作过程多功能带来的干扰度
- 对于数据可穷举的模块，如果数据量较低，无需制作分页功能

**添加操作**

- 同用户模块开发（一对多）
- 注意
  - 题目选项需求题目的id，此id不是通过列表的形式选择的，而是通过隐藏域传递的数据，需要保障加载该页面时，提前加载该数据
  - 对于隐藏域的数据，开发阶段建议一直显式的暴露出来，等功能开发完毕后再隐藏，节约调试时间

**删除功能**

- 功能完成后跳转页面时，要保障目标页面的功能完整性，并加载所有相关的数据

**修改功能**

- 功能完成后，要保障目标页面的功能完整性，并加载所有相关的数据
- 如果一组表单承载多种工作，使用动态数据的方式设定当前表单的实际功能（operation）
- 可以利用数据是否携带id作为判定是否是添加或者修改操作的标志是saveOrUpdate操作的常用方式

**题目模块删除功能问题分析与解决方案**

>当题目删除后，对应的题目选项并不能随之删除

*解决方案一：触发器*

- 注意使用触发器实现是将业务绑定到了数据库端，在进行系统设计时要确认方案

*解决方案二：业务层删除操作中分别调用两个模块的删除功能*

- 注意删除主使用按id删除，删除从使用按关联id删除
- 注意删除的顺序，先删从，再删主

*解决方案三：存储过程*

- 整套的数据层解决方案，没有单一功能使用的

*解决方案四：依赖框架提供的级联删除功能*

*解决方案五：定时维护/垃圾数据清理*

**代码**

*页面*

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->

    <script type="text/javascript">
        function deleteById(id) {
            var questionId = '${questionId}';
            if(confirm("你确认要删除此条记录吗？")) {
                window.location.href="${ctx}/store/questionItem?operation=delete&questionId=${questionId}&id="+id;
            }
        }
    </script>
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            题库管理
            <small>题目的选项</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">题库管理</a></li>
            <li class="active">选项添加及列表</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">新增选项</div>
            <form id="editForm" action="${ctx}/store/questionItem?operation=saveOrUpdate" method="post">
                <input type="text" name="questionId" value="${questionId}">
                <input type="hidden" name="id" value="${questionItem.id}">
                <div class="row data-type" style="margin: 0px">

                    <div class="col-md-2 title">选项内容</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" placeholder="选项内容" name="content" value="${questionItem.content}">
                    </div>

                    <div class="col-md-2 title">选项图片</div>
                    <div class="col-md-4 data">
                        <input type="file" class="form-control" placeholder="请选择" name="picture" >
                    </div>

                    <%--<div class="col-md-2 title">是否正确答案</div>
                    <div class="col-md-4 data">
                        <select class="form-control" name="isRight">
                            <option value="">请选择</option>
                            <option value="1">正确答案</option>
                            <option value="0">错误选项</option>
                        </select>
                    </div>--%>
                    <div class="col-md-2 title">是否正确答案</div>
                    <div class="col-md-4 data">
                        <select class="form-control" name="isRight">
                            <option value="">请选择</option>
                            <option value="1" ${questionItem.isRight eq "1" ? "selected" : ""}>正确答案</option>
                            <option value="0" ${questionItem.isRight eq "0" ? "selected" : ""}>错误选项</option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>

    <!-- 正文区域 /-->

    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">选项列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--工具栏-->
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                            </div>
                        </div>
                    </div>
                    <div class="box-tools pull-right">
                        <div class="has-feedback">
                            <input type="text" class="form-control input-sm" placeholder="搜索">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>
                    </div>


                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <td class="tableHeader">选项内容</td>
                            <td class="tableHeader">是否正确答案</td>
                            <td class="tableHeader">操作</td>
                        </tr>
                        </thead>
                        <tbody class="tableBody" >
                        <c:forEach items="${page.list}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td>${o.content}</td>
                                <td>${o.isRight eq "1" ? "正确答案" : ""}</td>
                                <td>
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/store/questionItem?operation=toEdit&questionId=${questionId}&id=${o.id}"'>编辑</button>
                                    <button type="button" class="btn bg-olive btn-xs" onclick="deleteById('${o.id}')">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!--数据列表/-->
                    <!--工具栏/-->
                </div>
                <!-- 数据表格 /-->
            </div>
        </div>

    </section>

</div>
<!-- 内容区域 /-->
</body>

</html>
```

*配置文件*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.store.QuestionItemDao">
    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.store.QuestionItem">
        <id column="id" jdbcType="VARCHAR" property="id"/>
        <result column="question_id" jdbcType="VARCHAR" property="questionId"/>
        <result column="content" jdbcType="VARCHAR" property="content"/>
        <result column="is_right" jdbcType="VARCHAR" property="isRight"/>
        <!--<result column="picture" jdbcType="VARCHAR" property="picture"/>-->
    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        id, question_id, content, picture, is_right
    </sql>

    <!--配置查询所有，带条件-->
    <select id="findAll" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from st_question_item
        where question_id = #{questionId,jdbcType=VARCHAR}
    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from st_question_item
        where id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from st_question_item where id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="save" parameterType="top.hellocode.domain.store.QuestionItem">
        insert into st_question_item (id, question_id, content,
        picture, is_right)
        values (#{id,jdbcType=VARCHAR}, #{questionId,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR},
        #{picture,jdbcType=VARCHAR}, #{isRight,jdbcType=VARCHAR})
    </insert>


    <!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
    <update id="update" parameterType="top.hellocode.domain.store.QuestionItem">
        update st_question_item
        set
        content = #{content,jdbcType=VARCHAR},
        <if test="picture != null">
          picture = #{picture,jdbcType=VARCHAR},
        </if>
        is_right = #{isRight,jdbcType=VARCHAR}
        where id = #{id,jdbcType=VARCHAR}
    </update>

    <!--根据题目id查询所有选项-->
    <select id="findByQuestionId" resultMap="BaseResultMap" parameterType="string">
        select
        <include refid="Base_Column_List"/>
        from st_question_item
        where question_id = #{questionId}
    </select>
</mapper>
```

*java*

```java
package top.hellocode.web.controller.store;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.store.QuestionItem;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 20:53
 */

// uri:/store/questionItem?operation=list

@WebServlet("/store/questionItem")
public class QuestionItemServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("save".equals(operation)){
            this.save(request,response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }else if("saveOrUpdate".equals(operation)){
            this.saveOrUpdate(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId = request.getParameter("questionId");

        // 进入list页面时，添加对应的问题id，为添加操作使用
        request.setAttribute("questionId",questionId);

        PageInfo all = questionItemService.findAll(questionId, 1, 100);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }


    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取数据并封装对象
        QuestionItem questionItem = BeanUtil.fillBean(request,QuestionItem.class,"yyyy-MM-dd");
        System.out.println(questionItem);
        // 调用业务层接口
        questionItemService.save(questionItem);
        // 跳转页面
        list(request,response);
    }

    private void saveOrUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取数据并封装对象
        QuestionItem questionItem = BeanUtil.fillBean(request,QuestionItem.class,"yyyy-MM-dd");
        // 如果页面传递了当前数据的id，为修改，否则为新增
        if(StringUtils.isNotBlank(questionItem.getId())){
            // 调用业务层接口
            questionItemService.update(questionItem);
        }else{
            questionItemService.save(questionItem);
        }

        // 跳转页面
        list(request,response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取数据并封装对象
        QuestionItem questionItem = BeanUtil.fillBean(request,QuestionItem.class,"yyyy-MM-dd");
        // 调用业务层接口
//        QuestionItemService questionItemService = new QuestionItemServiceImpl();
        questionItemService.update(questionItem);
        // 跳转页面
        list(request,response);
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");
        // 调用业务层方法查询数据
//        QuestionItemService questionItemService = new QuestionItemServiceImpl();
        QuestionItem questionItem = questionItemService.findById(id);
        request.setAttribute("questionItem",questionItem);
        // 跳转页面
        list(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 获取数据并封装对象
        QuestionItem questionItem = BeanUtil.fillBean(request,QuestionItem.class);
        // 调用业务层接口
        questionItemService.delete(questionItem);
        // 跳转页面
        list(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
```

```java
package top.hellocode.service.store.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.store.QuestionItemDao;
import top.hellocode.domain.store.QuestionItem;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.store.QuestionItemService;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 20:50
 */

public class QuestionItemServiceImpl implements QuestionItemService {

    @Override
    public void save(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            questionItem.setId(id);

            // 调用Dao层操作
            questionItemDao.save(questionItem);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            // 调用Dao层操作
            questionItemDao.delete(questionItem);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(QuestionItem questionItem) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            // 调用Dao层操作
            questionItemDao.update(questionItem);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public QuestionItem findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            // 调用Dao层操作
            return questionItemDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll (String questionId , int page, int size) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            QuestionItemDao questionItemDao = MapperFactory.getMapper(sqlSession,QuestionItemDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<QuestionItem> all = questionItemDao.findAll(questionId);
            PageInfo pageInfo = new PageInfo<>(all);
            return pageInfo;
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```

```java
package top.hellocode.dao.store;

import top.hellocode.domain.store.QuestionItem;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月28日 20:47
 */
public interface QuestionItemDao {
    int save(QuestionItem questionItem);		// 保存
    int delete(QuestionItem questionItem);    // 删除
    int update(QuestionItem questionItem);    // 修改
    QuestionItem findById(String id);    // 通过id查询
    /**
     * @Description:根据题目id查询所有选项
     * @param questionId:  题目id
     * @return: java.util.List<top.hellocode.domain.store.QuestionItem>
     */
    List<QuestionItem> findAll(String questionId);        // 查询全部
}
```

### 题目数据导出

#### 报表

- Excel：可编辑、动态数据、格式多样化
- pdf：不可编辑、死数据、格式固定
- html css js：不可编辑、动态数据、格式多样化

**Excel报表**

- JXL：早期比较常用，支持xls文件操作
- POI：支持xls与xlsx文件操作，现在使用量大（office所有系列都能读写）

**使用POI写Excel文件**

```java
@Test
public void testWriteByPoi() throws IOException {
    // 1. 获取到对应的Excel文件——工作簿文件
    XSSFWorkbook wb = new XSSFWorkbook();
    // 2. 创建工作表
    XSSFSheet sheet = wb.createSheet();
    wb.createSheet("工作表名称");     // 指定名称
    // 3. 创建工作表中的行对象
    XSSFRow row = sheet.createRow(1);       // 第二行（索引从0开始）
    // 4. 创建工作表中行中的列对象
    XSSFCell cell = row.createCell(1);      // 第二列
    // 5. 为单元格设置数据
    cell.setCellValue("测试一下单元格");

    // 创建一个文件对象,作为excel文件内容的输出文件
    File f = new File("test.xlsx");
    // 输出时通过流的形式对外输出，包装对应的目标文件
    OutputStream os = new FileOutputStream(f);
    // 将内存中的workbook数据写入到流中
    wb.write(os);
    // 释放资源
    os.close();
    wb.close();
}
```

**使用POI读Excel文件**

```java
@Test
public void testReadByPoi() throws IOException {
    // 1. 获取要读取的文件工作簿对象
    Workbook wb = new XSSFWorkbook("test.xlsx");
    // 2. 获取工作表
    Sheet sheet = wb.getSheetAt(0);
    // 3. 获取行
    Row row = sheet.getRow(1);
    // 4. 获取单元格
    Cell cell = row.getCell(1);
    // 5. 根据数据类型获取数据
    String data = cell.getStringCellValue();
    System.out.println(data);
    // 释放资源
    wb.close();
}
```

**题目数据导出模板制作**

- 内容分区
  - 表头
  - 标题
  - 数据
- 内容制作
  - 注意结构命名规范（row,cell）
  - 数据（模拟数据）
  - 样式（制作并抽取）

```java
@Test
public void testProjectPoi() throws IOException {
    // 1. 获取到对应的Excel文件——工作簿文件
    XSSFWorkbook wb = new XSSFWorkbook();
    // 2. 创建工作表
    XSSFSheet s = wb.createSheet("题目数据文件");

    // 设置通用配置
    //        s.setColumnWidth(4,100);        // 列宽
    // 设置水平居中
    CellStyle cs_field = wb.createCellStyle();
    cs_field.setAlignment(HorizontalAlignment.CENTER);
    // 表格线
    cs_field.setBorderTop(BorderStyle.THIN);
    cs_field.setBorderBottom(BorderStyle.THIN);
    cs_field.setBorderLeft(BorderStyle.THIN);
    cs_field.setBorderRight(BorderStyle.THIN);

    // 制作标题
    s.addMergedRegion(new CellRangeAddress(1,1,1,12));

    Row row_1 = s.createRow(1);
    Cell cell_1_1 = row_1.createCell(1);
    cell_1_1.setCellValue("在线试题导出信息");
    // 创建一个样式
    CellStyle cs_title = wb.createCellStyle();
    // 设置水平和居中对齐
    cs_title.setAlignment(HorizontalAlignment.CENTER);
    cs_title.setVerticalAlignment(VerticalAlignment.CENTER);
    // 为单元格添加样式
    cell_1_1.setCellStyle(cs_title);

    // 制作表头
    String[] fields = {"题目ID","所属公司ID","所属目录ID","题目简介","题干描述",
                       "题干配图","题目分析","题目类型","题目难度","是否经典题","题目状态","审核状态"};
    XSSFRow row_2 = s.createRow(2);

    for (int i = 0; i < fields.length; i++) {
        Cell cell_2_temp =  row_2.createCell(1 + i);
        cell_2_temp.setCellValue(fields[i]);
        cell_2_temp.setCellStyle(cs_field);
    }


    // 制作数据区
    List<Question> questionList = new ArrayList<>();
    Question qq = new Question();
    qq.setId("11");
    qq.setPicture("12");
    qq.setReviewStatus("13");
    qq.setAnalysis("14");
    qq.setCatalogId("15");
    qq.setCompanyId("16");
    qq.setDifficulty("17");
    qq.setIsClassic("18");
    qq.setRemark("19");
    qq.setState("110");
    qq.setSubject("111");
    qq.setType("112");
    questionList.add(qq);

    Question qqq = new Question();
    qqq.setId("11");
    qqq.setPicture("12");
    qqq.setReviewStatus("13");
    qqq.setAnalysis("14");
    qqq.setCatalogId("15");
    qqq.setCompanyId("16");
    qqq.setDifficulty("17");
    qqq.setIsClassic("18");
    qqq.setRemark("19");
    qqq.setState("110");
    qqq.setSubject("111");
    qqq.setType("112");
    questionList.add(qqq);


    int row_index = 0;
    for (Question q : questionList) {
        int cell_index = 0;
        XSSFRow row_temp = s.createRow(3 + row_index++);
        Cell cell_data_1 =  row_temp.createCell(1 + cell_index++);
        cell_data_1.setCellValue(q.getId());
        cell_data_1.setCellStyle(cs_field);

        Cell cell_data_2 =  row_temp.createCell(1 + cell_index++);
        cell_data_2.setCellValue(q.getCompanyId());
        cell_data_2.setCellStyle(cs_field);

        Cell cell_data_3 =  row_temp.createCell(1 + cell_index++);
        cell_data_3.setCellValue(q.getCatalogId());
        cell_data_3.setCellStyle(cs_field);

        Cell cell_data_4 =  row_temp.createCell(1 + cell_index++);
        cell_data_4.setCellValue(q.getRemark());
        cell_data_4.setCellStyle(cs_field);

        Cell cell_data_5 =  row_temp.createCell(1 + cell_index++);
        cell_data_5.setCellValue(q.getSubject());
        cell_data_5.setCellStyle(cs_field);

        Cell cell_data_6 =  row_temp.createCell(1 + cell_index++);
        cell_data_6.setCellValue(q.getPicture());
        cell_data_6.setCellStyle(cs_field);

        Cell cell_data_7 =  row_temp.createCell(1 + cell_index++);
        cell_data_7.setCellValue(q.getAnalysis());
        cell_data_7.setCellStyle(cs_field);

        Cell cell_data_8 =  row_temp.createCell(1 + cell_index++);
        cell_data_8.setCellValue(q.getType());
        cell_data_8.setCellStyle(cs_field);

        Cell cell_data_9 =  row_temp.createCell(1 + cell_index++);
        cell_data_9.setCellValue(q.getDifficulty());
        cell_data_9.setCellStyle(cs_field);

        Cell cell_data_10 =  row_temp.createCell(1 + cell_index++);
        cell_data_10.setCellValue(q.getIsClassic());
        cell_data_10.setCellStyle(cs_field);

        Cell cell_data_11 =  row_temp.createCell(1 + cell_index++);
        cell_data_11.setCellValue(q.getState());
        cell_data_11.setCellStyle(cs_field);

        Cell cell_data_12 =  row_temp.createCell(1 + cell_index++);
        cell_data_12.setCellValue(q.getReviewStatus());
        cell_data_12.setCellStyle(cs_field);
    }




    // 创建一个文件对象,作为excel文件内容的输出文件
    File f = new File("test.xlsx");
    // 输出时通过流的形式对外输出，包装对应的目标文件
    OutputStream os = new FileOutputStream(f);
    // 将内存中的workbook数据写入到流中
    wb.write(os);
    // 释放资源
    os.close();
    wb.close();
}
```

**导出题目报表**

- 根据报表内容组织要展示的数据
- 使用ByteOutputStream作为数据载体在业务层与表现层间进行数据交互
- 通过ServletOutputStream读取ByteOutputStream中保存的数据获取报表内容数据
- 定义输出的数据格式，通过Tomcat安装目录中的web.xml查询
- 请求头信息处理
  - 文件名设置
  - 中文字符设定

*servlet*

```java
    private void downloadReport(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 返回的数据类型为文件xlsx类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        // 修改文件名
        response.addHeader("Content-Disposition","attachment;fileName=questionReport.xlsx");
        // 生成报告的文件，传递到前端页面
        ByteArrayOutputStream os = questionService.getReport();
        // 获取产生响应的流对象
        ServletOutputStream sos = response.getOutputStream();
        // 将数据从原始的字节流对象中提取出来，写入到servlet对应的输出流中
        os.writeTo(sos);
        // 将输出流刷新
        sos.flush();
        os.close();

    }
```

*service*

```java
@Override
public ByteArrayOutputStream getReport() throws IOException {
    SqlSession sqlSession = null;
    List<Question> questionList = null;
    try{
        // 获取sqlSession对象
        sqlSession = MapperFactory.getSqlSession();
        // 获取Dao
        QuestionDao questionDao = MapperFactory.getMapper(sqlSession,QuestionDao.class);

        // 调用Dao层操作
        questionList = questionDao.findAll();
    }catch (Exception e){
        throw new RuntimeException(e);      // 抛出异常
        // 记录日志（占位，功能未实现）
    }finally {
        try{
            TransactionUtil.close(sqlSession);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // 1. 获取到对应的Excel文件——工作簿文件
    XSSFWorkbook wb = new XSSFWorkbook();
    // 2. 创建工作表
    XSSFSheet s = wb.createSheet("题目数据文件");

    // 设置通用配置
    //        s.setColumnWidth(4,100);        // 列宽
    // 设置水平居中
    CellStyle cs_field = wb.createCellStyle();
    cs_field.setAlignment(HorizontalAlignment.CENTER);
    // 表格线
    cs_field.setBorderTop(BorderStyle.THIN);
    cs_field.setBorderBottom(BorderStyle.THIN);
    cs_field.setBorderLeft(BorderStyle.THIN);
    cs_field.setBorderRight(BorderStyle.THIN);

    // 制作标题
    s.addMergedRegion(new CellRangeAddress(1,1,1,12));

    Row row_1 = s.createRow(1);
    Cell cell_1_1 = row_1.createCell(1);
    cell_1_1.setCellValue("在线试题导出信息");
    // 创建一个样式
    CellStyle cs_title = wb.createCellStyle();
    // 设置水平和居中对齐
    cs_title.setAlignment(HorizontalAlignment.CENTER);
    cs_title.setVerticalAlignment(VerticalAlignment.CENTER);
    // 为单元格添加样式
    cell_1_1.setCellStyle(cs_title);

    // 制作表头
    String[] fields = {"题目ID","所属公司ID","所属目录ID","题目简介","题干描述",
                       "题干配图","题目分析","题目类型","题目难度","是否经典题","题目状态","审核状态"};
    XSSFRow row_2 = s.createRow(2);

    for (int i = 0; i < fields.length; i++) {
        Cell cell_2_temp =  row_2.createCell(1 + i);
        cell_2_temp.setCellValue(fields[i]);
        cell_2_temp.setCellStyle(cs_field);
    }


    // 制作数据区

    int row_index = 0;
    for (Question q : questionList) {
        int cell_index = 0;
        XSSFRow row_temp = s.createRow(3 + row_index++);
        Cell cell_data_1 =  row_temp.createCell(1 + cell_index++);
        cell_data_1.setCellValue(q.getId());
        cell_data_1.setCellStyle(cs_field);

        Cell cell_data_2 =  row_temp.createCell(1 + cell_index++);
        cell_data_2.setCellValue(q.getCompanyId());
        cell_data_2.setCellStyle(cs_field);

        Cell cell_data_3 =  row_temp.createCell(1 + cell_index++);
        cell_data_3.setCellValue(q.getCatalogId());
        cell_data_3.setCellStyle(cs_field);

        Cell cell_data_4 =  row_temp.createCell(1 + cell_index++);
        cell_data_4.setCellValue(q.getRemark());
        cell_data_4.setCellStyle(cs_field);

        Cell cell_data_5 =  row_temp.createCell(1 + cell_index++);
        cell_data_5.setCellValue(q.getSubject());
        cell_data_5.setCellStyle(cs_field);

        Cell cell_data_6 =  row_temp.createCell(1 + cell_index++);
        cell_data_6.setCellValue(q.getPicture());
        cell_data_6.setCellStyle(cs_field);

        Cell cell_data_7 =  row_temp.createCell(1 + cell_index++);
        cell_data_7.setCellValue(q.getAnalysis());
        cell_data_7.setCellStyle(cs_field);

        Cell cell_data_8 =  row_temp.createCell(1 + cell_index++);
        cell_data_8.setCellValue(q.getType());
        cell_data_8.setCellStyle(cs_field);

        Cell cell_data_9 =  row_temp.createCell(1 + cell_index++);
        cell_data_9.setCellValue(q.getDifficulty());
        cell_data_9.setCellStyle(cs_field);

        Cell cell_data_10 =  row_temp.createCell(1 + cell_index++);
        cell_data_10.setCellValue(q.getIsClassic());
        cell_data_10.setCellStyle(cs_field);

        Cell cell_data_11 =  row_temp.createCell(1 + cell_index++);
        cell_data_11.setCellValue(q.getState());
        cell_data_11.setCellStyle(cs_field);

        Cell cell_data_12 =  row_temp.createCell(1 + cell_index++);
        cell_data_12.setCellValue(q.getReviewStatus());
        cell_data_12.setCellStyle(cs_field);
    }


    // 将内存中的workbook数据写入到流中
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    wb.write(os);
    // 释放资源
    wb.close();
    return os;
}
```

### 权限系统

- 权限系统是一种设定用户与可操作模块之间关系的系统
- 通过设定用户与可操作的模块之间的关系，控制用户在可指定范围内进行业务执行
- 基于用户的权限控制（UBAC：User-Based Access Control）
- 基于角色的权限控制（RBAC：Role-Based Access Control）

**关于模块的设定**

- 模块用于定义系统中可进行的操作
- 操作用户模块（菜单）
- 操作部门模块（菜单）
  - 添加部门（按钮、链接）
  - 删除部门（按钮、链接）

#### 角色与模块快速开发

**字段**

```java
public class Role{
    private String id;
    private String name;		// 名称
    private String remark;		// 描述
    private Date createTime;		// 创建时间
}
```

```java
public class Module{
    private String id;
    private String parentId;		// 所属模块id
    private String name;		// 名称
    private Long ctype;		// 类型（1-系统菜单，2-二级菜单，3-...，4-....）
    private Long state;		// 状态(1-可用，2-不可用)
    private String curl;		// 请求url（用于权限校验）
    private String remark;		// 描述
    
    private Module module;		// 自连接关系
}
```

> 角色模块参照单表增删改查制作（同企业模块）
>
> 模块参照自连接增删改查制作（同部门模块）

**树形控件结构分析**

- 明确控件的本质功能
- 尝试性删除调试效果
- 删除页面上所有非核心内容

**加载授权操作动态数据**

- 使用静态数据确认树形结构功能是否完备
- 后台按照页面数据结构拼写SQL查询数据
- 根据页面数据结构组织数据格式（json）

**绑定角色与模块间数据关系**

- 页面数据格式转换
  - 多数据格式转换成单数据格式，后台使用字符串接收数据
  - 多数据提交，后台使用数组接收数据
- 业务功能组合
  - *通过在业务层中调用多个数据层操作，组合业务功能*

**页面**

*Role*

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                系统管理
                <small>角色管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">编辑角色</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/system/role?operation=save" method="post">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">角色名</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="角色名" name="name" value="${role.name}">
                                    </div>
                                    <div class="col-md-2 title">备注</div>
                                    <div class="col-md-10 data line-height36">
                                        <input type="text" class="form-control" placeholder="备注" name="remark" value="${role.remark}">
                                    </div>
                                    <div class="col-md-2 title"></div>
                                    <div class="col-md-10 data text-center">
                                        <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</body>

</html>
```

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <base href="${ctx}/">
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
    <link rel="stylesheet" href="${ctx}/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx}/plugins/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>

    <SCRIPT type="text/javascript">
        // 定义页面对应的树形组件
        var zTreeObj;
        var setting = {check: {enable: true},data: {simpleData: {enable: true}}};

        var zNodes =${roleModuleJson}
        /*
        [
            {id:1,pId:0,name:'平台系统管理',checked:false},
            {id:101,pId:1,name:'企业管理',checked:false},
            {id:102,pId:1,name:'部门管理',checked:false},
            {id:103,pId:1,name:'用户管理',checked:false},
            {id:104,pId:1,name:'角色管理',checked:false},
            {id:105,pId:1,name:'模块管理',checked:false},
            {id:106,pId:1,name:'系统日志管理',checked:false},
            {id:2,pId:0,name:'题库管理',checked:false},
            {id:201,pId:2,name:'题目学科管理',checked:false},
            {id:202,pId:2,name:'题目类型管理',checked:false},
            {id:203,pId:2,name:'题目管理',checked:false},
            {id:204,pId:2,name:'题目审核日志',checked:false},
            {id:3,pId:0,name:'会员管理',checked:false},
            {id:301,pId:3,name:'会员账号管理',checked:false},
            {id:302,pId:3,name:'会员答题管理',checked:false}
        ]
        */
        $(document).ready(function(){
            /*
            $.get("${ctx}/system/role?operation=getModuleByRoleId&id=${role.id}",function(data) {
                var json = eval('(' + data + ')');
                initZtree(json);
            });
            */
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes)
            var zTree = $.fn.zTree.getZTreeObj("treeDemo")
            zTree.setting.check.chkboxType = { "Y" : "ps", "N" : "ps" }
            zTreeObj.expandAll(true);//true：展开所有
        });

        //实现权限分配
        function submitCheckedNodes() {
            //1.获取所有的勾选权限节点
            var nodes = zTreeObj.getCheckedNodes(true);//true:被勾选，false：未被勾选
            //2.循环nodes，获取每个节点的id，并将数据加入数组
            //1,2,3,4,5     1+","+2+","+3.....
            //数据的临时存储数组，为了方便内容连接成为一个由逗号分隔的字符串
            var moduleArrays = [];
            for(var i=0;i<nodes.length;i++) {
                moduleArrays.push(nodes[i].id);
            }
            //3.将数组中的数据使用,连接后，赋值给表单，传入后台
            $("#moduleIds").val(moduleArrays.join(','));    //1,2,3,4,5
            $("#icform").submit();
        }

</SCRIPT>


</head>

<body style="overflow: visible;">
<div id="frameContent" class="content-wrapper" style="margin-left:0px;height: 1200px" >
    <section class="content-header">
        <h1>
            菜单管理
            <small>菜单列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">角色 [${role.name}] 权限列表</h3>
            </div>
            <div class="box-body">
                <!-- 数据表格 -->
                <div class="table-box">
                    <!-- 树菜单 /-->
                    <form id="icform" method="post" action="${ctx}/system/role?operation=updateRoleModule">
                        <input type="hidden" name="roleId" value="${role.id}"/>
                        <input type="hidden" id="moduleIds" name="moduleIds" value=""/>
                        <ul id="treeDemo" class="ztree"></ul>
                    </form>
                    <!--工具栏-->
                    </form>
                    <div class="box-tools text-left">
                    <button type="button" class="btn bg-maroon" onclick="submitCheckedNodes()">保存</button>
                    <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
```

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->

</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/system/role?operation=delete&id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        系统管理
        <small>角色管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
<section class="content">

    <!-- .box-body -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">角色列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/system/role?operation=toAdd"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">名称</th>
                        <th class="sorting">说明</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="o" varStatus="status">
                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                        <td><input type="checkbox" name="id" value="${o.id}"/></td>
                        <td><a href="${ctx}/system/role?operation=toEdit&id=${o.id}">${o.name}</a></td>
                        <td>${o.remark}</td>
                        <th class="text-center">
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/system/role?operation=toEdit&id=${o.id}"'>编辑</button>
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/system/role?operation=author&id=${o.id}"'>授权</button>
                        </th>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/system/role?operation=list" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>

</section>
</div>
</body>

</html>
```

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                系统管理
                <small>角色管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">编辑角色</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${ctx}/system/role?operation=edit" method="post">
                            <input type="hidden" name="id" value="${role.id}">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">角色名</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="角色名" name="name" value="${role.name}">
                                    </div>
                                    <div class="col-md-2 title">备注</div>
                                    <div class="col-md-10 data line-height36">
                                        <input type="text" class="form-control" placeholder="备注" name="remark" value="${role.remark}">
                                    </div>
                                    <div class="col-md-2 title"></div>
                                    <div class="col-md-10 data text-center">
                                        <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</body>

</html>
```

*module*

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                系统管理
                <small>模块管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="panel panel-default">
                <div class="panel-heading">模块信息</div>
                <form id="editForm" action="${ctx}/system/module?operation=save" method="post">
                    <div class="row data-type" style="margin: 0px">
                        <div class="col-md-2 title">模块名</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="模块名" name="name" value="${module.name}">
                        </div>

                        <div class="col-md-2 title">上级模块</div>
                        <div class="col-md-4 data">
                            <select class="form-control" onchange="document.getElementById('parentName').value=this.options[this.selectedIndex].text" name="parentId">
                                <option value="">请选择</option>
                                <c:forEach items="${moduleList}" var="item">
                                    <option ${module.parentId == item.id ?'selected':''} value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-2 title">类型</div>
                        <div class="col-md-4 data">
                            <div class="form-group form-inline">
                                <div class="radio"><label><input type="radio" ${module.ctype==0?'checked':''} name="ctype" value="0">主菜单</label></div>
                                <div class="radio"><label><input type="radio" ${module.ctype==1?'checked':''} name="ctype" value="1">二级菜单</label></div>
                                <div class="radio"><label><input type="radio" ${module.ctype==2?'checked':''} name="ctype" value="2">按钮</label></div>
                            </div>
                        </div>

                        <div class="col-md-2 title">状态</div>
                        <div class="col-md-4 data">
                            <div class="form-group form-inline">
                                <div class="radio"><label><input type="radio" ${module.state==0?'checked':''} name="state" value="0">停用</label></div>
                                <div class="radio"><label><input type="radio" ${module.state==1?'checked':''} name="state" value="1">启用</label></div>
                            </div>
                        </div>

                        <div class="col-md-2 title">链接</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="链接" name="curl" value="${module.curl}">
                        </div>

                        <div class="col-md-2 title ">备注</div>
                        <div class="col-md-4 data ">
                            <input type="text" class="form-control" placeholder="备注" name="curl" value="${remark.curl}">
                        </div>

                    </div>
                </form>
            </div>
            <!--订单信息/-->
            <div class="box-tools text-center">
                <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
            </div>
        </section>
    </div>
</body>

</html>
```

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/system/module?operation=delete&id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        系统管理
        <small>模块管理</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
<section class="content">

    <!-- .box-body -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">模块列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/system/module?operation=toAdd"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">模块名</th>
                        <th class="sorting">类型</th>
                        <th class="sorting">上级模块</th>
                        <th class="sorting">链接</th>
                        <th class="sorting">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="o"  varStatus="st">
                        <tr>
                            <td><input type="checkbox" name="id" value="${o.id }"/></td>
                            <td><a href="#">${o.name}</a></td>
                            <td>${o.ctype==0?'主菜单':o.ctype==1?'二级菜单':'按钮'}</td>
                            <td>${o.module.name}</td>
                            <td>${o.curl}</td>
                            <td>${o.state==0?'停用':'启用'}</td>
                            <th class="text-center"><button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/system/module?operation=toEdit&id=${o.id}"'>编辑</button></th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/system/module?operation=list" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>
</section>
</div>
</body>
</html>
```

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                系统管理
                <small>模块管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>
        <section class="content">
            <div class="panel panel-default">
                <div class="panel-heading">模块信息</div>
                <form id="editForm" action="${ctx}/system/module?operation=edit" method="post">
                    <input type="hidden" name="id" value="${module.id}">
                    <div class="row data-type" style="margin: 0px">
                        <div class="col-md-2 title">模块名</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="模块名" name="name" value="${module.name}">
                        </div>

                        <div class="col-md-2 title">上级模块</div>
                        <div class="col-md-4 data">
                            <select class="form-control" onchange="document.getElementById('parentName').value=this.options[this.selectedIndex].text" name="parentId">
                                <option value="">请选择</option>
                                <c:forEach items="${moduleList}" var="item">
                                    <option ${module.parentId == item.id ?'selected':''} value="${item.id}">${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-2 title">类型</div>
                        <div class="col-md-4 data">
                            <div class="form-group form-inline">
                                <div class="radio"><label><input type="radio" ${module.ctype==0?'checked':''} name="ctype" value="0">主菜单</label></div>
                                <div class="radio"><label><input type="radio" ${module.ctype==1?'checked':''} name="ctype" value="1">二级菜单</label></div>
                                <div class="radio"><label><input type="radio" ${module.ctype==2?'checked':''} name="ctype" value="2">按钮</label></div>
                            </div>
                        </div>

                        <div class="col-md-2 title">状态</div>
                        <div class="col-md-4 data">
                            <div class="form-group form-inline">
                                <div class="radio"><label><input type="radio" ${module.state==0?'checked':''} name="state" value="0">停用</label></div>
                                <div class="radio"><label><input type="radio" ${module.state==1?'checked':''} name="state" value="1">启用</label></div>
                            </div>
                        </div>

                        <div class="col-md-2 title">链接</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="链接" name="curl" value="${module.curl}">
                        </div>

                        <div class="col-md-2 title ">备注</div>
                        <div class="col-md-4 data ">
                            <input type="text" class="form-control" placeholder="备注" name="curl" value="${remark.curl}">
                        </div>
                    </div>
                </form>
            </div>
            <!--订单信息/-->
            <div class="box-tools text-center">
                <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
            </div>
        </section>
    </div>
</body>

</html>
```

**配置文件**

*Role*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.system.RoleDao">
    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.system.Role">
        <id column="role_id" jdbcType="VARCHAR" property="id"/>
        <result column="name" jdbcType="VARCHAR" property="name"/>
        <result column="remark" jdbcType="VARCHAR" property="remark"/>
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime"/>
    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        role_id, name, remark, create_time
    </sql>

    <!--配置查询所有，带条件-->
    <select id="findAll" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_role
    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_role
        where role_id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from ss_role
        where role_id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="save" parameterType="top.hellocode.domain.system.Role">
        insert into ss_role (role_id, name, remark, create_time)
        values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR},
        #{createTime,jdbcType=TIMESTAMP})
    </insert>

    <!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
    <update id="update" parameterType="top.hellocode.domain.system.Role">
        update ss_role
        set name = #{name,jdbcType=VARCHAR},
        remark = #{remark,jdbcType=VARCHAR},
        create_time = #{createTime,jdbcType=TIMESTAMP}
        where role_id = #{id,jdbcType=VARCHAR}
    </update>

    <!--配置根据roleId删除关系表数据-->
    <delete id="deleteRoleModule" parameterType="java.lang.String">
        delete from ss_role_module
        where role_id = #{roleId,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="saveRoleModule" parameterType="map">
        insert into ss_role_module (role_id, module_id)
        values (#{roleId,jdbcType=VARCHAR}, #{moduleId,jdbcType=VARCHAR})
    </insert>

    <!--配置根据ID查询-->
    <select id="findAllRoleByUserId" parameterType="java.lang.String" resultMap="BaseResultMap">
        SELECT
            role_id,
            NAME,
            CASE
                WHEN role_id IN (SELECT role_id FROM ss_role_user WHERE user_id = #{'userId'})
                THEN 'checked'
                ELSE ''
            END
            AS remark
        FROM
            ss_role
    </select>

</mapper>
```

*Module*

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.system.ModuleDao">
    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.system.Module">
        <id column="module_id" jdbcType="VARCHAR" property="id"/>
        <result column="parent_id" jdbcType="VARCHAR" property="parentId"/>
        <result column="name" jdbcType="VARCHAR" property="name"/>
        <result column="ctype" jdbcType="DECIMAL" property="ctype"/>
        <result column="state" jdbcType="DECIMAL" property="state"/>
        <result column="curl" jdbcType="VARCHAR" property="curl"/>
        <result column="remark" jdbcType="VARCHAR" property="remark"/>
        <association
            property="module"
            javaType="top.hellocode.domain.system.Module"
            column="parent_id"
            select="top.hellocode.dao.system.ModuleDao.findById"
            />
    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        module_id, parent_id, name, ctype, state, curl, remark
    </sql>

    <!--配置查询所有，带条件-->
    <select id="findAll" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_module
    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_module
        where module_id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from ss_module where module_id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->


    <insert id="save" parameterType="top.hellocode.domain.system.Module">
        insert into ss_module (module_id, parent_id, name, ctype, state, curl, remark)
        values (#{id,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR},
        #{name,jdbcType=VARCHAR}, #{ctype,jdbcType=DECIMAL}, #{state,jdbcType=DECIMAL},
        #{curl,jdbcType=VARCHAR},  #{remark,jdbcType=VARCHAR})
    </insert>

    <!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
    <update id="update" parameterType="top.hellocode.domain.system.Module">
        update ss_module
        set parent_id = #{parentId,jdbcType=VARCHAR},
        name = #{name,jdbcType=VARCHAR},
        ctype = #{ctype,jdbcType=DECIMAL},
        state = #{state,jdbcType=DECIMAL},
        curl = #{curl,jdbcType=VARCHAR},
        remark = #{remark,jdbcType=VARCHAR}
        where module_id = #{id,jdbcType=VARCHAR}
    </update>

    <select id="findAuthorDataByRoleId" parameterType="string" resultType="java.util.Map">
        select
            module_id as id,
            parent_id as pId,
            name as name,
            case
                when module_id in (select module_id from ss_role_module where role_id = #{roleId})
                    then 'true'
                    else 'false'
                end
            as checked
        from
            ss_module
    </select>

</mapper>
```

**Java**

*Role*

```java
package top.hellocode.dao.system;

import org.apache.ibatis.annotations.Param;
import top.hellocode.domain.system.Role;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:33
 */
public interface RoleDao {
    int save(Role role);		// 保存
    int delete(Role role);    // 删除
    int update(Role role);    // 修改
    Role findById(String id);    // 通过id查询
    List<Role> findAll();        // 查询全部

    void deleteRoleModule(String roleId);

    void saveRoleModule(@Param("roleId") String roleId,@Param("moduleId") String moduleId);
}
```

```java
package top.hellocode.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.system.RoleDao;
import top.hellocode.domain.system.Role;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.system.RoleService;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:36
 */
public class RoleServiceImpl implements RoleService {

    @Override
    public void save(Role role) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            role.setId(id);

            // 调用Dao层操作
            roleDao.save(role);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Role role) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            roleDao.delete(role);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Role role) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            roleDao.update(role);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Role findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            return roleDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Role> findAll() {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            return roleDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<Role> all = roleDao.findAll();
            PageInfo pageInfo = new PageInfo<>(all);
            return pageInfo;
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateRoleModule(String roleId, String moduleIds) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            // 修改role_module
            // 取消所有的现有关系，建立新的关系（多个）
            roleDao.deleteRoleModule(roleId);
            String[] moduleArray = moduleIds.split(",");
            for(String moduleId : moduleArray){
                roleDao.saveRoleModule(roleId,moduleId);
            }
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```

```java
package top.hellocode.web.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.system.Role;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:37
 */

// uri:/system/role?operation=list

@WebServlet("/system/role")
public class RoleServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            this.save(request,response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }else if("author".equals(operation)){
            this.author(request,response);
        }else if("updateRoleModule".equals(operation)){
            this.updateRoleModule(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        RoleService roleService = new RoleServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = roleService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Role> all = roleService.findAll();
        request.setAttribute("roleList",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Role role = BeanUtil.fillBean(request,Role.class,"yyyy-MM-dd");
        // 调用业务层接口
//        RoleService roleService = new RoleServiceImpl();
        roleService.save(role);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");

        // 加载数据
        List<Role> all = roleService.findAll();
        request.setAttribute("roleList",all);
        // 调用业务层方法查询数据
        Role role = roleService.findById(id);
        request.setAttribute("role",role);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Role role = BeanUtil.fillBean(request,Role.class,"yyyy-MM-dd");
        // 调用业务层接口
//        RoleService roleService = new RoleServiceImpl();
        roleService.update(role);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Role role = BeanUtil.fillBean(request,Role.class);
        // 调用业务层接口
//        RoleService roleService = new RoleServiceImpl();
        roleService.delete(role);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    private void author(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要授权的角色id
        String roleId = request.getParameter("id");
        // 使用id查询对应的数据（角色对应的模块信息）
        Role role = roleService.findById(roleId);
        request.setAttribute("role",role);
        // 根据当前角色id获取所有的模块数据，并加载关系数据
        List<Map> map = moduleService.findAuthorDataByRoleId(roleId);
        // map转json
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(map);
        request.setAttribute("roleModuleJson",json);
        // TODO 数据未查询
        // 跳转到树页面中
        request.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(request,response);
    }

    private void updateRoleModule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roleId = request.getParameter("roleId");
        String moduleIds = request.getParameter("moduleIds");
        roleService.updateRoleModule(roleId, moduleIds);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
```

*Module*

```java
package top.hellocode.dao.system;


import top.hellocode.domain.system.Module;

import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:40
 */
public interface ModuleDao {
    int save(Module module);		// 保存
    int delete(Module module);    // 删除
    int update(Module module);    // 修改
    Module findById(String id);    // 通过id查询
    List<Module> findAll();        // 查询全部

    List<Map> findAuthorDataByRoleId(String roleId);
}
```

```java
package top.hellocode.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.system.ModuleDao;
import top.hellocode.domain.system.Module;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.system.ModuleService;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:42
 */
public class ModuleServiceImpl implements ModuleService {

    @Override
    public void save(Module module) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            module.setId(id);

            // 调用Dao层操作
            moduleDao.save(module);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Module module) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            moduleDao.delete(module);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Module module) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            moduleDao.update(module);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Module findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            return moduleDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Module> findAll() {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            return moduleDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<Module> all = moduleDao.findAll();
            PageInfo pageInfo = new PageInfo<>(all);
            return pageInfo;
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Map> findAuthorDataByRoleId(String roleId) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            return moduleDao.findAuthorDataByRoleId(roleId);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```

```java
package top.hellocode.web.controller.system;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.system.Module;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:48
 */
// uri:/system/module?operation=list

@WebServlet("/system/module")
public class ModuleServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            this.save(request,response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        ModuleService moduleService = new ModuleServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = moduleService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Module> all = moduleService.findAll();
        request.setAttribute("moduleList",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Module module = BeanUtil.fillBean(request,Module.class,"yyyy-MM-dd");
        // 调用业务层接口
//        ModuleService moduleService = new ModuleServiceImpl();
        moduleService.save(module);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");

        // 加载数据
        List<Module> all = moduleService.findAll();
        request.setAttribute("moduleList",all);
        // 调用业务层方法查询数据
        Module module = moduleService.findById(id);
        request.setAttribute("module",module);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Module module = BeanUtil.fillBean(request,Module.class,"yyyy-MM-dd");
        // 调用业务层接口
//        ModuleService moduleService = new ModuleServiceImpl();
        moduleService.update(module);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Module module = BeanUtil.fillBean(request,Module.class);
        // 调用业务层接口
//        ModuleService moduleService = new ModuleServiceImpl();
        moduleService.delete(module);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
```

#### 权限过滤及绑定

**绑定用户与角色间数据关系**

- 数据回显：获取用户对应的角色数据（同获取角色对应的模块数据）
- 数据保存：页面收集用户id与所有的角色id，在业务层中调用多个数据层操作，组合业务功能

**登录功能快速开发**

- 业务层方法名体现业务操作名称，数据层方法名体现数据操作名称
- 密码需要进行加密
- 页面保存位置发生变更，将之前webapp目录下的页面移动至WEB-INF目录下

**动态菜单控制**

- 前提：必须基于登录功能
- 步骤
  - 根据用户id查询用户可操作的菜单，通过关联关系获取模块表中对应的数据
  - 页面上根据查询到的模块数据按照层级关系分层加载

**业务权限过滤**

- 获取当前请求操作
  - 字符串拼接
  - 忽略operation之外的所有参数（operation参数决定了业务种类）
- 获取登陆人可执行的操作
  - 改造登录功能，记录登陆人可执行的所有操作（字符串格式）
  - 放行所有无需权限校验的操作，例如静态资源获取、登录功能等
  - 所有操作都必须配置到数据库中，数据库中未配置的操作将无法通过权限校验
- 比对当前操作是否在登陆人可执行的操作范围内
  - 放行无权限提醒页面

**过滤**

```java
package top.hellocode.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 9:47
 */

@WebFilter(value = "/*")
public class AuthorFilter implements Filter {

    private FilterConfig filterConfig;

    /**
     * 初始化方法，获取过滤器的配置对象
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        //1.定义和协议相关的请求和响应对象
        HttpServletRequest request ;
        HttpServletResponse response;
        HttpSession session;
        try{
            //2.把参数转换成协议相关的对象
            request = (HttpServletRequest)req;
            response = (HttpServletResponse)resp;
            session = request.getSession();

            // 获取本次操作
            String url = request.getRequestURI();
            // 提前放行一些文件
            // .css .js .png .jpg .index
            if(url.endsWith(".css")
                    || url.endsWith(".js")
                    || url.endsWith(".png")
                    || url.endsWith(".jpg")
                    || url.endsWith("index.jsp")
                    || url.endsWith("login.jsp")
                    || url.endsWith("unauthorized.jsp")
            ){
                chain.doFilter(request,response);
                return;
            }

            String queryString = request.getQueryString();

            if(queryString.endsWith("operation=login")
                    || queryString.endsWith("operation=home")
                    || queryString.endsWith("operation=logout")
            ){
                chain.doFilter(request,response);
                return;
            }

            // 当前获取到的uri: /system.dept
            url = url.substring(1);     // 去掉开头/
            // 当前获取到的查询参数：operation=list          operation=toEdit&id=100
            int index = queryString.indexOf('&');
            if (index != -1){
                queryString = queryString.substring(0, index);
            }
            url = url + "?" + queryString;

            // 获取当前登陆人允许的操作
            String authorStr = (String) session.getAttribute("authorStr");


            // 比对本次操作是否在当前登陆人允许的操作范围内
            // 如果允许就放行，否则跳转到非法访问页
            if(authorStr.contains(url)){
                //6.放行
                chain.doFilter(request,response);
                return;
            }else {
                // 跳转错误页面
                response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        //可以做一些清理操作
    }
}
```

**user**

```java
package top.hellocode.web.controller.system;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.system.Dept;
import top.hellocode.domain.system.Module;
import top.hellocode.domain.system.Role;
import top.hellocode.domain.system.User;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:56
 */


// uri:/system/user?operation=list

@WebServlet("/system/user")
public class UserServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if ("list".equals(operation)) {   // 进入列表页
            this.list(request, response);
        } else if ("toAdd".equals(operation)) {
            this.toAdd(request, response);
        } else if ("save".equals(operation)) {
            this.save(request, response);
        } else if ("toEdit".equals(operation)) {
            this.toEdit(request, response);
        } else if ("edit".equals(operation)) {
            this.edit(request, response);
        } else if ("delete".equals(operation)) {
            this.delete(request, response);
        } else if ("userRoleList".equals(operation)) {
            this.userRoleList(request, response);
        } else if ("updateRole".equals(operation)) {
            this.updateRole(request, response);
        } else if ("login".equals(operation)) {
            this.login(request, response);
        } else if ("logout".equals(operation)) {
            this.logout(request, response);
        }else if ("home".equals(operation)) {
            this.home(request, response);
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        UserService userService = new UserServiceImpl();
        int page = 1;
        int size = 5;
        if (StringUtils.isNotBlank(request.getParameter("page"))) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (StringUtils.isNotBlank(request.getParameter("size"))) {
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = userService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page", all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/list.jsp").forward(request, response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList", all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/add.jsp").forward(request, response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
        // 调用业务层接口
//        UserService userService = new UserServiceImpl();
        userService.save(user);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");

        // 加载数据
        List<Dept> all = deptService.findAll();
        request.setAttribute("deptList", all);
        // 调用业务层方法查询数据
        User user = userService.findById(id);
        request.setAttribute("user", user);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/update.jsp").forward(request, response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
        // 调用业务层接口
//        UserService userService = new UserServiceImpl();
        userService.update(user);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        User user = BeanUtil.fillBean(request, User.class);
        // 调用业务层接口
//        UserService userService = new UserServiceImpl();
        userService.delete(user);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void userRoleList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");

        // 调用业务层方法查询数据
        User user = userService.findById(userId);
        request.setAttribute("user", user);
        // 获取角色列表
        List<Role> all = roleService.findAllRoleByUserId(userId);
        request.setAttribute("roleList", all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/user/role.jsp").forward(request, response); //将页面放到web-info下（安全）
    }

    private void updateRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("userId");
        String[] roleIds = request.getParameterValues("roleIds");
        userService.updateRole(userId, roleIds);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/user?operation=list");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        User user = userService.login(email, pwd);
        if (user != null) {
            request.getSession().setAttribute("loginUser", user);
            // 如果登录成功，加载该用户对应的角色对应的所有模块
            List<Module> moduleList = userService.findMoudleById(user.getId());
            request.setAttribute("moduleList",moduleList);

            // 当前登录用户对应的可操作模块的所有url拼接成一个大的字符串
            StringBuilder sb = new StringBuilder();
            for(Module m :moduleList){
                sb.append(m.getCurl());
                sb.append(',');
            }
            request.getSession().setAttribute("authorStr",sb.toString());
            // 跳转页面
            request.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(request, response); //将页面放到web-info下（安全）
        } else {
            // 跳转页面
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("loginUser");
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request, response); //将页面放到web-info下（安全）
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
```

```java
package top.hellocode.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.system.ModuleDao;
import top.hellocode.dao.system.UserDao;
import top.hellocode.domain.system.Module;
import top.hellocode.domain.system.User;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.system.UserService;
import top.hellocode.utils.MD5Util;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月20日 22:54
 */

public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            user.setId(id);
            // 密码加密
            user.setPassword(MD5Util.md5(user.getPassword()));

            // 调用Dao层操作
            userDao.save(user);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(User user) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            userDao.delete(user);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(User user) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            userDao.update(user);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User findById(String id) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            return userDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            return userDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<User> all = userDao.findAll();
            PageInfo pageInfo = new PageInfo<>(all);
            return pageInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateRole(String userId, String[] roleIds) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            userDao.deleteRole(userId);
            for (String roleId : roleIds) {
                userDao.updateRole(userId, roleId);
            }
            // 提交事务
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User login(String email, String pwd) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            UserDao userDao = MapperFactory.getMapper(sqlSession, UserDao.class);

            // 调用Dao层操作
            // 密码加密
            pwd = MD5Util.md5(pwd);

            return userDao.findByEmailAndPwd(email, pwd);
        } catch (Exception e) {
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Module> findMoudleById(String id) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao =  MapperFactory.getMapper(sqlSession, ModuleDao.class);

            // 调用Dao层操作

            return moduleDao.findModuleByUserId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```

**role**

```java
package top.hellocode.web.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.system.Role;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:37
 */

// uri:/system/role?operation=list

@WebServlet("/system/role")
public class RoleServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            this.save(request,response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }else if("author".equals(operation)){
            this.author(request,response);
        }else if("updateRoleModule".equals(operation)){
            this.updateRoleModule(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        RoleService roleService = new RoleServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = roleService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Role> all = roleService.findAll();
        request.setAttribute("roleList",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Role role = BeanUtil.fillBean(request,Role.class,"yyyy-MM-dd");
        // 调用业务层接口
//        RoleService roleService = new RoleServiceImpl();
        roleService.save(role);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");

        // 加载数据
        List<Role> all = roleService.findAll();
        request.setAttribute("roleList",all);
        // 调用业务层方法查询数据
        Role role = roleService.findById(id);
        request.setAttribute("role",role);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/role/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Role role = BeanUtil.fillBean(request,Role.class,"yyyy-MM-dd");
        // 调用业务层接口
//        RoleService roleService = new RoleServiceImpl();
        roleService.update(role);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Role role = BeanUtil.fillBean(request,Role.class);
        // 调用业务层接口
//        RoleService roleService = new RoleServiceImpl();
        roleService.delete(role);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    private void author(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取要授权的角色id
        String roleId = request.getParameter("id");
        // 使用id查询对应的数据（角色对应的模块信息）
        Role role = roleService.findById(roleId);
        request.setAttribute("role",role);
        // 根据当前角色id获取所有的模块数据，并加载关系数据
        List<Map> map = moduleService.findAuthorDataByRoleId(roleId);
        // map转json
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(map);
        request.setAttribute("roleModuleJson",json);
        // TODO 数据未查询
        // 跳转到树页面中
        request.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(request,response);
    }

    private void updateRoleModule(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String roleId = request.getParameter("roleId");
        String moduleIds = request.getParameter("moduleIds");
        roleService.updateRoleModule(roleId, moduleIds);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/role?operation=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
```

```java
package top.hellocode.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.system.RoleDao;
import top.hellocode.domain.system.Role;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.system.RoleService;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:36
 */
public class RoleServiceImpl implements RoleService {

    @Override
    public void save(Role role) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession, RoleDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            role.setId(id);

            // 调用Dao层操作
            roleDao.save(role);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Role role) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            roleDao.delete(role);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Role role) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            roleDao.update(role);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Role findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            return roleDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Role> findAll() {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            return roleDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<Role> all = roleDao.findAll();
            PageInfo pageInfo = new PageInfo<>(all);
            return pageInfo;
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateRoleModule(String roleId, String moduleIds) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            // 修改role_module
            // 取消所有的现有关系，建立新的关系（多个）
            roleDao.deleteRoleModule(roleId);
            String[] moduleArray = moduleIds.split(",");
            for(String moduleId : moduleArray){
                roleDao.saveRoleModule(roleId,moduleId);
            }
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Role> findAllRoleByUserId(String userId) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            RoleDao roleDao = MapperFactory.getMapper(sqlSession,RoleDao.class);

            // 调用Dao层操作
            return roleDao.findAllRoleByUserId(userId);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```

**module**

```java
package top.hellocode.web.controller.system;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import top.hellocode.domain.system.Module;
import top.hellocode.utils.BeanUtil;
import top.hellocode.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:48
 */
// uri:/system/module?operation=list

@WebServlet("/system/module")
public class ModuleServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");   // 操作标志
        if("list".equals(operation)){   // 进入列表页
            this.list(request,response);
        }else if("toAdd".equals(operation)){
            this.toAdd(request,response);
        }else if("save".equals(operation)){
            this.save(request,response);
        }else if("toEdit".equals(operation)){
            this.toEdit(request,response);
        }else if("edit".equals(operation)){
            this.edit(request,response);
        }else if("delete".equals(operation)){
            this.delete(request,response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取数据
//        ModuleService moduleService = new ModuleServiceImpl();
        int page = 1;
        int size = 5;
        if(StringUtils.isNotBlank(request.getParameter("page"))){
            page = Integer.parseInt(request.getParameter("page"));
        }
        if(StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("size"));
        }

        PageInfo all = moduleService.findAll(page, size);
        // 将数据保存到指定的位置
        request.setAttribute("page",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/list.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 加载数据
        List<Module> all = moduleService.findAll();
        request.setAttribute("moduleList",all);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/add.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Module module = BeanUtil.fillBean(request,Module.class,"yyyy-MM-dd");
        // 调用业务层接口
//        ModuleService moduleService = new ModuleServiceImpl();
        moduleService.save(module);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");
    }

    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 拿到要编辑的数据
        String id = request.getParameter("id");

        // 加载数据
        List<Module> all = moduleService.findAll();
        request.setAttribute("moduleList",all);
        // 调用业务层方法查询数据
        Module module = moduleService.findById(id);
        request.setAttribute("module",module);
        // 跳转页面
        request.getRequestDispatcher("/WEB-INF/pages/system/module/update.jsp").forward(request,response); //将页面放到web-info下（安全）
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Module module = BeanUtil.fillBean(request,Module.class,"yyyy-MM-dd");
        // 调用业务层接口
//        ModuleService moduleService = new ModuleServiceImpl();
        moduleService.update(module);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取数据并封装对象
        Module module = BeanUtil.fillBean(request,Module.class);
        // 调用业务层接口
//        ModuleService moduleService = new ModuleServiceImpl();
        moduleService.delete(module);
        // 跳转页面
        response.sendRedirect(request.getContextPath() + "/system/module?operation=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
```

```java
package top.hellocode.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import top.hellocode.dao.system.ModuleDao;
import top.hellocode.domain.system.Module;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.system.ModuleService;
import top.hellocode.utils.TransactionUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 19:42
 */
public class ModuleServiceImpl implements ModuleService {

    @Override
    public void save(Module module) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession, ModuleDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            module.setId(id);

            // 调用Dao层操作
            moduleDao.save(module);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Module module) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            moduleDao.delete(module);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Module module) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            moduleDao.update(module);
            // 提交事务
            TransactionUtil.commit(sqlSession);
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Module findById(String id) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            return moduleDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Module> findAll() {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            return moduleDao.findAll();
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public PageInfo findAll(int page, int size) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            PageHelper.startPage(page, size);
            List<Module> all = moduleDao.findAll();
            PageInfo pageInfo = new PageInfo<>(all);
            return pageInfo;
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Map> findAuthorDataByRoleId(String roleId) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            ModuleDao moduleDao = MapperFactory.getMapper(sqlSession,ModuleDao.class);

            // 调用Dao层操作
            return moduleDao.findAuthorDataByRoleId(roleId);
        }catch (Exception e){
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
```

**配置文件**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.system.UserDao">
    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.system.User">
        <id column="user_id" jdbcType="VARCHAR" property="id"/>
        <result column="email" jdbcType="VARCHAR" property="email"/>
        <result column="user_name" jdbcType="VARCHAR" property="userName"/>
        <result column="password" jdbcType="VARCHAR" property="password"/>
        <result column="state" jdbcType="DECIMAL" property="state"/>
        <result column="gender" jdbcType="CHAR" property="gender"/>
        <result column="telephone" jdbcType="VARCHAR" property="telephone"/>
        <result column="birthday" jdbcType="VARCHAR" property="birthday"/>
        <result column="join_date" jdbcType="VARCHAR" property="joinDate"/>
        <result column="dept_id" jdbcType="VARCHAR" property="deptId"/>
        <association
            property="dept"
            column="dept_id"
            javaType="top.hellocode.domain.system.Dept"
            select="top.hellocode.dao.system.DeptDao.findById"
            />
    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        user_id, email, user_name,  password, state, gender, telephone, birthday, join_date, dept_id
    </sql>

    <!--配置查询所有，带条件-->
    <select id="findAll" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_user

    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_user
        where user_id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from ss_user where user_id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="save" parameterType="top.hellocode.domain.system.User">
        insert into ss_user (user_id, email, user_name,  password, state,
        gender, telephone, birthday, join_date, dept_id
        )
        values (#{id,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR},
        #{password,jdbcType=VARCHAR}, #{state,jdbcType=DECIMAL}, #{gender,jdbcType=CHAR},
        #{telephone,jdbcType=VARCHAR}, #{birthday,jdbcType=VARCHAR}, #{joinDate,jdbcType=VARCHAR},
        #{deptId,jdbcType=VARCHAR}
        )
    </insert>

    <!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
    <update id="update" parameterType="top.hellocode.domain.system.User">
        update ss_user
        SET
        user_name = #{userName,jdbcType=VARCHAR},
        state = #{state,jdbcType=DECIMAL},
        gender = #{gender,jdbcType=CHAR},
        telephone = #{telephone,jdbcType=VARCHAR},
        dept_id = #{deptId,jdbcType=VARCHAR}
        where user_id = #{id,jdbcType=VARCHAR}
    </update>

    <!--配置根据roleId删除关系表数据-->
    <delete id="deleteRole" parameterType="java.lang.String">
        delete from ss_role_user
        where user_id = #{userId,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="updateRole" parameterType="map">
        insert into ss_role_user (role_id, user_id)
        values (#{roleId,jdbcType=VARCHAR}, #{userId,jdbcType=VARCHAR})
    </insert>

    <select id="findByEmailAndPwd" parameterType="map" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_user
        where email = #{email,jdbcType=VARCHAR} and password = #{password,jdbcType=VARCHAR}
    </select>

</mapper>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.system.RoleDao">
    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.system.Role">
        <id column="role_id" jdbcType="VARCHAR" property="id"/>
        <result column="name" jdbcType="VARCHAR" property="name"/>
        <result column="remark" jdbcType="VARCHAR" property="remark"/>
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime"/>
    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        role_id, name, remark, create_time
    </sql>

    <!--配置查询所有，带条件-->
    <select id="findAll" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_role
    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_role
        where role_id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from ss_role
        where role_id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="save" parameterType="top.hellocode.domain.system.Role">
        insert into ss_role (role_id, name, remark, create_time)
        values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR},
        #{createTime,jdbcType=TIMESTAMP})
    </insert>

    <!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
    <update id="update" parameterType="top.hellocode.domain.system.Role">
        update ss_role
        set name = #{name,jdbcType=VARCHAR},
        remark = #{remark,jdbcType=VARCHAR},
        create_time = #{createTime,jdbcType=TIMESTAMP}
        where role_id = #{id,jdbcType=VARCHAR}
    </update>

    <!--配置根据roleId删除关系表数据-->
    <delete id="deleteRoleModule" parameterType="java.lang.String">
        delete from ss_role_module
        where role_id = #{roleId,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->
    <insert id="saveRoleModule" parameterType="map">
        insert into ss_role_module (role_id, module_id)
        values (#{roleId,jdbcType=VARCHAR}, #{moduleId,jdbcType=VARCHAR})
    </insert>

    <!--配置根据ID查询-->
    <select id="findAllRoleByUserId" parameterType="java.lang.String" resultMap="BaseResultMap">
        SELECT
            role_id,
            NAME,
            CASE
                WHEN role_id IN (SELECT role_id FROM ss_role_user WHERE user_id = #{'userId'})
                THEN 'checked'
                ELSE ''
            END
            AS remark
        FROM
            ss_role
    </select>

</mapper>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.system.ModuleDao">
    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.system.Module">
        <id column="module_id" jdbcType="VARCHAR" property="id"/>
        <result column="parent_id" jdbcType="VARCHAR" property="parentId"/>
        <result column="name" jdbcType="VARCHAR" property="name"/>
        <result column="ctype" jdbcType="DECIMAL" property="ctype"/>
        <result column="state" jdbcType="DECIMAL" property="state"/>
        <result column="curl" jdbcType="VARCHAR" property="curl"/>
        <result column="remark" jdbcType="VARCHAR" property="remark"/>
        <association
            property="module"
            javaType="top.hellocode.domain.system.Module"
            column="parent_id"
            select="top.hellocode.dao.system.ModuleDao.findById"
            />
    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        module_id, parent_id, name, ctype, state, curl, remark
    </sql>

    <!--配置查询所有，带条件-->
    <select id="findAll" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_module
    </select>

    <!--配置根据ID查询-->
    <select id="findById" parameterType="java.lang.String" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from ss_module
        where module_id = #{id,jdbcType=VARCHAR}
    </select>

    <!--配置根据id删除-->
    <delete id="delete" parameterType="java.lang.String">
        delete from ss_module where module_id = #{id,jdbcType=VARCHAR}
    </delete>

    <!--配置全字段插入，当某个字段没有值时，插入null-->


    <insert id="save" parameterType="top.hellocode.domain.system.Module">
        insert into ss_module (module_id, parent_id, name, ctype, state, curl, remark)
        values (#{id,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR},
        #{name,jdbcType=VARCHAR}, #{ctype,jdbcType=DECIMAL}, #{state,jdbcType=DECIMAL},
        #{curl,jdbcType=VARCHAR},  #{remark,jdbcType=VARCHAR})
    </insert>

    <!--配置全字段更新，当提供的数据为null时，数据库数据会被更新为null-->
    <update id="update" parameterType="top.hellocode.domain.system.Module">
        update ss_module
        set parent_id = #{parentId,jdbcType=VARCHAR},
        name = #{name,jdbcType=VARCHAR},
        ctype = #{ctype,jdbcType=DECIMAL},
        state = #{state,jdbcType=DECIMAL},
        curl = #{curl,jdbcType=VARCHAR},
        remark = #{remark,jdbcType=VARCHAR}
        where module_id = #{id,jdbcType=VARCHAR}
    </update>

    <select id="findAuthorDataByRoleId" parameterType="string" resultType="java.util.Map">
        select
            module_id as id,
            parent_id as pId,
            name as name,
            case
                when module_id in (select module_id from ss_role_module where role_id = #{roleId})
                    then 'true'
                    else 'false'
                end
            as checked
        from
            ss_module
    </select>


    <!--配置根据用户ID查询-->
    <select id="findModuleByUserId" parameterType="java.lang.String" resultMap="BaseResultMap">
        /*userid->用户角色关系表->roleid->角色模块关系表->moduleid->module信息*/
        SELECT DISTINCT
            m.module_id, m.parent_id, m.name, m.ctype, m.state, m.curl, m.remark
        FROM
            ss_module AS m,
            ss_role_module AS rm,
            ss_role_user AS ru
        WHERE
            m.module_id = rm.module_id
          AND	rm.role_id = ru.role_id
          AND	ru.user_id = #{id,jdbcType=VARCHAR}
    </select>

</mapper>
```

**页面**

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./WEB-INF/pages/base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            权限不足页面
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="error-page">
           <div class="error-content" style="margin-left:0">
                <h3><i class="fa fa-warning text-yellow"></i> Oops! 没有访问权限.</h3>

                <p>
                    没有访问权限, 你可以 <a href="${pageContext.request.contextPath}/login?operation=login">返回到后台首页</a> 或者通过搜索查询
                </p>

                <form class="search-form">
                    <div class="input-group">
                        <input type="text" name="search" class="form-control" placeholder="搜索">

                        <div class="input-group-btn">
                            <button type="submit" name="submit" class="btn btn-warning btn-flat"><i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                    <!-- /.input-group -->
                </form>
            </div>
        <!-- /.error-page -->
        </div>
    </section>
</div>
<!-- 内容区域 /-->
</body>

</html>
```

### 前台系统

**结构搭建**

- 创建web工程
- 创建包层次结构
- 加载页面（Vue、ElementUI）

#### 注册功能

**Member字段**

```java
private String id;
private String nickName;
private String password;
private Date birthday;
private String gender;
private String email;
private String telephone;
private String address;
private Date registerDate;
private String state;
```

**页面**

- 发送请求，传递json格式数据到后台
- 获取后台json数据

**表现层**

- 接收数据格式：json格式
- 返回数据：json格式
- 返回页面：无

- 数据层：同后台系统添加用户功能
- 业务层：同后台系统添加用户功能

**前后端数据交互**

```java
package top.hellocode.web.controller;

import java.io.Serializable;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 19:46
 */
// 页面返回的结果数据封装对象
public class Result implements Serializable {
    private String message;     // 对应操作的返回消息
    private boolean flag;       // 对应操作的返回结果是否成功
    private Object data;        // 对应操作返回的具体数据
    private Integer code;       // 对应操作的返回状态码

    public Result(String message, Object data) {
        this.message = message;
        this.data = data;
        this.code = Code.SUCCESS;
        this.flag = true;
    }

    public Result(String message, boolean flag, Object data, Integer code) {
        this.message = message;
        this.flag = flag;
        this.data = data;
        this.code = code;
    }
}
```

```java
package top.hellocode.web.controller;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 19:48
 */
// 状态码类
public class Code {
    public static final Integer SUCCESS = 20000;
    public static final Integer FAIL = 50000;
}
```

> 将要传递到前端的数据封装为对象，统一前后端格式

**Servlet请求分发功能升级**

- 反射思想

```java
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String url = request.getRequestURI();
    int lastIndex = url.lastIndexOf('/');
    String methodName = url.substring(lastIndex + 1, url.length());

    // 获取到了方法名叫做url的方法，利用反射执行，传递参数即可
    Class clazz = this.getClass();
    try {
        Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
        Result ret = (Result) method.invoke(this, request, response);
        returnData(response,ret);
    } catch (NoSuchMethodException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
}
```

```java
protected  <T> T getData(HttpServletRequest request, Class<T> clazz) throws IOException {
    // 收集数据
    String json = JSON.parseObject(request.getInputStream(), String.class);
    // 组织成一个实体类（Member）
    return JSON.parseObject(json, clazz);
}

protected void returnData(HttpServletResponse response, Result result) throws IOException {
    // 返回结果
    response.setContentType("application/json;charset=utf-8");
    JSON.writeJSONString(response.getOutputStream(),result);
}
```

> 存在于BaseServlet中，MemberServlet中配置的Servlet路径是*，里面的方法使用请求地址

```java
public Result register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Member member = getData(request,Member.class);
    // 调用逻辑层API
    boolean flag = memberService.register(member);
    // 返回的数据封装成一个对象
    return new Result("注册成功！", null);

}
```

#### 登录功能

- 数据层（同后台系统登录功能）
- 业务层（同后台系统登录功能）
- 表现层
  - 接收json数据
  - 封装domain对象
  - 返回Result对象
- 页面
  - 发送请求
  - 接收servlet响应的json数据
  - 根据flag结果进行页面跳转处理

**登录状态记录（客户端）**

- 客户端使用localStorage存储登录状态数据，替代cookie

- 获取localStorage对象
  `storage = window.localStorage`

- localStorage数据操作

  ```java
  // 设置数据（添加/修改）
  storage.属性名 = 值
  storage['属性名'] = 值
  storage.setItem('属性名','值')
  // 获取数据
  alert(storage.属性名)
  alert(storage['属性名'])
  alert(storage.getItem('属性名'))
  // 删除数据
  storage.removeItem('属性名')
  ```

*localStorage使用注意事项*

- 存储数据最大上限：5M
- 存储的数据类型：字符串【重要】
- 数据生命周期：永久性存储（必须通过指令清除）

**登录状态记录（服务端）**

- 服务端使用redis存储登录状态数据，替代session
- 优势
  - 速度快
  - 可以设置数据活动周期
- 缺点：与服务器状态不同步（通过设置web容器生命周期控制数据清理工作）
- 案例中应用
  - 将登录用户的id作为数据存储的key

**登录状态校验**

- 依赖localStorage在客户端记录登录状态
- 依赖redis在服务器端记录登录状态
- 每次加载页面/刷新页面判定登录状态（替代过滤器的工作）
- 登录状态应用：控制页面菜单功能的显示

**退出登录状态**

- 清除localStorage在客户端记录的状态信息
- 清除redis在服务端记录的状态信息
- 注意：退出系统后需要再次加载页面状态或跳转一次页面（让导航栏回到未登录状态）

**代码**

*页面*

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>黑马面面</title>
    <link rel="stylesheet" href="/css/tt.css">
    <script src="/js/jquery-3.3.1.min.js"></script>
    <script src="./js/vue.js" type="text/javascript"></script>
    <script src="./js/vue-router.js" type="text/javascript"></script>
    <script src="./js/axios-0.18.0.js" type="text/javascript"></script>
    <script src="./element-ui/lib/index.js"></script>
    <script src="https://unpkg.com/vue-cookies@1.7.0/vue-cookies.js"></script>
</head>
<body>
<div id="app">
    <div class="top">
        <span class="top-right"  id="register" style="display: none">
            <a href="./register.html" style="color:white">注册</a>
        </span>

        <span class="top-right" id="login" style="display: none">
            <a href="./login.html" style="color:white">登录</a>
        </span>

        <span class="top-right"  id="exit" style="display: none">
            <a href="#" @click="logout()" style="color:white">退出</a>
        </span>

        <span class="top-right"  id="myexam" style="display: none">
            <a href="./history.html" style="color:white">历史测试</a>
        </span>

        <span class="top-right" id="exam" style="display: none">
            <a href="./paper.html" style="color:white">开始测试</a>
        </span>

        <span class="top-right" id="nickname" style="display: none">
            {{nickname}},你好
        </span>
    </div>

    <div class="container">
    <div class="left">
        <a>
            <img src="/img/logo.png" style="width: 200px;height: 70px"><br/>
        </a>
        <ul>
            <li><a class="channel-item active" href="#">
                <span class="">
                    推荐
                </span>
            </a></li>

            <li><a class="channel-item" href="#">
                <span>
                    视频解题
                </span>
            </a></li>

            <li><a class="channel-item" href="#">
                <span>
                    热点题型
                </span>
            </a></li>

            <li><a class="channel-item" href="#">
                <span>
                    解题直播
                </span>
            </a></li>

            <li><a class="channel-item" href="#">
                <span>
                    热门题库
                </span>
            </a></li>

            <li><a class="channel-item" href="#">
                <span>
                    高手过招
                </span>
            </a></li>

            <li><a class="channel-item" href="#">
                <span>
                    一对一解题
                </span>
            </a></li>

            <li><a class="channel-item" href="#">
                <span>
                    入职实测
                </span>
            </a></li>

        </ul>

    </div>
    <div class="center">
        <ul class="news_list">
            <li class="">
                <div class="news-box">
                    <div class="single-mode-lbox">
                        <a href="#" target="_blank">
                            <img src="img/question_store.png">
                        </a>
                    </div>
                    <div class="single-mode-rbox">

                        <div class="title-box">
                            <a href="group/6760571446231040523/" class="link">阿里题库解析</a>
                        </div>

                        <div class="">
                            <div class="bui-left footer-bar-left">
                                <a href="#" target="_blank" class="footer-bar-action tag tag-style-other">
                                    新鲜出炉
                                </a>

                                <a href="#" target="_blank" class="footer-bar-action media-avatar">
                                    <img src="img/question_store.png">
                                </a>
                                <a href="#" target="_blank" class="footer-bar-action source">&nbsp;黑马论坛&nbsp;⋅
                                </a>
                                <a href="#" target="_blank" class="footer-bar-action source">&nbsp;5万+阅读&nbsp;⋅
                                </a>
                                <span class="footer-bar-action">&nbsp;23分钟前</span></div>

                        </div>

                    </div>
                </div>
            </li>

        </ul>
        <!-- <div class="loading" style="text-align: center; height: 80px">
            <img src="img/loading2.gif" height="100%">
        </div> -->
    </div>
    <div class="right">
        <div class="tt-input tt-input-group tt-input-group__append">
            <div class="tt-input ">
                <input type="text" placeholder="请输入要搜索内容" autocomplete="off" class="tt-input__inner">
            </div>
            <div class="tt-input">
                <button type="button" class="tt-button tt-button--default">
                    <span>搜索</span>
                </button>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    /* 脚本中创建对象,处理业务 */
    var vm = new Vue({
            el: '#app',
            data: {
                nickname: '',
            },
            methods: {
                checkLogin() {
                    //判断当前用户是否登录了，判断现在是否有登录人的信息

                    /*  状态1：未登录
                    document.querySelector("#register").style.display = 'block';
                    document.querySelector("#login").style.display = 'block';
                    document.querySelector("#myexam").style.display = 'none';
                    document.querySelector("#exam").style.display = 'none';
                    document.querySelector("#exit").style.display = 'none';
                    document.querySelector("#nickname").style.display = 'none';
                    */


                    /*  状态2：已登录
                    document.querySelector("#register").style.display = 'none';
                    document.querySelector("#login").style.display = 'none';
                    document.querySelector("#myexam").style.display = 'block';
                    document.querySelector("#exam").style.display = 'block';
                    document.querySelector("#exit").style.display = 'block';
                    document.querySelector("#nickname").style.display = 'block';
                    */

                    let _this = this;
                    //从localStorage中获取数据，获取当前保存的用户名，再根据用户名获取后台是否登录的状态
                    if(!window.localStorage){
                        alert("浏览器不支持localStorage，请升级浏览器")
                    }else {
                        //获取localStorage对象
                        let storage = window.localStorage;
                        alert("id："+storage.id);

                        //测试是否有登录数据，id
                        if(storage.id ==  undefined){
                            //如果本地没有用户信息，显示登陆和注册按钮
                            document.querySelector("#register").style.display = 'block';
                            document.querySelector("#login").style.display = 'block';
                            document.querySelector("#myexam").style.display = 'none';
                            document.querySelector("#exam").style.display = 'none';
                            document.querySelector("#exit").style.display = 'none';
                            document.querySelector("#nickname").style.display = 'none';
                        }else {


                            //如果本地存在用户信息，需要确认服务器是否存在当前用户登录信息（redis中）
                            //发送请求，根据当前id去服务器中查找对应的数据
                            axios.post('/member/checkLogin', '{"id":"'+storage.id+'"}').then(function (response) {
                                //alert(response.data.data)


                                //获取响应数据
                                let res = response.data;
                                //alert("redis服务器中存储的用户名信息  : "+res.data)
                                //判定本地用户对应是否处于登录状态，处于登录状态的用户具有用户名信息
                                if(res.data == undefined){
                                    //如果没有用户名信息，当前用户未登录，显示登陆与注册按钮
                                    document.querySelector("#register").style.display = 'block';
                                    document.querySelector("#login").style.display = 'block';
                                    document.querySelector("#myexam").style.display = 'none';
                                    document.querySelector("#exam").style.display = 'none';
                                    document.querySelector("#exit").style.display = 'none';
                                    document.querySelector("#nickname").style.display = 'none';
                                }else{
                                    //如果具有用户名信息，显示用户答题相关按钮
                                    //设置vue对象nickname属性值
                                    _this.nickname = res.data;
                                    document.querySelector("#register").style.display = 'none';
                                    document.querySelector("#login").style.display = 'none';
                                    document.querySelector("#myexam").style.display = 'block';
                                    document.querySelector("#exam").style.display = 'block';
                                    document.querySelector("#exit").style.display = 'block';
                                    //显示当前登录用户对应的登录信息组件
                                    document.querySelector("#nickname").style.display = 'block';
                                }
                            }).catch(function (err) {
                                console.log(err)
                            });
                        }
                    }
                },
                logout(){
                    //1.获取localStorage
                    let storage = window.localStorage;
                    //2.发送请求，清除登录状态
                    axios.post('/member/logout', '{"id":"'+storage.id+'"}').then(function (response) {
                        //1.获取响应数据
                        let data = response.data;
                        //2.提示
                        //alert(data.flag);
                    }).catch(function (err) {
                        console.log(err)
                    });
                    //3.清理localStorage
                    window.localStorage.clear();
                    //4.通过调用checkLogin方法重置主页面的右上角显示区域
                    this.checkLogin();
                }
            },
            created(){
                this.checkLogin();
            },

        }
    );
</script>

</body>
</html>
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="./img/asset-favicon.ico"/>
    <title>注册页面</title>
    <!-- 页面 css js -->
    <!-- <script type="text/javascript" src="../../plugins/sui/sui.min.js" charset="UTF-8"></script> -->
    <link rel="stylesheet" type="text/css" href="./plugins/normalize-css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/yui/cssgrids-min.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/sui/sui.min.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/sui/sui-append.min.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="./css/widget-base.css"/>
    <link rel="stylesheet" type="text/css" href="./css/widget-head-foot.css"/>
    <link rel="stylesheet" type="text/css" href="./css/page-sj-person-loginsign.css"/>
    <link rel="stylesheet" href="./element-ui/lib/theme-chalk/index.css">

    <script src="./js/vue.js" type="text/javascript"></script>
    <script src="./js/axios-0.18.0.js" type="text/javascript"></script>
    <script src="./element-ui/lib/index.js"></script>
</head>
<body>

<div id="app">
    <!--头部导航-->
    <header class="head-login">
        <div class="wrapper">
            <div class="sui-navbar">
                <div class="navbar-inner">
                    <a href="./index.html" class="sui-brand"> <img src="./img/asset-logo-black.png" alt="刷题"/> </a>
                    <span class="login-text">| 注册</span>
                </div>
            </div>
        </div>
    </header>
    <div class="item">
    <div class="form">
    <h3 class="loginsign-title">用户注册</h3>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

            <el-form-item label="用户昵称" prop="nickname">
                <el-input v-model="ruleForm.nickname"></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="ruleForm.password"></el-input>
            </el-form-item>

            <el-form-item label="生日" required>
                <el-col :span="11">
                    <el-form-item prop="birthday">
                        <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.birthday" style="width: 100%;">
                        </el-date-picker>
                    </el-form-item>
                </el-col>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-radio-group v-model="ruleForm.gender">
                    <el-radio label="男" v-model="male"></el-radio>
                    <el-radio label="女" v-model="female"></el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="邮箱" prop="email">
                <el-input v-model="ruleForm.email" @blur="checkEmail"></el-input>
            </el-form-item>

            <el-form-item label="电话" prop="telephone">
                <el-input v-model="ruleForm.telephone"></el-input>
            </el-form-item>

            <el-form-item label="地址" prop="address">
                <el-input v-model="ruleForm.address"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div></div>
    <div class="footer">
        <div class="wrapper">
            <div class="footer-bottom">
                <div class="link">
                    <dl>
                        <dt>
                            网站相关
                        </dt>
                        <dd>
                            关于我们
                        </dd>
                        <dd>
                            服务条款
                        </dd>
                        <dd>
                            帮助中心
                        </dd>
                        <dd>
                            编辑器语法
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            常用链接
                        </dt>
                        <dd>
                            传智播客
                        </dd>
                        <dd>
                            传智论坛
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            联系我们
                        </dt>
                        <dd>
                            联系我们
                        </dd>
                        <dd>
                            加入我们
                        </dd>
                        <dd>
                            建议反馈
                        </dd>
                    </dl>
                    <dl>
                        <dt>
                            关注我们
                        </dt>
                        <dd>
                            微博
                        </dd>
                        <dd>
                            twitter
                        </dd>
                    </dl>
                    <div class="xuke">
                        <h3>内容许可</h3>
                        <p>除特别说明外，用户内容均采用知识共享署名-非商业性使用-禁止演绎4.0 国际许可协议 (CC BY-NC-ND 4.0) 进行许可</p>
                        <p>本站由 传智研究院 提供更新服务</p>
                    </div>
                </div>
                <p class="Copyright">Copyright &copy; 2017 传智问答社区 当前版本 0.0.1</p>
            </div>
        </div>
    </div>

</div>


<!--<div id="app">-->
<!--    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">-->
<!--        <el-form-item label="用户昵称" prop="nickname">-->
<!--            <el-input v-model="ruleForm.nickname"></el-input>-->
<!--        </el-form-item>-->

<script>
    /* 脚本中创建对象,处理业务 */
    new Vue({
        el: '#app',
        data: {
            ruleForm: {
                nickname: '',
                password: '',
                birthday: '',
                gender: '',
                email: '',
                telephone:'',
                address:''
            },
            rules: {
                nickname: [
                    { required: true, message: '请输入用户名称', trigger: 'blur' },
                    { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 3, max: 8, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                ],
                birthday: [
                    { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
                ],
                gender: [
                    { required: true, message: '请选择性别', trigger: 'change' }
                ],
                email: [
                    { required: true, message: '请填写邮箱', trigger: 'blur' }
                ],
                telephone: [
                    { required: true, message: '请填写联系电话', trigger: 'blur' }
                ],
                address: [
                    { required: true, message: '请填写地址', trigger: 'blur' }
                ]
            }
        },
        methods: {
            checkEmail: function() {
                var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
                if (this.ruleForm.email != '' && !regEmail.test(this.ruleForm.email)) {
                    this.$message({
                        message: '邮箱格式不正确',
                        type: 'error'
                    })
                    this.ruleForm.email = ''
                }
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // alert('submit!');
                        axios.post('/member/register', this.ruleForm).then(function (response) {
                            //得到响应数据
                            var res = response.data;
                            //判断成功与否
                            if(res.flag){
                                window.open('/index.html','_self');
                            }else{
                                alert("提示："+res.message)
                            }
                        }).catch(function (err) {
                            console.log(err)
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }

    });
</script>



</body>



</html>
```

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <link rel="icon" href="./img/asset-favicon.ico"/>
    <title>登录页面</title>
    <!-- 页面 css js -->
    <!-- <script type="text/javascript" src="../../plugins/sui/sui.min.js" charset="UTF-8"></script> -->
    <link rel="stylesheet" type="text/css" href="./plugins/normalize-css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/yui/cssgrids-min.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/sui/sui.min.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/sui/sui-append.min.css"/>
    <link rel="stylesheet" type="text/css" href="./plugins/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="./css/widget-base.css"/>
    <link rel="stylesheet" type="text/css" href="./css/widget-head-foot.css"/>
    <link rel="stylesheet" type="text/css" href="./css/page-sj-person-loginsign.css"/>
    <link rel="stylesheet" href="./element-ui/lib/theme-chalk/index.css">

    <script src="./js/vue.js" type="text/javascript"></script>
    <script src="./js/axios-0.18.0.js" type="text/javascript"></script>
    <script src="./element-ui/lib/index.js"></script>
</head>
<body>
<!--头部导航-->
<header class="head-login">
    <div class="wrapper">
        <div class="sui-navbar">
            <div class="navbar-inner">
                <a href="./index-login.html" class="sui-brand"> <img src="./img/asset-logo-black.png" alt="刷题"/> </a>
                <span class="login-text">| 登录</span>
            </div>
        </div>
    </div>
</header>

<div id="app">
<div class="wrapper loginsign">
    <el-container style="height: auto; border: 1px solid #eee">
    <div class="item">
        <div class="form">
        <h3 class="loginsign-title">用户登录</h3>
        <el-form ref="form" :model="form" label-width="100px">
            <div class="control-group">
                <el-form-item label="登录名" class="control-label">
                    <el-input prefix-icon="el-icon-user" type="text" v-model="member.email" class="input-xlarge"
                              autocomplete="off"></el-input>
                </el-form-item>
            </div>
            <div class="control-group">
                <el-form-item label="密码" class="control-label">
                    <el-input prefix-icon="el-icon-lock" type="password" v-model="member.password" class="input-xlarge"
                              autocomplete="off"></el-input>
                </el-form-item>
            </div>
            <div class="controls">
                <el-form-item label="" class="control-label">
                    <el-checkbox-group v-model="member.remember">
                        <el-checkbox label="记住登录状态" name="member.remember"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
            </div>
            <div class="controls">
                <el-form-item>
                    <el-button @click="onSubmit" class="sui-btn btn-danger btn-yes">登录</el-button>
                </el-form-item>
            </div>
            <div class="other-methods">
            </div>
            <!--</form>-->
        </el-form>
    </div>
    </div>
    </el-container>
</div>

<div class="footer">
    <div class="wrapper">
        <div class="footer-bottom">
            <div class="link">
                <dl>
                    <dt>
                        网站相关
                    </dt>
                    <dd>
                        关于我们
                    </dd>
                    <dd>
                        服务条款
                    </dd>
                    <dd>
                        帮助中心
                    </dd>
                    <dd>
                        编辑器语法
                    </dd>
                </dl>
                <dl>
                    <dt>
                        常用链接
                    </dt>
                    <dd>
                        传智播客
                    </dd>
                    <dd>
                        传智论坛
                    </dd>
                </dl>
                <dl>
                    <dt>
                        联系我们
                    </dt>
                    <dd>
                        联系我们
                    </dd>
                    <dd>
                        加入我们
                    </dd>
                    <dd>
                        建议反馈
                    </dd>
                </dl>
                <dl>
                    <dt>
                        关注我们
                    </dt>
                    <dd>
                        微博
                    </dd>
                    <dd>
                        twitter
                    </dd>
                </dl>
                <div class="xuke">
                    <h3>内容许可</h3>
                    <p>除特别说明外，用户内容均采用知识共享署名-非商业性使用-禁止演绎4.0 国际许可协议 (CC BY-NC-ND 4.0) 进行许可</p>
                    <p>本站由 传智研究院 提供更新服务</p>
                </div>
            </div>
            <p class="Copyright">Copyright &copy; 2017 传智问答社区 当前版本 0.0.1</p>
        </div>
    </div>
</div>
</div>

<script>
    /* 脚本中创建对象,处理业务 */
    new Vue({
            el: '#app',
            data: {
                member: {
                    email: '1242306285@qq.com',
                    password: '1234',
                }
            },
            methods: {
                onSubmit() {
                    axios.post('/member/login', this.member).then(function (response) {
                        //将登录人信息放到一个指定的位置，然后到index页面中判断这个位置中是否有登录数据
                        //localStorage基础操作
                        /*
                        if(!window.localStorage){
                            alert("更升级到高版本的浏览器，否则无法正常使用！")
                        }else{
                            storage = window.localStorage;
                            storage.a = 1
                            storage['b']='2'
                            storage.setItem('c','3')
                            storage.removeItem('c')
                            alert(storage.a)
                            alert(typeof(storage.a))
                        }
                        */


                        /*
                        //根据返回的结果进行下一步的动作
                        if( res.flag){
                            // 跳转页面 index.html
                            window.open("index.html","_self");
                        }else{
                            alert(res.message);
                        }
                        */

                        //得到响应数据
                        var res = response.data;
                        // 提示登录
                        // alert(res.message);
                        // alert(res.data.nickName);
                        //判断成功与否
                        if(res.flag){
                            if(!window.localStorage){
                                alert("更升级到高版本的浏览器，否则无法正常使用！")
                            }else {
                                var storage = window.localStorage;
                                storage.id = res.data.id;
                                //storage.userName = res.data.nickName;
                            }
                            window.open('/index.html','_self');
                        }
                    }).catch(function (err) {
                        console.log(err)
                    });
                }
            },
        }
    );
</script>
</body>
</html>
```

*配置文件*

```properties
jedis.host=192.168.23.129
jedis.port=6379
jedis.maxTotal=30
jedis.maxIdle=10
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.hellocode.dao.front.MemberDao">

    <!--配置实体类属性和数据库表中列的对应关系-->
    <resultMap id="BaseResultMap" type="top.hellocode.domain.front.Member">
        <id column="id" jdbcType="VARCHAR" property="id"/>
        <result column="nick_name" jdbcType="VARCHAR" property="nickName"/>
        <result column="password" jdbcType="VARCHAR" property="password"/>
        <result column="gender" jdbcType="VARCHAR" property="gender"/>
        <result column="birthday" jdbcType="TIMESTAMP" property="birthday"/>
        <result column="email" jdbcType="VARCHAR" property="email"/>
        <result column="telephone" jdbcType="VARCHAR" property="telephone"/>
        <result column="address" jdbcType="VARCHAR" property="address"/>
        <result column="register_date" jdbcType="TIMESTAMP" property="registerDate"/>
        <result column="state" jdbcType="VARCHAR" property="state"></result>
    </resultMap>

    <!--配置查询的列名公共SQL语句-->
    <sql id="Base_Column_List">
        id, nick_name,password,gender,birthday,email,telephone,address,register_date,state
    </sql>

    <insert id="save" parameterType="top.hellocode.domain.front.Member">
        insert into tr_member (id, nick_name, password, gender,
        birthday, email,state,telephone, address, register_date)
        values (#{id,jdbcType=VARCHAR}, #{nickName,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, #{gender,jdbcType=VARCHAR},
        #{birthday,jdbcType=TIMESTAMP}, #{email,jdbcType=VARCHAR},#{state,jdbcType=VARCHAR},
        #{telephone,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{registerDate,jdbcType=TIMESTAMP})
    </insert>

    <select id="findByEmailAndPwd" parameterType="map" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from tr_member
        where email = #{email,jdbcType=VARCHAR} and password = #{password,jdbcType=VARCHAR}
    </select>
</mapper>
```

*java*

```java
package top.hellocode.service.front.impl;

import org.apache.ibatis.session.SqlSession;
import redis.clients.jedis.Jedis;
import top.hellocode.dao.front.MemberDao;
import top.hellocode.domain.front.Member;
import top.hellocode.factory.MapperFactory;
import top.hellocode.service.front.MemberService;
import top.hellocode.utils.JedisUtils;
import top.hellocode.utils.MD5Util;
import top.hellocode.utils.TransactionUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 19:23
 */
public class MemberServiceImpl implements MemberService {
    @Override
    public boolean register(Member member) {
        SqlSession sqlSession = null;
        try{
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            MemberDao memberDao = MapperFactory.getMapper(sqlSession,MemberDao.class);

            // id并不在表现层接收，使用uuid自动生成
            String id = UUID.randomUUID().toString();
            member.setId(id);
            member.setRegisterDate(new Date());
            member.setState("1");

            member.setPassword(MD5Util.md5(member.getPassword()));

            // 调用Dao层操作
            int row = memberDao.save(member);
            // 提交事务
            TransactionUtil.commit(sqlSession);
            return row > 0;
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);   // 回滚事务
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        }finally {
            try{
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Member login(String email, String password) {
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession对象
            sqlSession = MapperFactory.getSqlSession();
            // 获取Dao
            MemberDao memberDao = MapperFactory.getMapper(sqlSession, MemberDao.class);
            password = MD5Util.md5(password);
            Member member = memberDao.findByEmailAndPwd(email, password);

            // 将登陆人的信息保存到redis中
            Jedis jedis = JedisUtils.getResource();
            // 使用登陆人的id作为key，设置3600秒的过期时间，value待定
            jedis.setex(member.getId(),3600,member.getNickName());
            jedis.close();

            return member;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);      // 抛出异常
            // 记录日志（占位，功能未实现）
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getLoginInfo(String id) {
        // 使用给定的id去redis查找当前数据
        Jedis jedis = JedisUtils.getResource();
        // 使用登陆人的id作为key，设置3600秒的过期时间，value待定
        String nickName = jedis.get(id);
        jedis.close();
        return nickName;
    }

    @Override
    public boolean logout(String id) {
        // 使用给定的id去redis查找当前数据
        Jedis jedis = JedisUtils.getResource();
        Long row = jedis.del(id);
        jedis.close();
        return row > 0;
    }
}
```

```java
package top.hellocode.web.controller.front;

import top.hellocode.domain.front.Member;
import top.hellocode.web.controller.BaseServlet;
import top.hellocode.web.controller.Code;
import top.hellocode.web.controller.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 11:07
 */

@WebServlet("/member/*")
public class MemberServlet extends BaseServlet {

    public Result login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request,Member.class);
        // 调用逻辑层API
        member = memberService.login(member.getEmail(), member.getPassword());

        if(member != null){
            // 查询到了结果
            return new Result("登录成功！", member);
        }else{
            // 登录失败
            return new Result("用户名或密码错误，请重试",false, null, Code.LOGIN_FAIL);
        }
    }

    public Result logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request,Member.class);
        // 调用逻辑层API
        boolean flag = memberService.logout(member.getId());

        if(flag){
            // 成功
            return new Result("退出成功！", flag);
        }else{
            // 登录失败
            return new Result("",false, flag, Code.LOGOUT_FAIL);
        }
    }

    public Result register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request,Member.class);
        // 调用逻辑层API
        boolean flag = memberService.register(member);
        // 返回的数据封装成一个对象
        return new Result("注册成功！", null);
    }

    public Result checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Member member = getData(request,Member.class);
        // 根据获取到的id去redis查找，判断是否存在
        String nickName = memberService.getLoginInfo(member.getId());
        // 返回的数据封装成一个对象
        return new Result("", nickName);
    }

}
```

```java
package top.hellocode.web.controller;

import com.alibaba.fastjson.JSON;
import top.hellocode.service.front.MemberService;
import top.hellocode.service.front.impl.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 11:07
 */
public class BaseServlet extends HttpServlet {
    protected MemberService memberService;

    @Override
    public void init() throws ServletException {
        memberService = new MemberServiceImpl();
    }

    protected  <T> T getData(HttpServletRequest request, Class<T> clazz) throws IOException {
        // 收集数据
        String json = JSON.parseObject(request.getInputStream(), String.class);
        // 组织成一个实体类（Member）
        return JSON.parseObject(json, clazz);
    }

    protected void returnData(HttpServletResponse response, Result result) throws IOException {
        // 返回结果
        response.setContentType("application/json;charset=utf-8");
        JSON.writeJSONString(response.getOutputStream(),result);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        int lastIndex = url.lastIndexOf('/');
        String methodName = url.substring(lastIndex + 1, url.length());

        // 获取到了方法名叫做url的方法，利用反射执行，传递参数即可
        Class clazz = this.getClass();
        try {
            Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            Result ret = (Result) method.invoke(this, request, response);
            returnData(response,ret);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
```

```java
package top.hellocode.web.controller;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 19:48
 */
// 状态码类
public class Code {
    public static final Integer SUCCESS = 20000;
    public static final Integer FAIL = 50000;
    public static final Integer LOGIN_FAIL = 50101;
    public static final Integer LOGOUT_FAIL = 50102;
}
```

```java
package top.hellocode.web.controller;

import java.io.Serializable;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年06月01日 19:46
 */
// 页面返回的结果数据封装对象
public class Result implements Serializable {
    private String message;     // 对应操作的返回消息
    private boolean flag;       // 对应操作的返回结果是否成功
    private Object data;        // 对应操作返回的具体数据
    private Integer code;       // 对应操作的返回状态码

    public Result(String message, Object data) {
        this.message = message;
        this.data = data;
        this.code = Code.SUCCESS;
        this.flag = true;
    }

    public Result(String message, boolean flag, Object data, Integer code) {
        this.message = message;
        this.flag = flag;
        this.data = data;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
```

#### 答题功能

**生成试卷（加载题目/题干信息）**

- 后台加载数据

- 页面使用v-for循环数据

  ```html
  <ul class="detail-list" v-for="question in questions">
  </ul>
  ```

- 页面使用v-if根据题型不同走向不同的分支

  ```html
  <el-form-item label="多选题" v-if="question.type == 2">
  </el-form-item>
  ```

**加载选项**

- 从题目/题干信息中读取关联的选项数据

- 页面使用v-for循环数据

  ```html
  <el-checkbox v-for="(item,index) in question.questionItemList"
               :key="item.id"
               :label="item.content"
               :name="question.id"
               v-model="checked"
               @change="changeResultCheckBox(item)">
      {{item.content}}
  </el-checkbox>
  ```

  > 前台和后台使用的表一样，但是关注的字段不同

**提交试卷答案功能需求分析**

1. 单选与多选分情况处理

2. 单选题提交数据结构

   ```json
   {"questionId":"题目id","answer":"选项id"}
   ```

3. 多选题提交数据结构

   ```json
   {"questionId":"题目id","answer":"选项id,选项id,选项id"}
   ```

4. 整体试卷提交数据结构

   ```json
   [
       {"questionId":"题目id","answer":"选项id,选项id,选项id"},
       {"questionId":"题目id","answer":"选项id,选项id,选项id"}
   ]
   ```

5. 每次答题操作，先删除原有答题记录，再添加本次答题记录

**单选题提交处理方案**

1. 获取数据（题目id与选项id），组织答案数据

   ```javascript
   var temp = {"questionId":item.questionId,"answer":item.id};
   ```

2. 删除原有答题记录（使用过滤思想，取出results中当前题目之外的题目答案）

   ```javascript
   this.results = this.results.filter(e=>{return e.questionId !== item.questionId})
   ```

3. 添加本次答题结果数据到results中

   ```javascript
   this.results.push(temp);
   ```

**多选题提交处理方案**

```javascript
var temp = this.results.find(e=>{return e.questionId === item.questionId})

if(temp == undefined){
    //当前题目从来未作答过
    temp = {"questionId":item.questionId,"answer":item.id};
}else{
    if(this.checked){
        // 添加该答案
        temp.answer = temp.answer + "," + item.id;
    }else{
        //删除该答案
        var arr = temp.answer.split(",");
        var index = arr.indexOf(item.id);       // 查找元素在数组中的索引
        arr.splice(index,1);        // 删除数组元素
        temp.answer = arr.join(",");
    }
}
```

*多选答案全部取消的处理策略*

```javascript
var temp = this.results.find(e=>{return e.questionId === item.questionId})

if(temp == undefined){
    //当前题目从来未作答过
    temp = {"questionId":item.questionId,"answer":item.id};
}else{
    if(this.checked){
        // 添加该答案
        temp.answer = temp.answer + "," + item.id;
    }else{
        //删除该答案
        var arr = temp.answer.split(",");
        var index = arr.indexOf(item.id);       // 查找元素在数组中的索引
        arr.splice(index,1);        // 删除数组元素
        if(arr.length == 0){
            this.results = this.results.filter(e=>{return e.questionId !== item.questionId})
            return;
        }
        temp.answer = arr.join(",");
    }
}
```

**提交试卷**

*服务端获取答案数据*

```java
// 1. 得到全部请求的json数据
String json = JSONObject.parseObject(request.getInputStream(), String.class);
// 2. 将json数据转换为json对象
JSONObject jsonObject = JSONObject.parseObject(json);
// 3. 获取当前提交试卷人的id
String memberId = jsonObject.getObject("memberId", String.class);
// 4. 获取当前提交的试卷信息
JSONArray jsonArray = jsonObject.getJSONArray("results");
List<ExamQuestion> examQuestionList = jsonArray.toJavaList(ExamQuestion.class);
```

*保存答题数据*

```java
boolean flag = true;
// 获取sqlSession对象
sqlSession = MapperFactory.getSqlSession();
// 获取Dao
ExamPaperDao examPaperDao = MapperFactory.getMapper(sqlSession, ExamPaperDao.class);
ExamQuestionDao examQuestionDao = MapperFactory.getMapper(sqlSession, ExamQuestionDao.class);

// 提交保存的试卷信息
ExamPaper examPaper = new ExamPaper();
String paperId = UUID.randomUUID().toString();
examPaper.setId(paperId);
examPaper.setApplyTime(new Date());
examPaper.setMemberId(memberId);
examPaper.setState("1");
flag = flag && examPaperDao.save(examPaper) > 0;
// 提交保存到试卷
for(ExamQuestion eq : examQuestionList){
    eq.setId(UUID.randomUUID().toString());
    eq.setExamPaperId(paperId);

    flag = flag && examQuestionDao.save(eq) > 0;
}
```

### 总结

- 后台系统开发
  - 正向开发模式（数据-->业务-->表现-->页面）
  - 服务端采用页面跳转形式响应客户端
  - 以模块基础增删改查功能为主
- 前台系统开发
  - 反向开发模式（页面-->表现-->业务-->数据）
  - 服务端采用json数据形式响应客户端
  - 以业务功能为主

>  部分功能还没有做，有兴趣的可以继续完善，基本思路一致

1. 后台系统：审核题目
   - 提供两个按钮，审核通过与审核不通过
2. 后台系统：会员管理
   - 提供用户启用、停用功能
   - 提供查看用户信息功能（同修改但不能修改）
3. 后台系统：会员答题管理
   - 提供查看用户试卷答题记录功能
   - 选做阅卷功能（自动阅卷与手工阅卷）
4. 前台系统：历史答题记录查看
   - 提供查看登录用户试卷答题记录功能
