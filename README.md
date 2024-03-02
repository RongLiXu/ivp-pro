
**本项目为ivp pro修改版本，但是基本没做任何修改，只是添加了几个api以配合ivp-antd-ui使用**

# 开箱即用的28181协议视频平台


IOT VIDEO PLATFORM是一个基于GB28181-2016标准实现的开箱即用的网络视频平台，负责实现核心信令与设备管理后台部分，支持NAT穿透，支持海康、大华、宇视等品牌的IPC、NVR接入。支持国标级联，支持将不带国标功能的摄像机/直播流/直播推流转发到其他国标平台。   

流媒体服务基于@夏楚 ZLMediaKit [https://github.com/ZLMediaKit/ZLMediaKit](https://github.com/ZLMediaKit/ZLMediaKit)   
播放器使用@dexter jessibuca [https://github.com/langhuihui/jessibuca/tree/v3](https://github.com/langhuihui/jessibuca/tree/v3)  
前端页面基于@Kyle MediaServerUI [https://gitee.com/kkkkk5G/MediaServerUI](https://gitee.com/kkkkk5G/MediaServerUI) 进行修改.  

# 应用场景：
支持浏览器无插件播放摄像头视频。
支持国标设备(摄像机、平台、NVR等)设备接入
支持非国标(onvif, rtsp, rtmp，直播设备等等)设备接入，充分利旧。 
支持国标级联。多平台级联。跨网视频预览。
支持跨网网闸平台互联。


# 文档
ZLM使用文档 [https://github.com/ZLMediaKit/ZLMediaKit](https://github.com/ZLMediaKit/ZLMediaKit)


# 截图
![index](doc/_media/index.png "index.png")
![2](doc/_media/2.png "2.png")
![3](doc/_media/3.png "3.png")
![3-1](doc/_media/3-1.png "3-1.png")
![3-2](doc/_media/3-2.png "3-2.png")
![3-3](doc/_media/3-3.png "3-3.png")
![build_1](https://images.gitee.com/uploads/images/2022/0304/101919_ee5b8c79_1018729.png "2022-03-04_10-13.png")

# 功能特性 
- [X] 集成web界面
- [X] 兼容性良好
- [X] 支持电子地图，支持接入WGS84和GCJ02两种坐标系，并且自动转化为合适的坐标系进行展示和分发
- [X] 接入设备
  - [X] 视频预览
  - [X] 无限制接入路数，能接入多少设备只取决于你的服务器性能
  - [X] 云台控制，控制设备转向，拉近，拉远
  - [X] 预置位查询，使用与设置
  - [X] 查询NVR/IPC上的录像与播放，支持指定时间播放与下载
  - [X] 无人观看自动断流，节省流量
  - [X] 视频设备信息同步
  - [X] 离在线监控
  - [X] 支持直接输出RTSP、RTMP、HTTP-FLV、Websocket-FLV、HLS多种协议流地址
  - [X] 支持通过一个流地址直接观看摄像头，无需登录以及调用任何接口
  - [X] 支持UDP和TCP两种国标信令传输模式
  - [X] 支持UDP和TCP两种国标流传输模式
  - [X] 支持检索,通道筛选
  - [X] 支持通道子目录查询
  - [X] 支持过滤音频，防止杂音影响观看
  - [X] 支持国标网络校时
  - [X] 支持播放H264和H265
  - [X] 报警信息处理，支持向前端推送报警信息
  - [X] 支持订阅与通知方法
    - [X] 移动位置订阅
    - [X] 移动位置通知处理
    - [X] 报警事件订阅
    - [X] 报警事件通知处理
    - [X] 设备目录订阅
    - [X] 设备目录通知处理
  -  [X] 移动位置查询和显示
  - [X] 支持手动添加设备和给设备设置单独的密码
-  [X] 支持平台对接接入
-  [X] 支持国标级联
  - [X] 国标通道向上级联
    - [X] WEB添加上级平台
    - [X] 注册
    - [X] 心跳保活
    - [X] 通道选择
    - [X] 通道推送
    - [X] 点播
    - [X] 云台控制
    - [X] 平台状态查询
    - [X] 平台信息查询
    - [X] 平台远程启动
    - [X] 每个级联平台可自定义的虚拟目录
    - [X] 目录订阅与通知
    - [X] 录像查看与播放
    - [X] GPS订阅与通知（直播推流）
- [X] 支持自动配置ZLM媒体服务, 减少因配置问题所出现的问题;  
- [X] 多流媒体节点，自动选择负载最低的节点使用。
- [X] 支持启用udp多端口模式, 提高udp模式下媒体传输性能;
- [X] 支持公网部署； 
- [X] 支持ivp与zlm分开部署，提升平台并发能力
- [X] 支持拉流RTSP/RTMP，分发为各种流格式，或者推送到其他国标平台
- [X] 支持推流RTSP/RTMP，分发为各种流格式，或者推送到其他国标平台
- [X] 支持推流鉴权
- [X] 支持接口鉴权
- [X] 云端录像，推流/代理/国标视频均可以录制在云端服务器，支持预览和下载
- [X] 支持打包可执行jar和war
- [X] 支持跨域请求，支持前后端分离部署
- [X] 支持Mysql，Postgresql，金仓等数据库
- [X] 支持Onvif(目前在onvif分支，需要安装onvif服务，服务请在知识星球获取)

# 非开源的内容
- [X] ONVIF设备的接入，支持点播，云台控制，国标级联点播，自动点播。在[知识星球](https://t.zsxq.com/10WAnH2MP)放了试用安装包以及使用教程，没有使用时间限制，需要源码可以星球私信我或者邮箱联系。


# 授权协议
本项目自有代码使用宽松的MIT协议，在保留版权信息的情况下可以自由应用于各自商用、非商业的项目。 但是本项目也零碎的使用了一些其他的开源代码，在商用的情况下请自行替代或剔除； 由于使用本项目而产生的商业纠纷或侵权行为一概与本项目及开发者无关，请自行承担法律风险。 在使用本项目代码时，也应该在授权协议中同时表明本项目依赖的第三方库的协议


# 致谢
