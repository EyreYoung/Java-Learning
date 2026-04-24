# Roadmap: Java-Learning

## Overview

这份 roadmap 不把 `Java-Learning` 当成绿地产品，而是把它当成一个需要“先稳住主路径、再逐步理顺历史实验”的 brownfield 学习仓库。路线先做基线与边界，再稳定 Dubbo / Spring 两条可运行路径，随后清理 starter 与共享模块，最后补上验证与开发工作流。

## Phases

**Phase Numbering:**
- Integer phases (1, 2, 3): Planned milestone work
- Decimal phases (2.1, 2.2): Urgent insertions (marked with INSERTED)

- [ ] **Phase 1: Brownfield Baseline** - 建立模块边界、运行事实和风险基线
- [ ] **Phase 2: Dubbo Demo Stabilization** - 稳定 provider / consumer 主链路
- [ ] **Phase 3: Spring Learning Hardening** - 稳定 REST、JPA、Redis 和文档配置
- [ ] **Phase 4: Starter & Shared Module Cleanup** - 整理 starter 与共享实验代码边界
- [ ] **Phase 5: Verification Workflow** - 为关键链路补 smoke checks 与验证手册

## Phase Details

### Phase 1: Brownfield Baseline
**Goal**: 把当前仓库中哪些是主运行链路、哪些是实验代码、哪些是主要风险说清楚，并给后续 phase 建立可执行边界。
**Depends on**: Nothing (first phase)
**Requirements**: [BASE-01, BASE-02, DX-01, QUAL-02]
**Success Criteria** (what must be TRUE):
  1. 当前模块角色、入口类、关键端口和主要配置位置都有统一描述
  2. 后续 phase 可以基于明确文件位置和风险点拆计划，而不是继续猜
  3. 依赖漂移、测试缺口和边界混乱的风险已经被显式记录
**Plans**: 3 plans

Plans:
- [ ] 01-01: Consolidate module ownership and brownfield documentation
- [ ] 01-02: Normalize local runtime expectations for the runnable modules
- [ ] 01-03: Catalog dependency drift and verification gaps

### Phase 2: Dubbo Demo Stabilization
**Goal**: 让 Dubbo provider / consumer 的本地运行和验证路径清晰、稳定、可重复。
**Depends on**: Phase 1
**Requirements**: [DUBBO-01, DUBBO-02, DUBBO-03]
**Success Criteria** (what must be TRUE):
  1. `dubbo-provider` 和 `dubbo-consumer` 对注册中心的约定是一致的
  2. `/dubbo/hello` 可以作为本地 Dubbo happy path 的验证入口
  3. `dubbo-consumer` 中的实验代码不会再模糊真正的运行路径
**Plans**: 3 plans

Plans:
- [ ] 02-01: Unify registry and startup configuration for Dubbo apps
- [ ] 02-02: Verify the HTTP-to-Dubbo happy path end to end
- [ ] 02-03: Fence or document experimental Dubbo / custom RPC branches

### Phase 3: Spring Learning Hardening
**Goal**: 稳定 `spring-learning` 的数据、缓存、接口和文档相关行为，降低改动风险。
**Depends on**: Phase 1
**Requirements**: [SPR-01, SPR-02, SPR-03]
**Success Criteria** (what must be TRUE):
  1. `spring-learning` 能依赖仓库约定的 MySQL 和 Redis 设置稳定启动
  2. 账号 CRUD 与 cache demo 有清晰的验证方式
  3. JPA、事件、Swagger 改动前后至少有 smoke-level 保护
**Plans**: 3 plans

Plans:
- [ ] 03-01: Stabilize datasource and cache boot path
- [ ] 03-02: Verify account CRUD and demo endpoints
- [ ] 03-03: Add guardrails around event and API-doc wiring

### Phase 4: Starter & Shared Module Cleanup
**Goal**: 把 `email-spring-starter` 和 `java-learning-base` 的角色说清并整理遗留依赖问题。
**Depends on**: Phases 1-3
**Requirements**: [STRT-01, BASELIB-01, BASELIB-02]
**Success Criteria** (what must be TRUE):
  1. 邮件 starter 的启用方式、配置项和敏感信息策略是清晰的
  2. 共享学习样例不会继续和主运行链路混在一起
  3. 重复或过旧依赖有可执行的整理方向
**Plans**: 3 plans

Plans:
- [ ] 04-01: Document and harden starter configuration expectations
- [ ] 04-02: Separate shared learning code from runnable application paths
- [ ] 04-03: Reduce or catalog legacy dependency overlap

### Phase 5: Verification Workflow
**Goal**: 把最关键的运行链路变成可重复验证的工作流，为后续执行 phase 建立基础。
**Depends on**: Phases 2-4
**Requirements**: [QUAL-01]
**Success Criteria** (what must be TRUE):
  1. 关键模块至少有 smoke-level 自动化验证命令
  2. 手动验证步骤不再散落，需要时能在一个地方找到
  3. 后续 GSD 执行 phase 能以可信的 baseline 继续推进
**Plans**: 3 plans

Plans:
- [ ] 05-01: Establish smoke-test command set for runnable modules
- [ ] 05-02: Record repeatable manual verification playbooks
- [ ] 05-03: Close the highest-risk test gaps

## Progress

**Execution Order:**
Phases execute in numeric order: 1 → 2 → 3 → 4 → 5

| Phase | Plans Complete | Status | Completed |
|-------|----------------|--------|-----------|
| 1. Brownfield Baseline | 0/3 | Not started | - |
| 2. Dubbo Demo Stabilization | 0/3 | Not started | - |
| 3. Spring Learning Hardening | 0/3 | Not started | - |
| 4. Starter & Shared Module Cleanup | 0/3 | Not started | - |
| 5. Verification Workflow | 0/3 | Not started | - |
