package com.hanwei.problem.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description: 问题查询参数BO
 * @Author: hanwei
 * @Date:   2026-02-06
 * @Version: V1.0
 */
@Data
@Schema(title="ProblemQueryBO对象", description="问题查询参数")
public class ProblemQueryBO {

    @Schema(description = "问题状态（开启／关闭）")
    private String status;

    @Schema(description = "问题大类编号")
    private String problemCategoryId;

    @Schema(description = "开始时间")
    private String startTime;

    @Schema(description = "结束时间")
    private String endTime;

    @Schema(description = "提出人名称")
    private String proposerName;
}