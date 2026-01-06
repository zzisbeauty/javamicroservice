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
        workOrderNumber: '工单编号（格式：TN＋年月日＋5位序列，如 TN2025010500001）',
      parentWorkOrderId: '父工单ID（支持工单拆分）',
      proposerName: '提出人名称',
      workOrderType: '工单类型（内部／委外）',
      sourceSystem: '来源系统',
      workOrderCategoryId: '工单大类编号（支持树形结构）',
      workOrderSubCategoryId: '工单小类编号（支持树形结构）',
      workOrderTitle: '工单标题',
      workOrderContent: '工单内容',
      deviceSerialNumber: '设备序列号（冗余字段，便于快速查询）',
      priority: '优先级（紧急／正常）',
      status: '工单状态（待定／处理中／挂起／已解决／关闭）',
      isFollowUp: '是否回访（true-需回访，false-无需回访）',
      currentHandler: '当前处理人',
      currentDepartment: '当前处理部门',
      workOrderCost: '工单开销（单位：元）',
      resolvedAt: '解决时间',
      closedAt: '关闭时间',
      dueAt: '到期时间',
      longitude: '经度',
      latitude: '纬度',
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


