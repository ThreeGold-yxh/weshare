import com.yxh.weshare.utils.ClassUtils;
import org.junit.Test;

/**
 * @author Xinhao Yi
 * @date 2022/7/27 20:23
 * @description:
 */
public class MyTest {
    @Test
    public void myTest(){
        ///E:/Glasgow_IT/The_Graduate_project/weshare/target/test-classes/
        System.out.println(ClassUtils.testPath());

        //E:\Glasgow_IT\The_Graduate_project\weshare
        System.out.println(System.getProperty("user.dir"));
    }
}
