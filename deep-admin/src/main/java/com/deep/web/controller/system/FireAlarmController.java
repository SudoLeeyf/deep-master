package com.deep.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.deep.common.annotation.Log;
import com.deep.common.core.controller.BaseController;
import com.deep.common.core.domain.AjaxResult;
import com.deep.common.enums.BusinessType;
import com.deep.system.domain.FireAlarm;
import com.deep.system.service.IFireAlarmService;
import com.deep.common.utils.poi.ExcelUtil;
import com.deep.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 火灾报警Controller
 * @date 2025-02-13
 */
@RestController
@RequestMapping("/system/alarm")
public class FireAlarmController extends BaseController
{
    @Autowired
    private IFireAlarmService fireAlarmService;

    /**
     * 查询火灾报警列表
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:list')")
    @GetMapping("/list")
    public TableDataInfo list(FireAlarm fireAlarm)
    {
        startPage();
        List<FireAlarm> list = fireAlarmService.selectFireAlarmList(fireAlarm);
        return getDataTable(list);
    }

    /**
     * 导出火灾报警列表
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:export')")
    @Log(title = "火灾报警", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FireAlarm fireAlarm)
    {
        List<FireAlarm> list = fireAlarmService.selectFireAlarmList(fireAlarm);
        ExcelUtil<FireAlarm> util = new ExcelUtil<FireAlarm>(FireAlarm.class);
        util.exportExcel(response, list, "火灾报警数据");
    }

    /**
     * 获取火灾报警详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(fireAlarmService.selectFireAlarmById(id));
    }

    /**
     * 新增火灾报警
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:add')")
    @Log(title = "火灾报警", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FireAlarm fireAlarm)
    {
        return toAjax(fireAlarmService.insertFireAlarm(fireAlarm));
    }

    /**
     * AI新增火灾报警
     */
    @PostMapping("/ai")
    public AjaxResult aiAdd(@RequestParam("file") MultipartFile file, @RequestParam("imageUrl") String data, @RequestParam("name") String name) {
        return toAjax(fireAlarmService.insertFireAlarmByAI(file, data, name));
    }

    /**
     * 修改火灾报警
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:edit')")
    @Log(title = "火灾报警", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FireAlarm fireAlarm)
    {
        return toAjax(fireAlarmService.updateFireAlarm(fireAlarm));
    }

    /**
     * 删除火灾报警
     */
    @PreAuthorize("@ss.hasPermi('system:alarm:remove')")
    @Log(title = "火灾报警", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(fireAlarmService.deleteFireAlarmByIds(ids));
    }
}
