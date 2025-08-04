import com.binghu.risk.api.ApiApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApiApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class ApiTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("测试方法优先级")
    @Test
    @Order(1)
    public void testHello1() {
        System.out.println("juege-hello1");
    }

    @DisplayName("测试方法优先级")
    @Test
    @Order(2)
    public void testHello2() {
        System.out.println("juege-hello2");
    }

    @DisplayName("测试int null")
    @Test
    @Order(3)
    public void testIntNull() {
    }

    @BeforeEach
    public void setup(){
        System.out.println("====== start =======");
    }

    @DisplayName("测试MockMvc模拟Api调用")
    @Test
    @Order(3)
    public void testMockMvc() throws Exception {
        //构造Request
        MvcResult result = mockMvc.perform(
                        post("/hello/test")
                                //设置内容类型
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //断言
                .andExpect(status().isOk())
                //打印请求信息
                .andDo(print())
                .andReturn();

        //打印接口返回信息
        System.out.println(result.getResponse().getContentAsString());


    }
}
