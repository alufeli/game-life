package com.life.game.controller;

import com.life.game.model.Task;
import com.life.game.service.TaskService;
import com.life.game.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wagic
 */
@RestController
@RequestMapping("/v1/mockParams")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 分页查询
     * @date 2018-11-08 14:10
     * @param taskName
     * @param taskDesc
     * @param pageNum
     * @param pageSize
     * @return 分页数据
     **/
    @RequestMapping(value="/queryList", method = RequestMethod.GET)
    public PageUtil<Task> queryList(@RequestParam(value="taskName", required = false) String taskName,
                                    @RequestParam(value = "taskDesc", required = false) String taskDesc,
                                    @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                                    @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        Task params = new Task(taskName, taskDesc);
        return taskService.queryParamsByPage(params, pageNum, pageSize);
    }

    /**
     * 获取参数说明
     * @date 2018-11-08 14:31
     * @param id 主键id
     * @return 参数说明对象
     **/
    @RequestMapping(value="/paramDetail", method = RequestMethod.GET)
    public Task get(@RequestParam(value = "id") Long id){
        return taskService.get(id);
    }

    /**
     * 保存参数说明
     * @date 2018-11-08 14:31
     * @param mockParams 保存信息
     * @return 保存结果
     **/
    @RequestMapping(value="/saveParam", method = RequestMethod.POST)
    public Integer save(@RequestBody Task mockParams){
        return taskService.save(mockParams);
    }

    /**
     * 删除存参数说明
     * @date 2018-11-08 14:31
     * @param id 主键id
     * @return 删除结果
     **/
    @RequestMapping(value="/deleteParam/{id}", method = RequestMethod.DELETE)
    public Integer delete(@PathVariable(value = "id") Long id){
        if (taskService.get(id) == null){
            return -1;
        }
        return taskService.delete(id);
    }
}
