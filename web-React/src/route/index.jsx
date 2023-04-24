import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Layout } from 'antd'
import Home from "@/container/Home";
import Login from "@/container/Login";
import My_statistics from "@/container/My_statistics";
import Personal_center from "@/container/Personal_center";
import { Outlet } from "react-router-dom";
import Daily_paper from "@/container/Log/Daily_paper";
import Week_paper from "@/container/Log/Week_paper";
import Freetime from "@/container/Personal_schedule/Freetime"
import Last_month_clockin from "@/container/Personal_schedule/Last_month_clockin";
import My_process from "@/container/Personal_schedule/My_process";
import Back_sharing from "@/container/Share_schedule/Back_sharing";
import Front_sharing from "@/container/Share_schedule/Front_sharing";
import Test_sharing from "@/container/Share_schedule/Test_sharing";
function MyRoute(){
    return(
        <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />}>
          <Route path="*" element={<Login />} > </Route>{/* 默认的二级路由组件 */}
          <Route path="/log" element={<Layout><Outlet /></Layout>}>
            <Route path ="/log/Daily_paper" element={<Daily_paper />}></Route>
            <Route path ="/log/Week_paper" element={<Week_paper />}></Route>
          </Route>
          <Route path="/login" element={<Login />} />
          <Route path="/my_statistics" element={<My_statistics />} />
          <Route path="/personal_center" element={<Personal_center />} />
          <Route path="/share_schedule" element={<Layout><Outlet /></Layout>} >
          <Route path="/share_schedule/front_sharing" element={<Front_sharing />} />
          <Route path="/share_schedule/back_sharing" element={<Back_sharing />} />
          <Route path="/share_schedule/test_sharing" element={<Test_sharing />} />
          </Route>
          <Route path="/personal_schedule" element={<Layout><Outlet /></Layout>}>
            <Route path ="/personal_schedule/freetime" element={<Freetime />}></Route>
            <Route path ="/personal_schedule/last_month_clockin" element={<Last_month_clockin />}></Route>
            <Route path ="/personal_schedule/my_process" element={<My_process />}></Route>
          </Route>
        </Route>
      </Routes>
    </BrowserRouter>


    )
}
export default MyRoute