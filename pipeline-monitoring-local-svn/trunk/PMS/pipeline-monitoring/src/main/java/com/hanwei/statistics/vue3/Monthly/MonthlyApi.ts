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
        aggregationTime: '汇聚时间（如 2025-01-01 00:00:00 表示 1月起始，精确到秒）',
      deviceSerialNumber: '设备序列号',
      metricInfo: '指标信息（如：流量、压力、温度等指标编码）',
      maxValue: '最大值',
      maxValueTime: '最大值时间（精确到秒）',
      minValue: '最小值',
      minValueTime: '最小值时间（精确到秒）',
      avgValue: '平均值',
      sumValue: '和值',
      firstValue: '最初值（月度内第一条记录的值）',
      firstValueTime: '最初值时间（精确到秒）',
      lastValue: '最末值（月度内最后一条记录的值）',
      lastValueTime: '最末值时间（精确到秒）',
      recordCount: '汇聚条数（原始数据记录数量）',
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


