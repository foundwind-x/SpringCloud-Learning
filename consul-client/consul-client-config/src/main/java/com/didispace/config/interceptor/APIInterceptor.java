package com.didispace.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.didispace.api.model.ApiInput;
import com.didispace.api.model.Result;
import com.didispace.common.util.APIEncryption;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class APIInterceptor extends HandlerInterceptorAdapter {
//    @Autowired
//    IAkskService akskService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String bodyMsg = getBodyMsg(request);
        ApiInput apiInput = JSON.parseObject(bodyMsg, ApiInput.class);
        String ak = apiInput.getAk();
        String sn = apiInput.getSn();
        String timestamp = apiInput.getTimestamp();
        String param = apiInput.getParam();
//        String sk = akskService.getSkByAk(ak);
        String sk = "sk-0000";
        if(StringUtils.isEmpty(sk)){
            Result<Object> result = new Result();
            result.setCode(500);
            result.setInfo("sk为空");
            print(JSON.toJSONString(result),response);
            return false;
        }
        request.setAttribute("sk", sk);
        if(StringUtils.isBlank(ak) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(param) || StringUtils.isBlank(sk)){
            Result<Object> result = new Result();
            result.setCode(500);
            result.setInfo("存在参数为空的情况");
            print(JSON.toJSONString(result),response);
            return false;
        }
        String encodeStr = APIEncryption.encode(ak, param, timestamp, sk);
        if(!sn.equals(encodeStr)){
            Result<Object> result = new Result();
            result.setCode(500);
            result.setInfo("签名验证错误");
            print(JSON.toJSONString(result),response);
            return false;
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    private String getBodyMsg(HttpServletRequest request) {
        int contentLength = request.getContentLength();
        if (contentLength <= 0) {
            return "";
        }
        byte[] b = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = 0;
            try {
                readlen = request.getInputStream().read(b, i, contentLength - i);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        String body = new String(b);
        return body;
    }

    private void print(String str,HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            response.setContentType("application/json; charset=utf-8" );
            writer = response.getWriter();
            writer.print(str);
        } catch (Exception e) {
        } finally {
            writer.close();
        }
    }
}
