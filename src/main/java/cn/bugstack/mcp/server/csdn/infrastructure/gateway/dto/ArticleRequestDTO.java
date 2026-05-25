package cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ArticleRequestDTO {
    //标题
    private String title;
    //核心内容
    private String markdowncontent;
    //标签
    private String tags;
    //描述
    private String Description;

}