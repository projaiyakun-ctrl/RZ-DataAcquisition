<template>
  <div class="app-shell">
    <aside class="sidebar">
      <div class="brand">RZ</div>
      <nav class="icon-nav">
        <button class="icon-btn active">☰</button>
        <button class="icon-btn">⌂</button>
        <button class="icon-btn">⎈</button>
        <button class="icon-btn">⚙</button>
      </nav>
      <div class="sidebar-footer">张</div>
    </aside>

    <aside class="menu-panel">
      <header class="menu-panel__header">
        <h2>Foundry 工作区</h2>
      </header>
      <section class="menu-group">
        <h3>概览</h3>
        <a class="menu-item" href="#">Foundry 首页</a>
      </section>
      <section class="menu-group">
        <h3>数据建设</h3>
        <a class="menu-item active" href="#">数据接入中心</a>
        <a class="menu-item" href="#">数据开发与存储</a>
        <a class="menu-item" href="#">数据治理与质量</a>
      </section>
      <section class="menu-group">
        <h3>语义与知识</h3>
        <a class="menu-item" href="#">资产与元数据</a>
        <a class="menu-item" href="#">知识库中心</a>
      </section>
    </aside>

    <main class="workspace">
      <header class="topbar">
        <div class="crumbs">一体化 AI 数据智能平台 〉 Foundry 工作区 〉 数据接入中心</div>
        <div class="topbar__right">
          <span class="env-tag">● 生产环境</span>
          <input class="search" placeholder="搜索资源、对象、文档..." />
        </div>
      </header>

      <section class="tabbar">
        <button class="tab active">数据源目录</button>
        <button class="tab">接入任务</button>
        <button class="tab">调度配置</button>
        <button class="tab">日志与告警</button>
        <button class="tab">连接器管理</button>
      </section>

      <RouterView />
    </main>
  </div>
</template>
