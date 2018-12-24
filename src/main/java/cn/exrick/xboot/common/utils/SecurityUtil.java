package cn.exrick.xboot.common.utils;

import cn.exrick.xboot.common.constant.CommonConstant;
import cn.exrick.xboot.common.constant.SecurityConstant;
import cn.exrick.xboot.modules.base.entity.*;
import cn.exrick.xboot.modules.base.service.UserService;
import cn.exrick.xboot.modules.base.service.mybatis.IUserRoleService;
import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Exrickx
 */
@Component
public class SecurityUtil {

    @Value("${xboot.saveLoginTime}")
    private Integer saveLoginTime;

    @Value("${xboot.tokenExpireTime}")
    private Integer tokenExpireTime;

    @Autowired
    private UserService userService;

    @Autowired
    private IUserRoleService iUserRoleService;

    public String getToken(String username, Boolean saveLogin){

        if(saveLogin==null||saveLogin){
            tokenExpireTime = saveLoginTime * 60 * 24;
        }
        // 已绑定
        User u = userService.findByUsername(username);
        List<String> authorities = new ArrayList<>();
        for(Permission p : u.getPermissions()){
            if(CommonConstant.PERMISSION_OPERATION.equals(p.getType())
                    && StrUtil.isNotBlank(p.getTitle())
                    && StrUtil.isNotBlank(p.getPath())) {
                authorities.add(p.getTitle());
            }
        }
        for(Role r : u.getRoles()){
            authorities.add(r.getName());
        }
        //登陆成功生成JWT
        String token = Jwts.builder()
                //主题 放入用户名
                .setSubject(u.getUsername())
                //自定义属性 放入用户拥有请求权限
                .claim(SecurityConstant.AUTHORITIES, new Gson().toJson(authorities))
                //失效时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpireTime * 60 * 1000))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
                .compact();
        token = SecurityConstant.TOKEN_SPLIT + token;
        return token;
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public User getCurrUser(){

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findByUsername(user.getUsername());
    }

    /**
     * 获取当前用户数据权限 null代表具有所有权限
     */
    public List<String> getDeparmentIds(){

        List<String> deparmentIds = new ArrayList<>();
        User u = getCurrUser();
        // 用户角色
        List<Role> userRoleList = iUserRoleService.findByUserId(u.getId());
        // 判断有无全部数据的角色
        Boolean flagAll = false;
        for(Role r : userRoleList){
            if(r.getDataType()==null||r.getDataType().equals(CommonConstant.DATA_TYPE_ALL)){
                flagAll = true;
                break;
            }
        }
        if(flagAll){
            return null;
        }
        // 查找自定义
        return iUserRoleService.findDepIdsByUserId(u.getId());
    }
}
