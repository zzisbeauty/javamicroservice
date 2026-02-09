package com.hanwei.problem.service;

import com.hanwei.problem.entity.ProblemInfo;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.List;  // 添加这个导入
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hanwei.problem.entity.ProblemQueryBO;

/**
 * @Description: 问题信息管理表
 * @Author: hanwei
 * @Date:   2026-01-05
 * @Version: V1.0
 */
public interface IProblemInfoService extends IService<ProblemInfo> {

    /**
     * 创建问题并处理附件
     *
     * @param problemInfo 问题信息
     * @param attachIdsStr 附件ID字符串（逗号分隔）
     * @return 问题ID
     */
    String createProblemWithAttachments(ProblemInfo problemInfo, String attachIdsStr);


    /**
     * 编辑问题信息（含附件追加和操作记录）
     *
     * @param problemInfo 问题信息
     * @param attachIdsStr 新增附件ID字符串（逗号分隔）
     * @return 是否编辑成功
     */
    boolean editProblemWithAttachments(ProblemInfo problemInfo, String attachIdsStr);

    /**
     * 删除问题（含操作记录）
     *
     * @param problemId 问题ID
     * @return 是否删除成功
     */
    boolean deleteProblemWithRecord(String problemId);


    /**
     * 批量删除问题（含操作记录）
     *
     * @param problemIds 问题ID列表
     * @return 是否删除成功
     */
    boolean deleteProblemsWithRecord(List<String> problemIds);









    // ================================== 问题转工单
    /**
     * 问题升级为工单
     *
     * @param problemId 问题ID
     * @return 工单ID
     */
    String transferToWorkOrder(String problemId);



    /**
     * 根据提出人查询问题列表
     *
     * @param proposerName 提出人名称
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return 问题列表
     */
    IPage<ProblemInfo> getProblemsByProposer(String proposerName, Integer pageNo, Integer pageSize);


    /**
     * 根据提出人导出问题为CSV
     *
     * @param proposerName 提出人名称
     * @param outputStream 输出流
     */
    void exportProblemsByProposerToCsv(String proposerName, ServletOutputStream outputStream);



    /**
     * 创建问题并处理附件和操作记录
     *
     * @param problemInfo 问题信息
     * @param attachIds 附件ID字符串
     * @return 问题ID
     */
    String createProblemWithAttachmentsAndOperation(ProblemInfo problemInfo, String attachIds);

    /**
     * 编辑问题（含附件和操作记录）
     *
     * @param problemInfo 问题信息
     * @param attachIds 附件ID字符串
     * @return 是否编辑成功
     */
    boolean editProblemWithAttachmentsAndOperation(ProblemInfo problemInfo, String attachIds);


//    /**
//     * 多条件查询问题信息（含分页）
//     *
//     * @param queryParams 查询参数Map
//     * @param pageNo 页码
//     * @param pageSize 页大小
//     * @return 分页结果
//     */
//    IPage<ProblemInfo> queryProblemsByConditions(Map<String, Object> queryParams, Integer pageNo, Integer pageSize);

    /**
     * 根据条件查询问题列表
     *
     * @param queryParams 查询参数
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return 问题列表
     */
    IPage<ProblemInfo> queryProblemsByConditions(ProblemQueryBO queryParams, Integer pageNo, Integer pageSize);
    // ========================================================================

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param problemInfo
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, ProblemInfo problemInfo);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param problemInfo
     * @return String
     */
    String exportDataToBase64(Map paramMap, ProblemInfo problemInfo);

    /**
     * 通过excel导入数据
     *
     * @param file
     * @return
     */
    Result<?> importData(MultipartFile file);

    /**
     * 下载导入模板
     * @return
     */
    String getImportTemplate();
}
