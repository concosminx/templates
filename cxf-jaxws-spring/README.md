Spring - CXF Web Services - using `gretty`
===============================================

Starting the server
-------------------

`gradle appStart`

Stopping the server
-------------------

`gradle appStop`

Testing the server
------------------

`gradle -PrunClassName=com.nimsoc.cxf.ws.spring.client.Client -x check runSingle`


Check available services
------------------------

`http://localhost:8080/cxf_jaxws_spring/services`