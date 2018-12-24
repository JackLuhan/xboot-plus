package cn.exrick.xboot.modules.food.entity;

import cn.exrick.xboot.base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.util.List;
import java.util.Set;

/**
 * @author 后羿i
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_food_lib")
@TableName("t_food_lib")
public class FoodLib extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "食品库名称")
    private String foodLibName;

    //菜品库和机构 多对多关系
//    @ManyToMany
//    private List<Organization> organizationList;


}