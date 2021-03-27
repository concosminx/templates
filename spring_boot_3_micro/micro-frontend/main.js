let invoicesUrl = "http://localhost:8081/v1/invoices";
let partnersUrl = "http://localhost:8080/v1/partners";

let app = new Vue({
    el: '#app',
    data: {
      invoices: [],
	  partners: []
    }
  });

fetch(invoicesUrl)
    .then(r => r.json())
    .then(data => {
        app.invoices = data;
    });
	
fetch(partnersUrl)
    .then(r => r.json())
    .then(data => {
        app.partners = data;
    });	