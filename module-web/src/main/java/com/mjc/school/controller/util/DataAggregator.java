package com.mjc.school.controller.util;

import java.util.List;

public record DataAggregator(List<Long> tagsIds,
                             String tagName,
                             String authorName,
                             String title,
                             String content) {

}
