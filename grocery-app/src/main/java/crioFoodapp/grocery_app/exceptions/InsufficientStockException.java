// File: InsufficientStockException.java
package crioFoodapp.grocery_app.exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
