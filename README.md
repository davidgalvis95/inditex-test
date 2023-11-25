# Inditex PVP Fetcher

### Description

The following API queries a db from Inditex where there are product prices for different brands belonging all of them to the Inditex group. The price fluctuates depending on the date it is queried given some price lists that apply some discount rate depending on its kind. So, basically this API returns that final price that comes up after the calculation of the price considering that price list.

For the implementation, there was adopted a non-blocking paradigm so that multiple requests for this API can be handled at the same time, given that querying this information might require high concurrency. Hence there was adopted the spring reactive architecture combined with a standard layered architecture for the REST service.

The API is currently running in port 9200, and the docs for the api can be found in this link http://localhost:9200/webjars/swagger-ui/index.html.
Here is and example of a curl request that can be sent to test the api:

`curl --location 'http://localhost:9200/api/v1/catalog/prices?productId=35455&brandId=1&targetDate=2020-06-14-16%3A00%3A00'`