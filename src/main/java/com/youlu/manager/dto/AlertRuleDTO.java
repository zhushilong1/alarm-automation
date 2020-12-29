package com.youlu.manager.dto;

import com.youlu.manager.pojo.AlertRule;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhushilong
 */
@Data
public class AlertRuleDTO {
    private String alertType;
    private String alertMode;
    private String startTime;
    private String endTime;
}
