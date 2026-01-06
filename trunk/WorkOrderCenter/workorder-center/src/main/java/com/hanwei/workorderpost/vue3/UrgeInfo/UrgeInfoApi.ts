// api枚举
enum API {
  //字典转义
  TypeOption = 'f9a4b671060d0702',
  //导出
  Export = '',
  //获取下载模板
  GetFile = '',
  //批量删除
  MulDelete = '',
  //删除
  Delete = '',
  //导入
  FileUpload = '',
  //数据列表
  FetchData = '',
  //编辑
  Edit = '',
  //新增
  Add = '',
}

// 详情弹框需要展示数据项
export const dialogFields = {
        workOrderId: '工单ID',
      workOrderNumber: '工单编号',
      operationNodeId: '操作节点ID（可选，标识流程中哪个节点被催办）',
      urgeAt: '催办时间（精确到秒）',
      urgeContent: '催办内容（如：请尽快处理）',
      assigneeCode: '被催办人编号',
      assigneeName: '被催办人姓名',
      isRead: '是否已读（true-已读，false-未读）',
}

// get\post接口
import { commonGetData, commonPostData, dealParams } from '@/utils/requestJava'

// 获取下拉框数据接口
export const getTypeOption = () => {
  return commonGetData(API.TypeOption, {
    code: 'TODO',
    table: '',
    text: '',
    keys: ''
  }).then((res: any) => {
    const list = res.result
    const options = []
    list.forEach((item: any) => {
      options.push({
        value: item.value,
        label: item.text
      })
    })
    return options
  })
}


/**
 *
 *  Index.vue 中调用
 *
 */
// 请求数据、获取表格获取行内容
export const handleFetchData = (query) => {
  return commonPostData(API.FetchData, { ...query }, {})
}

// 批量删除
export const handleMulDelete = (query) => {
  return commonPostData(API.MulDelete, { ...query }, {})
}
// 单选删除
export const handleDelete = (query) => {
  return commonPostData(API.Delete, { ...query }, {})
}
// 发送 GET 请求获取模板文件
export const handleGetFile = () => {
  return commonGetData(API.GetFile, {})
}
// 导入上传文件
export const handleFileUpload = (params) => {
  return commonPostData(API.FileUpload, {}, { ...params }, 'multipart/form-data')
}
// 导出文件方法
export const handleExport = () => {
  return commonGetData(API.Export, {})
}

/**
 *
 *  在Edit.vue 中调用
 *
 */
// 编辑、新增提交方法
export const handleEdit = (params) => {
  return commonPostData(API.Edit, {}, { ...params })
}
export const handleAdd = (params) => {
  return commonPostData(API.Add, {}, {...params})
}


