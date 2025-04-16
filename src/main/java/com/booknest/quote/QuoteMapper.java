package com.booknest.quote;

public final class QuoteMapper {

    private QuoteMapper() {
    }

    public static QuoteResponse toResponse(Quote quote) {
        return new QuoteResponse(
                quote.getId(),
                quote.getUserId(),
                quote.getBookId(),
                quote.getPage(),
                quote.getText(),
                quote.getComment(),
                quote.getTags(),
                quote.getCreatedAt()
        );
    }
}
