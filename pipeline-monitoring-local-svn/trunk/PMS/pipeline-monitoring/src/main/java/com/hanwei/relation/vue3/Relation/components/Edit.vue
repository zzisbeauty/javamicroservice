<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../RelationApi'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '监测点ID（关联监测点位信息）',
      field: 'monitorPointId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '场景ID（关联场景基础信息）',
      field: 'sceneId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '数据ID（关联原始数据记录）',
      field: 'dataId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '设备ID',
      field: 'deviceId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '指标ID（如：温度、压力、流量等）',
      field: 'metricId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '设备序列号',
      field: 'deviceSerialNumber',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '状态（INT2，如：0-无效, 1-有效, 2-停用）',
      field: 'status',
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
        monitorPointId: [required()],
        sceneId: [required()],
        dataId: [required()],
        deviceId: [required()],
        metricId: [required()],
        deviceSerialNumber: [required()],
        status: [required()],
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
