package com.recommender.user_service.DTO;

import com.recommender.user_service.model.ESize;
import com.recommender.user_service.model.EStyle;

import java.util.List;

public class UserPatchDTO {
    private String name;
    private List<EStyle> styles;
    private List<ESize> sizes;
}
