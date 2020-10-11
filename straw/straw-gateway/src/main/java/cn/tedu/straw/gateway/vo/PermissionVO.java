package cn.tedu.straw.gateway.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class PermissionVO implements Serializable {

    private Integer id;
    private String authority;
    private String description;
}
