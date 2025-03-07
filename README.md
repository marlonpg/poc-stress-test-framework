# poc-stress-test-framework

## build
`./mvnw clean install`

## start application 
`./mvnw spring-boot:run -Dspring-boot.run.profiles=master -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"`
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