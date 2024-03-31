package com.sreview.sharedReview.domain.util;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class MarkdownUtil {

    public String markdownParser(String markdown) {
        Parser parser = Parser.builder().build();
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();

        // Markdown을 HTML로 변환
        Node document = parser.parse(markdown);
        String htmlContent = htmlRenderer.render(document);
        return htmlContent;
    }
}
