import { useState,useEffect } from 'react';
import './index.css'
import AddForm from './AddForm/addForm';
import { Space, Table, DatePicker, Pagination, Layout } from 'antd';
import Banner from "@/container/Banner";
import { reqgetPaperbydate,  reqDailyPaper } from '@/api';

const pageSizeOptions = ['10', '20', '30', '50'];
const Daily_paper = () => {
  //分页
  const [data, setData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [total, setTotal] = useState(0);

  useEffect(() => {
    fetchDailyPaper();
  }, [currentPage, pageSize]);

  const fetchDailyPaper = async () => {
    let username = localStorage.getItem("username");
    const res = await reqDailyPaper({
      currentPage,
      pageSize,
      username
    });

    if (res.code === 200) {
      message.success(result.message);
    } else {
      message.error(result.message);

    }
  };

  const handlePageChange = (page, pageSize) => {
    setCurrentPage(page);
    setPageSize(pageSize);
  };
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
    let result = await reqgetPaperbydate({ startTime: startTime.format('YYYY-MM-DD'), endTime: endTime.format('YYYY-MM-DD'), username });
    console.log(result);
    if (result.code == 200) {
      message.success(result.message);
    } else {
      message.error(result.message);
    }
  };
  //表单上的数据
  const data1 = [
    {
      key: '1',
      dailyDate: "2022-04-13",
      work: "完成工作内容1",
      risk: "有风险的操作1",
      delay: "是",
      action: "编辑",
    },
    {
      key: '2',
      dailyDate: "2022-04-13",
      work: "完成工作内容1",
      risk: "有风险的操作1",
      delay: "是",
      action: "编辑",
    },
    {
      key: '3',
      dailyDate: "2022-04-13",
      work: "完成工作内容1",
      risk: "有风险的操作1",
      delay: "是",
      action: "编辑",
    },
    {
      key: '4',
      dailyDate: "2022-04-13",
      work: "完成工作内容1",
      risk: "有风险的操作1",
      delay: "是",
      action: "编辑",
    },
  ];
  const columns = [
    {
      title: '日期',
      dataIndex: 'dailyDate',
      key: 'data',
    },
    {
      title: '完成工作内容',
      dataIndex: 'work',
      key: 'work',
    },
    {
      title: '有风险的内容',
      dataIndex: 'risk',
      key: 'risk',
    },
    {
      title: '是否有延迟',
      key: 'delay',
      dataIndex: 'delay',
    },
    {
      title: '操作',
      key: 'action',
      dataIndex: 'action',
      render: (_, record) => (
        <Space size="middle">
          <a onClick={() => handleDetailClick(record)}>详情 {record.name}</a>
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
          <RangePicker format="YYYY-MM-DD"
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
          onClick={handleAddClick}
        >
          新增日报
        </button>
        <AddForm
          open={open}
          isEdit={editData !== null}
          editData={editData}
          onCreate={handleCreate}
          onCancel={handleCancel}
          initialValues={selectedRecord}
          onClose={handleFormClose}
        />
        <div className="tabelDiv">
          <Table className='viewData' columns={columns} dataSource={data1}
            pagination={false}>
          </Table>
          <div>
            <Pagination
              className='paginationDiv'
              current={currentPage}
              pageSize={pageSize}
              total={total}
              showSizeChanger
              showQuickJumper
              showTotal={(total) => `总共 ${total} 条`}
              pageSizeOptions={pageSizeOptions}
              onChange={handlePageChange}
            />
          </div>

        </div>
      </div>
    </Layout>
  );
};

export default Daily_paper;