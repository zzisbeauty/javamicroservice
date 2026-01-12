<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../ModelTypeMatchApi'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '监测点ID',
      field: 'monitorPointId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '监测点名称',
      field: 'monitorPointName',
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
      label: '设备型号',
      field: 'deviceModel',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '最小量程（设备量程下限）',
      field: 'minRange',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '最大量程（设备量程上限）',
      field: 'maxRange',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '30天小于最小量程占比（单位：%，如 12.50 表示 12.50%）',
      field: 'belowMinRatio30d',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '30天大于最大量程占比（单位：%，如 5.75 表示 5.75%）',
      field: 'aboveMaxRatio30d',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '30天平均流量',
      field: 'avgFlow30d',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '分析结果（如：匹配良好／量程不足／设备异常）',
      field: 'analysisResult',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '分析日期（精确到秒）',
      field: 'analysisDate',
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
        monitorPointName: [required()],
        deviceId: [required()],
        deviceModel: [required()],
        minRange: [required()],
        maxRange: [required()],
        belowMinRatio30d: [required()],
        aboveMaxRatio30d: [required()],
        avgFlow30d: [required()],
        analysisResult: [required()],
        analysisDate: [required()],
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
