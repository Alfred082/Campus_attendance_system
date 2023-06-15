package com.jdk.projectinterface.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 管理员表
 */
@Data
@NoArgsConstructor
@ApiModel(description = "管理员实体类对象")
public class Admin {
    @TableId(type = IdType.AUTO)
    /**
     * 管理员id
     */
    @ApiModelProperty(value = "管理员id")
    private Integer adminId;
    /**
     * 管理员账号
     */
    @ApiModelProperty(value = "管理员账号")
    private String adminAccount;
    /**
     * 管理员密码
     */
    @ApiModelProperty(value = "管理员密码")
    private String adminPassword;
}
