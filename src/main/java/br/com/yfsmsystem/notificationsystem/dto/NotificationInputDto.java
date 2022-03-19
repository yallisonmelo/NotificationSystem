package br.com.yfsmsystem.notificationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationInputDto {

    @NotEmpty(message = "Title not empty")
    private String title;
    @NotEmpty(message = "Description not empty")
    private String description;
}
