import { Button, Form, Input, Modal, Radio } from 'antd';
import { useState, useEffect } from 'react';
import { DatePicker } from 'antd';
import "./addForm.css"
import { reqAddNewPaper } from '@/api';
const AddForm = ({ open, onCreate, onCancel, isEdit, editData }) => {
  const [form] = Form.useForm();

//钩子函数，传递数据
useEffect(() => {
    if (editData) {
      if(isEdit == true){
        description.disabled = false
      }
      form.setFieldsValue(editData);
      description.value = editData.work;
      dangerDescription.value = editData.risk;
      datepickerDaily.value = editData.dailyDate;
      radioIsYesorNo.value = editData.delay;
      console.log(radioIsYesorNo.value);
    } else {
      form.resetFields();
    }
  }, [editData]);

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
    <Modal
      className='model'
      open={open}
      title={isEdit ? "日报详情" : "新增日报"}
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
         initialValues={{
          

         }}
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
        autoComplete="off"

      >
        <Form.Item
          name="日报时间"
          label="日报时间"
          rules={[
            {
              required: true,
              message: '请选择时间',
            },
          ]}
        >
        
          <DatePicker  id='datepickerDaily' 
          placeholder={['请选择时间']}/>
        </Form.Item>
        <Form.Item  label="完成内容描述">
          <>
            <TextArea rows={4} placeholder="请输入本日完成的内容" maxLength={6}  id="description"/>
          </>
        </Form.Item>
        <Form.Item name="modifier" label="与计划相比是否有偏差" >
          <Radio.Group  id="radioIsYesorNo">
            <Radio value="public">是</Radio>
            <Radio value="private">否</Radio>
          </Radio.Group>
        </Form.Item>
        <Form.Item label="风险内容描述">
          <>
            <TextArea rows={4} placeholder="描述可能有风险的问题，需要帮助的内容，以及延迟的部分和预估延迟的情况" maxLength={4} id="dangerDescription" />
          </>
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default AddForm;
