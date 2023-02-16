package com.example.mostvaluableplayer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlayerStats {

    private String name;
    private String nickname;
    private int number;
    private String teamName;
    private int rating;

}
