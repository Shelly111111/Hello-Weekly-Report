import requests_login from "@/api/ajax_login";
import requests from "@/api/ajax";
import mockAjax from '@/api/mockAjax'


export const reqgetUserLogin = (username,password,grant_type,scope,client_id,client_secret) => requests_login.get(`/oauth/token?username=${username}&password=${password}&grant_type=${grant_type}&scope=${scope}&client_id=${client_id}&client_secret=${client_secret}`)
// export const reqgetUserLogin = (params) => requests.post(`/oauth/token`,params)

export const reqgetIdleTime = (username)=>requests.get(`/userdaily/idletime?username=${username}`)


export const reqgetUserLogout = () => requests_login.post('/oauth/revoke')

export const reqgetLastMonRecord = (username,currentPage,size) => requests.get(`/userdailycheckin?username=${username}&currentPage=${currentPage}&size=${size}`)


export const reqgetUpdateLastMonRecord = (params) => requests.post('/userdaily/idletime',params)


export const reqgetModifyinfo = (params)=>requests.post('/userInfo',params)//修改信息
export const reqgetModifypassword = (params)=>requests.post('/change',params)//修改密码
export const reqgetPersonInfo = (username) => requests.get(`/userInfo?username=${username}`)//查询个人信息
export const regetShareSchedule = (params) =>requests.post('./course',params)


export const reqgetPaperbydate = (params) => requests.post('/daliypaper/getpaperbydate',params)   //查询日报
export const reqgetPaperbyweek = (params) => requests.post('/weeklypaper/getpaperbydate',params)   //查询周报
export const reqAddNewPaper = (params) => requests.post('/dailypaper/addpaper',params)   //新增日报
export const reqAddNewweek = (params) => requests.post('/weeklypaper/addpaper',params)   //新增周报
export const reqDailyPaper = (params) => requests.post('/dailypaper/',params)   //日报分页
export const reqWeeklyPaper = (params) => requests.post('/weeklypaper/',params)   //周报分页



