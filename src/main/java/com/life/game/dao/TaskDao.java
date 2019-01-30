package com.life.game.dao;


import com.life.game.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TaskDao {
	int deleteByPrimaryKey(Long id);

	int insert(Task record);

	int insertSelective(Task record);

	Task selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Task record);

	int updateByPrimaryKey(Task record);

	List<Task> queryParamsByPage(@Param("queryParams") Task queryParams, @Param("startNum") Integer startNum, @Param("pageSize") Integer pageSize);

	int countParams(@Param("queryParams") Task queryParams);

	List<Task> selectByTaskName(String taskName);
}
