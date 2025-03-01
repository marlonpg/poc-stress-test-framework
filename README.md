# poc-stress-test-framework

## start application 
`./mvnw spring-boot:run -Dspring.profiles.active=master`
## or like this for worker
`./mvnw spring-boot:run -Dspring.profiles.active=worker`


## goals 
- configurable
- parallel execution
- monitor custom properties(?)
- collect metrics
- generate report
- multiples instances (master and worker nodes)

## metrics
- response time
- throughput (requests per second)
- latency
- error rates