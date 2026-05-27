package cn.bugstack.mcp.server.csdn.infrastructure.gateway;

import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ICSDNService {

    @Headers({
            "accept: application/json, text/plain, */*",
            "accept-language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6",
            "content-type: application/json;", // 注意：这里保留了你ApiPost里的分号
            "origin: https://mp.csdn.net",
            "priority: u=1, i",
            "referer: https://mp.csdn.net/mp_blog/creation/editor?spm=1001.2014.3001.4503",
            "sec-ch-ua: \"Chromium\";v=\"148\", \"Microsoft Edge\";v=\"148\", \"Not/A)Brand\";v=\"99\"",
            "sec-ch-ua-mobile: ?0",
            "sec-ch-ua-platform: \"Windows\"",
            "sec-fetch-dest: empty",
            "sec-fetch-mode: cors",
            "sec-fetch-site: same-site",
            "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/148.0.0.0 Safari/537.36 Edg/148.0.0.0",
            "x-ca-key: 203803574",
            "x-ca-nonce: eb1298fe-e333-4ab2-b81a-eeab3bf0f672",
            "x-ca-signature: Fnir9KfELJDTFnQiy/TVl7iyCfGGcGP8emaheCABgGs=",
            "x-ca-signature-headers: x-ca-key,x-ca-nonce",
            "x-ca-stage: "
    })
    @POST("/blog-console-api/v1/postedit/saveArticle") // 这里换成了你ApiPost成功的v1路径
    Call<ArticleResponseDTO> saveArticle(@Body ArticleRequestDTO request, @Header("Cookie") String cookieValue);

}