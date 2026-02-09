package com.hanwei.problem.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.core.util.SmartHttpUtil;
import com.hanwei.operateflow.service.IOperateFlowService;
import com.hanwei.problem.entity.ProblemInfo;
import com.hanwei.problem.service.IProblemInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

import com.hanwei.problem.entity.ProblemQueryBO;


/**
 * @Description: 问题信息管理表
 * @Author: hanwei
 * @Date:   2026-01-05
 * @Version: V1.0
 */
@Slf4j
@Tag(name="问题信息管理表")
@RestController
@RequestMapping("/problem/problemInfo")
public class ProblemInfoController extends BaseController<ProblemInfo, IProblemInfoService> {

	@Autowired
	private IProblemInfoService problemInfoService;
	@Autowired
	private IOperateFlowService operateFlowService;  // 添加这行注入


//	/**
//	 * 调用网关 附件 + 问题 ID 关联接口； 将问题 ID 和其对应的 files id 关联起来 - 方法没用
//	 *
//	 * @param dataId 问题ID
//	 * @param fileIds 文件ID字符串（逗号分隔）
//	 * @return 是否调用成功
//	 */
//	private boolean callAttachmentRelationApi(String dataId, String fileIds) {
//		try {
//			String url = "http://10.0.51.35:9080/8ae4ff5594e82a78257c1ceeaf75eb1e-TEST/";
//			// 构建请求体与发送请求
//			Map<String, String> headers = new HashMap<>();
//			headers.put("Ticket", "eyJraWQiOiI3MjRjYzc4ZC1jYTFjLTRiYWMtOGMwNi1lM2U1Y2MxMWZiMDIiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJmdXNpb25odWJ4IiwiYXVkIjoiYXBpMTU2NWY0MGU5YzRiNWRjNiIsIm5iZiI6MTc2ODIwMDY0Niwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSJdLCJpc3MiOiJodHRwOi8vMTAuMC4xNS40MDo4MDAxL2F1dGhsaW5rIiwiZGVwdElkIjoiY2I4YzlkYmItMzBjNS00MjlkLWFiNWItZjc4NzE5MDc1NWJlIiwiZXhwIjoxNzY4Mjg3MDQ2LCJpYXQiOjE3NjgyMDA2NDYsImp0aSI6Ijk2NDg1MzU2LTg5OTgtNGI0OC1iOTgzLTNhYTJmYjg2MjEwMSJ9.3JSujnueiKyjFT2s00uPdZlhZ_TaSTKxazQKKAZOm_cHZXNm7Tm3qt20eiJC1lRIymxxg470Q6L3FMwaqLucgs2EoEXqb78LOOGHeAsG8vF876E6LoxcBtoevryJoHNGlEoTokPN6VI64lbCZpPxM41Y4JNIbzpLkKKjHOMa6ci01HXbqVAoiXpUJQt-j3IjpYFMU08c8FekdxyaVxmtIIvPnQpVDkxjDgCCkZqiQ4AhHDaOjsXZbaD1TaTOLcnAeQP9a4fPiT87EzOlaAkKz5U98cFUfOPuvNSZQWL9J7qewmhxAaY6hMQTNm1Iu6wsiU2Lw_dxgla09e_gYow34w");
//			headers.put("Content-Type", "application/json");
//			Map<String, Object> requestBody = new HashMap<>();
//			requestBody.put("dataId", dataId);
//			requestBody.put("fileIds", fileIds);
//			String response = SmartHttpUtil.sendPostData(url, headers, null, JSON.toJSONString(requestBody));
//			// 处理响应
//			if (response != null) {
//				log.info("附件关联接口调用成功，问题ID: {}, 文件IDs: {}", dataId, fileIds);
//				return true;
//			} else {
//				log.error("附件关联接口调用失败，响应为空");
//				return false;
//			}
//		} catch (Exception e) {
//			log.error("调用附件关联接口异常: {}", e.getMessage(), e);
//			return false;
//		}
//	}


