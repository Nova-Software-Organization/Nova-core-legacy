package com.api.apibackend.modules.Log.infra.plugin;
// package com.api.apibackend.Modules.Domain.Log.infra.plugin;
// package com.api.apibackend.Modules.Infraestructure.plugin;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// import org.apache.logging.log4j.core.appender.db.jdbc.ConnectionSource;
// import org.apache.logging.log4j.core.appender.db.jdbc.FactoryMethodConnectionSource;
// import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
// import org.apache.logging.log4j.core.config.Configuration;
// import org.apache.logging.log4j.core.config.plugins.Plugin;

// @Plugin(name = "LogConnectionFactory", category = "Core")
// public class LogConnectionFactory extends FactoryMethodConnectionSource<Connection> {

//     public LogConnectionFactory(String description, String user, String password, String url) {
//         super(description, user, password, url);
//     }

//     @Override
//     public Connection createConnection() {
//         try {
//             Class.forName(getDriver());
//             return DriverManager.getConnection(getUrl(), getUser(), getPassword());
//         } catch (SQLException | ClassNotFoundException e) {
//             // Tratar exceções ou fazer o log de erros de conexão aqui, se necessário.
//             return null;
//         }
//     }

//     public static ConnectionSource<Connection> createConnectionSource(final Configuration configuration) {
//         return new LogConnectionFactory(configuration.getName(), "your_db_username", "your_db_password", "your_db_url");
//     }
// }
