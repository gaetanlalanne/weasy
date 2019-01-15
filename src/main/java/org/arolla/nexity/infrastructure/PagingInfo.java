package org.arolla.nexity.infrastructure;

public class PagingInfo {
    /**
     * Starting index
     */
    private int offset;

    /**
     * Number of results to fetch from the starting index
     */
    private int numberOfResults;

    public int getOffset() {
        return offset;
    }

    public int getNumberOfResults() {
        return numberOfResults;
    }

    public PagingInfo(int offset, int numberOfResults) {
        this.offset = offset;
        this.numberOfResults = numberOfResults;
    }
}