	@AutoLog(value = "问题信息管理表-添加")
	@Operation(summary="问题信息管理表-添加；并完成附件存储以及问题操作记录更新")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ProblemInfo problemInfo, @RequestParam(required = false) String attachIds) {
		try {
			String problemId = problemInfoService.createProblemWithAttachmentsAndOperation(problemInfo, attachIds);
			return Result.OK("添加成功问题成功", problemId);
		} catch (Exception e) {
			log.error("添加问题失败: " + e.getMessage(), e);
			return Result.error("添加失败", e.getMessage());
		}
	}
//	@AutoLog(value = "问题信息管理表-添加")
//	@Operation(summary="问题信息管理表-添加；并完成附件存储以及问题操作记录更新")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody Map<String, Object> requestData) {
//		try {
//			// 提取问题信息
//			ProblemInfo problemInfo = JSON.parseObject(JSON.toJSONString(requestData.get("problemInfo")), ProblemInfo.class);
//			// 提取附件ID字符串 - 直接转换
//			String attachIdsStr = null;
//			if (requestData.containsKey("attachIds")) {
//				Object attachIdsObj = requestData.get("attachIds");
//				attachIdsStr = attachIdsObj.toString();  // 直接转换为字符串
//				log.info("提取到的附件字符串: {}", attachIdsStr);
//			}
//			// 调用service层 完成问题新增、附件表中存储附件，同时保证附件表的问题id和问题表的id是关联的
//			String problemId = problemInfoService.createProblemWithAttachments(problemInfo, attachIdsStr);
//			// 在问题创建完成后，记录问题操作流程
//			if (problemId != null && !problemId.trim().isEmpty()) {
//				try {
//					operateFlowService.recordProblemOperation(
//							problemId,                    // 问题ID作为流程ID
//							"创建问题",                    // 操作类型
//							problemInfo.getProposerName(), // 处理人姓名
//							problemInfo.getProposerName(), // 处理人编号
//							"",                          // 处理前状态
//							problemInfo.getStatus(),     // 处理后状态
//							"问题创建成功"                // 备注
//					);
//				} catch (Exception e) {
//					log.warn("问题操作记录失败，但问题已创建成功，问题ID: {}", problemId);
//					// 不影响主流程，只记录警告
//				}
//			}
//			return Result.OK("添加成功问题成功", problemId);
//		} catch (Exception e) {
//			log.error("添加问题失败: " + e.getMessage(), e);
//			return Result.error("添加失败", e.getMessage());
//		}
//	}



	@AutoLog(value = "问题信息管理表-编辑")
	@Operation(summary="问题信息管理表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.POST})
	public Result<?> edit(@RequestBody ProblemInfo problemInfo,
						  @RequestParam(required = false) String attachIds) {
		try {
			boolean result = problemInfoService.editProblemWithAttachmentsAndOperation(
					problemInfo, attachIds);
			if (result) {
				return Result.OK("编辑成功！");
			} else {
				return Result.error("编辑失败");
			}
		} catch (Exception e) {
			log.error("编辑问题失败: " + e.getMessage(), e);
			return Result.error("编辑失败", e.getMessage());
		}
	}

//	@AutoLog(value = "问题信息管理表-编辑")
//	@Operation(summary="问题信息管理表-编辑")
//	@RequestMapping(value = "/edit", method = {RequestMethod.POST})
//	public Result<?> edit(@RequestBody Map<String, Object> requestData) {
//		try {
//			// 提取问题信息
//			ProblemInfo problemInfo = JSON.parseObject(JSON.toJSONString(requestData.get("problemInfo")), ProblemInfo.class);
//			// 提取附件ID字符串
//			String attachIdsStr = null;
//			if (requestData.containsKey("attachIds")) {
//				Object attachIdsObj = requestData.get("attachIds");
//				if (attachIdsObj != null) {
//					attachIdsStr = attachIdsObj.toString();
//				}
//			}
//			// 1. 查询原问题信息
//			ProblemInfo originalProblem = problemInfoService.getById(problemInfo.getId());
//			if (originalProblem == null) {
//				return Result.error("问题不存在");
//			}
//			// 2. 调用现有的编辑方法（保留原有逻辑）
//			boolean result = problemInfoService.editProblemWithAttachments(problemInfo, attachIdsStr);
//			if (!result) {
//				return Result.error("编辑失败");
//			}
//			// 3. 添加操作记录（新增功能）
//			try {
//				operateFlowService.recordProblemEditOperation(
//						problemInfo.getId(),
//						"编辑问题",
//						originalProblem.getCreateBy() != null ? originalProblem.getCreateBy() : "system",
//						problemInfo.getUpdateBy() != null ? problemInfo.getUpdateBy() : "system",
//						originalProblem.getStatus(),
//						problemInfo.getStatus(),
//						"编辑问题信息"
//				);
//			} catch (Exception e) {
//				log.warn("操作记录添加失败，但编辑已成功: {}", e.getMessage());
//			}
//			return Result.OK("编辑成功！");
//		} catch (Exception e) {
//			log.error("编辑问题失败: " + e.getMessage(), e);
//			return Result.error("编辑失败", e.getMessage());
//		}
//	}





	// ========================= 查询相关代码 =================================


	@AutoLog(value = "问题信息管理表-多条件查询")
	@Operation(summary="问题信息管理表-多条件查询")
	@PostMapping(value = "/queryByConditions")
	public Result<?> queryByConditions(@RequestBody ProblemQueryBO queryParams,
									   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		try {
			IPage<ProblemInfo> pageList = problemInfoService.queryProblemsByConditions(queryParams, pageNo, pageSize);
			return Result.OK(pageList);
		} catch (Exception e) {
			log.error("查询问题失败: " + e.getMessage(), e);
			return Result.error("查询失败", e.getMessage());
		}
	}
//	/**
//	 * 多条件查询问题信息（含分页）
//	 *
//	 * @param pageNo 页码
//	 * @param pageSize 页大小
//	 * @return
//	 */
//	@AutoLog(value = "问题信息管理表-多条件查询")
//	@Operation(summary="问题信息管理表-多条件查询")
//	@PostMapping(value = "/queryByConditions")
//	public Result<?> queryByConditions(@RequestBody Map<String, Object> queryParams,
//									   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//									   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
//		try {
//			// 创建ProblemInfo对象并设置查询条件
//			ProblemInfo problemInfo = new ProblemInfo();
//
//			// 从JSON body中提取查询条件并设置到实体对象
//			if (queryParams.containsKey("id")) {
//				problemInfo.setId((String) queryParams.get("id"));
//			}
//			if (queryParams.containsKey("problemNumber")) {
//				problemInfo.setProblemNumber((String) queryParams.get("problemNumber"));
//			}
//			if (queryParams.containsKey("status")) {
//				problemInfo.setStatus((String) queryParams.get("status"));
//			}
//			if (queryParams.containsKey("problemCategoryId")) {
//				problemInfo.setProblemCategoryId((String) queryParams.get("problemCategoryId"));
//			}
//			if (queryParams.containsKey("priority")) {
//				problemInfo.setPriority((String) queryParams.get("priority"));
//			}
//			if (queryParams.containsKey("proposerName")) {
//				problemInfo.setProposerName((String) queryParams.get("proposerName"));
//			}
//			// 构建查询条件
//			QueryWrapper<ProblemInfo> queryWrapper = QueryGenerator.initQueryWrapper(problemInfo, new HashMap<>());
//			// 设置分页
//			Page<ProblemInfo> page = new Page<ProblemInfo>(pageNo, pageSize);
//			IPage<ProblemInfo> pageList = problemInfoService.page(page, queryWrapper);
//			return Result.OK(pageList);
//		} catch (Exception e) {
//			log.error("查询问题失败: " + e.getMessage(), e);
//			return Result.error("查询失败", e.getMessage());
//		}
//	}



	// ======================// ======================// ====================== 删除功能


	@AutoLog(value = "问题信息管理表-通过id删除")
	@Operation(summary="问题信息管理表-通过id删除")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			Boolean flag = problemInfoService.deleteProblemWithRecord(id);
			if(flag){
				return Result.OK("删除成功！");
			}else{
				return Result.OK("删除失败！");
			}
		} catch (Exception e) {
			log.error("删除问题失败: " + e.getMessage(), e);
			return Result.error("删除失败", e.getMessage());
		}
	}


	@AutoLog(value = "问题信息管理表-批量删除")
	@Operation(summary="问题信息管理表-批量删除")
	@RequestMapping(value = "/deleteBatch", method = {RequestMethod.POST})
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		List<String> problemIds = Arrays.asList(ids.split(","));
		Boolean flag = problemInfoService.deleteProblemsWithRecord(problemIds);
		if(flag){
			return Result.OK("批量删除成功！");
		}else{
			return Result.OK("批量删除失败！");
		}
	}




	// ==================================================== 问题升级为工单  ; 完成在 工单操作记录表中同步新增该工单的第一条操作记录

	@AutoLog(value = "问题信息管理表-升级为工单")
	@Operation(summary="问题信息管理表-升级为工单")
	@PostMapping(value = "/transferToWorkOrder")
	public Result<?> transferToWorkOrder(@RequestParam String problemId) {
		try {
			String workOrderId = problemInfoService.transferToWorkOrder(problemId);
			return Result.OK("问题升级工单成功且完成操作表中的日志新增", workOrderId);
		} catch (Exception e) {
			log.error("问题升级工单失败: {}", e.getMessage(), e);
			return Result.error("升级失败", e.getMessage());
		}
	}



	@AutoLog(value = "问题信息管理表-根据提出人查询")
	@Operation(summary="问题信息管理表-根据提出人查询")
	@GetMapping(value = "/getByProposer")
	public Result<?> getProblemsByProposer(@RequestParam String proposerName,
										   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
										   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		try {
			IPage<ProblemInfo> pageList = problemInfoService.getProblemsByProposer(proposerName, pageNo, pageSize);
			return Result.OK(pageList);
		} catch (Exception e) {
			log.error("查询问题失败: " + e.getMessage(), e);
			return Result.error("查询失败", e.getMessage());
		}
	}


	@AutoLog(value = "问题信息管理表-我的问题CSV导出")
	@Operation(summary="问题信息管理表-我的问题CSV导出")
	@GetMapping(value = "/exportMyProblemsCsv")
	public void exportMyProblemsCsv(@RequestParam String proposerName, HttpServletResponse response) {
		try {
			response.setContentType("application/csv");
			response.setCharacterEncoding("utf-8");
			String fileName = URLEncoder.encode("我的问题", "UTF-8").replaceAll("\\+", "%20");
			response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".csv");
			problemInfoService.exportProblemsByProposerToCsv(proposerName, response.getOutputStream());
		} catch (IOException e) {
			log.error("导出我的问题CSV失败: " + e.getMessage(), e);
		}
	}

	// ===========================// ===========================// ===========================




	 /**
	  * 支持文件流的情况下直接使用该方式
	  * 文件流
	  *
	  * @param request
	  * @param response
	  * @param problemInfo
	  * @param fileName
	  */
	 @AutoLog(value = "问题信息管理表-文件流导出")
	 @Operation(summary="问题信息管理表-文件流导出")
	 @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
	 public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, ProblemInfo problemInfo, String fileName){
		 try {
			 // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
			 response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			 response.setCharacterEncoding("utf-8");
			 // URLEncoder.encode可以防止中文乱码
			 fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("问题信息管理表"), "UTF-8").replaceAll("\\+", "%20");
			 response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			 problemInfoService.exportData(response.getOutputStream(), request.getParameterMap(), problemInfo);
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
	  * @param problemInfo
	  */
	 @AutoLog(value = "问题信息管理表-base64方式导出")
	 @Operation(summary="问题信息管理表-base64方式导出")
	 @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
	 public Result<?> exportXlsToBase64(HttpServletRequest request, ProblemInfo problemInfo) {
		 try {
			 String data = problemInfoService.exportDataToBase64(request.getParameterMap(), problemInfo);
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
	 @AutoLog(value = "问题信息管理表-excel文件导入")
	 @Operation(summary="问题信息管理表-excel文件导入")
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestParam MultipartFile file) {
		 return problemInfoService.importData(file);
	 }

	 /**
	  * 下载导入模板
	  * @return
	  */
	 @Operation(summary="问题信息管理表-下载导入模板")
	 @RequestMapping(value = "/getImportTemplate", method = RequestMethod.GET)
	 public Result<?> getImportTemplate() {
		 try {
			 String data = problemInfoService.getImportTemplate();
			 return Result.OK("导出模板成功",data);
		 } catch (Exception e) {
			 log.error("导出Excel异常{}", e.getMessage());
			 return Result.error("导出模板失败", e.getMessage());
		 }
	 }

}
