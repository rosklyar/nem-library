# lightweight java library to integrate with NEM blockchain

Uses feign(https://github.com/OpenFeign/feign) to make http calls to NIS(based on docs from https://bob.nem.ninja/docs/)
and nem.core(https://github.com/NEMPH/nem.core) for signing transactions

<h2>Build</h2>
Requires mvn validate step to install nem-core(from dependencies folder) to maven

```bash
git clone https://github.com/rosklyar/nem-library.git
cd nem-library
mvn validate
mvn clean install
```

You can support project if you want
XEM: NALNZB-Q4JJP2-PYAS6I-4KWTLT-367SJJ-RKXX6I-WUQR
ETH: 0x310535217083ba1073b4CB57B9DDc4dFF6176961
XLM: GBBLG6YGAHVFNQJ6ZE7MCJENRYLHY2G475PRQKRWF46YZQ7Q3MES4NRD

<sub>Copyright (c) 2018</sub>
