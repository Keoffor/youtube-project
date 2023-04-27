package com.kentechies.youtube.youtubeclone.dto;

import com.kentechies.youtube.youtubeclone.model.Comment;
import com.kentechies.youtube.youtubeclone.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {
    private String id;
    private String title;
    private String description;
    private Set<String> tags;
    private String videourl;
    private VideoStatus videoStatus;
    private Integer viewCount;
    private String thumbnailurl;

}
