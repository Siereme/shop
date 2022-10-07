package app.search;

import org.hibernate.search.engine.search.predicate.SearchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.SearchPredicateFactory;
import org.hibernate.search.engine.search.predicate.factories.NamedPredicateProvider;
import org.hibernate.search.engine.search.predicate.factories.NamedPredicateProviderContext;

public class SearchPredicateProvider implements NamedPredicateProvider {

    @Override
    public SearchPredicate create(NamedPredicateProviderContext context) {
        SearchPredicateFactory f = context.predicate();
        String query = (String) context.param( "query" );

        return f.bool(b -> {
            b.must(f.matchAll());
            b.must(f.match().field("name").matching(query)
                    .constantScore().boost(2.0f));
            b.should(f.match().field("description.shortDescription").matching(query));
            b.should(f.match().field("description.longDescription").matching(query));
        }).toPredicate();
    }
}
