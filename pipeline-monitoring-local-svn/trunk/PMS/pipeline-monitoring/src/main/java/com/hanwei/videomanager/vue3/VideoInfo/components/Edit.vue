<script setup lang="tsx">
import { Form } from '@/components/Form'
import { useForm } from '@/hooks/web/useForm'
import { reactive, ref } from 'vue'
import { useValidator } from '@/hooks/web/useValidator'
import { FormSchema } from '@/components/Form'
import { commonGetData, commonPostData } from '@/utils/requestJava'
import { GetLocation } from '@/components/GetLocation'
import { LocateBtn } from '@/components/LocateBtn'
import { handleEdit, handleAdd } from '../VideoInfoApi'
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
      label: '相机编号',
      field: 'cameraCode',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '相机类型（如：枪机、球机、全景等）',
      field: 'cameraType',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '视频流类型（如：RTMP、HLS、FLV等）',
      field: 'videoStreamType',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '推流地址（完整URL）',
      field: 'pushStreamUrl',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '经度（WGS84坐标系）',
      field: 'longitude',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '纬度（WGS84坐标系）',
      field: 'latitude',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '安装位置（如：A栋3楼走廊）',
      field: 'installLocation',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '海拔高度（单位：米，精确到厘米）',
      field: 'altitudeMeters',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '像素（如：1920x1080 可存为总像素数 2073600，或前端解析）',
      field: 'resolutionPixels',
      component: 'Input',
      colProps: {
        span: 24
      }
    },
   {
      label: '状态（如：在线／离线／维护中）',
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
        cameraCode: [required()],
        cameraType: [required()],
        videoStreamType: [required()],
        pushStreamUrl: [required()],
        longitude: [required()],
        latitude: [required()],
        installLocation: [required()],
        altitudeMeters: [required()],
        resolutionPixels: [required()],
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
