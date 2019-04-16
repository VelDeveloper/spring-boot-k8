package com.sample.kubernetes.model;

import com.sample.kubernetes.custom.ValidateUserInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserInfo {
    @ValidateUserInfo
    private List<Map<String, Object>> informationChunks;
}
