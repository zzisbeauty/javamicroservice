<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../DataModificationRecord2Api'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '设备序列号',
      field: 'deviceSerialNumber',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '指标编号（如：flow_rate、pressure 等）',
      field: 'metricCode',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '数据描述（对数据的简要说明）',
      field: 'dataDescription',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '原始数据（修改前的数值）',
      field: 'originalValue',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '原始采集时间（原始数据的采集时间，精确到秒）',
      field: 'originalCollectTime',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '修改后数据（修正后的数值）',
      field: 'modifiedValue',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '是否最终值（true-最终确认值，false-临时修正值）',
      field: 'isFinalValue',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '修改时间（本次修改操作的时间，精确到秒）',
      field: 'modificationTime',
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
        deviceSerialNumber: [required()],
        metricCode: [required()],
        dataDescription: [required()],
        originalValue: [required()],
        originalCollectTime: [required()],
        modifiedValue: [required()],
        isFinalValue: [required()],
        modificationTime: [required()],
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
