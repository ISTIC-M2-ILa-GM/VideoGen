package fr.istic.idm.videogen.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PreCalculationValues {
    private int optionalsSize;
    private int alternativesSize;
    private int optionalVariants;
    private int variants;
}