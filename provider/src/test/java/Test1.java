import com.zxy.common.domain.User;
import com.zxy.provider.ProviderApplication;
import com.zxy.provider.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={ProviderApplication.class})
public class Test1 {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        User user = userMapper.getOne(2L);
        Assert.notNull(user, "用户不存在.");
    }
}
