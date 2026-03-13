import axios from 'axios'

export function fetchSystemInfo() {
  return axios.get('/api/system/info')
}
