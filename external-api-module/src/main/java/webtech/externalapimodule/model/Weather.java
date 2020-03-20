package webtech.externalapimodule.model;

import lombok.Data;

//@MappedSuperclass
@Data
public class Weather {

   // @Column
    protected float longitude;

   //@Column
    protected float latitude;
}

