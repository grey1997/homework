package org.geektimes.rest.client.convert;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

public interface HttpBodyConverter<E, R> extends MessageBodyReader<E>, MessageBodyWriter<R> {


}
