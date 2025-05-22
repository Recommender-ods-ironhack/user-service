package com.recommender.user_service.DTO;

import com.recommender.user_service.model.ESize;
import com.recommender.user_service.model.EStyle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPatchDTO {
    private String name;
    private List<EStyle> styles;
    private List<ESize> sizes;
}
