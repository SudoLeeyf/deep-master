package com.deep.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.deep.common.annotation.Excel;
import com.deep.common.core.domain.BaseEntity;

/**
 * 火灾报警对象 fire_alarm
 *
 * @author ruoyi
 * @date 2025-02-13
 */
public class FireAlarm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 报警名称 */
    @Excel(name = "报警名称")
    private String name;

    /** 报警时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "报警时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date alarmTime;

    /** 状态（0正常 1关闭） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=关闭")
    private String status;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String imageUrl;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setAlarmTime(Date alarmTime)
    {
        this.alarmTime = alarmTime;
    }

    public Date getAlarmTime()
    {
        return alarmTime;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("alarmTime", getAlarmTime())
                .append("status", getStatus())
                .append("imageUrl", getImageUrl())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
