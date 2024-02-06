package br.com.app.space.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpacesDto {

    private String name;
    private Integer idUser;
    private List<String> applications;
}
