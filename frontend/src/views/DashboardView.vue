<script setup>
import { onMounted, onUnmounted, ref } from 'vue'
import { useSystemStore } from '../stores/system'

const systemStore = useSystemStore()
const streamRef = ref(null)

onMounted(async () => {
  await systemStore.loadInfo()
  streamRef.value = systemStore.connectHeartbeat()
})

onUnmounted(() => {
  streamRef.value?.close()
})
</script>

<template>
  <section class="card">
    <h2>框架初始化看板</h2>

    <div v-if="systemStore.loading">正在加载后端信息...</div>
    <div v-else-if="systemStore.info" class="info">
      <p><strong>平台：</strong>{{ systemStore.info.platformName }}</p>
      <p><strong>后端版本：</strong>{{ systemStore.info.backendVersion }}</p>
      <p><strong>后端时间：</strong>{{ systemStore.info.timestamp }}</p>
    </div>

    <h3>实时心跳流（SSE 示例）</h3>
    <ul>
      <li v-for="log in systemStore.heartbeatLogs" :key="log">{{ log }}</li>
    </ul>
  </section>
</template>
