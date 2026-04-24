# Java-Learning

## What This Is

`Java-Learning` 是一个 brownfield 的多模块 Java 学习仓库。它同时承载了可运行的 Spring Boot / Dubbo demo、一个邮件 starter，以及大量 Java 基础与中间件实验代码，主要服务于“边学边试、边改边留档”的个人工程流。

## Core Value

保持每个学习实验都“看得懂、跑得起来、改得动”，而不是把仓库继续堆成一团只能靠记忆导航的 demo 集合。

## Requirements

### Validated

- ✓ Dubbo provider / consumer 的 `GreetingService` happy path 已经存在于 `dubbo-api`、`dubbo-provider`、`dubbo-consumer` 中 — existing
- ✓ `spring-learning` 已经提供基础 REST、JPA 和 Redis cache demo — existing
- ✓ `docker-compose.yml` 已经能编排本地 Redis / MySQL / Nacos / Kafka / ZooKeeper / Nginx / app 容器 — existing
- ✓ `email-spring-starter` 已经通过 `spring.factories` 暴露自动配置入口 — existing
- ✓ `java-learning-base` 已经沉淀出大量 Java 主题练习代码 — existing

### Active

- [ ] 给当前 brownfield 仓库建立清晰的 GSD 规划基线，让后续 phase 不再基于猜测拆任务
- [ ] 稳定 Dubbo 与 Spring 两条主要可运行路径，降低“代码在但不确定怎么跑”的摩擦
- [ ] 明确 starter、共享 demo、实验代码之间的边界，减少误改和依赖漂移
- [ ] 提升最关键模块的 smoke-level 验证能力，为后续执行 phase 打底

### Out of Scope

- 把这个仓库改造成单一的生产级业务系统 — 当前价值在于学习与演示，不在于做成统一产品
- 一次性重写所有历史 demo / 算法练习 — 范围过大，会把学习沉淀和可运行主路径一起打碎
- 引入前端界面或完整权限体系 — 这不是当前 brownfield 稳定化的核心阻塞点

## Context

- 仓库采用 Maven reactor，多模块共享根 `pom.xml` 的版本和依赖管理
- 目前真正的主运行面主要是 `dubbo-provider`、`dubbo-consumer`、`spring-learning`
- `dubbo-consumer` 与 `java-learning-base` 都混有较多实验性质代码，模块边界不够清楚
- 顶层 `README.md` 基本为空，仓库事实主要散落在源码、配置和 Docker 文件里
- 本次 GSD 初始化采用 brownfield 视角：先尊重现有代码，再规划后续清理与增强

## Constraints

- **Tech stack**: Java 17 + Spring Boot 2.6.6 + Maven reactor — 现有模块已经围绕这套栈组织
- **Brownfield**: 不能假设这是绿地项目 — 已有 demo、旧实验和混合依赖都要纳入规划
- **Local Infra**: Redis / MySQL / Nacos / Kafka / ZooKeeper 通过 Docker Compose 提供 — 本地演示链路依赖这些服务
- **Learning-first**: 需要保留实验代码的学习价值 — 清理时要避免把可复用的知识样例一起抹掉

## Key Decisions

| Decision | Rationale | Outcome |
|----------|-----------|---------|
| 用 brownfield GSD 初始化，而不是把仓库当成 greenfield 项目 | 现有代码量和模块耦合已经足够大，先做 codebase map 才不会后续 phase 失真 | ✓ Good |
| 当前 roadmap 先聚焦“稳定主路径 + 明确边界 + 增加验证” | 这是最能立刻提升仓库可维护性的方向 | — Pending |
| 规划文档保留在仓库中（`commit_docs: true`） | 这个项目本身需要可追踪的学习过程和决策历史 | — Pending |
| 粒度采用 standard，后续 phase 默认允许并行 planning / verification | 这个仓库既不够小，也还没复杂到必须 fine-grained 切碎 | — Pending |

## Evolution

This document evolves at phase transitions and milestone boundaries.

**After each phase transition** (via `$gsd-transition`):
1. Requirements invalidated? → Move to Out of Scope with reason
2. Requirements validated? → Move to Validated with phase reference
3. New requirements emerged? → Add to Active
4. Decisions to log? → Add to Key Decisions
5. "What This Is" still accurate? → Update if drifted

**After each milestone** (via `$gsd-complete-milestone`):
1. Full review of all sections
2. Core Value check — still the right priority?
3. Audit Out of Scope — reasons still valid?
4. Update Context with current state

---
*Last updated: 2026-04-16 after initialization*
