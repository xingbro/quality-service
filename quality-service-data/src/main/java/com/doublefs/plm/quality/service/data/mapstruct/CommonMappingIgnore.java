package com.doublefs.plm.quality.service.data.mapstruct;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mappings(value = {
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "createdUserId", ignore = true),
        @Mapping(target = "createdUserName", ignore = true),
        @Mapping(target = "updatedUserId", ignore = true),
        @Mapping(target = "updatedUserName", ignore = true)
})
public @interface CommonMappingIgnore {
}