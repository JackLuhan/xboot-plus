package cn.exrick.xboot.modules.food.controller;

import cn.exrick.xboot.base.XbootBaseController;
import cn.exrick.xboot.common.constant.CommonConstant;
import cn.exrick.xboot.common.constant.SettingConstant;
import cn.exrick.xboot.common.utils.PageUtil;
import cn.exrick.xboot.common.utils.ResultUtil;
import cn.exrick.xboot.common.vo.OssSetting;
import cn.exrick.xboot.common.vo.PageVo;
import cn.exrick.xboot.common.vo.Result;
import cn.exrick.xboot.common.vo.SearchVo;
import cn.exrick.xboot.modules.base.entity.File;
import cn.exrick.xboot.modules.base.entity.Log;
import cn.exrick.xboot.modules.base.entity.Role;
import cn.exrick.xboot.modules.base.entity.elasticsearch.EsLog;
import cn.exrick.xboot.modules.food.dao.OrganizationDao;
import cn.exrick.xboot.modules.food.entity.FoodLib;
import cn.exrick.xboot.modules.food.entity.Organization;
import cn.exrick.xboot.modules.food.service.FoodLibService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author 后羿i
 */
@Slf4j
@RestController
@Api(description = "菜品库管理接口")
@RequestMapping("/xboot/foodLib")
@Transactional
public class FoodLibController extends XbootBaseController<FoodLib, String>{

    @Autowired
    private FoodLibService foodLibService;

    @Override
    public FoodLibService getService() {
        return foodLibService;
    }

    @RequestMapping(value = "/getAllList",method = RequestMethod.GET)
    @ApiOperation(value = "获取全部菜品库")
    public Result<Object> roleGetAll(){

        List<FoodLib> list = foodLibService.getAll();
        return new ResultUtil<>().setData(list);
    }

    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取菜品库")
    public Result<Page<FoodLib>> getFoodLibList(@ModelAttribute FoodLib foodLib,
                                          @ModelAttribute SearchVo searchVo,
                                          @ModelAttribute PageVo pageVo){
        Page<FoodLib> page = foodLibService.findByCondition(foodLib, searchVo, PageUtil.initPage(pageVo));

        return new ResultUtil<Page<FoodLib>>().setData(page);
    }


}
