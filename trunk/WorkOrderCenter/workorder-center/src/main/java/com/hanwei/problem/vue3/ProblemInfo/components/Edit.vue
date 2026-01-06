<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../ProblemInfoApi'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '问题编号（格式：QN＋年月日＋5位序列，如 QN2025010500001）',
      field: 'problemNumber',
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
      label: '来源系统',
      field: 'sourceSystem',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '问题大类编号（支持树形结构）',
      field: 'problemCategoryId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '问题小类编号（支持树形结构）',
      field: 'problemSubCategoryId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '问题标题',
      field: 'problemTitle',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '问题内容',
      field: 'problemContent',
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
      label: '问题状态（开启／关闭）',
      field: 'status',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '是否生成工单（true-已生成，false-未生成）',
      field: 'isWorkOrderGenerated',
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
        problemNumber: [required()],
        proposerName: [required()],
        sourceSystem: [required()],
        problemCategoryId: [required()],
        problemSubCategoryId: [required()],
        problemTitle: [required()],
        problemContent: [required()],
        priority: [required()],
        status: [required()],
        isWorkOrderGenerated: [required()],
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
