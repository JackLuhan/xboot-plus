package cn.exrick.xboot.modules.food.service;

import cn.exrick.xboot.base.XbootBaseService;
import cn.exrick.xboot.common.vo.SearchVo;
import cn.exrick.xboot.modules.base.entity.File;
import cn.exrick.xboot.modules.food.entity.FoodLib;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 菜品库接口
 * @author 后羿i
 */
public interface FoodLibService extends XbootBaseService<FoodLib,String> {
    /**
     * @Author 后羿i
     * @Description //TODO 分页查询
     * @Date
     * @Param
     * @Return
     **/
    Page<FoodLib> findByCondition(FoodLib foodLib, SearchVo searchVo, Pageable pageable);
}