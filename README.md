# Lightweight java library to integrate with NEM blockchain

Uses:
- feign(https://github.com/OpenFeign/feign) to make http calls to NIS(based on docs from https://bob.nem.ninja/docs/)
- hystrix(https://github.com/Netflix/Hystrix) to implement circuit breaker
- ribbon(https://github.com/Netflix/ribbon) to implement load balancing
- archaius(https://github.com/Netflix/archaius) to configure library

<h2>Install</h2>
Just install library to local maven storage. I'm going to add it to maven central in a few days.

```bash
git clone https://github.com/rosklyar/nem-library.git
cd nem-library
mvn clean install
```

<h2>Usage</h2>
<h3>1. Import library as maven dependency</h3>

```xml
<dependency>
    <groupId>com.github.rosklyar</groupId>
    <artifactId>nem-library</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

<h3>2. Configure library using archaius</h3>
I used static config instance in tests to setup configuration:

```java
ConfigurationManager.getConfigInstance().setProperty("transactionApi.ribbon.listOfServers", "153.122.112.137:7890");
ConfigurationManager.getConfigInstance().setProperty("accountApi.ribbon.listOfServers", "153.122.112.137:7890");
ConfigurationManager.getConfigInstance().setProperty("mosaicApi.ribbon.listOfServers", "153.122.112.137:7890");
ConfigurationManager.getConfigInstance().setProperty("nodeApi.ribbon.listOfServers", "153.122.112.137:7890");
ConfigurationManager.getConfigInstance().setProperty("statusApi.ribbon.listOfServers", "153.122.112.137:7890");
ConfigurationManager.getConfigInstance().setProperty("blockchainApi.ribbon.listOfServers", "153.122.112.137:7890");
ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", 20000);
```
You can also use separate file(or any other configuration source archaius supports - https://github.com/Netflix/archaius/wiki) to setup configuration. Add this to your application start up script to import configuration from file:

```bash
-Darchaius.configurationSource.additionalUrls=file:///apps/myapp/nem-library.properties
```
Example of properties file needed for MAIN network see at src/test/resources/nem-library.properties.<br>
You also can configure hystrix(circuit breaker) and ribbon(load balancing) with archaius configuration. For details see projects documentation pages:<br>
- https://github.com/Netflix/Hystrix/wiki/Configuration
- https://github.com/Netflix/ribbon/wiki/Programmers-Guide

<h3>3. Create client instance using DefaultNemClientFactory</h3>

```java
AccountClient accountClient = new DefaultNemClientFactory().createAccountClient("accountApi");
BlockchainClient blockchainClient = new DefaultNemClientFactory().createBlockchainClient("blockchainApi");
MosaicClient mosaicClient = new DefaultNemClientFactory().createMosaicClient("mosaicApi");
NodeClient nodeClient = new DefaultNemClientFactory().createNodeClient("nodeApi");
StatusClient statusClient = new DefaultNemClientFactory().createStatusClient("statusApi");
```

```java
DefaultNemClientFactory factory = new DefaultNemClientFactory();
        transactionClient = factory.createTransactionClient(
                "transactionApi",
                DefaultNemClientFactory.MAIN,
                mosaicClient,
                accountClient,
                nodeClient
        );
```

<h2>Simple clients</h2>

You can create needed clients much more simple using just url of desired NIS:

```java
AccountClient accountClient = new DefaultNemClientFactory().simpleAccountClient("http://153.122.112.137:7890");
BlockchainClient blockchainClient = new DefaultNemClientFactory().simpleBlockchainClient("http://153.122.112.137:7890");
MosaicClient mosaicClient = new DefaultNemClientFactory().simpleMosaicClient("http://153.122.112.137:7890");
NodeClient nodeClient = new DefaultNemClientFactory().simpleNodeClient("http://153.122.112.137:7890");
StatusClient statusClient = new DefaultNemClientFactory().simpleStatusClient("http://153.122.112.137:7890");
TransactionClient simpleTransactionClient = new DefaultNemClientFactory().simpleTransactionClient("http://153.122.112.137:7890", DefaultNemClientFactory.MAIN);
```

Example of usage you can see in tests package src/test/java/com/github/rosklyar/client

You can support project if you want <br/>
XEM: NALNZB-Q4JJP2-PYAS6I-4KWTLT-367SJJ-RKXX6I-WUQR <br/>
ETH: 0x310535217083ba1073b4CB57B9DDc4dFF6176961 <br/>
XLM: GBBLG6YGAHVFNQJ6ZE7MCJENRYLHY2G475PRQKRWF46YZQ7Q3MES4NRD

<sub>Copyright (c) 2018</sub>
