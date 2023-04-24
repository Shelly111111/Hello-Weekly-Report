import { Button, Form, Input, Modal, Radio } from 'antd';
import { useState,useEffect } from 'react';
import { DatePicker } from 'antd';
const AddDataForm = ({ open, onCreate, onCancel, isEdit, editData }) => {
  const [form1] = Form.useForm();
  //钩子函数，传递数据
useEffect(() => {
  if (editData) {
    if(isEdit == true){
      description.disabled = false
    }
    form.setFieldsValue(editData);
    description.value = editData.weekData;
    dangerDescription.value = editData.riskData;
    datepickerDaily.value = editData.workData;
    radioIsYesorNo.value = editData.delayData;
    // getNextWeekWork.value = editData.
    console.log(radioIsYesorNo.value);
  } else {
    form.resetFields();
  }
}, [editData]);
  const [form] = Form.useForm();
  const onFinishFailed = false;
  const onFinish = false;
  const { TextArea } = Input;

  // 点击确认提交按钮时的回调函数
const handleOk = async () => {
  // 获取表单中各个字段的值
  const date = moment(dateValue).format('YYYY-MM-DD');
  const completeWork = document.getElementById('textArea').value;
  const risk = document.getElementById('risk').value;
  const delay = document.getElementById('radioIsYesorNo').value === 'public';
  const requestBody = {
    username: localStorage.getItem('username'),
    date,
    completeWork,
    risk,
    delay,
  };
  // 发送新增请求
  const result = await reqAddNewPaper(requestBody);
  // 处理返回结果
  if (result.code === 200) {
    message.success(result.message);
    onCancel();
  } else {
    message.error(result.message);
  }
};
  return (
    <Modal className='model'
      open={open}
      title={isEdit ? "周报详情" : "新增周报"}
      okText="确认提交" 
      cancelText="取消"
      onCancel={onCancel}
      onOk={() => {
        form
          .validateFields()
          .then((values) => {
            form.resetFields();
            onCreate(values);
          })
          .catch((info) => {
            console.log('Validate Failed:', info);
          });
      }}
    >
      <Form
        name="basic"
        labelCol={{
          span: 8
        }}
        wrapperCol={{
          span: 16
        }}
        style={{
          maxWidth: 600
        }}
        initialValues={{
          remember: true
        }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"

      >
        <Form.Item
          name="周报时间"
          label="周报时间"
          rules={[
            {
              // required: true,
              message: '周报时间',
            },
          ]}
        >
          <DatePicker id = "datepickerDaily" />
        </Form.Item>
        <Form.Item name="description" label="本周完成内容">
          <>
            <TextArea rows={4} placeholder="请输入本周完成的内容" maxLength={6} id="description" />
          </>
        </Form.Item>
        <Form.Item name="modifier" label = "与计划相比是否有偏差" >
          <Radio.Group id="radioIsYesorNo">
            <Radio value="public">是</Radio>
            <Radio value="private">否</Radio>
          </Radio.Group>
        </Form.Item>
        <Form.Item name="dangerDescription" label="本周风险内容">
          <>
            <TextArea rows={4} placeholder="描述可能有风险的问题，需要帮助的内容，以及延迟的部分和预估延迟的情况" maxLength={4} id="dangerDescription" />
          </>
        </Form.Item>
        <Form.Item name="dangerDescription" label="下周计划内容">
          <>
            <TextArea rows={4} placeholder="描述下周要进行的工作计划" maxLength={4} id='getNextWeekWork' />
          </>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default AddDataForm;
