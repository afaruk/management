package clientApi;

import java.io.Serializable;

public interface ActionRequest<T> extends Serializable {

    String getType();

    T getParameters();
}
