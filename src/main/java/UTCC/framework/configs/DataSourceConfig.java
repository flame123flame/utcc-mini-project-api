package UTCC.framework.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration

@EnableTransactionManagement
public class DataSourceConfig {

	@Bean(name = "dataSource")
	@ConfigurationProperties("spring.datasource")
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}


}
