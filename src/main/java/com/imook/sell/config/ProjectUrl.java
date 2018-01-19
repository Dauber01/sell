package com.imook.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目路径地址
 * @author Lucifer
 * @date 2018/01/19 15:14
 */
@Component
@ConfigurationProperties(prefix = "projectUrl")
@Data
public class ProjectUrl {

    /** 微信公众平台授权url. */
    private String wechatMpAuthorize;

    /** 微信开放平台授权url. */
    private String wechatOpenAuthorize;

    /** 点餐系统. */
    private String sell;

}
