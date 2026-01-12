<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../MonitorPointAuthorityApi'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '点位ID',
      field: 'pointId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '责权类型（INT2，如：1-管理, 2-维护, 3-监管）',
      field: 'authorityType',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '单位（责任单位名称）',
      field: 'unit',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '职位（如：负责人、技术员）',
      field: 'position',
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
      label: '电话（联系电话）',
      field: 'phone',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '排序（数值越小越靠前）',
      field: 'sortOrder',
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
        pointId: [required()],
        authorityType: [required()],
        unit: [required()],
        position: [required()],
        manufacturerName: [required()],
        phone: [required()],
        sortOrder: [required()],
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
