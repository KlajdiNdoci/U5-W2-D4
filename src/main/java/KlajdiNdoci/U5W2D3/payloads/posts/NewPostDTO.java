package KlajdiNdoci.U5W2D3.payloads.posts;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

;import java.time.LocalDate;

public record NewPostDTO(
        @NotEmpty(message = "La categoria deve essere necessariamente inserito!")
        @Size(min = 3, max= 20, message = "La categoria deve avere tra i 3 e i 20 caratteri")
        String categoria,
        @NotEmpty(message = "Il titolo deve essere necessariamente inserito!")
        @Size(min = 1, message = "Il titolo deve avere almeno un carattere")
        String Titolo,
        @NotEmpty(message = "Il cognome deve essere necessariamente inserito!" )
        String contenuto,
        @NotEmpty(message = "La cover deve essere necessariamente inserita!")
        @Pattern(regexp = "^(http(s):\\/\\/.)[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$", message = "L'URL della cover non è valido")
        String cover,
        @NotEmpty(message = "Il tempo di lettura deve essere necessariamente inserito!")
        @Pattern(regexp = "^[0-9]+$", message = "Inserisci un numero valido")
        String tempoDiLettura,
        @NotEmpty(message = "L'email deve essere necessariamente inserita")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non é valida")
        String email,
        @NotEmpty(message = "La data di nascita deve essere necessariamente inserita!" )
        @Pattern(regexp = "^\\\\d{4}-\\\\d{2}-\\\\d{2}$", message = "La data inserita non é valida")
        LocalDate dataDiNascita
){}
