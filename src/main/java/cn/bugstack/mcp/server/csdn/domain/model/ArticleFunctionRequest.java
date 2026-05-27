package cn.bugstack.mcp.server.csdn.domain.model;

import cn.bugstack.mcp.server.csdn.type.utils.MarkdownConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleFunctionRequest {

    @JsonProperty(required = true, value = "title")
    @JsonPropertyDescription("文章标题")
    private String title;

    @JsonProperty(required = true, value = "markdowncontent")
    @JsonPropertyDescription("文章的核心正文内容，请务必使用高质量的 Markdown 格式排版，包含合适的标题、列表或代码块等")
    private String markdowncontent;

    @JsonProperty(required = true, value = "tags")
    @JsonPropertyDescription("文章标签，必须使用英文逗号隔开，最多提供 3 到 5 个标签")
    private String tags;

    @JsonProperty(required = true, value = "Description")
    @JsonPropertyDescription("文章简述")
    private String Description;

    @JsonProperty(required = true, value = "status")
    @JsonPropertyDescription("文章状态，2：存为草稿 0：发布文章")
    private String status = "2";

    public String getContent() {
        return MarkdownConverter.convertToHtml(markdowncontent);
    }

}
