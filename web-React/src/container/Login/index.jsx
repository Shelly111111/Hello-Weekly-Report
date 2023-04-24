import { Button, ConfigProvider, Checkbox, Input, Layout, Col, Row, Dropdown, Space, message, Form } from 'antd'
import { DownOutlined, LockOutlined, UserOutlined } from '@ant-design/icons';
import React, { useState } from "react";
import img from '@/assets/images/u10.png'
const { Content } = Layout;
import "./index.css";
import { reqgetUserLogin, reqgetUserLogout, reqgetIdleTime, reqgetLastMonRecord } from '@/api';
import zhCN from 'antd/lib/locale/zh_CN'
import { useNavigate } from "react-router-dom";
const items = [
  {
    label: '退出登录',
    key: 'logout',
  },
];

const Login = () => {
  const navigate = useNavigate();
  const [messageApi, contextHolder] = message.useMessage();
  const [username, setUsername] = useState('');
  const [loginUsername, setLoginUsername] = useState(JSON.parse(localStorage.getItem('username')))
  const [password, setPassword] = useState('');
  const handleLogout = async (e) => {
    // message.info('Click on menu item.');
    console.log('click', e);

    if (localStorage.getItem('token')) {
      let result = await reqgetUserLogout()
      // localStorage.removeItem('username')
      // localStorage.removeItem('token')
      localStorage.clear()
      sessionStorage.clear()
      console.log(result);
      messageApi.open({
        type: 'success',
        content: '退出登录成功！',
      });
      setLoginUsername(null)
    }
    // messageApi.open({
    //   type: 'error',
    //   content: '退出登录失败！',
    // });
  };
  const menuProps = {
    items,
    onClick: handleLogout,
  };
  //测试跨域请求是否解决
  // const login = async () => {
  //   let result = await reqgetUserLogin({ id: '1', username, password });
  //   console.log(result);
  //   console.log('login-->');
  // }
  const getIdleTime = async () => {
    let username = JSON.parse(localStorage.getItem('username'))
    const res = await reqgetIdleTime(username)
    console.log(res);
    console.log(res.error);
    if (res.code == 200) {
      localStorage.setItem('idleTime', JSON.stringify(res.data))
      return 'ok'
    } else if (res.error == 'unauthorized') {
      return Promise.reject(new Error('用户未登录！'))
    }
    else {
      return Promise.reject(new Error('获取空闲时间失败！'))
    }
  }
  const get_last_mon_record = async () => {
    let username = JSON.parse(localStorage.getItem('username'))
    let res = await reqgetLastMonRecord(username, 1, 10)
    console.log(res);
    if (res.code == 200) {
      localStorage.setItem('last_mon_record', JSON.stringify(res.data))
      return 'ok'
    } else if (res.error == 'unauthorized') {
      return Promise.reject(new Error('用户未登录！'))
    }
    else {
      return Promise.reject(new Error('获取上月打卡记录失败！'))
    }
  }
  const onFinish = async (values) => {
    let username = values.username
    let password = values.password
    let grant_type = 'password'
    let scope = 'all'
    let client_id = 'client_1'
    let client_secret = 'secret'
    let x = { username, password, grant_type, scope, client_id, client_secret }
    console.log(x);
    let res = await reqgetUserLogin(username, password, grant_type, scope, client_id, client_secret)
    // let res = await reqgetUserLogin(x)
    if (res.access_token) {
      localStorage.setItem('token', JSON.stringify(res.access_token))
      localStorage.setItem('username', JSON.stringify(username))
      setLoginUsername(JSON.parse(localStorage.getItem('username')))
      messageApi.open({
        type: 'success',
        content: '登录成功！',
      });
      if (JSON.parse(sessionStorage.getItem('title_url')) != null) {
        let url = JSON.parse(sessionStorage.getItem('title_url')).url
        if (url) {
          if (url == "login") { // 登录
            navigate("/login");
          } else if (url == "share_schedule") { // 分享课表
            navigate("/share_schedule")
          } else if (url == "my_info") { // 我的统计信息
            navigate("/my_statistics")
          } else if (url == "personal_center") { // 个人中心
            navigate("/personal_center")
          } else if (url == "ldle_time") { // 空闲时间
            getIdleTime().then(
              (result) => {
                if (result == 'ok') {
                  navigate("/personal_schedule/Freetime")
                }
              }
            ).catch(
              (error) => {
                messageApi.open({
                  type: 'error',
                  content: error.message,
                });
                if (error.message == '用户未登录！') {
                  navigate("/login");
                }
              }
            )
          } else if (url == "last_mon_record") { // 上月打卡记录
            get_last_mon_record().then(
              (result) => {
                if (result == 'ok') {
                  navigate("/personal_schedule/Last_month_clockin")
                }
              }
            ).catch(
              (error) => {
                messageApi.open({
                  type: 'error',
                  content: error.message,
                });
                if (error.message == '用户未登录！') {
                  navigate("/login");
                }
              }
            )
          } else if (url == "my_participation_course") { // 我的参与过程
            navigate("/personal_schedule/my_process")
          } else if (url == "daily_paper") { // 日报
            navigate("/log/Daily_paper")
          } else if (url == "weekly_paper") { // 周报
            navigate("/log/Week_paper")
          } else if (url == "front_sharing") { // 前端课程分享
            navigate("/share_schedule/front_sharing")
          } else if (url == "back_sharing") { // 后端课程分享
            navigate("/share_schedule/back_sharing")
          } else if (url == "test_sharing") { // 测试课程分享
            navigate("/share_schedule/test_sharing")
          }
        }
      }

    }
    console.log(res);
    // console.log('Received values of form: ', values);
  };

  return (
    <Layout>
      <div className="header_second_login" style={{ height: "50px", display: 'flex' }}>
        <div className="go_run_login">Go Run!</div>
        {loginUsername && <Dropdown menu={menuProps}><Button style={{ marginLeft: "auto", marginTop: "7px", color: "white" }} size="small" type="text">
          <Space>{loginUsername}<DownOutlined /></Space>
        </Button>
        </Dropdown>}
        {contextHolder}
      </div>
      <Content>
        <Row>
          <Col span={16}>
            <Layout style={{ height: "80vh" }}>
              <img className='login_img' height={500} src={img} alt="Login_img" />
            </Layout>
          </Col>
          <Col span={8}>
            <Layout className='Layout_right' style={{ height: "80vh" }}>
              {/* <Layout className='login_input'>
                <Form >
                <Input name='username' type="text" placeholder='用户名'  onChange={e => setUsername(e.target.value)}></Input>
                <Input type="password" placeholder='密码' onChange={e => setPassword(e.target.value)}></Input>
                <Button type="primary" onClick={login}>登录</Button>
                </Form>
              </Layout> */}
              <ConfigProvider locale={zhCN}>
                <Form
                  name="normal_login"
                  className="login-form"
                  initialValues={{ remember: true }}
                  onFinish={onFinish}
                >
                  <Form.Item
                    // label='用户名'
                    name="username"
                    rules={[{ required: true, message: '请输入用户名！' }, { min: 5, message: '用户名不得少于5位!' }, { max: 16, message: '用户名最长是16位字符' }, { pattern: new RegExp('^[0-9a-zA-Z_]{1,}$', 'g'), message: '用户名只允许包含数字、字母、下划线！' }]}
                  >
                    <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="用户名" />
                  </Form.Item>
                  <Form.Item
                    name="password"
                    rules={[{ required: true, message: '请输入密码！' }, { min: 3, message: '密码不得少于3位!' }, { max: 16, message: '密码最长是16位字符' }, { pattern: new RegExp('^[0-9a-zA-Z_]{1,}$', 'g'), message: '密码只允许包含数字、字母、下划线！' }]}
                  >
                    <Input
                      prefix={<LockOutlined className="site-form-item-icon" />}
                      type="password"
                      placeholder="密码"
                    />
                  </Form.Item>
                  <Form.Item>
                    <Form.Item name="remember" valuePropName="checked" noStyle>
                      <Checkbox>记住密码</Checkbox>
                    </Form.Item>

                    <a className="login-form-forgot" href="">
                      忘记密码
                    </a>
                  </Form.Item>
                  <Form.Item >
                    <Button type="primary" htmlType="submit" className="login-form-button">
                      登录
                    </Button>
                    {/* Or <a href="">register now!</a> */}
                  </Form.Item>
                </Form>
              </ConfigProvider>
            </Layout>
          </Col>
        </Row>
      </Content>
    </Layout>
  );
};

export default Login;
