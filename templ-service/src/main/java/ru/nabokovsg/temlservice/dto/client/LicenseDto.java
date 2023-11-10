package ru.nabokovsg.temlservice.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Schema(description = "Данные лицензии")
public class LicenseDto {

    @Schema(description = "Индентификатор лицензии")
    @NotNull(message = "id should not be blank")
    @Positive(message = "license id can only be positive")
    private Long id;
    @Schema(description = "Вид документа")
    @NotBlank(message = "document should not be blank")
    private String documentType;
    @Schema(description = "Номер свидетельства")
    @NotBlank(message = "license number should not be blank")
    private String licenseNumber;
    @Schema(description = "Дата выдачи лицензии")
    @NotNull(message = "start date license should not be blank")
    private LocalDate startData;
    @Schema(description = "Дата окончания действия свидетельства")
    @NotNull(message = "end date license should not be blank")
    private LocalDate endData;
}