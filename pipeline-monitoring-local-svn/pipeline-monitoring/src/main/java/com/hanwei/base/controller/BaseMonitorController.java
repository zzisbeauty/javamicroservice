package com.hanwei.base.controller;

import java.util.List;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import com.hanwei.base.entity.dto.BaseMonitorDto;
import com.hanwei.base.entity.dto.BaseMonitorInput;
import com.hanwei.core.annotation.ApiKind;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.annotation.AutoRegister;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.base.entity.BaseMonitor;
import com.hanwei.base.service.IBaseMonitorService;
import com.hanwei.data.entity.vo.MonitorRealtimeVo;
import com.hanwei.video.entity.dto.VideoFollowInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Description: 监测点
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
@Slf4j
@RequiredArgsConstructor
@Tag(name="监测点")
@RestController
@RequestMapping("/base/baseMonitor")
@AutoRegister
@ApiKind(value = "base-监测点")
public class BaseMonitorController extends BaseController<BaseMonitor, IBaseMonitorService> {

	private final IBaseMonitorService baseMonitorService;

	/**
	 * 监测点及其关系查询
	 */

	@AutoLog(value = "管网监测系统-监测点-分页列表查询")
	@Operation(summary = "管网监测系统-监测点-分页列表查询")
	@PostMapping(value = "/list")
	public Result<?> queryMonitorRelate(@RequestBody BaseMonitorDto dto,
										@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
										@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		return Result.OK(baseMonitorService.queryMonitorRelate(dto, pageNo, pageSize));
	}

	/**
	 * 监测点及其关系添加
	 *
	 * @param input
	 * @return
	 */
	@AutoLog(value = "管网监测系统-监测点-监测点及其关系添加")
	@Operation(summary = "管网监测系统-监测点-监测点及其关系添加")
	@PostMapping(value = "/addMonitorRelate")
	public Result<?> addMonitorRelate(@RequestBody BaseMonitorInput input) {

		baseMonitorService.addMonitorRelate(input);

		return Result.OK("添加成功！");
	}

	@AutoLog(value = "管网监测系统-监测点-监测点主从关系删除")
	@Operation(summary = "管网监测系统-监测点-监测点主从关系删除")
	@PostMapping(value = "/deleteMonitorRelate")
	public Result<?> deleteMonitorRelate(@RequestParam(name = "id", required = true)
										 @ApiParameter(name = "id", description = "id", required = true, demovalue = "1") String id) {
		return baseMonitorService.deleteMonitorRelate(id);

	}

	@AutoLog(value = "管网监测系统-监测点-监测点主从关系编辑")
	@Operation(summary = "管网监测系统-监测点-监测点主从关系编辑")
	@PostMapping(value = "/editMonitorRelate")
	public Result<?> editMonitorRelate(@RequestBody BaseMonitorInput input) {

		return baseMonitorService.editMonitorRelate(input);


	}

	@AutoLog(value = "管网监测系统-监测点-监测点主从关系批量删除")
	@Operation(summary = "管网监测系统-监测点-监测点主从关系批量删除")
	@PostMapping(value = "/deleteMonitorRelates")
	public Result<?> deleteMonitorRelates(@RequestBody List<String> ids) {
		return baseMonitorService.deleteMonitorRelates(ids);
	}


	/**
	 * 通过excel导入数据
	 *
	 * @param file
	 * @return
	 */
	@AutoLog(value = "管网监测系统-监测点-excel文件导入")
	@Operation(summary = "管网监测系统-监测点-excel文件导入")
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public Result<?> importExcel(@RequestParam
								 @ApiParameter(name = "file", description = "导入文件", required = true, demovalue = "excel.xlsx") MultipartFile file) {
		return baseMonitorService.importData(file);
	}

	/**
	 * 数据-实时数据查询-列表查询
	 *
	 * @param monitorId
	 * @param authorityType
	 * @param type
	 * @return
	 */

	@AutoLog(value = "数据-实时数据查询-列表查询")
	@Operation(summary = "数据-实时数据查询-列表查询")
	@GetMapping(value = "/voList")
	public Result<List<MonitorRealtimeVo>> queryMonitorRealtimeListVo(
			@RequestParam(name = "monitorId",required = false)
			@Schema (description = "监测点id")
			@ApiParameter (name = "monitorId", description = "监测点id", required = false, demovalue = "1") String monitorId,
	        @RequestParam(name = "authorityType", required = false )
			@Schema (description = "权限类型")
			@ApiParameter(name = "authorityType", description = "权限类型", required = false, demovalue = "1")Integer authorityType,
			@RequestParam (name = "type", required = false)
			@Schema (description = "类型")
			@ApiParameter (name = "type", description = "类型", required = false, demovalue = "1")Integer type){

		List<MonitorRealtimeVo> list = baseMonitorService.queryMonitorRealtimeListVo(monitorId, authorityType, type);

		return Result.OK(list);



	}
}
