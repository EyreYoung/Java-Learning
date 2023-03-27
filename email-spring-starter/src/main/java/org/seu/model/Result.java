package org.seu.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private boolean success = true;

    private int code = 200;

    private String message = "";

}
