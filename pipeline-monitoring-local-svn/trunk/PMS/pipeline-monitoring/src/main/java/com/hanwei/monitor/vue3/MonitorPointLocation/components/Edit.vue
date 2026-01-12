<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../MonitorPointLocationApi'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '编号（点位唯一业务编号）',
      field: 'code',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '厂家名称',
      field: 'manufacturerName',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '部门编码',
      field: 'departmentCode',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '状态（INT2，如：0-停用, 1-启用）',
      field: 'status',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '类型（INT2，如：1-视频, 2-传感器, 3-环境监测）',
      field: 'type',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '经度（WGS84坐标系，数值类型）',
      field: 'longitude',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '纬度（WGS84坐标系，数值类型）',
      field: 'latitude',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '图片（点位示意图或现场照片URL）',
      field: 'imageUrl',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '详细地址',
      field: 'addressDetail',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '区域编码（如行政区划代码）',
      field: 'areaCode',
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
        code: [required()],
        manufacturerName: [required()],
        departmentCode: [required()],
        status: [required()],
        type: [required()],
        longitude: [required()],
        latitude: [required()],
        imageUrl: [required()],
        addressDetail: [required()],
        areaCode: [required()],
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
