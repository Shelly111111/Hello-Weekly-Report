import { Button, Form, Input, Radio, Select, message } from "antd";
import { useState } from "react";
import "./index.css";
import { reqgetModifyinfo, reqgetModifypassword } from "@/api";
import onFinish from "@/container/Login"

// const initVal=JSON.parse(localStorage.getItem("data"));
// const initVal = {
//   username: "admin",
//   nickName: "alone",
//   headSculpture: "www.dashuaibi.com",
//   college: "WUST",
//   major: "CS",
//   grade: 2023,
// };

const Personal_center = () => {
  const [initVal,setInitVal] = useState(JSON.parse(localStorage.getItem("data")));
  const handleSubmitInfoChange = async (data) => {
    // if(){}
  
    // const result = await axios.post('/userInfo', data);
    console.log("aaa");
    let username = localStorage.getItem("username");
    console.log(username);
    console.log(initVal);
    var arr = [];
    for (let i in data) {
      let o = {};
      o[i] = data[i]
      arr.push(o);
    }
    console.log(arr);
    if (!data.hasOwnProperty("username")) {
      arr.splice(0, 0, { "username": username.substring(1, username.length - 1) })
    }
    var result1 = {};
    arr.forEach((item) => {
      result1[Object.keys(item)[0]] = Object.values(item)[0];
    })
    console.log(result1);
    const result = await reqgetModifyinfo(result1);
    if (result.code === 200) {
      message.success(result.message);
    } else if (result.code === 461) {
      message.error(result.message);
    } else if (result.code === 462) {
      message.error(result.message);
    }
  };
  const handleModifyPassword = async (data) => {
    console.log(data);
    let username = localStorage.getItem("username");
    console.log(username);
    var arr = [];
    for (let i in data) {
      let o = {};
      o[i] = data[i]
      arr.push(o);
    }
    console.log(arr);
    if (!data.hasOwnProperty("username")) {
      arr.splice(0, 0, { "username": username.substring(1, username.length - 1) })
    }
    var result1 = {};
    arr.forEach((item) => {
      result1[Object.keys(item)[0]] = Object.values(item)[0];
    })
    delete result1.newpassword1;
    console.log(result1);

    console.log('修改密码');
    const result = await reqgetModifypassword(result1);
    if (result.code === 200) {
      message.success(result.message);
    } else if (result.code === 461) {
      message.error(result.message);
    } else if (result.code === 462) {
      message.error(result.message);
    } 
  }

  return (

    <div className="wrapper">
      <Form
        className="center_input"
        labelCol={{
          xs: { span: 24 },
          sm: { span: 7 },
        }}
        wrapperCol={{
          xs: { span: 24 },
          sm: { span: 17 },
        }}
        initialValues={initVal}
        onFinish={handleSubmitInfoChange}
      >
        <h1 className="center_h1">修改信息</h1>
        <Form.Item label="用户昵称:" name="nickName">
          <Input className="inp" placeholder="请输入昵称" />
        </Form.Item>
        <Form.Item label="头像地址:" name='headSculpture'>
          <Input className="inp" placeholder="请输入头像地址" />
        </Form.Item>
        <Form.Item label="学院:" name="college">
          <Input className="inp" placeholder="请输入学院名称" />
        </Form.Item>
        <Form.Item label="专业:" name="major">
          <Input className="inp" placeholder="请输入专业名称" />
        </Form.Item>
        <Form.Item
          name="grade"
          label="年级:"
        >
          <Select
            placeholder="请选择年级"
          >
            <Select.Option value="2020">2020</Select.Option>
            <Select.Option value="2021">2021</Select.Option>
            <Select.Option value="2022">2022</Select.Option>
            <Select.Option value="2023">2023</Select.Option>
            <Select.Option value="2024">2024</Select.Option>
          </Select>
        </Form.Item>
        <Form.Item >
          <Button className='btn1' type="primary" htmlType="submit">确认修改</Button>
        </Form.Item>
      </Form>

      <div className="line"></div>

      <Form
        className="center_input"
        labelCol={{
          xs: { span: 24 },
          sm: { span: 7 },
        }}
        wrapperCol={{
          xs: { span: 24 },
          sm: { span: 17 },
        }}
        onFinish={handleModifyPassword}
      >
        <h1 className="center_h1">修改密码</h1>
        <Form.Item label="当前密码:" name="oldpassword">
          <Input className="inp" placeholder="请输入当前密码" />
        </Form.Item>
        <Form.Item label="新密码:" name="newpassword" rules={[
          {
            required: true,
            message: 'Please input your password!',
          },
        ]}>
          <Input className="inp" placeholder="请输入新密码" />
        </Form.Item>
        <Form.Item label="确认新密码:" name="newpassword1" rules={[
          {
            required: true,
            message: 'Please confirm your password!',
          },
          ({ getFieldValue }) => ({
            validator(rule, value) {
              if (!value || getFieldValue("newpassword") === value) {
                return Promise.resolve();
              }
              return Promise.reject("两次密码输入不一致");
            },
          }),
        ]}>
          <Input className="inp" placeholder="确认新密码      " />
        </Form.Item>
        <Form.Item>
          <Button className="btn2" type="primary" htmlType="submit">
            确认修改
          </Button>
        </Form.Item>
      </Form>
    </div>

  );
}
export default Personal_center;