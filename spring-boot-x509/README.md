# generate certificates | ca | etc

- Self Signed Root CA: `openssl req -x509 -sha256 -days 3650 -newkey rsa:4096 -keyout rootCA.key -out rootCA.crt`
- Server-side Certificate: `openssl req -new -newkey rsa:4096 -keyout localhost.key –out localhost.csr`
- Sign the request with our rootCA.crt certificate and its private key: `openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in localhost.csr -out localhost.crt -days 365 -CAcreateserial -extfile localhost.ext`
- Verify: `openssl x509 -in localhost.crt -text`
- Import to the Keystore: `openssl pkcs12 -export -out localhost.p12 -name "localhost" -inkey localhost.key -in localhost.crt`
- Create a keystore.jks repository and import the localhost.p12 file with a single command: `keytool -importkeystore -srckeystore localhost.p12 -srcstoretype PKCS12 -destkeystore keystore.jks -deststoretype JKS`

[created after this tutorial](https://www.baeldung.com/x-509-authentication-in-spring-security)
