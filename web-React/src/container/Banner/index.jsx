import { Button, Layout, Dropdown, Space, message } from 'antd'
import { useState } from 'react'
import { DownOutlined } from '@ant-design/icons';
import React from "react";
const { Content } = Layout;
import { reqgetUserLogout } from '@/api';
import { useNavigate } from "react-router-dom";

import "./index.css";
const items = [
    {
        label: '退出登录',
        key: 'logout',
    },
];

const Banner = () => {
    const navigate = useNavigate();
    // const [selectedButton, setSelectedButton] = useState(null);
    const [messageApi,contextHolder] = message.useMessage();
    // const [modal, contextHolder] = Modal.useModal();
    const [loginUsername,setLoginUsername] = useState(JSON.parse(localStorage.getItem('username')))
    const handleLogout = async (e) => {
        // message.info('Click on menu item.');
        console.log('click', e);
        if (localStorage.getItem('token')) {
            let result = await reqgetUserLogout()
            // localStorage.removeItem('username')
            // localStorage.removeItem('token')
            localStorage.clear()
            sessionStorage.clear()
            messageApi.open({
                type: 'success',
                content: '退出登录成功！',
            });
            setTimeout(() => {
                navigate('/login')
                setLoginUsername(null)
                sessionStorage.setItem('title_url',JSON.stringify({title:'登录',url:'login'}) )
            }, 500);
        }
    }
    const menuProps = {
        items,
        onClick: handleLogout,
    };


    return (
        <Content>
            <div className="header_second" style={{ height: "50px", display: 'flex' }}>
                <div className="go_run">Go Run!</div>
                {/* <Content className='header_main'>
        <Button  type={selectedButton === 0 ? 'primary' : 'text'} onClick={() => handleButtonClick(0)} size='small'>个人日程</Button>
        <Button  type={selectedButton === 1 ? 'primary' : 'text'} onClick={() => handleButtonClick(1)} size='small'>日志</Button>
        <Button  type={selectedButton === 2 ? 'primary' : 'text'} onClick={() => handleButtonClick(2)} size='small'>分享课表</Button>
        <Button  type={selectedButton === 3 ? 'primary' : 'text'} onClick={() => handleButtonClick(3)} size='small'>我的统计信息</Button>
        <Button  type={selectedButton === 4 ? 'primary' : 'text'} onClick={() => handleButtonClick(4)} size='small'>个人中心</Button>
        </Content> */}

                {/* <Dropdown menu={menuProps}><Button style={{ marginLeft: "auto", marginTop: "7px" }} size="small" type="text">
                    <Space>张三<DownOutlined /></Space>
                    {contextHolder}
                </Button>
                </Dropdown> */}
                {loginUsername && <Dropdown colorBgElevated='#ff4d4f' menu={menuProps}><Button style={{ marginLeft: "auto", marginTop: "7px", color: "white" }} size="small" type="text">
                    <Space style={{color:'black'}}>{loginUsername}<DownOutlined /></Space>
                </Button>
                </Dropdown>}
                {contextHolder}
            </div>
        </Content>
    )
}
export default Banner;