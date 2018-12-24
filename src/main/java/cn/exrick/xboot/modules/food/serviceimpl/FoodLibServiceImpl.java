package cn.exrick.xboot.modules.food.serviceimpl;

import cn.exrick.xboot.common.vo.SearchVo;
import cn.exrick.xboot.modules.base.entity.File;
import cn.exrick.xboot.modules.food.dao.FoodLibDao;
import cn.exrick.xboot.modules.food.entity.FoodLib;
import cn.exrick.xboot.modules.food.service.FoodLibService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜品库接口实现
 * @author 后羿i
 */
@Slf4j
@Service
@Transactional
public class FoodLibServiceImpl implements FoodLibService {

    @Autowired
    private FoodLibDao foodLibDao;

    @Override
    public FoodLibDao getRepository() {
        return foodLibDao;
    }

    @Override
    public Page<FoodLib> findByCondition(FoodLib foodLib, SearchVo searchVo, Pageable pageable) {

        return foodLibDao.findAll(new Specification<FoodLib>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<FoodLib> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> nameField = root.get("foodLibName");
                Path<Date> createTimeField=root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
                if(StrUtil.isNotBlank(foodLib.getFoodLibName())) {
                    list.add(cb.like(nameField, '%' + foodLib.getFoodLibName() + '%'));
                }
                //创建时间
                if(StrUtil.isNotBlank(searchVo.getStartDate())&&StrUtil.isNotBlank(searchVo.getEndDate())){
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    Date end = DateUtil.parse(searchVo.getEndDate());
                    list.add(cb.between(createTimeField, start, DateUtil.endOfDay(end)));
                }

                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }
}