package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import io.dropwizard.db.DataSourceFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class MainConfig extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("dataSourceFactory")
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @JsonProperty("dataSourceFactory")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty("dataSourceFactory")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }
}
