package com.zhi.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @authot :charlesing
 */
public class Payment implements Serializable {
    private  int id;
    private String serial;
}
