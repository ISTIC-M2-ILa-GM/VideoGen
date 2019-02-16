package fr.istic.idm.videogen.model;

import lombok.Data;

@Data
public class ParsedMedia {

    private String fileName;
    private boolean active;
    private MediaType type;
    private int totalAlternative;
}
