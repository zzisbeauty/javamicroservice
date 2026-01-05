<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../SimInfoApi'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '注册信息：物理卡号(ICCID)',
      field: 'iccid',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '注册信息：卡号/手机号',
      field: 'msisdn',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '注册信息：运营商',
      field: 'operator',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '注册信息：套餐名称',
      field: 'planName',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '状态更新：active-在用, inactive-停机, scrapped-报废',
      field: 'status',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '临期报警：套餐到期时间',
      field: 'expiryDate',
      component: 'DatePicker',
      colProps: {
        span: 24
      }
    },
   {
      label: 'activeTime',
      field: 'activeTime',
      component: 'DatePicker',
      colProps: {
        span: 24
      }
    },
   {
      label: '删除记录：逻辑删除标记(0-正常, 1-已删除)',
      field: 'isDeleted',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: 'remark',
      field: 'remark',
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
        iccid: [required()],
        msisdn: [required()],
        operator: [required()],
        planName: [required()],
        status: [required()],
        expiryDate: [required()],
        activeTime: [required()],
        isDeleted: [required()],
        remark: [required()],
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
