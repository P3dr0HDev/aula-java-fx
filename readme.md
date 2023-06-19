# Aplicação de cadastro de cliente
Aplicação em javafx para cadastro de cliente
## Tecnologia
* Java 20
* Java fx
* PostgreSql

| Coluna 1 | Coluna 2 |
|----------| ---------|
| Valor | Valor 2 | 

```java
   public synchronized Connection getConexao() throws SQLException {
        if(conn == null){
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AulaJavaDB", "postgres", "postgres");
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/ecommerce", "root", "");
        }

        return conn;
    }
```
