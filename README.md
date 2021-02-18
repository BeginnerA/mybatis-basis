自定义持久框架本身（工程）：本质就是对JDBC代码进行封装 1、加载配置文件：根据配置文件的路径加载配置文件成字节输入流，存储在内存中；

      创建Resources类方法：InputSteam getResourceAsSteam(String path)；

2、创建两个JavaBean（容器对象）：存放的就是对配置文件解析出来的内容；

      Configuration（核心配置类）：存放sqlMapConfig.xml解析出来的内容；

      MappedStatement（映射配置类）：存放mapper.xml解析出来的内容；

3、解析配置文件：dom4j

      创建类：SqlSessionFactoryBuilder； 方法：build(InputSteam in)；

      第一：使用dom4j解析配置文件，将解析出来的内容封装到容器对象中；

      第二：创建SqlSessionFactory对象；生产sqlSession会话对象（工厂模式）；

4、创建SqlSessionFactory接口及实现类DefaultSqlSessionFacotry

      第一：openSession()；生产sqlSession；

5、创建SqlSession接口及实现类DefaultSession

      定义对数据库的CRUD操作：增删改查；

6、创建Executor接口及实现类SimpleExecutor类

      query(Configuration c, MappedStatement ms, Object... params)：执行的就是JDBC代码；
