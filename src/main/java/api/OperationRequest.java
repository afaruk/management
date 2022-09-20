package api;

public interface OperationRequest<T> {

    String getType();

    T getParameters();
}
