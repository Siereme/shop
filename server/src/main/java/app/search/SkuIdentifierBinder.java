package app.search;


import org.hibernate.search.engine.backend.document.model.dsl.IndexSchemaObjectField;
import org.hibernate.search.mapper.pojo.bridge.binding.PropertyBindingContext;
import org.hibernate.search.mapper.pojo.bridge.mapping.programmatic.PropertyBinder;

public class SkuIdentifierBinder implements PropertyBinder {
    @Override
    public void bind(PropertyBindingContext context) {
        context.dependencies().useRootOnly();

        IndexSchemaObjectField skuIdObjectField = context.indexSchemaElement()
                .objectField( context.bridgedElement().name() );

        context.bridge( String.class, new Bridge(
                skuIdObjectField.toReference()
        ) );

        skuIdObjectField.namedPredicate(
                "searchQuery",
                new SearchPredicateProvider()
        );
    }
}
