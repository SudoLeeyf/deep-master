package com.deep.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.deep.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deep.system.mapper.FireAlarmMapper;
import com.deep.system.domain.FireAlarm;
import com.deep.system.service.IFireAlarmService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 火灾报警Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
@Service
public class FireAlarmServiceImpl implements IFireAlarmService 
{
    @Autowired
    private FireAlarmMapper fireAlarmMapper;

    /**
     * 查询火灾报警
     * 
     * @param id 火灾报警主键
     * @return 火灾报警
     */
    @Override
    public FireAlarm selectFireAlarmById(Long id)
    {
        return fireAlarmMapper.selectFireAlarmById(id);
    }

    /**
     * 查询火灾报警列表
     * 
     * @param fireAlarm 火灾报警
     * @return 火灾报警
     */
    @Override
    public List<FireAlarm> selectFireAlarmList(FireAlarm fireAlarm)
    {
        return fireAlarmMapper.selectFireAlarmList(fireAlarm);
    }

    /**
     * 新增火灾报警
     *
     * @param fireAlarm 火灾报警
     * @return 结果
     */
    @Override
    public int insertFireAlarm(FireAlarm fireAlarm)
    {
        fireAlarm.setCreateTime(DateUtils.getNowDate());
        return fireAlarmMapper.insertFireAlarm(fireAlarm);
    }

    @Override
    public int insertFireAlarmByAI(MultipartFile file, String data, String name) {
        FireAlarm fireAlarm = new FireAlarm();
        if (Objects.equals("fire", name)) {
            fireAlarm.setName("火灾");
        } else if (Objects.equals("smoke", name)) {
            fireAlarm.setName("烟感");
        }
        fireAlarm.setCreateTime(DateUtils.getNowDate());
        fireAlarm.setAlarmTime(new Date());
        fireAlarm.setImageUrl(data);
        fireAlarm.setRemark("暂无");
        return fireAlarmMapper.insertFireAlarmByAI(fireAlarm);
    }

    /**
     * 修改火灾报警
     * 
     * @param fireAlarm 火灾报警
     * @return 结果
     */
    @Override
    public int updateFireAlarm(FireAlarm fireAlarm)
    {
        fireAlarm.setUpdateTime(DateUtils.getNowDate());
        return fireAlarmMapper.updateFireAlarm(fireAlarm);
    }

    /**
     * 批量删除火灾报警
     * 
     * @param ids 需要删除的火灾报警主键
     * @return 结果
     */
    @Override
    public int deleteFireAlarmByIds(Long[] ids)
    {
        return fireAlarmMapper.deleteFireAlarmByIds(ids);
    }

    /**
     * 删除火灾报警信息
     * 
     * @param id 火灾报警主键
     * @return 结果
     */
    @Override
    public int deleteFireAlarmById(Long id)
    {
        return fireAlarmMapper.deleteFireAlarmById(id);
    }
}
