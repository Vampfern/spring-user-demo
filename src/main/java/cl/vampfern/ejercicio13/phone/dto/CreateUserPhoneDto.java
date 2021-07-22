package cl.vampfern.ejercicio13.phone.dto;

import lombok.Data;

@Data
public class CreateUserPhoneDto {

    private String number;
    private String citycode;
    private String contrycode;

}
