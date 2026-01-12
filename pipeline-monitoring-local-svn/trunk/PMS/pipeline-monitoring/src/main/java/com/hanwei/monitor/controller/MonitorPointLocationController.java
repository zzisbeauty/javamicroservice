package com.hanwei.monitor.controller;

import java.util.Arrays;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.monitor.entity.MonitorPointLocation;
import com.hanwei.monitor.service.IMonitorPointLocationService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



 /**
 * @Description: 监测点位信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Slf4j
@Tag(name="监测点位信息表")
@RestController
@RequestMapping("/monitor/monitorPointLocation")
public class MonitorPointLocationController extends BaseController<MonitorPointLocation, IMonitorPointLocationService> {
	@Autowired
	private IMonitorPointLocationService monitorPointLocationService;

	/**
	 * 分页列表查询
	 *
	 * @param monitorPointLocation
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "监测点位信息表-分页列表查询")
	@Operation(summary="监测点位信息表-分页列表查询")
	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
	public Result<?> queryPageList(MonitorPointLocation monitorPointLocation,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MonitorPointLocation> queryWrapper = QueryGenerator.initQueryWrapper(monitorPointLocation, req.getParameterMap());
		Page<MonitorPointLocation> page = new Page<MonitorPointLocation>(pageNo, pageSize);
		IPage<MonitorPointLocation> pageList = monitorPointLocationService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 *
	 * @param monitorPointLocation
	 * @return
	 */
	@AutoLog(value = "监测点位信息表-添加")
	@Operation(summary="监测点位信息表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MonitorPointLocation monitorPointLocation) {
		Boolean flag = monitorPointLocationService.save(monitorPointLocation);
		if(flag){
		    return Result.OK("添加成功！");
		}else{
		    return Result.OK("添加失败！");
		}

	}

	/**
	 * 编辑
	 *
	 * @param monitorPointLocation
	 * @return
	 */
	@AutoLog(value = "监测点位信息表-编辑")
	@Operation(summary="监测点位信息表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.POST})
	public Result<?> edit(@RequestBody MonitorPointLocation monitorPointLocation) {
		Boolean flag = monitorPointLocationService.updateById(monitorPointLocation);
		if(flag){
            return Result.OK("编辑成功！");
        }else{
            return Result.OK("编辑失败！");
        }
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
    @AutoLog(value = "监测点位信息表-通过id删除")
	@Operation(summary="监测点位信息表-通过id删除")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		Boolean flag = monitorPointLocationService.removeById(id);
		if(flag){
            return Result.OK("删除成功！");
        }else{
            return Result.OK("删除失败！");
        }
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "监测点位信息表-批量删除")
	@Operation(summary="监测点位信息表-批量删除")
	@RequestMapping(value = "/deleteBatch", method = {RequestMethod.POST})
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Boolean flag = this.monitorPointLocationService.removeByIds(Arrays.asList(ids.split(",")));
		if(flag){
            return Result.OK("批量删除成功！");
        }else{
            return Result.OK("批量删除失败！");
        }
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "监测点位信息表-通过id查询")
	@Operation(summary="监测点位信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MonitorPointLocation monitorPointLocation = monitorPointLocationService.getById(id);
		return Result.OK(monitorPointLocation);
	}

	 /**
	  * 支持文件流的情况下直接使用该方式
	  * 文件流
	  *
	  * @param request
	  * @param response
	  * @param monitorPointLocation
	  * @param fileName
	  */
	 @AutoLog(value = "监测点位信息表-文件流导出")
	 @Operation(summary="监测点位信息表-文件流导出")
	 @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
	 public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, MonitorPointLocation monitorPointLocation, String fileName){
		 try {
			 // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
			 response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			 response.setCharacterEncoding("utf-8");
			 // URLEncoder.encode可以防止中文乱码
			 fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("监测点位信息表"), "UTF-8").replaceAll("\\+", "%20");
			 response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			 monitorPointLocationService.exportData(response.getOutputStream(), request.getParameterMap(), monitorPointLocation);
			 return Result.OK("导出成功");
		 } catch (IOException e) {
			 log.error("导出Excel异常{}", e.getMessage());
			 return Result.error("导出失败",e.getMessage());
		 }
	 }

	 /**
	  * 不支持文件流的情况下直接使用该方式
	  * base64文件
	  *
	  * @param request
	  * @param monitorPointLocation
	  */
	 @AutoLog(value = "监测点位信息表-base64方式导出")
	 @Operation(summary="监测点位信息表-base64方式导出")
	 @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
	 public Result<?> exportXlsToBase64(HttpServletRequest request, MonitorPointLocation monitorPointLocation) {
		 try {
			 String data = monitorPointLocationService.exportDataToBase64(request.getParameterMap(), monitorPointLocation);
			 return Result.OK("导出成功",data);
		 } catch (Exception e) {
			 log.error("导出Excel异常{}", e.getMessage());
			 return Result.error("导出失败", e.getMessage());
		 }
	 }

	 /**
	  * 通过excel导入数据
	  *
	  * @param file
	  * @return
	  */
	 @AutoLog(value = "监测点位信息表-excel文件导入")
	 @Operation(summary="监测点位信息表-excel文件导入")
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestParam MultipartFile file) {
		 return monitorPointLocationService.importData(file);
	 }

	 /**
	  * 下载导入模板
	  * @return
	  */
	 @Operation(summary="监测点位信息表-下载导入模板")
	 @RequestMapping(value = "/getImportTemplate", method = RequestMethod.GET)
	 public Result<?> getImportTemplate() {
		 try {
			 String data = monitorPointLocationService.getImportTemplate();
			 return Result.OK("导出模板成功",data);
		 } catch (Exception e) {
			 log.error("导出Excel异常{}", e.getMessage());
			 return Result.error("导出模板失败", e.getMessage());
		 }
	 }

}
