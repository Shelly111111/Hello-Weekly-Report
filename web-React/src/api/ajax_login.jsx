//对axios进行二次封装
import axios from 'axios'
const requests_login = axios.create({
    baseURL: 'http://10.162.32.129:8080',
    timeout: 5000,
});

//请求拦截器
requests_login.interceptors.request.use((config) => {
    if (localStorage.getItem('token') != null) {
        config.headers.Authorization = "Bearer " + JSON.parse(localStorage.getItem('token'))
    } 
    return config
}, err => {
    console.log('request error', err);
    return Promise.reject(new Error(err))
}
);

//响应拦截器
requests_login.interceptors.response.use(
    res => {
        return res.data
    },
    err => {
        //响应失败的回调函数
        // console.log(err.response.data);
        // return Promise.reject(new Error(err))
        if (err.response.data) {
            return err.response.data
        }
        return Promise.reject(new Error(err))
    }
);

export default requests_login





