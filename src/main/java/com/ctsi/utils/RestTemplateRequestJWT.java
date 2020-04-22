package com.ctsi.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * RestTemplate调用接口
 * @author ctsi
 * 
 */
public class RestTemplateRequestJWT {
    private static Logger logger = LoggerFactory.getLogger(RestTemplateRequestJWT.class);

    private static RestTemplate getRestTemplate() {
        RestTemplate rt;
        List<HttpMessageConverter<?>> lstConverters;
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.getObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        lstConverters = new ArrayList<HttpMessageConverter<?>>();
        lstConverters.add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        lstConverters.add(converter);

        rt = new RestTemplate();
          rt.setMessageConverters(lstConverters);

        return rt;
    }
    /**
     * 利用RestTemplate封装GET调用，提供类似于本地方法的调用接口
     * @param url 接口
     * @param responseType 响应类型
     * @return 结果
     */
    /*public static <T> T get(String url, Class<T> responseType) {
        return get(url, responseType, null);
    }*/
    /**
     * 带url参数的GET调用
     * @param url 接口
     * @param responseType 响应类型
     * @param urlVariables 请求参数
     * @return 结果
     */
    public static <T> T getMap(String url, Class<T> responseType,  Map<String, Object> urlVariables,HttpServletRequest request, String authorization1) {
     
        //String auth=SecurityUtils.getCurrentUserJWT().get();
        String authorization = request.getHeader("Authorization");
        HttpHeaders header=new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        if (authorization != null && !"".equals(authorization)) {
            header.add("Authorization", authorization);
        }else {
            header.add("Authorization", authorization1);
        }

        //String param="?value=1&type=0&id=1";
//        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
//        paramMap.add("value", "1");
//        paramMap.add("type", 0);
//        paramMap.add("id", "");
        //HttpEntity<MultiValueMap<String, Object>> httpEntity=new HttpEntity<MultiValueMap<String, Object>>(paramMap,header);
        HttpEntity<MultiValueMap<String, Object>> httpEntity=new HttpEntity<MultiValueMap<String, Object>>(null,header);
        logger.info("[rest get] {}", url);
        logger.info("[responseType] {}", responseType);

//        ResponseEntity<T> result=getRestTemplate().exchange(url+param, HttpMethod.GET, httpEntity, responseType,urlVariables);
        //循环map的变量 赋给参数
        String param = null;
        int i = 1;
        for (Map.Entry entry : urlVariables.entrySet()) {
        	if(i==1){
        		param = "?" + entry.getKey() + "=" + entry.getValue();
        	}else{
        		param = param + "&" + entry.getKey() + "=" + entry.getValue();
        	}
        	i = i+1;
        	
        }
        ResponseEntity<T> result=getRestTemplate().exchange(URLDecoder.decode(url + param), HttpMethod.GET, httpEntity, responseType,urlVariables);
        
      /*  

        if (urlVariables == null) {
            result = getRestTemplate().getForObject(url, responseType,httpEntity);
        }
        else {
            for (Map.Entry entry : urlVariables.entrySet()) {
                logger.info("[params] {} = {}", entry.getKey(), entry.getValue());
            }

            result = getRestTemplate().getForObject(url, responseType, httpEntity);
        }*/

        logger.info("[result] {}", result);

        return result.getBody();
    }

   /* public static <T> T get(String url, Class<T> responseType, Object request) {

        String auth=SecurityUtils.getCurrentUserJWT().get();

        HttpHeaders header=new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.add("Authorization", JWTConfigurer.AUTHORIZATION_BEARER+auth);

        HttpEntity httpEntity = new HttpEntity(request, header);
        ResponseEntity<T> result=getRestTemplate().exchange(URLDecoder.decode(url), HttpMethod.GET, httpEntity, responseType);


        logger.info("[result] {}", result);

        return result.getBody();
    }*/
    /**
     * 利用RestTemplate封装POST调用，提供类似于本地方法的调用接口
     * @param url 接口
     * @param request 请求
     * @param responseType 响应类型
     * @return 结果
     */
    /*public static <T> T post(String url, Object request, Class<T> responseType) {
        return post(url, request, responseType, null);
    }*/
    /**
     * 带url参数的POST调用
     * @param url 接口
     * @param request 请求
     * @param responseType 响应类型
     * @param urlVariables 参数
     * @return 结果
     */
    public static <T> T post(String url, Object request, Class<T> responseType, Map<String, ?> urlVariables,HttpServletRequest httpRequest) {
        T result;

        logger.info("[rest post] {}", url);
        logger.info("[request] {}", request);
        logger.info("[responseType] {}", responseType);
        String authorization = httpRequest.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
       /* String auth=SecurityUtils.getCurrentUserJWT().get();*/
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", authorization);

        HttpEntity httpEntity = new HttpEntity(request, headers);
        if (urlVariables == null) {
            result = getRestTemplate().postForObject(url, httpEntity, responseType);
        }
        else {
            for (Map.Entry entry : urlVariables.entrySet()) {
                logger.info("[params] {} = {}", entry.getKey(), entry.getValue());
            }

            result = getRestTemplate().postForObject(url, httpEntity, responseType, urlVariables);
        }

        logger.info("[result] {}", result);

        return result;
    }

}
