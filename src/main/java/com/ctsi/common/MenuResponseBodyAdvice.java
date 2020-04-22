package com.ctsi.common;

import com.alibaba.fastjson.JSONObject;
import com.ctsi.dao.CscpConfDao;
import com.ctsi.domain.CscpLicense;
import com.ctsi.service.CscpFuncService;
import com.ctsi.ssdc.admin.domain.dto.CscpMenusDTO;
import com.ctsi.ssdc.admin.service.CscpUserDetailService;
import com.ctsi.ssdc.security.SecurityUtils;
import com.ctsi.utils.Base64Util;
import com.ctsi.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ClassName MyResponseBodyAdvice
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/20 16:17
 * Version v1.0
 **/
@ControllerAdvice
public class MenuResponseBodyAdvice implements ResponseBodyAdvice {
    @Autowired
    private CscpFuncService funcService;
    @Autowired
    private CscpUserDetailService userDetailService;
    @Autowired
    private CscpConfDao confDao;
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType mediaType, Class clz, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        String uid = SecurityUtils.getCurrentUserId();
        String orgID=userDetailService.findByUserId(uid).getOrgId();
        List<String> list=funcService.getFuncMenus(orgID);
        byte[] info = null;
        String publicKey = confDao.getConfig("publicKey");
        String license = confDao.getConfig(orgID);
        List<CscpMenusDTO> menus=(List<CscpMenusDTO>)body;
        List<CscpMenusDTO> result=new ArrayList<>();
        if(license==null) {
            Optional<CscpMenusDTO> menu=menus.stream().filter(m->m.getName().equals("configure")).findFirst();
            if (menu.isPresent())
                result.add(menu.get());
            Optional<CscpMenusDTO> subMenu=menus.stream().filter(m->m.getName().equals("license")).findFirst();
            if (subMenu.isPresent())
                result.add(subMenu.get());
        }else {
            try {
                byte[] bytes = Base64Util.decode(license);
                info = RSAUtil.decryptByPublicKey(bytes, publicKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String decryptValue = null;
            decryptValue = new String(info, StandardCharsets.UTF_8);
            JSONObject object = JSONObject.parseObject(decryptValue);
            CscpLicense cscpLicense = JSONObject.toJavaObject(object, CscpLicense.class);
            if (cscpLicense.isValid() && !cscpLicense.isExpired()) {
                for (String str : list) {
                    Optional<CscpMenusDTO> menu = menus.stream().filter(m -> m.getId().equals(str)).findFirst();
                    if (menu.isPresent()) {
                        result.add(menu.get());
                        getSubMenus(menu.get(),menus,result);
                    }
                }
            } else {
                Optional<CscpMenusDTO> menu=menus.stream().filter(m->m.getName().equals("configure")).findFirst();
                if (menu.isPresent())
                    result.add(menu.get());
                Optional<CscpMenusDTO> subMenu=menus.stream().filter(m->m.getName().equals("license")).findFirst();
                if (subMenu.isPresent())
                    result.add(subMenu.get());
            }
        }
        return result;
    }
    private void getSubMenus(CscpMenusDTO menu,List<CscpMenusDTO> menus,List<CscpMenusDTO> result){
        List<CscpMenusDTO> subMenu= menus.stream().filter(m->m.getParentId().equals(menu.getId())).collect(Collectors.toList());
        for (CscpMenusDTO dto:subMenu) {
            result.add(dto);
            getSubMenus(dto,menus,result);
        }
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class clz) {
        String methodName=methodParameter.getMethod().getName();
        String method= "getCscpMenus1";
        return method.equals(methodName);
    }
}
