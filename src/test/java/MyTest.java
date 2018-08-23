import cn.whe.freamwork.utils.HttpRequest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTest {

    private static final Logger log = LoggerFactory.getLogger(MyTest.class);

    @Test
    public void mytest() throws InterruptedException {
        int i = 0;
        while (i < 60 * 150) {
            log.info("mytest" + i);
            Thread.sleep(100);
            i++;
        }
    }
    @Test
    public void Httptest() throws InterruptedException {
        System.out.println(HttpRequest.sendGet("https://www.baidu.com/", ""));
    }
}
