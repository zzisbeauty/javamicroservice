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
import com.hanwei.monitor.entity.MonitorPointFollow;
import com.hanwei.monitor.service.IMonitorPointFollowService;

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
 * @Description: 监测点关注信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Slf4j
@Tag(name="监测点关注信息")
@RestController
@RequestMapping("/monitor/monitorPointFollow")
public class MonitorPointFollowController extends BaseController<MonitorPointFollow, IMonitorPointFollowService> {
	@Autowired
	private IMonitorPointFollowService monitorPointFollowService;

	/**
	 * 分页列表查询
	 *
	 * @param monitorPointFollow
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "监测点关注信息-分页列表查询")
	@Operation(summary="监测点关注信息-分页列表查询")
	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
	public Result<?> queryPageList(MonitorPointFollow monitorPointFollow,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MonitorPointFollow> queryWrapper = QueryGenerator.initQueryWrapper(monitorPointFollow, req.getParameterMap());
		Page<MonitorPointFollow> page = new Page<MonitorPointFollow>(pageNo, pageSize);
		IPage<MonitorPointFollow> pageList = monitorPointFollowService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 添加
	 *
	 * @param monitorPointFollow
	 * @return
	 */
	@AutoLog(value = "监测点关注信息-添加")
	@Operation(summary="监测点关注信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MonitorPointFollow monitorPointFollow) {
		Boolean flag = monitorPointFollowService.save(monitorPointFollow);
		if(flag){
		    return Result.OK("添加成功！");
		}else{
		    return Result.OK("添加失败！");
		}

	}

	/**
	 * 编辑
	 *
	 * @param monitorPointFollow
	 * @return
	 */
	@AutoLog(value = "监测点关注信息-编辑")
	@Operation(summary="监测点关注信息-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.POST})
	public Result<?> edit(@RequestBody MonitorPointFollow monitorPointFollow) {
		Boolean flag = monitorPointFollowService.updateById(monitorPointFollow);
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
    @AutoLog(value = "监测点关注信息-通过id删除")
	@Operation(summary="监测点关注信息-通过id删除")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		Boolean flag = monitorPointFollowService.removeById(id);
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
	@AutoLog(value = "监测点关注信息-批量删除")
	@Operation(summary="监测点关注信息-批量删除")
	@RequestMapping(value = "/deleteBatch", method = {RequestMethod.POST})
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Boolean flag = this.monitorPointFollowService.removeByIds(Arrays.asList(ids.split(",")));
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
	@AutoLog(value = "监测点关注信息-通过id查询")
	@Operation(summary="监测点关注信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MonitorPointFollow monitorPointFollow = monitorPointFollowService.getById(id);
		return Result.OK(monitorPointFollow);
	}

	 /**
	  * 支持文件流的情况下直接使用该方式
	  * 文件流
	  *
	  * @param request
	  * @param response
	  * @param monitorPointFollow
	  * @param fileName
	  */
	 @AutoLog(value = "监测点关注信息-文件流导出")
	 @Operation(summary="监测点关注信息-文件流导出")
	 @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
	 public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, MonitorPointFollow monitorPointFollow, String fileName){
		 try {
			 // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
			 response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			 response.setCharacterEncoding("utf-8");
			 // URLEncoder.encode可以防止中文乱码
			 fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("监测点关注信息"), "UTF-8").replaceAll("\\+", "%20");
			 response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			 monitorPointFollowService.exportData(response.getOutputStream(), request.getParameterMap(), monitorPointFollow);
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
	  * @param monitorPointFollow
	  */
	 @AutoLog(value = "监测点关注信息-base64方式导出")
	 @Operation(summary="监测点关注信息-base64方式导出")
	 @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
	 public Result<?> exportXlsToBase64(HttpServletRequest request, MonitorPointFollow monitorPointFollow) {
		 try {
			 String data = monitorPointFollowService.exportDataToBase64(request.getParameterMap(), monitorPointFollow);
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
	 @AutoLog(value = "监测点关注信息-excel文件导入")
	 @Operation(summary="监测点关注信息-excel文件导入")
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestParam MultipartFile file) {
		 return monitorPointFollowService.importData(file);
	 }

	 /**
	  * 下载导入模板
	  * @return
	  */
	 @Operation(summary="监测点关注信息-下载导入模板")
	 @RequestMapping(value = "/getImportTemplate", method = RequestMethod.GET)
	 public Result<?> getImportTemplate() {
		 try {
			 String data = monitorPointFollowService.getImportTemplate();
			 return Result.OK("导出模板成功",data);
		 } catch (Exception e) {
			 log.error("导出Excel异常{}", e.getMessage());
			 return Result.error("导出模板失败", e.getMessage());
		 }
	 }

}
