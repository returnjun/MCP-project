package cn.bugstack.mcp.server.csdn.infrastructure.gateway;

import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
import com.microsoft.playwright.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
public class CsdnAutoPublisher {

    @Value("${csdn.auth-path:csdn-auth.json}")
    private String authPath;

    /**
     * 执行 CSDN 自动发布 (整合登录与发文)
     */
    public ArticleResponseDTO publishArticle(ArticleRequestDTO request) {
        ArticleResponseDTO response = new ArticleResponseDTO();

        try (Playwright playwright = Playwright.create()) {
            // === 修改前：无图形界面（后台运行） ===
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));

            // === 修改后：显示图形界面（调试模式） ===
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            // 加载已保存的凭证
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions().setStorageStatePath(Paths.get(authPath))
            );
            Page page = context.newPage();

            try {
                page.navigate("https://mp.csdn.net/mp_blog/creation/editor");

                // 智能等待标题输入框
                Locator titleInput = page.getByPlaceholder("请输入文章标题（5～100个字）");
                titleInput.waitFor(new Locator.WaitForOptions().setTimeout(10000));

                // 填入数据
                titleInput.fill(request.getTitle());

                FrameLocator editorFrame = page.frameLocator(".cke_wysiwyg_frame");
                editorFrame.locator(".cke_editable").click();
                page.keyboard().insertText(request.getMarkdowncontent());
                page.keyboard().press("Space");
                page.keyboard().press("Backspace");

                page.locator(".originalRadio").click();
                page.locator("textarea[placeholder*='摘要：会在推荐']").fill(request.getDescription());

                page.getByText("添加文章标签").click();
                page.waitForTimeout(500);
                Locator tagInput = page.getByPlaceholder("请输入文字搜索，Enter键入可添加自定义标签");
                tagInput.fill(request.getTags()); // 简化：假设输入第一个标签
                tagInput.press("Enter");
                page.keyboard().press("Escape");
//                page.waitForTimeout(5000);
                page.locator("button:has-text('保存草稿')").click();
//                page.waitForTimeout(50000);
                response.setCode(200);
                response.setMsg("success");
            } catch (Exception e) {
                response.setCode(400);
                response.setMsg("发布失败: " + e.getMessage());
            } finally {
                browser.close();
            }
        }
        return response;
    }

    /**
     * 手动更新凭证的登录函数
     */
    public void performLogin() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://passport.csdn.net/login");
            System.out.println("请在 60 秒内完成手动扫码登录...");
            page.waitForTimeout(60000);
            page.context().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(authPath)));
            browser.close();
        }
    }
}