# Fizz Buzz Cloud

**Fizz Buzz Game Solver with Spring Cloud, Spring boot and Angular**

This is a [proof-of-concept application](https://fizzbuzzcloud.herokuapp.com/), which demonstrates [Microservice Architecture Pattern](http://martinfowler.com/microservices/) using Spring Boot, Spring Cloud and Docker.
With a pretty neat user interface, by the way.

#### Notes
The player designated to go first says the number "1", and each player henceforth counts one number in turn. However, any number divisible by three is replaced by the word fizz and any divisible by five by the word buzz. Numbers divisible by both become fizz buzz.

Allowed Inputs are 
- Single value like "500"
- manual values like "1 15 20"
- range "1-100"

For example, a typical round of fizz buzz would start as follows:

1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, Fizz Buzz, 16, 17, Fizz, 19, Buzz, Fizz, 22, 23, Fizz, Buzz, 26, Fizz, 28, 29, Fizz Buzz, 31, 32, Fizz, 34, Buzz, Fizz, ...
(Wikipedia)

A custom game mode has been added, it will provide the chance to add or change existing rules, like (7 - Woof), so it will return "Woof" to every number divisible by "7", was added an ordination option, to provide or not an ordered output, and at last 2 new game modes, besides the classic "Divisible" game mode, was added the "Containing" mode that will return the "Word" when the tested number contains the number key, for instance, 73 will be replaced by "Fizz Woof" because contains 7 and 3, and the last mode is "Both" that is the union of "Divisible" and "Containing" modes.

### Log analysis

Centralized logging can be very useful when attempting to identify problems in a distributed environment. Elasticsearch, Logstash and Kibana stack lets you search and analyze your logs, utilization and network activity data with ease.

## Infrastructure automation

Deploying micro services, with their interdependence, is much more complex process than deploying monolithic application. It is important to have fully automated infrastructure. We can achieve following benefits with Continuous Delivery approach:

- The ability to release software anytime
- Any build could end up being a release
- Build artifacts once - deploy as needed

Here is a simple Continuous Delivery workflow, implemented in this project:


## How to run all the things?

Keep in mind, that you are going to start 5 Spring Boot applications and RabbitMq. Make sure you have `4 Gb` RAM available on your machine. You can always run just vital services though: Gateway, Registry, Config.

#### Before you start
- Install Docker and Docker Compose.
- Make sure to build the project: `mvn package [-DskipTests]`

#### Production mode
In this mode, all latest images will be pulled from Docker Hub.
Just copy `docker-compose.yml` and hit `docker-compose up`

#### Development mode
If you'd like to build images yourself (with some changes in the code, for example), you have to clone all repository and build artifacts with maven. Then, run `docker-compose -f docker-compose.yml -f docker-compose.dev.yml up`

`docker-compose.dev.yml` inherits `docker-compose.yml` with additional possibility to build images locally and expose all containers ports for convenient development.

#### Important endpoints
- http://localhost:80 - Gateway
- http://localhost:7000 - FizzBuzzService
- http://localhost:8761 - Eureka Dashboard
- http://localhost:9000/hystrix - Hystrix Dashboard (paste Turbine stream link on the form)
- http://localhost:8989 - Turbine stream (source for the Hystrix Dashboard)
- http://localhost:15672 - RabbitMq management (default login/password: guest/guest)

#### Notes
All Spring Boot applications require already running [Config Server](https://github.com/MuhammdAli/FizzBuzzCloud#config) for startup. But we can start all containers simultaneously because of `depends_on` docker-compose option.

Also, Service Discovery mechanism needs some time after all applications startup. Any service is not available for discovery by clients until the instance, the Eureka server and the client all have the same metadata in their local cache, so it could take 3 heartbeats. Default heartbeat period is 30 seconds.

## Feedback welcome

Fizz Buzz Cloud is open source, and would greatly appreciate your help. Feel free to contact me with any questions.
