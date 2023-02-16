package com.example.mostvaluableplayer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Player {
    private String name;
    private String nickname;
    private int rating;
}
