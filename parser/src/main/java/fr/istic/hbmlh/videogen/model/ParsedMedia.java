package fr.istic.hbmlh.videogen.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParsedMedia {

    private String fileName;
    private boolean active;
    private MediaType type;
    private int previousAlternatives;
    private int currentAlternatives;
    private int index;
}
