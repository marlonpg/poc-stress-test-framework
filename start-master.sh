#!/bin/bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=master -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"