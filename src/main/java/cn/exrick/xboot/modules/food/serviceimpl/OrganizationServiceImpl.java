package cn.exrick.xboot.modules.food.serviceimpl;

import cn.exrick.xboot.modules.food.dao.OrganizationDao;
import cn.exrick.xboot.modules.food.entity.Organization;
import cn.exrick.xboot.modules.food.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构表接口实现
 * @author 后羿i
 */
@Slf4j
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public OrganizationDao getRepository() {
        return organizationDao;
    }
}