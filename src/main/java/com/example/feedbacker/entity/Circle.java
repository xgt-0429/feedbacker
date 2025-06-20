package com.example.feedbacker.entity;

import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Circle {
    @NotNull(message = "不能为null")
    private Long id;

    @Size(max = 100,message = "最大长度要小于 100")
    @NotBlank(message = "不能为空")
    private String name;

    @NotNull(message = "不能为null")
    private Long ownerId;

    private Date createdAt;

    private String description;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", description=").append(description);
        sb.append("]");
        return sb.toString();
    }
}