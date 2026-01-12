<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../QuarterlyApi'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '汇聚时间（如 2025-01-01 00:00:00 表示 Q1 起始，精确到秒）',
      field: 'aggregationTime',
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
      label: '指标信息（如：流量、压力、温度等指标编码）',
      field: 'metricInfo',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '最大值',
      field: 'maxValue',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '最大值时间（精确到秒）',
      field: 'maxValueTime',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '最小值',
      field: 'minValue',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '最小值时间（精确到秒）',
      field: 'minValueTime',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '平均值',
      field: 'avgValue',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '和值',
      field: 'sumValue',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '最初值（季度内第一条记录的值）',
      field: 'firstValue',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '最初值时间（精确到秒）',
      field: 'firstValueTime',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '最末值（季度内最后一条记录的值）',
      field: 'lastValue',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '最末值时间（精确到秒）',
      field: 'lastValueTime',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '汇聚条数（原始数据记录数量）',
      field: 'recordCount',
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
        aggregationTime: [required()],
        deviceSerialNumber: [required()],
        metricInfo: [required()],
        maxValue: [required()],
        maxValueTime: [required()],
        minValue: [required()],
        minValueTime: [required()],
        avgValue: [required()],
        sumValue: [required()],
        firstValue: [required()],
        firstValueTime: [required()],
        lastValue: [required()],
        lastValueTime: [required()],
        recordCount: [required()],
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
