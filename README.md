# RZ-DataAcquisition

基于 **Spring Boot + Vue3** 的数据实时采集平台框架（初始化版）。

## 项目结构

```text
RZ-DataAcquisition/
├── backend/   # Spring Boot 后端
└── frontend/  # Vue3 + Vite 前端
```

## 后端（Spring Boot）

已完成：
- 基础工程搭建（Java 17 / Spring Boot 3）
- CORS 配置（允许前端本地开发端口）
- 基础系统信息接口：`GET /api/system/info`
- SSE 实时流示例接口：`GET /api/system/stream`
- 项目内 Maven 镜像配置：`backend/.mvn/settings.xml`

启动（使用项目内镜像配置）：

```bash
cd backend
mvn -s .mvn/settings.xml spring-boot:run
```

## 前端（Vue3）

已完成：
- Vue3 + Vite 基础工程
- Vue Router / Pinia 初始化
- 仪表盘首页（展示后端信息）
- 对接 SSE 心跳流并实时展示
- 项目内 npm 镜像配置：`frontend/.npmrc`

启动：

```bash
cd frontend
npm install
npm run dev
```

## 下一步建议

你可以继续告诉我平台需要的模块和功能，我会在当前框架基础上逐步补齐，例如：
- 数据源管理（MQTT / OPC UA / Modbus / HTTP）
- 采集任务编排与调度
- 实时监控看板
- 告警规则与通知
- 数据存储与历史查询
