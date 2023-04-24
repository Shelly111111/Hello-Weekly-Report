import { Layout, Menu, theme, Button, Dropdown, Space, message } from "antd";
import { DownOutlined } from '@ant-design/icons';
import React, { useState,useEffect } from "react";
const { Header, Content, Sider } = Layout;
import Login from "@/container/Login";
import "./index.css";
import { Outlet, useNavigate } from "react-router-dom";
import { reqgetIdleTime,reqgetLastMonRecord,reqgetPersonInfo } from '@/api';


const items_richeng = [
  {
    key: "ldle_time",
    label: "空闲时段",
  },
  {
    key: "last_mon_record",
    label: "上月打卡记录"
  },
  {
    key: "my_participation_course",
    label: "我的参与课程"
  },
]
const items_first = [
  // {
  //   key: "login",
  //   label: "登录",
  // },
  {
    key: "personal_schedule",
    label: "个人日程",
    children: [
      ...items_richeng
    ]
  },
  {
    key: "daily_record",
    label: "日志",
    children: [
      {
        key: "daily_paper",
        label: "日报"
      },
      {
        key: "weekly_paper",
        label: "周报"
      },

    ]
  },
  {
    key: "share_schedule",
    label: "分享课表",
    children: [
      {
        key: "front_sharing",
        label: "前端课程分享"
      },
      {
        key: "back_sharing",
        label: "后端课程分享"
      },
      {
        key: "test_sharing",
        label: "测试课程分享"
      },

    ]
  },
  {
    key: "my_info",
    label: "我的统计信息",
  },
  {
    key: "personal_center",
    label: "个人中心",
  },
]
const items = [
  {
    label: '退出登录',
    key: 'logout',
  },
];
const state_current = {
  state: "页面框架",
}
console.log(window.innerWidth);
const Home = () => {
  const [messageApi, contextHolder] = message.useMessage();
  const {
    token: { colorBgContainer },
  } = theme.useToken();

  // session 存储左上角title_url 避免刷新丢失当前title问题
  let title_url = JSON.parse(sessionStorage.getItem('title_url'))
  const [icon, setIcon] = useState('normal');
  const [isShow, setIsShow] = useState(true);
  const [currentTitle, setCurrentTitle] = useState(()=>{
    // title_url == '{}' ? '登录':title_url.title
    if(JSON.parse(sessionStorage.getItem('title_url')) == null){
      return '登录';
    }else{
      return title_url.title ;
    }
  }  );
  //监听currentTitle是否发生变化
  // useEffect(() => {
  //   const handleStorage = () => {
  //     setCurrentTitle(JSON.parse(sessionStorage.getItem('title_url'))!=null?'登录':JSON.parse(sessionStorage.getItem('title_url')).title);
  //   };
  //   window.addEventListener('storage', handleStorage);
  //   return () => window.removeEventListener('storage', handleStorage);
  // }, []);

  const navigate = useNavigate();

  function getCurrentTitle(key) {
    let currentTitle = ''
    let currentUrl = '/'
    let label = items_first.filter(item => {
      if (item.key == key) {
        currentTitle = item.label
        currentUrl = item.key
        return item
      }
      if (item.children !== undefined) {
        let label_children = item.children.filter(item2 => {
          if (item2.key == key) {
            currentTitle = item2.label
            currentUrl = item2.key
          }
        })
        // console.log(label_children);
        if (label_children.length != 0) {
          return label_children
        }
      }
    })

    return { "title": currentTitle, "url": currentUrl };
  }

  function handleMouseEnter() {
    setIcon('hover');
  }
  function handleMouseLeave() {
    setIcon('normal');
  }
  const handleClickMenuBtn = () => {
    setIsShow(!isShow);
  }
  const getIdleTime = async () => {
    let username = JSON.parse(localStorage.getItem('username'))
    const res = await reqgetIdleTime(username)
    console.log(res);
    console.log(res.error);
    if (res.code == 200) {
      localStorage.setItem('idleTime', JSON.stringify(res.data))
      return 'ok'
    }else if(res.error == 'unauthorized'){
      return Promise.reject(new Error('用户未登录！'))
    }
    else {
      return Promise.reject(new Error('获取空闲时间失败！'))
    }
  }
  const get_last_mon_record = async() =>{
    let username = JSON.parse(localStorage.getItem('username'))
    let res =  await reqgetLastMonRecord(username,1,10)
    console.log(res);
    if (res.code == 200) {
      localStorage.setItem('last_mon_record', JSON.stringify(res.data))
      return 'ok'
    }else if(res.error == 'unauthorized'){
      return Promise.reject(new Error('用户未登录！'))
    }
    else {
      return Promise.reject(new Error('获取上月打卡记录失败！'))
    }
  }
  const get_user_info = async() =>{
    let username= JSON.parse(localStorage.getItem('username')) 
    const res = await reqgetPersonInfo(username);
    console.log(res);
    if(res.code == '200'){
      localStorage.setItem("data",JSON.stringify(res.data));
      return 'ok'
    }else if(res.error == 'unauthorized'){
      return Promise.reject(new Error('用户未登录！'))
    }
    else {
      return Promise.reject(new Error('获取用户信息失败！'))
    }
  }


  const handleLeftMenuClick = (e) => {
    // message.info('Click on menu item.');
    const { title, url } = getCurrentTitle(e.key)
    setCurrentTitle(title)
    sessionStorage.setItem('title_url', JSON.stringify({ title, url }))
    console.log(url);

    if (url == "login") { // 登录
      navigate("/login");
    } else if (url == "share_schedule") { // 分享课表
      navigate("/share_schedule")
    } else if (url == "my_info") { // 我的统计信息
      navigate("/my_statistics")
    } else if (url == "personal_center") { // 个人中心
      get_user_info().then(
        (result)=>{
          if(result == 'ok'){
            navigate("/personal_center")
          }
        }
      ).catch(
        (error)=>{
          messageApi.open({
            type: 'error',
            content: error.message,
          });
          if(error.message == '用户未登录！'){
            setCurrentTitle('登录')
            sessionStorage.setItem('title_url', JSON.stringify({ title:'登录', url:'login' }))
            navigate("/login");
          }
        }
      )
    } else if (url == "ldle_time") { // 空闲时间
      getIdleTime().then(
        (result)=>{
          if(result == 'ok'){
            navigate("/personal_schedule/Freetime")
          }
        }
      ).catch(
        (error)=>{
          messageApi.open({
            type: 'error',
            content: error.message,
          });
          if(error.message == '用户未登录！'){
            setCurrentTitle('登录')
            sessionStorage.setItem('title_url', JSON.stringify({ title:'登录', url:'login' }))
            navigate("/login");
          }
        }
      )
    } else if (url == "last_mon_record") { // 上月打卡记录
      get_last_mon_record().then(
        (result)=>{
          if(result == 'ok'){
            navigate("/personal_schedule/Last_month_clockin")
          }
        }
      ).catch(
        (error)=>{
          messageApi.open({
            type: 'error',
            content: error.message,
          });
          if(error.message == '用户未登录！'){
            setCurrentTitle('登录')
            sessionStorage.setItem('title_url', JSON.stringify({ title:'登录', url:'login' }))
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
  };
  return (
    <Layout style={{ height: "150vh" }}>

      <div className="header">
        {/* <Button size="small" type="text"><span className="iconfont icon-wrong"></span></Button> */}
        {/* {contextHolder} */}
        <Button size="small" type="text"><span id='icon_change' className={icon === 'normal' ? 'iconfont icon-wrong' : 'iconfont icon-left'} onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave}></span></Button>
        <Button className="logo" size="small" type="text" onClick={handleClickMenuBtn}><span className="iconfont icon-caidanlan"></span> &nbsp;&nbsp;&nbsp;{currentTitle}&nbsp;&nbsp;</Button>
      </div>
      <Layout>
      {/* {contextHolder} */}
      <div>
        {contextHolder}
        {isShow && <Sider width={200} style={{ background: colorBgContainer }}>
          
          <Menu
            mode="inline"
            // defaultSelectedKeys={["login"]}
            // defaultOpenKeys={["sub1"]}
            onClick={handleLeftMenuClick}
            style={{ height: "100%", borderRight: 0 }}
            items={items_first}
          />
          
        </Sider>}
        </div>
        <Layout style={{ padding: "0 24px 24px" }}>
          <Content
            style={{
              padding: 24,
              margin: 0,
              minHeight: 280,
              background: colorBgContainer,
            }}
          >
            <Layout style={{ height: "80vh" }}>
              <Layout>
                {window.location.pathname == '/' && <Login />}
                <Content
                >
                  <Outlet />
                </Content>
              </Layout>
            </Layout>
          </Content>
        </Layout>
      </Layout>
    </Layout>
  );
};

export default Home;
