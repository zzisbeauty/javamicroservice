<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../OperateFlowApi'
const { required, isMobileOrNull } = useValidator()

const dialogSchemas = reactive<FormSchema[]>([
   {
      label: '处理时间（精确到秒）',
      field: 'handledAt',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '流程ID（关联问题或工单ID）',
      field: 'processId',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '类型（tag：问题／工单）',
      field: 'type',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '处理人姓名',
      field: 'handlerName',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '处理人编号',
      field: 'handlerCode',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '操作类型（如：创建、分配、处理、关闭、催办等）',
      field: 'operationType',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '处理前状态（文本，如：待处理）',
      field: 'statusBefore',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '处理后状态（文本，如：已解决）',
      field: 'statusAfter',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '处理备注',
      field: 'remark',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '处理耗时（单位：秒）',
      field: 'handlingDurationSeconds',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '节点序号（流程步骤顺序）',
      field: 'nodeSequence',
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
        handledAt: [required()],
        processId: [required()],
        type: [required()],
        handlerName: [required()],
        handlerCode: [required()],
        operationType: [required()],
        statusBefore: [required()],
        statusAfter: [required()],
        remark: [required()],
        handlingDurationSeconds: [required()],
        nodeSequence: [required()],
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
