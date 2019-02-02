package ir.ood.backend;

import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Configuration extends io.dropwizard.Configuration implements ConfigurationConstants {
    @Valid
    @NotNull
    public DataSourceFactory dataSourceFactory = new DataSourceFactory();
    public String jwtSecret;
    public String adminEmail;
    public String adminPassword;

    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

}
