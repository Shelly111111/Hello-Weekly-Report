import { Button,Layout, Col, Row,Divider,Dropdown,Space,Radio,message} from 'antd'
import {useState,useEffect} from 'react'
import { DownOutlined } from '@ant-design/icons';
import React from "react";
const { Content } = Layout;
import Banner from "@/container/Banner";
import "./index.css";
import {reqgetUpdateLastMonRecord} from '@/api'
const rowStyle = {
  marginBottom: '5px', // 设置行之间的间距
  height:"40px"
};

const Freetime = () => {
  const [messageApi,contextHolder] = message.useMessage();
  const [idleTimeSubmit,setIdleTimeSubmit] = useState('');
  const [idleTime,setIdle_time] = useState(JSON.parse(localStorage.getItem('idleTime')))
  const [date,setDate] = useState(()=>{
    let attr = []
    // for (let i = 0;i < idleTime.length; i++){
    //   idleTime[i]
    // }
    idleTime.filter((item,index)=>{
      if(attr.indexOf(item.date) == -1){
        attr.push(item.date)
      }
    })
    return attr
  })
  console.log(date);
  const [value, setValue] = useState(1);
  const [_9_10,set_9_10] = useState(()=>{
    return idleTime.filter((item,index)=>index%9==0)
  });
  const [_10_11,set_10_11] = useState(()=>{
    return idleTime.filter((item,index)=>index%9==1)
  });
  const [_11_12,set_11_12] = useState(()=>{
    return idleTime.filter((item,index)=>index%9==2)
  });
  const [_14_15,set_14_15] = useState(()=>{
    return idleTime.filter((item,index)=>index%9==3)
  });
  const [_15_16,set_15_16] = useState(()=>{
    return idleTime.filter((item,index)=>index%9==4)
  });
  const [_16_17,set_16_17] = useState(()=>{
    return idleTime.filter((item,index)=>index%9==5)
  });
  const [_17_18,set_17_18] = useState(()=>{
    return idleTime.filter((item,index)=>index%9==6)
  });
  const [_19_20,set_19_20] = useState(()=>{
    return idleTime.filter((item,index)=>index%9==7)
  });
  const [_20_21,set_20_21] = useState(()=>{
    return idleTime.filter((item,index)=>index%9==8)
  });
  const [temp_value_,setTemp_value_] = useState(()=>{
    const all_times = [_9_10,_10_11,_11_12,_14_15,_15_16,_16_17,_17_18,_19_20,_20_21]
    let temp_value = {};
    for(let i=0;i<3;i++){
      let attrName = 'list_'+(9+i)+'_'+(10+i)
      temp_value[attrName] = all_times[i].map(item =>
        <Col span={4}  key={item.id} className='flex_col' ><div id={item.id} className={item.idle?"style_free":"style_normal"}></div></Col>
      );
    }
    for(let i=0+3;i<4+3;i++){
      let attrName = 'list_'+(14+i-3)+'_'+(15+i-3)
      temp_value[attrName] = all_times[i].map(item =>
        <Col span={4}  key={item.id} className='flex_col' ><div id={item.id} className={item.idle?"style_free":"style_normal"}></div></Col>
      );
    }
    for(let i=0+7;i<2+7;i++){
      let attrName = 'list_'+(19+i-7)+'_'+(20+i-7)
      temp_value[attrName] = all_times[i].map(item =>
        <Col span={4}  key={item.id} className='flex_col' ><div  id={item.id} className={item.idle?"style_free":"style_normal"}></div></Col>
      );
    }
    return temp_value
  })

  const onChange = (e) => {
    console.log('radio checked', e.target.value);
    setValue(e.target.value);
  };
  // const list_9_10 = _9_10.map(item =>
  //   <Col span={4}  key={item.id} className={item.idle?"style_free":"style_normal"} ><div className='style_normal'></div></Col>
  // );
  
  const changeState = (e)=>{
    if(e.target.className=='style_normal'){
      e.target.className = 'style_free'
    }else if(e.target.className == 'style_free'){
      e.target.className = 'style_normal'
    }
    // console.log('click',e.target);
    let idleTimeSubmit = idleTime.map((item)=>{
      if (item.id == e.target.id){
        // item.idle = true?e.target.className==
        e.target.className=='style_free'?item.idle=true:item.idle=false;
      }
      return item
    })
    setIdleTimeSubmit(idleTimeSubmit);
  }

  const success = async () => {
    let res = await reqgetUpdateLastMonRecord(idleTimeSubmit);
    console.log(res);
    if(res.code==200){
      localStorage.setItem('idleTime',JSON.stringify(idleTimeSubmit))
      messageApi.open({
        type: 'success',
        content: '提交成功！',
      });
    }else{
      messageApi.open({
        type: 'error',
        content: '提交失败！',
      });
    }


  };
    return (
      <Layout >
    <Banner></Banner>
    <Layout>
      <Row gutter={[16, 16]} className='rowStyle'>
      <Col span={4} className='flex_col'>
      <Radio.Group onChange={onChange} size='small' value={value}  className='col_item_date'>
      <Space direction="vertical">
        <Radio style={{flex:1}}  value={1} >本周</Radio>
        <Radio style={{flex:1}}  value={2} >下周</Radio>
      </Space>
    </Radio.Group>
        </Col>
        <Col span={4} className='flex_col'><div className='col_item_date'>{date[0]}</div><div className='col_item_date'>周一</div></Col>
        <Col span={4} className='flex_col'><div className='col_item_date'>{date[1]}</div><div className='col_item_date'>周二</div></Col>
        <Col span={4} className='flex_col'><div className='col_item_date'>{date[2]}</div><div className='col_item_date'>周三</div></Col>
        <Col span={4} className='flex_col'><div className='col_item_date'>{date[3]}</div><div className='col_item_date'>周四</div></Col>
        <Col span={4} className='flex_col'><div className='col_item_date'>{date[4]}</div><div className='col_item_date'>周五</div></Col>
      </Row>
      <Row gutter={[16, 16]} className='rowStyle' onClick={changeState}>
      <Col span={4}  className='flex_col'><div className='col_item_date_time'>9:00-10:00</div></Col>
      {temp_value_.list_9_10}
      </Row>
      <Row gutter={[16, 16]} className='rowStyle' onClick={changeState}>
      <Col span={4}  className='flex_col'><div className='col_item_date_time'>10:00-11:00</div></Col>
      {temp_value_.list_10_11}
      </Row>
      <Row gutter={[16, 16]} className='rowStyle' onClick={changeState}>
      <Col span={4}  className='flex_col'><div className='col_item_date_time'>11:00-12:00</div></Col>
      {temp_value_.list_11_12}
      </Row>
      <Row gutter={[16, 16]} className='rowStyle' onClick={changeState}>
      <Col span={4}  className='flex_col'><div className='col_item_date_time'>14:00-15:00</div></Col>
      {temp_value_.list_14_15}
      </Row>
      <Row gutter={[16, 16]} className='rowStyle' onClick={changeState}>
      <Col span={4}  className='flex_col'><div className='col_item_date_time'>15:00-16:00</div></Col>
      {temp_value_.list_15_16}
      </Row>
      <Row gutter={[16, 16]} className='rowStyle' onClick={changeState}>
      <Col span={4}  className='flex_col'><div className='col_item_date_time'>16:00-17:00</div></Col>
      {temp_value_.list_16_17}
      </Row>
      <Row gutter={[16, 16]} className='rowStyle' onClick={changeState}>
      <Col span={4}  className='flex_col'><div className='col_item_date_time'>17:00-18:00</div></Col>
      {temp_value_.list_17_18}
      </Row>
      <Row gutter={[16, 16]} className='rowStyle' onClick={changeState}>
      <Col span={4}  className='flex_col'><div className='col_item_date_time'>19:00-20:00</div></Col>
      {temp_value_.list_19_20}
      </Row>
      <Row gutter={[16, 16]} className='rowStyle' onClick={changeState}>
      <Col span={4}  className='flex_col'><div className='col_item_date_time'>20:00-21:00</div></Col>
      {temp_value_.list_20_21}
      </Row>
    </Layout>
    <Row gutter={[16, 16]} className='demo_block_row' >
        <Col span={4}><div className='demo_block_show1'>空闲</div></Col>
        <Col span={4}><div className='demo_block_show2'>忙碌</div></Col>
        {contextHolder}
        <Button size='large' type='primary' onClick={success} style={{ marginLeft: "auto", marginTop: "7px" }}>提交</Button>
    </Row>
    </Layout>
      
    );
  };
  
  export default Freetime;


