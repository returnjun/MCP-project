package cn.bugstack.mcp.server.csdn.test;

import cn.bugstack.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import cn.bugstack.mcp.server.csdn.domain.model.ArticleFunctionResponse;
import cn.bugstack.mcp.server.csdn.domain.service.CSDNArticleService;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Resource
    private CSDNArticleService articleService;

    @Test
    public void test_saveArticle() throws IOException {
        ArticleFunctionRequest request = ArticleFunctionRequest.builder()
                .title("测试文章002")
                .markdowncontent("这是一个测试文章")
                .tags("测试标签")
                .Description("这是一个测试文章")
                .build();
        ArticleFunctionResponse articleFunctionResponse = articleService.saveArticle(request);
        System.out.println(JSON.toJSONString(articleFunctionResponse));
    }
}
