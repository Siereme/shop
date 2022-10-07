package app.search;

import org.hibernate.search.engine.backend.document.DocumentElement;
import org.hibernate.search.engine.backend.document.IndexObjectFieldReference;
import org.hibernate.search.mapper.pojo.bridge.PropertyBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.PropertyBridgeWriteContext;

public class Bridge implements PropertyBridge<String> {

    private final IndexObjectFieldReference skuIdObjectField;

    public Bridge(IndexObjectFieldReference skuIdObjectField) {
        this.skuIdObjectField = skuIdObjectField;
    }

    @Override
    public void write(DocumentElement target, String s, PropertyBridgeWriteContext context) {
        target.addObject( this.skuIdObjectField );
    }
}
