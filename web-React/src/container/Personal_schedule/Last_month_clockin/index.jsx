import { Pagination ,ConfigProvider,Button,Layout, Col, Row,Divider,Dropdown,Space,Radio,message} from 'antd'

import zhCN from 'antd/lib/locale/zh_CN'
import Banner from "@/container/Banner";
import React, { useState } from "react";
const { Content } = Layout;
import "./index.css";


const Last_month_clockin = () => {
  const [lastMonRecord,setLastMonRecord] = useState(JSON.parse(localStorage.getItem('last_mon_record')));
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

  const onChange = (page,pageSize)=>{
    console.log('onChange',page,pageSize);
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
    <Pagination
      showSizeChanger = {false}
      showQuickJumper = {true}
      onChange={onChange}
      defaultCurrent={1}
      pageSize={10}
      total={lastMonRecord.data.total}
    />
    </ConfigProvider>
    </Content>
  </Layout>
  
  );
};
  













export default Last_month_clockin;