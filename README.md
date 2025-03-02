# poc-stress-test-framework

## start application 
`./mvnw spring-boot:run -Dspring-boot.run.profiles=master`
## or like this for worker
`./mvnw spring-boot:run -Dspring-boot.run.profiles=worker`


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