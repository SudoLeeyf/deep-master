package com.deep.system.service;

import java.util.List;
import com.deep.system.domain.FireAlarm;
import org.springframework.web.multipart.MultipartFile;

/**
 * 火灾报警Service接口
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
public interface IFireAlarmService 
{
    /**
     * 查询火灾报警
     * 
     * @param id 火灾报警主键
     * @return 火灾报警
     */
    public FireAlarm selectFireAlarmById(Long id);

    /**
     * 查询火灾报警列表
     * 
     * @param fireAlarm 火灾报警
     * @return 火灾报警集合
     */
    public List<FireAlarm> selectFireAlarmList(FireAlarm fireAlarm);

    /**
     * 新增火灾报警
     * 
     * @param fireAlarm 火灾报警
     * @return 结果
     */
    public int insertFireAlarm(FireAlarm fireAlarm);
    public int insertFireAlarmByAI(MultipartFile file, String data, String name);

    /**
     * 修改火灾报警
     * 
     * @param fireAlarm 火灾报警
     * @return 结果
     */
    public int updateFireAlarm(FireAlarm fireAlarm);

    /**
     * 批量删除火灾报警
     * 
     * @param ids 需要删除的火灾报警主键集合
     * @return 结果
     */
    public int deleteFireAlarmByIds(Long[] ids);

    /**
     * 删除火灾报警信息
     * 
     * @param id 火灾报警主键
     * @return 结果
     */
    public int deleteFireAlarmById(Long id);
}
