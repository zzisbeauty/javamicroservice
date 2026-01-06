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
        handledAt: '处理时间（精确到秒）',
      processId: '流程ID（关联问题或工单ID）',
      type: '类型（tag：问题／工单）',
      handlerName: '处理人姓名',
      handlerCode: '处理人编号',
      operationType: '操作类型（如：创建、分配、处理、关闭、催办等）',
      statusBefore: '处理前状态（文本，如：待处理）',
      statusAfter: '处理后状态（文本，如：已解决）',
      remark: '处理备注',
      handlingDurationSeconds: '处理耗时（单位：秒）',
      nodeSequence: '节点序号（流程步骤顺序）',
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


