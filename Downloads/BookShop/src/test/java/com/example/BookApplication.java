package com.example;

import com.example.core.Author;
import com.example.core.Book;
import com.example.db.AuthorDAO;
import com.example.db.BookDAO;
import com.example.resource.authorResource;
import com.example.resource.bookResource;
import io.dropwizard.*;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BookApplication extends Application<BookConfiguration>{

   private Bootstrap<BookConfiguration> bootstrap;
    public static void main(String[] args) throws Exception {
        new BookApplication().run(args);
    }


    private final HibernateBundle<BookConfiguration> hibernateBundle =
            new HibernateBundle<BookConfiguration>(Author.class, Book.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(BookConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };
    @Override
    public void initialize(Bootstrap<BookConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(new MigrationsBundle<BookConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(BookConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(hibernateBundle);

    }
    @Override
    public void run(BookConfiguration bookConfiguration, Environment environment) throws Exception {
       final BookDAO bookDAO=new BookDAO(hibernateBundle.getSessionFactory());
       final AuthorDAO authorDAO=new AuthorDAO(hibernateBundle.getSessionFactory());
       environment.jersey().register(new bookResource(bookDAO));
       environment.jersey().register(new authorResource(authorDAO));
    }
}
