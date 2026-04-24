# Requirements: Java-Learning

**Defined:** 2026-04-16
**Core Value:** 保持每个学习实验都“看得懂、跑得起来、改得动”，而不是把仓库继续堆成一团只能靠记忆导航的 demo 集合。

## v1 Requirements

### Foundations

- [ ] **BASE-01**: 仓库具有完整的 brownfield 地图，能说明六个 Maven 模块各自负责什么、依赖什么、风险在哪
- [ ] **BASE-02**: 每个后续 phase 在开始执行前都能指向明确的代码位置和可观察成功标准
- [ ] **DX-01**: 开发者能快速判断每个可运行模块的启动方式、端口、入口类和主要接口

### Dubbo Demo

- [ ] **DUBBO-01**: `dubbo-provider` 能通过约定好的注册中心配置对外注册 `GreetingService`
- [ ] **DUBBO-02**: `dubbo-consumer` 暴露的 HTTP 接口能在本地栈中成功调用 Dubbo provider
- [ ] **DUBBO-03**: `dubbo-consumer` 中的实验代码与真实 Dubbo 路径边界清晰，不会再误导后续改动

### Spring Demo

- [ ] **SPR-01**: `spring-learning` 能稳定依赖仓库内约定的 MySQL + Redis 配置启动
- [ ] **SPR-02**: `spring-learning` 的账号 CRUD 与 cache demo 接口拥有清晰的行为说明和验证路径
- [ ] **SPR-03**: `spring-learning` 的 JPA、事件发布、Swagger 配置在修改前后都能被 smoke-level 验证

### Starter & Shared Modules

- [ ] **STRT-01**: `email-spring-starter` 的启用方式、配置项和敏感信息处理方式被明确记录
- [ ] **BASELIB-01**: `java-learning-base` 中的学习样例仍可保留，但不会继续和主运行链路混在一起
- [ ] **BASELIB-02**: 历史遗留和重复依赖（如 Dubbo / Mockito 分叉）有明确的整理方向

### Quality

- [ ] **QUAL-01**: 关键可运行模块至少具备 smoke-level 自动化验证命令
- [ ] **QUAL-02**: 高风险区域、依赖漂移点和测试缺口在执行 phase 前是可见的

## v2 Requirements

### Workflow

- **WF-01**: 建立 CI 流程，对重点模块自动执行测试和打包校验
- **WF-02**: 为核心 demo 提供统一的开发者运行手册或脚本入口

### Productization (Deferred)

- **PROD-01**: 如果未来把部分 demo 继续产品化，再补鉴权、配置分层和生产级安全基线
- **PROD-02**: 如果未来需要展示型入口，再考虑 UI 或 API portal

## Out of Scope

| Feature | Reason |
|---------|--------|
| 把全部历史算法 / 面试题样例都纳入严格工程治理 | 学习材料过多，收益低于成本 |
| 为当前 demo 直接建设生产级云部署方案 | 现阶段主要目标是 brownfield 稳定化与可维护性 |
| 新增前端应用来包装现有接口 | 不解决当前最关键的代码边界和验证问题 |

## Traceability

| Requirement | Phase | Status |
|-------------|-------|--------|
| BASE-01 | Phase 1 | Pending |
| BASE-02 | Phase 1 | Pending |
| DX-01 | Phase 1 | Pending |
| QUAL-02 | Phase 1 | Pending |
| DUBBO-01 | Phase 2 | Pending |
| DUBBO-02 | Phase 2 | Pending |
| DUBBO-03 | Phase 2 | Pending |
| SPR-01 | Phase 3 | Pending |
| SPR-02 | Phase 3 | Pending |
| SPR-03 | Phase 3 | Pending |
| STRT-01 | Phase 4 | Pending |
| BASELIB-01 | Phase 4 | Pending |
| BASELIB-02 | Phase 4 | Pending |
| QUAL-01 | Phase 5 | Pending |

**Coverage:**
- v1 requirements: 14 total
- Mapped to phases: 14
- Unmapped: 0 ✓

---
*Requirements defined: 2026-04-16*
*Last updated: 2026-04-16 after initial definition*
