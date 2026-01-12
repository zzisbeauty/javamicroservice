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
        deviceSerialNumber: '设备序列号',
      metricCode: '指标编号（如：flow_rate、pressure 等）',
      dataDescription: '数据描述（对数据的简要说明）',
      originalValue: '原始数据（修改前的数值）',
      originalCollectTime: '原始采集时间（原始数据的采集时间，精确到秒）',
      modifiedValue: '修改后数据（修正后的数值）',
      isFinalValue: '是否最终值（true-最终确认值，false-临时修正值）',
      modificationTime: '修改时间（本次修改操作的时间，精确到秒）',
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


