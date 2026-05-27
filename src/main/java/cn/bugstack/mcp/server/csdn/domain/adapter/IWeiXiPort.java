package cn.bugstack.mcp.server.csdn.domain.adapter;


import cn.bugstack.mcp.server.csdn.domain.model.WeiXinNoticeFunctionRequest;
import cn.bugstack.mcp.server.csdn.domain.model.WeiXinNoticeFunctionResponse;

import java.io.IOException;

public interface IWeiXiPort {
    WeiXinNoticeFunctionResponse weixinNotice(WeiXinNoticeFunctionRequest request) throws IOException;

}
