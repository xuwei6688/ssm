import com.xu.mapper.AccountMapper;
import com.xu.pojo.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        //加载资源文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

        //使用SqlSessionFactoryBuilder构建出SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //使用SqlSessionFactory打开一个SqlSession
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void close() {
        sqlSession.close();
    }


    /**
     * 根据全限定名执行对应的sql
     */
    @Test
    public void test1() {
        List<Object> objects = sqlSession.selectList("com.xu.mapper.AccountMapper.selectById", 1);
        System.out.println(objects);
    }

    /**
     * 根据Mapper接口生成代理对象，然后执行对应的sql
     */
    @Test
    public void test2() {
//        //获得mapper接口的代理对象（推荐用法）
//        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
//        List<Account> accounts = accountMapper.queryAccountList();
//        System.out.println(accounts);
    }

    /**
     * 一级缓存测试
     *  一级缓存是SqlSession级别的，多个SqlSession不共享
     *  一级缓存默认开启
     */
    @Test
    public void test3(){
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        AccountMapper mapper1 = sqlSession1.getMapper(AccountMapper.class);
        AccountMapper mapper2 = sqlSession2.getMapper(AccountMapper.class);


        mapper1.selectById(1);
        System.out.println("***********第二个sqlSession***********");
        mapper2.selectById(1);
    }


}
