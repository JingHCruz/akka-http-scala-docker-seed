# akka-http-scala-docker-seed
Sample akka-http seed project, ready for packaging with docker.

## Operations

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/7d91311bacdf7872c884)

## Prerequisites

- Git

- Docker **(optional)**

  Download at [https://docs.docker.com/engine/installation/](https://docs.docker.com/engine/installation/)

- Java 8 SDK

  Download at  [Java SE Development Kit 8 Downloads](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
  
- Scala 2.11.x SDK

  Download at  [http://www.scala-lang.org/download](http://www.scala-lang.org/download/)
  
  http://www.scala-lang.org/download/

- SBT (Scala Build Tool)
 
  Download at [http://www.scala-sbt.org/download.html](http://www.scala-sbt.org/download.html) and optionally add it to your `PATH` environment variable.

- AWS Configuration

  This application uses [AWS SQS](https://aws.amazon.com/sqs/) so it expects to find aws configuration and credentials in the usual locations (e.g. `~/.aws/credentials` and `~/.aws/config` on linux)

- Intellij IDEA Community Edition

  Download at [https://www.jetbrains.com/idea/](https://www.jetbrains.com/idea/)

- REST API client

  We recommend Postman. [Chrome-plugin](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en)

## Structure

- `src/main/resources`: configuration files 

- `src/main/scala/com/vtex/akkahttpseed/actors`: actor classes
 
- `src/main/scala/com/vtex/akkahttpseed/models`: case classes and objects used for many reasons such as for request validation, response formats and marshalling (converting classes and objects to/from serialized formats, such as json).

- `src/main/scala/com/vtex/akkahttpseed/routes`: classes that define routes (i.e. what paths and methods trigger which operations) and call whatever resources (actor operations, web services, etc) they need to complete their tasks.

- `src/main/scala/com/vtex/akkahttpseed/utils`: utils directory contains code that is generic enough so as to be used in other projects.

- `src/main/scala/com/vtex/akkahttpseed/AkkaHttpScalaDockerSeed.scala`: this file can be thought of as a "main" method. Here the actor system is started, others actors are started too and all routes are merged.

- `src/test`: test classes

## Behaviour

This sample app uses akka-http to expose operations that write and read to/from an AWS SQS queue. 

In addition, a worker is started off when you start the application. 

The worker will periodically (every 10 seconds) write sample messages to the queue. 

This means that, when you read from the queue, chances are you will see messages sent automatically by the worker (as well as the ones you've manually sent using the write operation)


## Installation & Running

#### Creating Intellij Project

> When opening Intellij for the first time, check the `Scala Plugin` option so it gets downloaded too

- Open Intellij and create a new project
- Under Project location inform the location where you cloned this repo
- Select `1.8` under Project SDK
- Select `2.11.8` under Scala version (if unavailable, any other 2.11 version is fine too)
- Check `"Sources"` and `"Sources for SBT and plugins`" unless your connection is poor
- ![intellij](http://i.imgur.com/mOxeZdVg.png)

#### Run configuration

Once the intellij project is created, you need to define how you will run it:

- Open `Run` -> `Edit Configurations...`
- Click the green cross on the top left corner
- Select `SBT Task` and create a `run` task, as per the following image:
- ![img](http://i.imgur.com/kOss71d.png)

#### Application configuration

You'll need to change some configuration in `src/main/resources/application.conf`, namely:

- your queue name in SQS
- the api key for using the stock quote API (use what's been given to you or get a new one at [https://www.quandl.com/users/login](https://www.quandl.com/users/login))
- the message that you want the automatic worker to write to the aforementioned queue

#### Docker packaging

This application uses [sbt-native-packager](https://github.com/sbt/sbt-native-packager) which provides a docker plugin to package sbt projects.

Just run `sbt docker:publishLocal` at your project root.

#### Run docker container

> If you're not running linux, replace `~/.aws` with the path to the AWS configuration directory

After packaging the app as per the previous step, run `$ docker ps --latest` to see its name.

You should see a name like `sample-project/akka-http-docker-seed:17fb541d99b587241d22f86d4170f65e6c40f9e1` (your exact values will be different)

To run the container containing your app, run the following command, while replacing the image name (as above) with your own value:

`$ docker run -dit -v ~/.aws:/usr/sbin/.aws -p 5000:5000 sample-project/akka-http-docker-seed:17fb541d99b587241d22f86d4170f65e6c40f9e1`

Test your application by opening *http://localhost:5000/healthcheck* on a web browser.

### See also

#### Useful links
[Akka and Scala running samples from Lightbend Activator](https://www.lightbend.com/activator/download)
[The Reactive Manifesto](http://www.reactivemanifesto.org/)

#### Feedback
Send feedback to `felipe.almeida@vtex.com.br` or `wellfabr@vtex.com.br`
