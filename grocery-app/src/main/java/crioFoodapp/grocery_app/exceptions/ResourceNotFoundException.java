// File: ResourceNotFoundException.java
package crioFoodapp.grocery_app.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
