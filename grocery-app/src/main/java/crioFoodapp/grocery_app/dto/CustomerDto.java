package crioFoodapp.grocery_app.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;

    // Proper constructor implementation
    public CustomerDto(Long id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
    
    // No-args constructor (needed for frameworks like Jackson)
    public CustomerDto() {
    }
}