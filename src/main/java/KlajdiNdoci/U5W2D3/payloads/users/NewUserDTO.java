package KlajdiNdoci.U5W2D3.payloads.users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;;
public record NewUserDTO (
        @NotEmpty(message = "Il nome deve essere necessariamente inserito!")
        @Size(min = 3, max= 20, message = "Il nome deve avere tra i 3 e i 20 caratteri")
        String nome,
        @NotEmpty(message = "Il cognome deve essere necessariamente inserito!" )
        @Size(min = 3, max= 20, message = "Il cognome deve avere tra i 3 e i 20 caratteri")
        String cognome,
        @NotEmpty(message = "L'email deve essere necessariamente inserita")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non é valida")
        String email){}
