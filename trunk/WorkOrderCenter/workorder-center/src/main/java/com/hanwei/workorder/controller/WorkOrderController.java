package com.hanwei.workorder.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanwei.core.annotation.AutoLog;
import com.hanwei.core.base.BaseController;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.operateflow.service.IOperateFlowService;
import com.hanwei.workorder.entity.WorkOrder;
import com.hanwei.workorder.service.IWorkOrderService;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @Description: 工单信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Slf4j
@Tag(name="工单信息表")
@RestController
@RequestMapping("/workOrder")
public class WorkOrderController extends BaseController<WorkOrder, IWorkOrderService> {
	@Autowired
	private IWorkOrderService workOrderService;



	@AutoLog(value = "工单信息表-新增")
	@Operation(summary="工单信息表-新增")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody WorkOrder workOrder,
						 @RequestParam(required = false) String attachIds,
						 @RequestParam(required = false) String creatorCode,
						 @RequestParam(required = false) String creatorName) {
		try {
			// 调用服务层方法
			String workOrderId = workOrderService.createWorkOrderWithAttachments(
					workOrder, attachIds, creatorCode, creatorName);

			return Result.OK("添加成功！", workOrderId);

		} catch (Exception e) {
			log.error("添加工单失败: " + e.getMessage(), e);
			return Result.error("添加失败", e.getMessage());
		}
	}
//	/** 新增工单（含流转操作） */
//	@AutoLog(value = "工单信息表-新增")
//	@Operation(summary="工单信息表-新增")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody Map<String, Object> requestData) {
//		try {
//			// 提取工单信息
//			WorkOrder workOrder = JSON.parseObject(JSON.toJSONString(requestData.get("workOrder")), WorkOrder.class);
//
//			// 提取附件ID字符串
//			String attachIdsStr = null;
//			if (requestData.containsKey("attachIds")) {
//				Object attachIdsObj = requestData.get("attachIds");
//				attachIdsStr = attachIdsObj != null ? attachIdsObj.toString() : null;
//			}
//
//			// 提取创建人信息
//			String creatorCode = (String) requestData.get("creatorCode");
//			String creatorName = (String) requestData.get("creatorName");
//
//			// 调用服务层方法
//			String workOrderId = workOrderService.createWorkOrderWithAttachments(
//					workOrder, attachIdsStr, creatorCode, creatorName);
//
//			return Result.OK("添加成功！", workOrderId);
//
//		} catch (Exception e) {
//			log.error("添加工单失败: " + e.getMessage(), e);
//			return Result.error("添加失败", e.getMessage());
//		}
//	}


	@AutoLog(value = "工单信息表-派送")
	@Operation(summary="工单信息表-派送")
	@PostMapping(value = "/assign")
	public Result<?> assign(@RequestParam String workOrderId,
							@RequestParam String assigneeCode,
							@RequestParam String assigneeName) {
		try {
			boolean result = workOrderService.assignWorkOrder(workOrderId, assigneeCode, assigneeName);
			if (result) {
				return Result.OK("派送成功！");
			} else {
				return Result.error("派送失败！");
			}
		} catch (Exception e) {
			log.error("派送工单失败: " + e.getMessage(), e);
			return Result.error("派送失败", e.getMessage());
		}
	}
//	/** 工单派送 */
//	@AutoLog(value = "工单信息表-派送")
//	@Operation(summary="工单信息表-派送")
//	@PostMapping(value = "/assign")
//	public Result<?> assign(@RequestBody Map<String, Object> requestData) {
//		try {
//			String workOrderId = (String) requestData.get("workOrderId");
//			String assigneeCode = (String) requestData.get("assigneeCode");
//			String assigneeName = (String) requestData.get("assigneeName");
//			boolean result = workOrderService.assignWorkOrder(workOrderId, assigneeCode, assigneeName);
//			if (result) {
//				return Result.OK("派送成功！");
//			} else {
//				return Result.error("派送失败！");
//			}
//		} catch (Exception e) {
//			log.error("派送工单失败: " + e.getMessage(), e);
//			return Result.error("派送失败", e.getMessage());
//		}
//	}


	@AutoLog(value = "工单信息表-抄送")
	@Operation(summary="工单信息表-抄送")
	@PostMapping(value = "/copy")
	public Result<?> copy(@RequestParam String workOrderId,
						  @RequestParam List<String> copyToCodes,
						  @RequestParam List<String> copyToNames) {
		try {
			boolean result = workOrderService.copyWorkOrder(workOrderId, copyToCodes, copyToNames);
			if (result) {
				return Result.OK("抄送成功！");
			} else {
				return Result.error("抄送失败！");
			}

		} catch (Exception e) {
			log.error("抄送工单失败: " + e.getMessage(), e);
			return Result.error("抄送失败", e.getMessage());
		}
	}
