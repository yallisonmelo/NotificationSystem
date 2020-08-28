package br.com.yfsmsystem.notificationsystem.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;


@Getter
@Builder
public class NotificationInputDto {

    @NotEmpty(message = "Title not empty")
    private String title;
    @NotEmpty(message = "Description not empty")
    private String description;
}
