package web.bike.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import web.bike.ecommerce.entity.Country;
import web.bike.ecommerce.entity.Product;
import web.bike.ecommerce.entity.ProductCategory;
import web.bike.ecommerce.entity.State;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        HttpMethod[] theUnsupportedMethods = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};
        // disable HTTP Methods for Product
        disableHttpMethods(Product.class,config, theUnsupportedMethods);
        disableHttpMethods(ProductCategory.class,config, theUnsupportedMethods);
        disableHttpMethods(State.class,config, theUnsupportedMethods);
        disableHttpMethods(Country.class,config, theUnsupportedMethods);

        // call an internal helper method
        exposeIds(config);
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedMethods) {
        config.getExposureConfiguration().forDomainType(theClass).withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedMethods)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedMethods)));
    }

    private void exposeIds(RepositoryRestConfiguration config){

        // expose entity ids

        // get a list of all entity clases from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // create an array of the entity types
        List<Class> entityClasses = new ArrayList<Class>();

        // get the entity types for the entities
        for(EntityType temEntityType : entities){
            entityClasses.add(temEntityType.getJavaType());
        }

        // expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