//	/** 工单抄送 */
//	@AutoLog(value = "工单信息表-抄送")
//	@Operation(summary="工单信息表-抄送")
//	@PostMapping(value = "/copy")
//	public Result<?> copy(@RequestBody Map<String, Object> requestData) {
//		try {
//			String workOrderId = (String) requestData.get("workOrderId");
//			@SuppressWarnings("unchecked")
//			List<String> copyToCodes = (List<String>) requestData.get("copyToCodes");
//			@SuppressWarnings("unchecked")
//			List<String> copyToNames = (List<String>) requestData.get("copyToNames");
//			boolean result = workOrderService.copyWorkOrder(workOrderId, copyToCodes, copyToNames);
//			if (result) {
//				return Result.OK("抄送成功！");
//			} else {
//				return Result.error("抄送失败！");
//			}
//		} catch (Exception e) {
//			log.error("抄送工单失败: " + e.getMessage(), e);
//			return Result.error("抄送失败", e.getMessage());
//		}
//	}




	// ==============================================================

	@AutoLog(value = "工单信息表-编辑")
	@Operation(summary="工单信息表-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.POST})
	public Result<?> edit(@RequestBody WorkOrder workOrder) {
		try {
			// 从session或其他地方获取编辑人信息，这里使用默认值
			String editorCode = "system";
			String editorName = "系统";
			boolean result = workOrderService.editWorkOrderWithRecord(workOrder, editorCode, editorName);
			if (result) {
				return Result.OK("编辑成功！");
			} else {
				return Result.error("编辑失败！");
			}
		} catch (Exception e) {
			log.error("编辑工单失败: " + e.getMessage(), e);
			return Result.error("编辑失败", e.getMessage());
		}
	}





	 // =================// =================// =================// =================// =================



	/**
	 * 分页列表查询
	 *
	 * @param workOrder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "工单信息表-分页列表查询")
	@Operation(summary="工单信息表-分页列表查询")
	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
	public Result<?> queryPageList(WorkOrder workOrder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WorkOrder> queryWrapper = QueryGenerator.initQueryWrapper(workOrder, req.getParameterMap());
		Page<WorkOrder> page = new Page<WorkOrder>(pageNo, pageSize);
		IPage<WorkOrder> pageList = workOrderService.page(page, queryWrapper);
		return Result.OK(pageList);
	}


	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
    @AutoLog(value = "工单信息表-通过id删除")
	@Operation(summary="工单信息表-通过id删除")
	@RequestMapping(value = "/delete", method = {RequestMethod.POST})
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		Boolean flag = workOrderService.removeById(id);
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
	@AutoLog(value = "工单信息表-批量删除")
	@Operation(summary="工单信息表-批量删除")
	@RequestMapping(value = "/deleteBatch", method = {RequestMethod.POST})
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Boolean flag = this.workOrderService.removeByIds(Arrays.asList(ids.split(",")));
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
	@AutoLog(value = "工单信息表-通过id查询")
	@Operation(summary="工单信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		WorkOrder workOrder = workOrderService.getById(id);
		return Result.OK(workOrder);
	}


	@AutoLog(value = "工单信息表-催办")
	@Operation(summary="工单信息表-催办")
	@PostMapping(value = "/urge")
	public Result<?> urge(@RequestParam String workOrderId, @RequestParam String urgerCode,
						  @RequestParam String urgerName, @RequestParam String urgeContent) {
		try {
			boolean result = workOrderService.urgeWorkOrder(workOrderId, urgerCode, urgerName, urgeContent);
			if (result) {
				return Result.OK("催办成功！");
			} else {
				return Result.error("催办失败！");
			}
		} catch (Exception e) {
			log.error("催办工单失败: " + e.getMessage(), e);
			return Result.error("催办失败", e.getMessage());
		}
	}
//	@AutoLog(value = "工单信息表-催办")
//	@Operation(summary="工单信息表-催办")
//	@PostMapping(value = "/urge")
//	public Result<?> urge(@RequestBody Map<String, Object> requestData) {
//		try {
//			String workOrderId = (String) requestData.get("workOrderId");
//			String urgerCode = (String) requestData.get("urgerCode");
//			String urgerName = (String) requestData.get("urgerName");
//			String urgeContent = (String) requestData.get("urgeContent");
//			boolean result = workOrderService.urgeWorkOrder(workOrderId, urgerCode, urgerName, urgeContent);
//			if (result) {
//				return Result.OK("催办成功！");
//			} else {
//				return Result.error("催办失败！");
//			}
//		} catch (Exception e) {
//			log.error("催办工单失败: " + e.getMessage(), e);
//			return Result.error("催办失败", e.getMessage());
//		}
//	}


	@AutoLog(value = "工单信息表-我的工单")
	@Operation(summary="工单信息表-我的工单")
	@GetMapping(value = "/myWorkOrders")
	public Result<?> getMyWorkOrders(
			@RequestParam(name="userCode") String userCode,
			@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
			@RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
		try {
			IPage<WorkOrder> pageList = workOrderService.getMyWorkOrders(userCode, pageNo, pageSize);
			return Result.OK(pageList);
		} catch (Exception e) {
			log.error("查询我的工单失败: " + e.getMessage(), e);
			return Result.error("查询失败", e.getMessage());
		}
	}



	 /**
	  * 支持文件流的情况下直接使用该方式
	  * 文件流
	  *
	  * @param request
	  * @param response
	  * @param workOrder
	  * @param fileName
	  */
	 @AutoLog(value = "工单信息表-文件流导出")
	 @Operation(summary="工单信息表-文件流导出")
	 @RequestMapping(value = "/exportXls", method = {RequestMethod.GET})
	 public Result<?> exportXls(HttpServletRequest request, HttpServletResponse response, WorkOrder workOrder, String fileName){
		 try {
			 // 这里注意 使用swagger 会导致各种问题，请直接用浏览器或者用postman
			 response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			 response.setCharacterEncoding("utf-8");
			 // URLEncoder.encode可以防止中文乱码
			 fileName = URLEncoder.encode(Optional.ofNullable(fileName).orElse("工单信息表"), "UTF-8").replaceAll("\\+", "%20");
			 response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			 workOrderService.exportData(response.getOutputStream(), request.getParameterMap(), workOrder);
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
	  * @param workOrder
	  */
	 @AutoLog(value = "工单信息表-base64方式导出")
	 @Operation(summary="工单信息表-base64方式导出")
	 @RequestMapping(value = "/exportXlsToBase64", method = {RequestMethod.GET})
	 public Result<?> exportXlsToBase64(HttpServletRequest request, WorkOrder workOrder) {
		 try {
			 String data = workOrderService.exportDataToBase64(request.getParameterMap(), workOrder);
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
	 @AutoLog(value = "工单信息表-excel文件导入")
	 @Operation(summary="工单信息表-excel文件导入")
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestParam MultipartFile file) {
		 return workOrderService.importData(file);
	 }

	 /**
	  * 下载导入模板
	  * @return
	  */
	 @Operation(summary="工单信息表-下载导入模板")
	 @RequestMapping(value = "/getImportTemplate", method = RequestMethod.GET)
	 public Result<?> getImportTemplate() {
		 try {
			 String data = workOrderService.getImportTemplate();
			 return Result.OK("导出模板成功",data);
		 } catch (Exception e) {
			 log.error("导出Excel异常{}", e.getMessage());
			 return Result.error("导出模板失败", e.getMessage());
		 }
	 }



	@AutoLog(value = "工单信息表-导出我的工单CSV")
	@Operation(summary="工单信息表-导出我的工单CSV")
	@GetMapping(value = "/exportMyWorkOrdersCsv")
	public void exportMyWorkOrdersCsv(@RequestParam String userCode, HttpServletResponse response) {
		try {
			response.setContentType("application/csv");
			response.setCharacterEncoding("utf-8");
			String fileName = URLEncoder.encode("我的工单", "UTF-8").replaceAll("\\+", "%20");
			response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".csv");
			workOrderService.exportMyWorkOrdersToCsv(userCode, response.getOutputStream());
		} catch (Exception e) {
			log.error("导出我的工单CSV失败: " + e.getMessage(), e);
		}
	}


}
