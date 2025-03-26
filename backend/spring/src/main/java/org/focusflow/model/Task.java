package org.focusflow.model;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;


@Data
public class Task {

private int id;
private String title;
private String short_description;
private String long_description;
private Date due_date;
//...

}
