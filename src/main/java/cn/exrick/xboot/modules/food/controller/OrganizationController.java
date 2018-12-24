package cn.exrick.xboot.modules.food.controller;

import cn.exrick.xboot.base.XbootBaseController;
import cn.exrick.xboot.common.utils.PageUtil;
import cn.exrick.xboot.common.utils.ResultUtil;
import cn.exrick.xboot.common.vo.PageVo;
import cn.exrick.xboot.common.vo.Result;
import cn.exrick.xboot.modules.food.entity.Organization;
import cn.exrick.xboot.modules.food.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 后羿i
 */
@Slf4j
@RestController
@Api(description = "机构表管理接口")
@RequestMapping("/xboot/organization")
@Transactional
public class OrganizationController extends XbootBaseController<Organization, String>{

    @Autowired
    private OrganizationService organizationService;

    @Override
    public OrganizationService getService() {
        return organizationService;
    }

}
