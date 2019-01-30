package com.life.game.service.impl;

import com.life.game.dao.TaskDao;
import com.life.game.model.Task;
import com.life.game.service.TaskService;
import com.life.game.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private TaskDao taskDao;

    @Override
    public Integer save(Task mockParams) {
        if(mockParams.getId() == null){
            return taskDao.insert(mockParams);
        }
        return taskDao.updateByPrimaryKey(mockParams);    }

    @Override
    public Task get(Long id) {
        return taskDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer delete(Long id) {
        return taskDao.deleteByPrimaryKey(id);
    }

    @Override
    public PageUtil<Task> queryParamsByPage(Task queryParams, Integer pageNum, Integer pageSize) {
        PageUtil<Task> pageUtil = new PageUtil<>();
        pageUtil.setTotalRecords(taskDao.countParams(queryParams));
        pageUtil.setPageRecords(pageSize);
        pageUtil.setMaxPages();
        pageUtil.setPageIndex(pageNum);

        int startNum = (pageUtil.getPageIndex()-1)*pageSize;
        pageUtil.setList(taskDao.queryParamsByPage(queryParams, startNum, pageSize));

        return pageUtil;
    }

    @Override
    public List<Task> selectByTaskName(String taskName) {
        return taskDao.selectByTaskName(taskName);
    }
}
