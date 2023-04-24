//对axios进行二次封装
import axios from 'axios'
const requests = axios.create({
    baseURL:'/mock',
    timeout:5000,
})

//请求拦截器
requests.interceptors.request.use((config)=>{


    return config
},err =>{
    console.log('request error',err);
    return Promise.reject(new Error(err))
}
)

//响应拦截器
requests.interceptors.response.use(
    res =>{
        return res.data
    },
    err =>{
        //响应失败的回调函数
        // return Promise.reject(new Error('failed'))
        return Promise.reject(new Error(err))
    }
)

export default requests





