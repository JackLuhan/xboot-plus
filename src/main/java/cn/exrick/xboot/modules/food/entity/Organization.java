package cn.exrick.xboot.modules.food.entity;

import cn.exrick.xboot.base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author 后羿i
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_organization")
@TableName("t_organization")
public class Organization extends XbootBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父id")
    private String orgId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    //菜品库和机构 多对多关系
    /*@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,mappedBy = "organization")
    @JoinTable(
            name = "foodLib_organization",
            joinColumns = {@JoinColumn(name = "food_lib_id")},
            inverseJoinColumns = {@JoinColumn(name = "organization_id")}
    )*/
//    private List<FoodLib> foodLibList;

}