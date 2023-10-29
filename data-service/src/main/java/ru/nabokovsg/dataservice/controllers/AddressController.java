package ru.nabokovsg.dataservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.dataservice.dto.address.AddressDto;
import ru.nabokovsg.dataservice.dto.address.NewAddressDto;
import ru.nabokovsg.dataservice.dto.address.UpdateAddressDto;
import ru.nabokovsg.dataservice.services.AddressService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(
        value = "/data/address",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Адрес",
     description="API для работы с данными адреса")
public class AddressController {

    private final AddressService service;

    @Operation(summary = "Добавление нового адреса")
    @PostMapping
    public ResponseEntity<AddressDto> save(
                                   @RequestBody @Valid @Parameter(description = "Адрес") NewAddressDto addressDto) {
        return ResponseEntity.ok().body(service.save(addressDto));
    }

    @Operation(summary = "Изменение данных адреса")
    @PatchMapping
    public ResponseEntity<AddressDto> update(
                                @RequestBody @Valid @Parameter(description = "Адрес") UpdateAddressDto addressDto) {
        return ResponseEntity.ok().body(service.update(addressDto));
    }

    @Operation(summary = "Получение всех адресов")
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll(
                          @RequestParam(required = false)
                          @Positive @Parameter(description = "Индентификатор населенного пункта") String city,
                          @RequestParam(name = "from", defaultValue = "0") @PositiveOrZero int from,
                          @RequestParam(name = "size", defaultValue = "10") @Positive  int size) {
        return ResponseEntity.ok().body(service.getAll(city, from, size));
    }

    @Operation(summary = "Удаление адреса")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable @NotNull @Positive @Parameter(description = "Индентификатор адреса") Long id) {
        return ResponseEntity.ok(service.delete(id) + " - удален.");
    }
}
