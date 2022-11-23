# Microservice architecture example

## Start the apps in order

1. **service-registry** (Eureka)
2. **config-server** (reads configuration from a git repo, from the File System or GitHub, see folder *store-this-in-a-git-repo*)
3. **zipkin** server
4. **api-gateway** (Gateway)
5. **order-service** and **payment-service**
