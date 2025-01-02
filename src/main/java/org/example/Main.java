package org.example;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application<MainConfig> {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello and welcome!");
        new Main().run(args);
    }

    private final HibernateBundle<MainConfig> hibernateBundle =
            new HibernateBundle<MainConfig>(Student.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(MainConfig configuration) {
                    return configuration.getDataSourceFactory();  // Accesses database configuration from the YAML file
                }
            };

    @Override
    public void initialize(Bootstrap<MainConfig> bootstrap) {
        bootstrap.addBundle(hibernateBundle); // Register HibernateBundle
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

    @Override
    public void run(MainConfig mainConfig, Environment environment) throws Exception {
        System.out.println("Session Factory: " + hibernateBundle.getSessionFactory());
//        if (mainConfig.getDataSourceFactory() == null) {
//            throw new IllegalStateException("DataSourceFactory is not properly initialized.");
//        }
        final StudentDAO studentDAO = new StudentDAO(hibernateBundle.getSessionFactory());  // Initializes DAO with Hibernate session factory
        final StudentService studentService = new StudentService(studentDAO);
        final StudentResource studentResource = new StudentResource(studentService);

        //environment.jersey().register(studentResource);
        environment.jersey().register(new TestResource());
    }


}