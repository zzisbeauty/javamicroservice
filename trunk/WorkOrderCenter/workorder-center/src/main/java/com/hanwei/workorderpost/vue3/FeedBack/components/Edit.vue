<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../FeedBackApi'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '工单ID',
      field: 'workOrderId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '工单编号',
      field: 'workOrderNumber',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '回访方式（如：电话、短信、上门等）',
      field: 'feedbackMethod',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '满意度（如：非常满意/满意/一般/不满意，或 5星制）',
      field: 'satisfactionLevel',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '不满意原因',
      field: 'dissatisfactionReason',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '备注',
      field: 'remark',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '回访时间（精确到秒）',
      field: 'feedbackAt',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '回访人编号',
      field: 'feedbackByCode',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '回访人姓名',
      field: 'feedbackByName',
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
        workOrderId: [required()],
        workOrderNumber: [required()],
        feedbackMethod: [required()],
        satisfactionLevel: [required()],
        dissatisfactionReason: [required()],
        remark: [required()],
        feedbackAt: [required()],
        feedbackByCode: [required()],
        feedbackByName: [required()],
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
