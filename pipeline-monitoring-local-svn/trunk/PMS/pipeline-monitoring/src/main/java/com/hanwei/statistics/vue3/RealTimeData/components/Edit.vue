<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../RealTimeDataApi'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '数据ID（业务唯一标识，如传感器数据编号）',
      field: 'dataId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '采集时间（设备实际采集数据的时间，精确到秒）',
      field: 'collectTime',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '上传时间（数据上传至服务器的时间，精确到秒）',
      field: 'uploadTime',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '数值（如：123.456）',
      field: 'value',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '单位（如：m³/h、kPa、℃、% 等）',
      field: 'unit',
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
        dataId: [required()],
        collectTime: [required()],
        uploadTime: [required()],
        value: [required()],
        unit: [required()],
        dataDescription: [required()],
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
