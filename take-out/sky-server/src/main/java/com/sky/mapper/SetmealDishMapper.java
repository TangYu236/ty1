package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author TangYu
 * @Date 2025/5/12 20:22
 */
@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id获取到套餐id集合
     * @param dishIds
     * @return
     */
    List<Long> getSetMealDishIdsByIds(List<Long> dishIds);

    /**
     * 根据套餐id查询套餐菜品
     * @param id
     * @return
     */
    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> getSetMealDishIdsBySetmealId(Long id);

    void insertBatch(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id删除套餐关联菜品
     * @param id
     */
    @Delete("delete from setmeal_dish where setmeal_id = #{id}")
    void deleteBySetmealId(Long id);


    /**
     * 根据id批量删除套餐关联菜品
     * @param ids
     */
    void deleteBySetmealIds(List<Long> ids);
}
