# Dubbo

![img](http://dubbo.apache.org/docs/en-us/user/sources/images/dubbo-architecture-roadmap.jpg)

## Monolithic architecture

- traffic is low
- the key is ORM/DAO layer to load(bottleneck)

## Vertical architecture

- MVC
- accelerate Frontend development

## Distributed service architecture

- core businesses are extracted and served as independent services
- forms a stable service center
- distributed service framework(RPC) for reuse

## Flow Computing architecture

- capacity evaluation
- manage cluster capacity to improve the utilization of cluster
- SOA/resource scheduling/governance center


## Requirements of Dubbo

- Register Center
- Governance Center
- Monitor Center
- Resource Center
- Provider/Consumer/Composer
- Service Dependencies
- Soft load balancing
- Dynamic Change Weight and threshold

## Specification of Dubbo Node Rule

- Provider: provider exposes remote service
- Consumer: consumer calls the remote service
- Registry: for service discovery and configuration
- Monitor:  metrics/counts/rt
- Container: service lifecycle management

- Deployer
- Repository
- Scheduler
- Admin
- Registry
- Monitor

## Usage

- Local-provider/consumer.xml
- Remote-provider/consumer.xml
