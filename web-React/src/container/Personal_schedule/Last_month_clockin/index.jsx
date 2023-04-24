import { Pagination ,ConfigProvider,Button,Layout, Col, Row,Divider,Dropdown,Space,Radio,message} from 'antd'

import zhCN from 'antd/lib/locale/zh_CN'
import Banner from "@/container/Banner";
import React, { useState } from "react";
import { reqgetLastMonRecord } from '@/api';
import { Outlet, useNavigate } from "react-router-dom";

const { Content } = Layout;
import "./index.css";


const Last_month_clockin = () => {
  const [messageApi, contextHolder] = message.useMessage();
  const [lastMonRecord,setLastMonRecord] = useState(JSON.parse(localStorage.getItem('last_mon_record')));
  const [currentPage,setCurrentPage] = useState(JSON.parse(localStorage.getItem('currentPage')))
  const [lastMonDom,setLastMonDom] = useState(()=>{
    let record = lastMonRecord.data;
    return record.map((item,index)=>
       <Content className='content_data' key={index}>
      <div className='content_div'>
        <span className='content_span'>{item.date}</span>
        <span className='content_span'>{item.time}</span>
        <span className='content_span'>{item.work_hour}</span>
      </div>
      </Content>
    )
  })
  const navigate = useNavigate();
  const get_last_mon_record = async(page,pageSize) =>{
    let username = JSON.parse(localStorage.getItem('username'))
    let res =  await reqgetLastMonRecord(username,page,pageSize)
    if (res.code == 200) {
      localStorage.setItem('last_mon_record', JSON.stringify(res.data))
      setLastMonRecord(res.data)
      setLastMonDom(res.data.data.map((item,index)=>
      <Content className='content_data' key={index}>
     <div className='content_div'>
       <span className='content_span'>{item.date}</span>
       <span className='content_span'>{item.time}</span>
       <span className='content_span'>{item.work_hour}</span>
     </div>
     </Content>
   ))
      setCurrentPage(page)
      return 'ok'
    }else if(res.error == 'unauthorized'){
      return Promise.reject(new Error('用户未登录！'))
    }
    else {
      return Promise.reject(new Error('获取上月打卡记录失败！'))
    }
  }

  const onChange = (page,pageSize)=>{
    get_last_mon_record(page,pageSize).then(
      (result)=>{
        if(result == 'ok'){
          localStorage.getItem('defaultCurrent',JSON.stringify('currentPage','page'))
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
  }
  return (
  <Layout>
  <Layout style={{display:'flex'}}>
    <Banner></Banner>
    <Content className='title_top_div'>
    <div className='title_content'>
      <span className='title_span'>打卡日期</span>
      <span className='title_span'>打卡时段</span>
      <span className='title_span'>有效工时</span>
    </div>
    </Content>
    {/* <Content className='content_data'>
    <div className='content_div'>
      <span className='content_span'>20230301</span>
      <span className='content_span'>10-20</span>
      <span className='content_span'>5</span>
    </div>
    </Content> */}
    {lastMonDom}
    {/* <Content style={{display:'flex',flex:'1'}}></Content>
    <Content style={{display:'flex',flex:'1'}}></Content> */}
  </Layout>
  <Content className="content_pagination">
    <ConfigProvider locale={zhCN}>
      {contextHolder}
    <Pagination
      showSizeChanger = {false}
      showQuickJumper = {true}
      onChange={onChange}
      defaultCurrent={currentPage}
      pageSize={10}
      total={lastMonRecord.total}
    />
    </ConfigProvider>
    </Content>
  </Layout>
  
  );
};
  













export default Last_month_clockin;