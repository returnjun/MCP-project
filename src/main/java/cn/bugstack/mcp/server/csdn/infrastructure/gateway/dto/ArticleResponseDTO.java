package cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto;

import lombok.Data;

@Data
public class ArticleResponseDTO {
    //成功：200，失败：400
    private Integer code;
    //成功：success，失败：详细信息
    private String msg;
}