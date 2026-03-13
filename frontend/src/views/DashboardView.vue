<script setup>
import { onMounted, onUnmounted, ref } from 'vue'
import { useSystemStore } from '../stores/system'

const systemStore = useSystemStore()
const streamRef = ref(null)

const dataSources = [
  { name: 'pg-warehouse-prod', type: 'PostgreSQL', desc: '主数据仓库，存储客户、订单、产品核心数据', status: '已连接', tag: '生产' },
  { name: 'mysql-analytics', type: 'MySQL 8.0', desc: '分析数据库，用于 BI 报表与指标计算', status: '已连接', tag: '分析' },
  { name: 'sap-erp-finance', type: 'SAP ERP', desc: 'SAP ERP 财务模块，采购/应付/总账数据', status: '同步中', tag: 'ERP' },
  { name: 'kafka-events-prod', type: 'Kafka 3.5', desc: '生产事件流，用户行为、交易、系统事件', status: '已连接', tag: '实时' },
  { name: 'mongodb-users', type: 'MongoDB 6.0', desc: '用户画像与偏好数据，部分集合同步延迟', status: '告警', tag: 'NoSQL' },
  { name: 's3-data-lake', type: 'AWS S3', desc: '企业数据湖，Parquet/CSV/JSON 格式', status: '已连接', tag: '数据湖' }
]

onMounted(async () => {
  await systemStore.loadInfo()
  streamRef.value = systemStore.connectHeartbeat()
})

onUnmounted(() => {
  streamRef.value?.close()
})
</script>

<template>
  <section class="content">
    <div class="page-title-row">
      <div>
        <h1>数据源目录</h1>
        <p>管理所有已接入的数据源，共 {{ dataSources.length }} 个数据源</p>
      </div>
      <button class="primary-btn">+ 新建数据源</button>
    </div>

    <div class="filters">
      <button class="chip active">全部</button>
      <button class="chip">数据库</button>
      <button class="chip">业务系统</button>
      <button class="chip">API</button>
      <button class="chip">流式数据</button>
    </div>

    <div class="search-row">
      <input placeholder="搜索数据源名称、类型..." />
      <select>
        <option>全部状态</option>
      </select>
    </div>

    <div class="cards">
      <article class="source-card" v-for="item in dataSources" :key="item.name">
        <div class="source-card__head">
          <div>
            <h3>{{ item.name }}</h3>
            <span>{{ item.type }}</span>
          </div>
          <span class="status">{{ item.status }}</span>
        </div>
        <p>{{ item.desc }}</p>
        <div class="source-card__meta">
          <span>{{ item.tag }}</span>
        </div>
      </article>
    </div>

    <section class="heartbeat">
      <h3>实时心跳流（SSE）</h3>
      <p v-if="systemStore.loading">正在加载后端状态...</p>
      <p v-else-if="systemStore.info">后端版本：{{ systemStore.info.backendVersion }} · 时间：{{ systemStore.info.timestamp }}</p>
      <ul>
        <li v-for="log in systemStore.heartbeatLogs" :key="log">{{ log }}</li>
      </ul>
    </section>
  </section>
</template>
