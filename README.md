# Lucky

> 幸运抽奖项目

## 包结构介绍

service 包结构：
- activity: 业务
  - facade: 门面，流程编排（未必每个业务都有该包）
  - model: service 所需 model
    - vo: 对外展示视图
    - req: 复杂服务入参
    - res: 复杂服务返回值
    - context: 上下文
  - service: 业务实现/基础数据支撑