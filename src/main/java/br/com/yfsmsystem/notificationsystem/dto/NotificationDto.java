package br.com.yfsmsystem.notificationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
public class NotificationDto {

    @NotEmpty(message = "Title not empity")
    private String title;
    @NotEmpty(message = "Description not empity")
    private String description;
}
