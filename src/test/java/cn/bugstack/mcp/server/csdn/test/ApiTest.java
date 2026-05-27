package cn.bugstack.mcp.server.csdn.test;

import cn.bugstack.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import cn.bugstack.mcp.server.csdn.domain.model.ArticleFunctionResponse;
import cn.bugstack.mcp.server.csdn.domain.service.CSDNArticleService;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.ICSDNService;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
import cn.bugstack.mcp.server.csdn.type.utils.MarkdownConverter;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    private final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Autowired
    private ICSDNService csdnService;

    @Autowired
    private CSDNArticleService csdnArticleService;

    @Test
    public void test_saveArticle() throws IOException {
        // 1. 构建请求对象 (严格与修改后的 ArticleRequestDTO 对齐)
        ArticleRequestDTO request = new ArticleRequestDTO();
        request.setArticle_id("");
        request.setTitle("测试文章标题01");
        request.setDescription("这是一篇测试文章的描述");
        // pc_postedit 富文本格式，传入 HTML
        request.setContent("<h1>测试文章内容</h1><p>这是一篇测试文章</p>");
        request.setTags("测试,文章");
        request.setCategories("后端");
        request.setType("original");
        request.setStatus(2); // 0或2，取决于你要保存草稿还是直接发布
        request.setRead_type("public");
        request.setCreation_statement(0);
        request.setReason("");
        request.setOriginal_link("");
        request.setAuthorized_status(true);
        request.setCheck_original(false);
        request.setSource("pc_postedit");
        request.setNot_auto_saved(0); // 注意：DTO里定义的是Integer
        request.setCreator_activity_id("");
        request.setCover_images(Collections.emptyList());
        request.setCover_type(0);
        request.setVote_id(0);
        request.setResource_id("");
        request.setScheduled_time(0);
        request.setIs_new(1);
        request.setSync_git_code(0);

        // 2. 调用接口
        String cookie = "UN=m0_73837404; uuid_tt_dd=10_19674619640-1747749102964-126363; c_dl_fpage=/download/Yao__Shun__Yu/14932325; c_dl_um=-; c_dl_prid=1755918089581_568220; c_dl_rid=1755918102247_254339; c_dl_fref=default; fid=20_72790424794-1757903798764-226205; p_uid=U010000; tfstk=gzloX2idkYy7LWC5EeVWE9g4h6vvw7NQfDCLvWEe3orbvUZ822on-VyEwuKIKxmmj6Ez2YInKkoBPWnpdWqn47yIekE8xW0IjEK96C3SPWweXhdTvS1MI5sPUJSLuszatzPiwDIIPWNeDFaXwR0S2uaOa8PeoSz3r9PU4_8cuPEVay5z8i80RoPUz6zUusz87kWzUDu2oyZ48_oUTEV4cyPUYDPEMl-z4Xl-gH1JiF4g5nG4r8qZzuu-yjJYhTggmdptixVYb6qcT6l0rqg_Sf1Mplk_2REj0ICutqznjPml0nVEobGg3b-ftcmmQS4jhHX3jvu-CvyDzQu0Zu2iMRbhU7DEVX2SEwTsu7ojClwyhnamwfe3f-j24q304RDn2n1U2A0qmRhAD1Fq7xVc4aWVQKh1Or8Kg96QUra0Xue6rcb3zC9poEXgA8zbzlLDo96QUra0XEYcI7wzlzrO.; c_ins_fpage=/index.html; c_ins_um=-; ins_first_time=1766405424533; UserName=m0_73837404; UserInfo=89cbb3a03da54220a032735d4dda15b4; UserToken=89cbb3a03da54220a032735d4dda15b4; UserNick=zhuodedao; AU=DD7; BT=1768806067428; csdn_newcert_m0_73837404=1; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19674619640-1747749102964-126363!5744*1*m0_73837404; c_ins_prid=1777942305837_805651; c_ins_rid=1777942310553_878804; c_ins_fref=https://blog.csdn.net/qq_45471661/article/details/132884019; c_adb=1; creative_btn_mp=3; _clck=sfl1p5%5E2%5Eg6b%5E0%5E2064; dc_session_id=10_1779673024811.856970; c_first_ref=default; c_first_page=https%3A//blog.csdn.net/bit452/category_10569531.html; c_dsid=11_1779673024575.878796; c_segment=11; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1779518036,1779593489,1779607275,1779673025; HMACCOUNT=8350F443D6D4E590; dc_sid=ed05d5a1951f6f0daea1b7718ef0677c; c_pref=https%3A//blog.csdn.net/m0_73837404%3Ftype%3Dblog; c_ref=https%3A//mp.csdn.net/mp_blog/creation/editor%3Fspm%3D1001.2014.3001.4503; c_page_id=default; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1779673056; log_Id_pv=6; log_Id_view=73; log_Id_click=12; dc_tos=tfkkkd";
        Call<ArticleResponseDTO> call = csdnService.saveArticle(request, cookie);
        Response<ArticleResponseDTO> response = call.execute();

        // 打印 FastJSON 序列化后的完整结果，方便调试
        System.out.println("\r\n测试结果: " + JSON.toJSONString(response.body()));

        // 3. 验证结果
        if (response.isSuccessful() && response.body() != null) {
            ArticleResponseDTO articleResponseDTO = response.body();
            log.info("发布/保存文章成功: {}", articleResponseDTO);
        } else {
            log.error("请求失败，HTTP状态码: {}", response.code());
            if (response.errorBody() != null) {
                log.error("错误原因: {}", response.errorBody().string());
            }
        }
    }

    @Test
    public void test_md2html() {
        System.out.println(MarkdownConverter.convertToHtml("**关于DDD是什么，在维基百科有一个明确的定义。\"Domain-driven design (DDD) is a major software design approach.\" 也就是说DDD是一种主要的软件设计方法。而软件设计涵盖了；范式、模型、框架、方法论。**\n" +
                "\n" +
                "- 范式（paradigm）指的是一种编程思想。\n" +
                "- 模型（model）指的是对现实世界或者问题的抽象描述。\n" +
                "- 框架（framework）指的是提供了一系列通用功能和结构的软件工具。\n" +
                "- 方法论（methodology）指的是一种系统的、有组织的解决问题的方法。\n" +
                "\n" +
                "所以，DDD不只是只有指导思想，伴随的DDD的还包括框架结构分层。但说到底，这些仍然是理论讨论。在没有一个DDD落地项目物参考下，其实大部分码农是没法完成DDD开发的。所以小傅哥今年花费了5个月假期/周末的时间，完成的《DDD简明开发教程》，帮助大家落地DDD编码。"));
    }

    @Test
    public void test_domain_saveArticle() throws IOException {
        String json = "{\"content\":\"<h2>场景：</h2>\\n<p>在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。</p>\\n<p><strong>面试官</strong>：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？</p>\\n<p><strong>程序员小张</strong>：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？</p>\\n<p><strong>面试官</strong>：嗯，第二个问题，请说说HashMap的工作原理。</p>\\n<p><strong>程序员小张</strong>：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……</p>\\n<p><strong>面试官</strong>：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？</p>\\n<p><strong>程序员小张</strong>：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……</p>\\n<p><strong>面试官</strong>：好，今天的问题就问到这里。回去等通知吧。</p>\\n<h2>答案解析：</h2>\\n<ol>\\n<li>\\n<p><strong>JVM内存管理</strong>：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。</p>\\n</li>\\n<li>\\n<p><strong>HashMap原理</strong>：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。</p>\\n</li>\\n<li>\\n<p><strong>Spring与SpringBoot区别</strong>：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。</p>\\n</li>\\n</ol>\\n\",\"cover_images\":[],\"cover_type\":0,\"description\":\"在互联网大厂的面试中，严肃的面试官与搞笑的程序员上演了一场精彩的对话。面试官提出Java核心知识、HashMap、Spring等问题，程序员则用幽默的方式作答。本文不仅展现了轻松的面试氛围，还附上了详细的技术问题答案解析，帮助读者更好地理解相关知识。\",\"is_new\":1,\"level\":\"0\",\"markdowncontent\":\"## 场景：\\n\\n在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。\\n\\n**面试官**：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？\\n\\n**程序员小张**：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？\\n\\n**面试官**：嗯，第二个问题，请说说HashMap的工作原理。\\n\\n**程序员小张**：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……\\n\\n**面试官**：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？\\n\\n**程序员小张**：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……\\n\\n**面试官**：好，今天的问题就问到这里。回去等通知吧。\\n\\n## 答案解析：\\n\\n1. **JVM内存管理**：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。\\n\\n2. **HashMap原理**：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。\\n\\n3. **Spring与SpringBoot区别**：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。\",\"not_auto_saved\":\"0\",\"pubStatus\":\"draft\",\"readType\":\"public\",\"resource_id\":\"\",\"resource_url\":\"\",\"source\":\"pc_mdeditor\",\"status\":2,\"sync_git_code\":0,\"tags\":\"Java,面试,互联网,程序员,Spring,SpringBoot,HashMap,JVM\",\"title\":\"互联网大厂Java面试：严肃面试官与搞笑程序员的对决\",\"vote_id\":0}";
        ArticleFunctionRequest request = JSON.parseObject(json, ArticleFunctionRequest.class);
        ArticleFunctionResponse response = csdnArticleService.saveArticle(request);
        log.info("测试结果:{}", JSON.toJSONString(response));
    }

}
