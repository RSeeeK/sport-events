package ru.devhack.motomoto.sportevents.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class ActivityModel {
    @ApiModelProperty(value = "Тип активности")
    private ActivityType type;
    @ApiModelProperty(value = "Описание")
    private String description;
    @ApiModelProperty(value = "URL изображения")
    private String imageUrl;

    public enum ActivityType {
        RUNNING("Бег"),
        WALKING("Ходьба"),
        BICYCLING("Велосипед");

        private final String description;

        ActivityType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
