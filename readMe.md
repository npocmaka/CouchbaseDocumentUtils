examples:

boolean b=CouchbaseDocumentUtils.createDocument("http://localhost:8091", "admin", "pass", "my_bucket", "testId", "{\"cow\":\"moo\"}");

String d=CouchbaseDocumentUtils.getDocument("http://localhost:8091", "admin", "pass", "my_bucket", "testId");

System.out.println(d);

b=CouchbaseDocumentUtils.deleteDocument("http://localhost:8091", "admin", "pass", "my_bucket", "testId");


