package cn.bugstack.mcp.server.csdn.test;

import cn.bugstack.mcp.server.csdn.infrastructure.gateway.IWeixinApiService;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.WeixinTemplateMessageDTO;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.WeixinTokenResponseDTO;
import cn.bugstack.mcp.server.csdn.type.properties.WeiXinApiProperties;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private WeiXinApiProperties weiXinApiProperties;
    @Resource
    private IWeixinApiService weixinApiService;

    private String accessToken ;

    @Before
    public void before() throws IOException {
        Call<WeixinTokenResponseDTO> call = weixinApiService.getToken("client_credential", weiXinApiProperties.getAppid(), weiXinApiProperties.getAppsecret());
        WeixinTokenResponseDTO weixinTokenResponseDTO = call.execute().body();
        assert weixinTokenResponseDTO != null;
        accessToken = weixinTokenResponseDTO.getAccess_token();
        log.info("weixin accessToken:{}", accessToken);
    }

    @Test
    public void test_template_message() throws IOException {
        Map<String, Map<String, String>> data = new HashMap<>();
        WeixinTemplateMessageDTO.put(data, WeixinTemplateMessageDTO.TemplateKey.platform, "CSDN");
        WeixinTemplateMessageDTO.put(data, WeixinTemplateMessageDTO.TemplateKey.subject, "Java求职面试：从Spring Boot到微服务的技术探索");
        WeixinTemplateMessageDTO.put(data, WeixinTemplateMessageDTO.TemplateKey.description, "面试官是一位经验丰富且严肃的技术大牛，而谢飞机则是以幽默著称的“水货”程序员。：Elasticsearch用于存储，Logstash用于日志收集和传输。");

        WeixinTemplateMessageDTO templateMessageDTO = new WeixinTemplateMessageDTO(weiXinApiProperties.getTouser(), weiXinApiProperties.getTemplate_id());
        templateMessageDTO.setUrl("https://blog.csdn.net/m0_73837404/article/details/158072401?spm=1001.2014.3001.5501");
        templateMessageDTO.setData(data);

        Call<Void> call = weixinApiService.sendMessage(accessToken, templateMessageDTO);
        call.execute();

        log.info("请求参数:{}", JSON.toJSONString(templateMessageDTO));
    }
}
