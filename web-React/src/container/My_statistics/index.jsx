

import "./index.css"
import { Divider } from "antd";
import CaretUpOutlined from "@ant-design/icons/CaretUpOutlined"
import CaretDownOutlined from "@ant-design/icons/CaretDownOutlined"
import QuestionCircleOutlined from "@ant-design/icons/QuestionCircleOutlined"
import CodeGragh from "./CodeGragh/codeGragh"
const My_statistics = () => {
  // 请求后端数据
  const fetchData = async () => {
    // 
    useEffect(() => {
      fetchData();
    }, []);
  };
  return (
    <><span className="graghSpan">
      <div className="AllDiv">
        <div className='workTime'>
          <label className="getWeekNumbers">本周投入工时</label>
          <QuestionCircleOutlined className="question1" />
          <label className="getHours">132</label>
          <label className="Hours">小时</label>
          <label className="compareWeek">周同比</label>
          <CaretUpOutlined className="upIcon" />
          <label className="getPercentage">12%</label>
          <label className="compareDaily">日环比</label>
          <CaretDownOutlined className="downIcon" />
          <label className="getPercentage2">11%</label>
          <label className="totalTime">2023总投入工时 8910</label>

        </div>
        <div className='codeAmount'>
          <label className="getWeekNumbers">代码量</label>
          <QuestionCircleOutlined className="question2" />
          <label className="codeNumbers">8846</label>
          <label className="codeHang">行</label>
          <label className="totalCode">周代码量      1423</label>
        </div>
        <div className='dailyNumber'>
          <label className="getWeekNumbers">日报条数</label>
          <label className="weeklyNumbers">999</label>
        </div>
        <div className='weeklyNumber'>
          <label className="getWeekNumbers">周报条数</label>
          <label className="weeklyNumbers">425</label>
        </div>
      </div>
      <div className="codeHistogram">
        <CodeGragh />
      </div>
    </span>

    </>
  );
};

export default My_statistics;