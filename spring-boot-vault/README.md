# Use vault standalone on windows 
- download from [https://developer.hashicorp.com/vault/downloads](https://developer.hashicorp.com/vault/downloads)
- start the server in development mode `vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"` (see [docs](https://developer.hashicorp.com/vault/tutorials/getting-started/getting-started-dev-server))

- write|get|delete a secret: 
```shell
vault kv put secret/spring-boot-vault myprops.username=JohnDoe myprops.password=pa$$w0rd
vault kv put secret/spring-boot-vault/cloud myprops.username=CloudJoe myprops.password=$uper$secure
vault kv get secret/spring-boot-vault
vault kv delete secret/spring-boot-vault
```