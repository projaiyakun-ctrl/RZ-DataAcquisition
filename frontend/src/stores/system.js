import { defineStore } from 'pinia'
import { fetchSystemInfo } from '../api/system'

export const useSystemStore = defineStore('system', {
  state: () => ({
    info: null,
    loading: false,
    heartbeatLogs: []
  }),
  actions: {
    async loadInfo() {
      this.loading = true
      try {
        const { data } = await fetchSystemInfo()
        this.info = data
      } finally {
        this.loading = false
      }
    },
    connectHeartbeat() {
      const stream = new EventSource('/api/system/stream')
      stream.addEventListener('heartbeat', (event) => {
        this.heartbeatLogs.unshift(event.data)
        this.heartbeatLogs = this.heartbeatLogs.slice(0, 10)
      })
      return stream
    }
  }
})
