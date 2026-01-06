<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../WorkOrderApi'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '工单编号（格式：TN＋年月日＋5位序列，如 TN2025010500001）',
      field: 'workOrderNumber',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '父工单ID（支持工单拆分）',
      field: 'parentWorkOrderId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '提出人名称',
      field: 'proposerName',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '工单类型（内部／委外）',
      field: 'workOrderType',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '来源系统',
      field: 'sourceSystem',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '工单大类编号（支持树形结构）',
      field: 'workOrderCategoryId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '工单小类编号（支持树形结构）',
      field: 'workOrderSubCategoryId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '工单标题',
      field: 'workOrderTitle',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '工单内容',
      field: 'workOrderContent',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '设备序列号（冗余字段，便于快速查询）',
      field: 'deviceSerialNumber',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '优先级（紧急／正常）',
      field: 'priority',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '工单状态（待定／处理中／挂起／已解决／关闭）',
      field: 'status',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '是否回访（true-需回访，false-无需回访）',
      field: 'isFollowUp',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '当前处理人',
      field: 'currentHandler',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '当前处理部门',
      field: 'currentDepartment',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '工单开销（单位：元）',
      field: 'workOrderCost',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '解决时间',
      field: 'resolvedAt',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '关闭时间',
      field: 'closedAt',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '到期时间',
      field: 'dueAt',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '经度',
      field: 'longitude',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '纬度',
      field: 'latitude',
      component: 'Input',
      colProps: {
        span: 24
      }
    },

  //  {
  //    component: 'Input',
  //    field: 'address',
  //    label: '地址',
  //    colProps: {
  //      span: 24
  //    },
  //   componentProps: {
  //      slots: {
  //        append: <LocateBtn onClick={getLocation}></LocateBtn>
  //      }
  //    }
  //  },
])

// 配置表单验证，可调用useValidator正则库方便各种情况验证
const rules = reactive({
        workOrderNumber: [required()],
        parentWorkOrderId: [required()],
        proposerName: [required()],
        workOrderType: [required()],
        sourceSystem: [required()],
        workOrderCategoryId: [required()],
        workOrderSubCategoryId: [required()],
        workOrderTitle: [required()],
        workOrderContent: [required()],
        deviceSerialNumber: [required()],
        priority: [required()],
        status: [required()],
        isFollowUp: [required()],
        currentHandler: [required()],
        currentDepartment: [required()],
        workOrderCost: [required()],
        resolvedAt: [required()],
        closedAt: [required()],
        dueAt: [required()],
        longitude: [required()],
        latitude: [required()],
})

const { formRegister, formMethods } = useForm()
const { setValues, getFormData, getElFormExpose } = formMethods

const isEdit = ref(false)

/**
 * 新增、编辑提交事件
 * 先进行表单验证，验证通过则进行编辑或新增提交
 */
const submit = async () => {
  const elForm = await getElFormExpose()
  const valid = await elForm?.validate().catch((err) => {
    console.log(err)
  })
  if (valid) {
    const formData = await getFormData()
    console.log(formData)
    if (isEdit.value) {
      // 编辑数据
      const res: any = await handleEdit(formData)
      console.log(res)
      if (res.success) {
        return res.message
      } else {
        return false
      }
    } else {
      // 新增数据
      const res: any = await handleAdd(formData)
      if (res.success) {
        return res.message
      } else {
        return false
      }
    }
  }
}

const isDisable = ref(false)

const feedBackData = (row) => {
  isEdit.value = true
  const parm = {}
  Object.keys(row).forEach((key: any) => {
    parm[key] = row[key]
  })
  setValues(parm)
}
defineExpose({
  submit,
  feedBackData
})
</script>

<template>
  <div>
    <Form
      require-asterisk-position="right"
      :disabled="isDisable"
      :rules="rules"
      @register="formRegister"
      :schema="dialogSchemas"
    />
  </div>
</template>
