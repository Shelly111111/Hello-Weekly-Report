package com.hello.oauth.config;

import com.hello.oauth.Res.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 删除token服务
 *
 * @author： 漫舞枪神
 * @date: 2023/4/19
 */
@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;

    /**
     * 删除token
     *
     * @param request javax.servlet.http.HttpServletRequest
     * @return com.hello.oauth.Res.ResponseData
     *
     * @author: 漫舞枪神
     * @date: 2023/4/19
     */
    @RequestMapping(method = RequestMethod.POST, value = "/oauth/revoke")
    @ResponseBody
    public ResponseData revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")) {
            String tokenId = authorization.substring("Bearer".length() + 1);
            if(tokenServices.revokeToken(tokenId))
                return new ResponseData(ResponseData.success,"删除token成功！");
            return new ResponseData(ResponseData.notFound,"token未存在，可能您输入的token错误！");
        }
        return new ResponseData(ResponseData.error,"您输入的token格式错误！");
    }

}