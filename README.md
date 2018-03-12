# lightweight java library to integrate with NEM blockchain

Uses:
- feign(https://github.com/OpenFeign/feign) to make http calls to NIS(based on docs from https://bob.nem.ninja/docs/)
- hystrix(https://github.com/Netflix/Hystrix) to implement circuit breaker
- ribbon(https://github.com/Netflix/ribbon) to implement load balancing
- archaius(https://github.com/Netflix/archaius) to configure library

<h2>Install</h2>
Just install library to local maven storage

```bash
git clone https://github.com/rosklyar/nem-library.git
cd nem-library
mvn clean install
```

<h2>Usage</h2>
<h3>1. Import library as maven dependency<h3>

```xml
<dependency>
    <groupId>io.nem</groupId>
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
ConfigurationManager.getConfigInstance().setProperty("transaction.client.network", "TEST");
ConfigurationManager.getConfigInstance().setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", 20000);
```
You can also use separate file(or any other configuration source archaius supports - https://github.com/Netflix/archaius/wiki) to setup configuration. Add this to your application start up script to import configuration from file:

```bash
-Darchaius.configurationSource.additionalUrls=file:///apps/myapp/nem-library.properties
```
Example of properties file needed for MAIN network see at src/test/resources/nem-library.properties

<h3>3. Create client instance using DefaultNemClientFactory<h3>

```java
AccountClient accountClient = new DefaultNemClientFactory().createAccountClient();
BlockchainClient blockchainClient = new DefaultNemClientFactory().createBlockchainClient();
MosaicClient mosaicClient = new DefaultNemClientFactory().createMosaicClient();
NodeClient nodeClient = new DefaultNemClientFactory().createNodeClient();
StatusClient statusClient = new DefaultNemClientFactory().createStatusClient();
TransactionClient transactionClient = new DefaultNemClientFactory().createTransactionClient();
```

Example of usage you can see in tests package src/test/java/io/nem/client

You can support project if you want <br/>
XEM: NALNZB-Q4JJP2-PYAS6I-4KWTLT-367SJJ-RKXX6I-WUQR <br/>
ETH: 0x310535217083ba1073b4CB57B9DDc4dFF6176961 <br/>
XLM: GBBLG6YGAHVFNQJ6ZE7MCJENRYLHY2G475PRQKRWF46YZQ7Q3MES4NRD

<sub>Copyright (c) 2018</sub>
