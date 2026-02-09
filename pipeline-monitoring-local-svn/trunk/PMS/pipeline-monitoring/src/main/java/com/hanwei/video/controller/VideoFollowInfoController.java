package com.hanwei.video.controller;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import com.hanwei.core.annotation.ApiKind;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.annotation.AutoRegister;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.video.entity.VideoFollowInfo;
import com.hanwei.video.entity.dto.VideoFollowInfoDto;
import com.hanwei.video.service.IVideoFollowInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



 /**
 * @Description: 视频监控关注信息
 * @Author: hanwei
 * @Date:   2026-01-20
 * @Version: V1.0
 */
@Slf4j
@RequiredArgsConstructor
@Tag(name="视频监控关注信息")
@RestController
@RequestMapping("/video/videoFollowInfo")
@AutoRegister
@ApiKind(value = "video-视频监控关注信息")
public class VideoFollowInfoController extends BaseController<VideoFollowInfo, IVideoFollowInfoService> {

	private final IVideoFollowInfoService videoFollowInfoService;

	/**
	 * 分页列表查询
	 *
	 * @param videoFollowInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "视频监控关注信息-分页列表查询")
	@Operation(summary = "视频监控关注信息-分页列表查询")
	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
	public Result<?> queryPageList(VideoFollowInfo videoFollowInfo,
								   @RequestParam(name="pageNo", defaultValue="1")
								   @ApiParameter(name = "pageNo", description = "当前页", required = true, demovalue = "1", defaultvalue = "1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10")
								   @ApiParameter(name = "pageSize", description = "每页条数", required = true, demovalue = "10", defaultvalue = "10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VideoFollowInfo> queryWrapper = QueryGenerator.initQueryWrapper(videoFollowInfo, req.getParameterMap());
		Page<VideoFollowInfo> page = new Page<VideoFollowInfo>(pageNo, pageSize);
		IPage<VideoFollowInfo> pageList = videoFollowInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}


	 /**
	  * 视频关注列表分页列表查询
	  *
	  * @param pageNo
	  * @param pageSize
	  * @return
	  */
	 @AutoLog(value = "视频关注列表分页列表查询")
	 @Operation(summary = "视频关注列表分页列表查询")
	 @RequestMapping(value = "/voList", method = {RequestMethod.POST})
	 public Result<?> queryVoPageList(
			 @RequestBody VideoFollowInfoDto dto,
			 @RequestParam(name = "pageNo", defaultValue = "1")
			 @ApiParameter(name = "pageNo", description = "当前页", required = true, demovalue = "1", defaultvalue = "1")
			 Integer pageNo,
			 @RequestParam(name = "pageSize", defaultValue = "10")
			 @ApiParameter(name = "pageSize", description = "每页条数", required = true, demovalue = "10", defaultvalue = "10")
			 Integer pageSize) {
		 return Result.OK(videoFollowInfoService.videoFollowInfoVoPageList(dto, pageNo, pageSize));
	 }



	/**
	 * 添加
	 *
	 * @param videoFollowInfo
	 * @return
	 */
	@AutoLog(value = "视频监控关注信息-添加")
	@Operation(summary = "视频监控关注信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VideoFollowInfo videoFollowInfo) {
		Boolean flag = videoFollowInfoService.save(videoFollowInfo);
		if(flag){
		    return Result.OK("添加成功！");
		}else{
		    return Result.OK("添加失败！");
		}

	}

	/**
	 * 编辑
	 *
	 * @param videoFollowInfo
	 * @return
	 */
	@AutoLog(value = "视频监控关注信息-编辑")
	@Operation(summary = "视频监控关注信息-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.POST})
	public Result<?> edit(@RequestBody VideoFollowInfo videoFollowInfo) {
		Boolean flag = videoFollowInfoService.updateById(videoFollowInfo);
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
    @AutoLog(value = "视频监控关注信息-通过id删除")
	@Operation(summary = "视频监控关注信息-通过id删除")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public Result<?> delete(@RequestParam(name="id",required=true)
	                        @ApiParameter(name = "id", description = "id", required = true, demovalue = "1") String id) {
		Boolean flag = videoFollowInfoService.removeById(id);
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
	@AutoLog(value = "视频监控关注信息-批量删除")
	@Operation(summary = "视频监控关注信息-批量删除")
	@RequestMapping(value = "/deleteBatch", method = {RequestMethod.POST})
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true)
	                             @ApiParameter(name = "ids", description = "ids", required = true, demovalue = "1,2") String ids) {
		Boolean flag = this.videoFollowInfoService.removeByIds(Arrays.asList(ids.split(",")));
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
	@AutoLog(value = "视频监控关注信息-通过id查询")
	@Operation(summary = "视频监控关注信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true)
	                           @ApiParameter(name = "id", description = "id", required = true, demovalue = "1") String id) {
		VideoFollowInfo videoFollowInfo = videoFollowInfoService.getById(id);
		return Result.OK(videoFollowInfo);
	}

	 /**
	  * 支持文件流的情况下直接使用该方式
	  * 文件流
	  *
	  * @param request
	  * @param response
	  * @param videoFollowInfo
	  * @param fileName
	  */
	 @AutoLog(value = "视频监控关注信息-文件流导出")
	 @Operation(summary = "视频监控关注信息-文件流导出")
	 @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
	 public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, VideoFollowInfo videoFollowInfo,
	                            @ApiParameter(name = "fileName", description = "导出文件名", required = true, demovalue = "excel") String fileName){
		 try {
			 // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
			 response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			 response.setCharacterEncoding("utf-8");
			 // URLEncoder.encode可以防止中文乱码
			 fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("视频监控关注信息"), StandardCharsets.UTF_8).replaceAll("\\+", "%20");
			 response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			 videoFollowInfoService.exportData(response.getOutputStream(), request.getParameterMap(), videoFollowInfo);
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
	  * @param videoFollowInfo
	  */
	 @AutoLog(value = "视频监控关注信息-base64方式导出")
	 @Operation(summary = "视频监控关注信息-base64方式导出")
	 @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
	 public Result<?> exportXlsToBase64(HttpServletRequest request, VideoFollowInfo videoFollowInfo) {
		 try {
			 String data = videoFollowInfoService.exportDataToBase64(request.getParameterMap(), videoFollowInfo);
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
	 @AutoLog(value = "视频监控关注信息-excel文件导入")
	 @Operation(summary = "视频监控关注信息-excel文件导入")
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestParam
	                              @ApiParameter(name = "file", description = "导入文件", required = true, demovalue = "excel.xlsx") MultipartFile file) {
		 return videoFollowInfoService.importData(file);
	 }

	 /**
	  * 下载导入模板
	  * @return
	  */
	 @Operation(summary = "视频监控关注信息-下载导入模板")
	 @RequestMapping(value = "/getImportTemplate", method = RequestMethod.GET)
	 public Result<?> getImportTemplate() {
		 try {
			 String data = videoFollowInfoService.getImportTemplate();
			 return Result.OK("导出模板成功",data);
		 } catch (Exception e) {
			 log.error("导出Excel异常{}", e.getMessage());
			 return Result.error("导出模板失败", e.getMessage());
		 }
	 }

}
