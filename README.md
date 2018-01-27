# knowledgeGo
答题助手，适用于 百万英雄 / 芝士超人 / 冲顶大会 / 百度贴吧 等多个直播答题类 APP，采用了接口识别，整合了多家ai平台的答案，帮助你更好的闯关。


## 特点

- 超快的响应速度
- 全面覆盖百万英雄/芝士超人/冲顶大会/贴吧等多个直播答题类APP
- 整合了多家ai答案，多维度参考值，答案交叉验证

## 运行截图
![成果展示](http://m.qpic.cn/psb?/7d196e8d-bcb0-4b10-96f0-58980105d3ae/G6o1NFd4K6GtqzqD6NwGQZon3N7xu6spx*QaR.WRZ.0!/b/dGcBAAAAAAAA&bo=FQFYAQAAAAADB28!&rf=viewer_4)
## 更新日志
- 2018.01.27
  - 修复了百度答题的bug
- 2018.01.25
  - 采用了多线程，提高了运行效率
  - 删除了多余的日志，修复了校验token出现的问题

## 方法原理

1. 使用了httpclient模拟报文获取到题目
2. 根据得到的题目自动打开相对应的百度页面
3. 同时获取蛋哥，搜狗，uc等ai答题辅助的答案



## 使用步骤

### 配置 java 环境

#### 安装 java
```
https://www.java.com/zh_CN/
```

#### 获得到冲顶大会的token
因为接口的变更现在需要使用fiddler等抓包工具获取到冲顶大会的X-Live-Session-Token。

#### 载入程序代码
##### 1.直接下载源码
在github中直接下载源码
##### 2.直接下载可执行文件（小白使用）

根据平台直接下载各版本运行文件： [releases](https://github.com/cjy9492/knowledgeGo/releases/)


### 运行

##### 1. 根据实际情况设置配置文件

**配置参数说明：**

```
#
session=你的冲顶大会token
#如果不使用贴吧答题可以不配置
cookies=你的百度cookies
```

#####  2. 程序运行

直接运行main函数

## 项目参考

  - [qanswer](https://github.com/silenceper/qanswer) (golang)
  - [MillionHeroAssistant](https://github.com/smileboywtu/MillionHeroAssistant) (python)


本项目在开发过程中参考了以上开源项目，在此对开源作者表示感谢！



