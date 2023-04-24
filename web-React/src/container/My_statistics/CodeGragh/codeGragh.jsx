import { useEffect,useState } from 'react';
import * as echarts from 'echarts';
import './codeGragh.css'
const CodeGragh = () => {
  const [data, setData] = useState([]);
  const [isThisWeek, setIsThisWeek] = useState(true);
  useEffect(() => {
    const myChart = echarts.init(document.getElementById('code-histogram'));
    const option = {
      title: {
        text: '代码量',
        left: 'left',
      },
      splitLine:{
        //坐标轴背景虚线
        show:true,
        lineStyle:'dashed',//虚线
      },
      tooltip: {},
      xAxis: {
        type: 'category',
        data: ['周一', '周二', '周三', '周四', '周五'],
      },
      yAxis: {
        type: 'value',
        axisLine: {
          lineStyle: {
            color: '#999',
            type: 'dashed',
          },
        },
      },
      series: [
        {
          data: [360, 500, 999, 750, 180],
          type: 'bar',
          barWidth:40,
          itemStyle: {
            color: '#1890ff',
          
          },
        },
      ],
    };
    myChart.setOption(option);
  }, []);
  //判断是本周还是上周
  useEffect(() => {
    fetchData();
  }, [isThisWeek]);
  const fetchData = () => {
    if (isThisWeek) {
      setData([
        { rank: 1, name: '张三', code: 654321 },
        { rank: 2, name: '李四', code: 523234 },
        { rank: 3, name: '王五', code: 345432 },
        { rank: 4, name: '赵六', code: 234454 },
        { rank: 5, name: '钱七', code: 123454 },
      ]);
    } else {
      setData([
        { rank: 1, name: '李四', code: 123432 },
        { rank: 2, name: '张三', code: 112344 },
        { rank: 3, name: '王五', code: 100234 },
        { rank: 4, name: '赵六', code: 99876 },
        { rank: 5, name: '钱七', code: 86432 },
      ]);
    }
  };
  return (
    <div className="codeHistogram">
      <div id="code-histogram" style={{ width: '60%', height: '400px' }} />
      <div className="rank-table">
      <div className="switch-week">
          <button onClick={() => setIsThisWeek(true)}>本周</button>
          <button onClick={() => setIsThisWeek(false)}>上周</button>
        </div>
        <table className='compareTable'>
          <thead>
            <tr>
              <th></th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {data.map((item) => (
              <tr key={item.rank}>
                <td>{item.rank}</td>
                <td>{item.name}</td>
                <td>{item.code}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default CodeGragh;
