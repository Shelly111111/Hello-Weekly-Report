import { useState } from 'react';
import './index.css'
import AddDataForm from './AddDataPaper/addDataPaper';
import { Space, Table, DatePicker, Pagination, Layout } from 'antd';
import Banner from "@/container/Banner";
import { reqgetPaperbyweek,reqAddNewweek,reqWeeklyPaper } from '@/api';

const Week_paper = () => {

  //调用新增组件
  const [showAddForm, setShowAddForm] = useState(false);
  const [open, setOpen] = useState(false);
  const [editData, setEditData] = useState(null);
  const [selectedRecord, setSelectedRecord] = useState(null);
  // 点击新增按钮
  const handleAddClick = () => {
    setShowAddForm(true);
    setEditData(null);
    setOpen(true);
  };

  // 点击详情按钮
  const handleDetailClick = (record) => {
    setShowAddForm(true);
    setSelectedRecord(record);
    setEditData(record);
    setOpen(true);
    console.log(record);
  };

  const handleCancel = () => {
    setOpen(false);
  };
  const handleFormClose = () => {
    setShowAddForm(false);
  };
  const handleCreate = (values) => {
    console.log(values);
    setOpen(false);
  };

  const [formData, setFormData] = useState(null); // added state variable for form data
  const onCreate = (values) => {
    console.log('Received values of form: ', values);
    setOpen(false);
  };
  const handleCancelClick = () => {
    setShowAddForm(false);
  };
  //定义开始和结束时间
  const [startTime, setStartTime] = useState(null);
  const [endTime, setEndTime] = useState(null);
  const handleDateChange = (dates) => {
    if (dates && dates.length === 2) {
      setStartTime(dates[0]);
      setEndTime(dates[1]);
    } else {
      setStartTime(null);
      setEndTime(null);
    }
  }
  //查询
  const handleSearch = async () => {
    let username = localStorage.getItem("username");
    let result = await reqgetPaperbyweek({ startTime: startTime.format('YYYY-MM-DD'), endTime: endTime.format('YYYY-MM-DD'), username });
    console.log(result);
    if (result.code == 200) {
      message.success(result.message);
    } else {
      message.error(result.message);
    }
  };

  //点击详情显示表单

  //表单上的数据
  const data = [
    {
      key: '1',
      weekData: "20230401~20230403",
      workData: "针对算法做一个编程落地",
      riskData: "单测还不太熟悉",
      delayData: "是",
    },
    {
      key: '2',
      weekData: "20230401~20230403",
      workData: "针对算法做一个编程落地",
      riskData: "单测还不太熟悉",
      delayData: "是",
    }, {
      key: '3',
      weekData: "20230401~20230403",
      workData: "针对算法做一个编程落地",
      riskData: "单测还不太熟悉",
      delayData: "是",
    }, {
      key: '4',
      weekData: "20230401~20230403",
      workData: "针对算法做一个编程落地",
      riskData: "单测还不太熟悉",
      delayData: "是",
    },
  ];
  const columns = [
    {
      title: '日期范围',
      dataIndex: 'weekData',
      key: 'weekData',
    },
    {
      title: '本周完成内容',
      dataIndex: 'workData',
      key: 'workData',
    },
    {
      title: '下周计划内容',
      dataIndex: 'riskData',
      key: 'riskData',
    },
    {
      title: '是否有延迟',
      key: 'delayData',
      dataIndex: 'delayData',
    },
    {
      title: '操作',
      key: 'action2',
      dataIndex: 'action',
      render: (_, record) => (
        <Space size="middle">
          <a onClick={() => handleDetailClick(record)
          }>详情 {record.name}</a>
        </Space>
      )
    }
  ]
  const handleReset = () => {
    const currentDate = new Date();
    const year = currentDate.getFullYear();
    const month = currentDate.getMonth() + 1;
    const day = currentDate.getDate();

    setDate(`${year}-${month}-${day}`);
  };
  const { RangePicker } = DatePicker;
  return (
    <Layout>
      <Banner></Banner>
      <div className='allContainer'>
        <div className="container">
          <label className='data-choose'>日期范围：</label>
          <RangePicker
            format="YYYY-MM-DD"
            onChange={handleDateChange}
            value={startTime && endTime ? [startTime, endTime] : null} placeholder={['开始时间', '结束时间']}
          />
          <button className="search-btn" onClick={handleSearch}>
            查询
          </button>
          <button className="reset-btn" onClick={handleReset}>
            重置
          </button>
        </div>
        <button
          className='addButton'
          type="primary"
          onClick={() => {
            setOpen(true);
          }}
        >
          新增周报
        </button>
        <AddDataForm
          open={open}
          onCreate={onCreate}
          onCancel={() => {
            setOpen(false);
          }}
        />
        <div className="tabelDiv">
          <Table className='viewData' columns={columns} dataSource={data}
            pagination={false}>
          </Table>
          <Pagination
            className='paginationDiv'
            total={85}
            showSizeChanger
            showQuickJumper
            showTotal={(total) => `总共 ${total} 项`}
          />
        </div>
      </div>
    </Layout>
  );
};

export default Week_paper;